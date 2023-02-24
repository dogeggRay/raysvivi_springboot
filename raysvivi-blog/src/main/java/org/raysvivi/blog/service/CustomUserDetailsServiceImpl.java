package org.raysvivi.blog.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import lombok.extern.slf4j.Slf4j;
import org.raysvivi.blog.dao.UserInfoMapper;
import org.raysvivi.blog.model.user.UserSecurityInfo;
import org.spider.model.user.UserInfo;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

@Slf4j
@Component("CustomUserDetailsService")
public class CustomUserDetailsServiceImpl implements UserDetailsService {

    private UserInfoMapper userInfoMapper;

    public CustomUserDetailsServiceImpl(UserInfoMapper userInfoMapper){
        this.userInfoMapper = userInfoMapper;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserInfo user = userInfoMapper.selectOne(new QueryWrapper<UserInfo>().eq("name", username));
        if (user == null) {
            log.warn("用户名：{}，不存在", username);
            return null;
        }
        if (0==user.getDeleteFlag()) {
            log.warn("用户名：{}，已停用", username);
            return null;
        }
        return UserSecurityInfo.builder()
                .id(user.getId())
                .name(username)
                .pwd(user.getPwd())
                .state(true)
                .build();
    }
}
