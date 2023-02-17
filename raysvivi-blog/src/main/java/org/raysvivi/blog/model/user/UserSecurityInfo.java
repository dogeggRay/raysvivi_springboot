package org.raysvivi.blog.model.user;

import lombok.Builder;
import lombok.Data;
import lombok.ToString;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Set;

@Builder
@Data
@ToString
public class UserSecurityInfo implements UserDetails {
    /**
     *
     */
    private String id;
    /**
     * 用户名称
     */
    private String name;
    /**
     * 用户密码
     */
    private String pwd;
    /**
     * 状态
     */
    private Boolean state;

    private Set<GrantedAuthority> authorities;

    public String getKey() {
        return this.name;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return this.authorities;
    }

    @Override
    public String getPassword() {
        return this.pwd;
    }

    @Override
    public String getUsername() {
        return this.name;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
