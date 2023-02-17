package org.spider.common.cache;

import org.apache.commons.lang3.StringUtils;
import org.spider.model.user.UserInfo;

public class CommonSession {

    private static final ThreadLocal<UserInfo> userInfoCache = new ThreadLocal<>();
    private static final ThreadLocal<String> tokenCache = new ThreadLocal<>();

    public static void clearCache() {
        userInfoCache.remove();
        tokenCache.remove();
    }

    /**
     * NlaSession 初始化（当前线程）
     *
     * @param name  用户名
     * @param token token
     */
    public static void init(String name, String token) {
        if (StringUtils.isEmpty(name) || StringUtils.isEmpty(token)) {
            return;
        }
        tokenCache.set(token);
        UserInfo uinfo = new UserInfo();
        uinfo.setName(name);
//        AutUserInfo userInfo = DataCache.AutUserInfo.getAll(actId).stream().
//                filter(d -> name.equals(d.getName()) && actId.equals(d.getActId()))
//                .findAny().orElse(null);
        userInfoCache.set(uinfo);
    }
}
