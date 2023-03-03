package org.spider.common.util;

import java.util.regex.Pattern;
import javax.servlet.http.HttpServletRequest;

public class HttpUtil {

    public static String getClientIp(HttpServletRequest request) {
        String ip = request.getHeader("x-forwarded-for");

        try {
            String unknownStr = "unknown";
            if (ip != null && ip.length() != 0 && !unknownStr.equalsIgnoreCase(ip)) {
                // 多次反向代理后会有多个ip值，第一个ip才是真实ip
                if (ip.contains(",")) {
                    ip = ip.split(",")[0];
                }
            }
            if (ip == null || ip.length() == 0 || unknownStr.equalsIgnoreCase(ip)) {
                ip = request.getHeader("Proxy-Client-IP");
            }
            if (ip == null || ip.length() == 0 || unknownStr.equalsIgnoreCase(ip)) {
                ip = request.getHeader("WL-Proxy-Client-IP");
            }
            if (ip == null || ip.length() == 0 || unknownStr.equalsIgnoreCase(ip)) {
                ip = request.getHeader("HTTP_CLIENT_IP");
            }
            if (ip == null || ip.length() == 0 || unknownStr.equalsIgnoreCase(ip)) {
                ip = request.getHeader("HTTP_X_FORWARDED_FOR");
            }
            if (ip == null || ip.length() == 0 || unknownStr.equalsIgnoreCase(ip)) {
                ip = request.getHeader("X-Real-IP");
            }
            if (ip == null || ip.length() == 0 || unknownStr.equalsIgnoreCase(ip)) {
                ip = request.getRemoteAddr();
            }

            String regex = "^((2[0-4]\\d|25[0-5]|[01]?\\d\\d?)\\.){3}(2[0-4]\\d|25[0-5]|[01]?\\d\\d?)$";
            if (!Pattern.matches(regex, ip)) {
                ip = "127.0.0.1";
            }
        } catch (Exception ignored) {
        }

        return ip;
    }
}
