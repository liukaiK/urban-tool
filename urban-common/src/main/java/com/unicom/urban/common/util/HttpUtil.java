package com.unicom.urban.common.util;

import cn.hutool.extra.servlet.ServletUtil;
import eu.bitwalker.useragentutils.UserAgent;

import javax.servlet.http.HttpServletRequest;

public class HttpUtil {

    private HttpUtil() {
    }

    public static boolean isAjaxRequest(HttpServletRequest request) {
        String accept = request.getHeader("accept");
        if (accept != null && accept.contains("application/json")) {
            return true;
        }

        String xRequestedWith = request.getHeader("X-Requested-With");
        if (xRequestedWith != null && xRequestedWith.contains("XMLHttpRequest")) {
            return true;
        }

        String uri = request.getRequestURI();
        if (inStringIgnoreCase(uri, ".json", ".xml")) {
            return true;
        }

        String ajax = request.getParameter("__ajax");
        return inStringIgnoreCase(ajax, "json", "xml");
    }

    /**
     * 获取操作系统名称
     */
    public static String getOperatingSystem(HttpServletRequest request) {
        UserAgent userAgent = getUserAgent(request);
        return userAgent.getOperatingSystem().getName();
    }

    /**
     * 获取浏览器
     */
    public static String getBrowser(HttpServletRequest request) {
        UserAgent userAgent = getUserAgent(request);
        return userAgent.getBrowser().getName();
    }

    private static UserAgent getUserAgent(HttpServletRequest request) {
        return UserAgent.parseUserAgentString(request.getHeader("User-Agent"));
    }


    public static String getIp(HttpServletRequest request) {
        String ip = ServletUtil.getClientIP(request);
        if ("0:0:0:0:0:0:0:1".equals(ip)) {
            return "127.0.0.1";
        }
        return ip;
    }

    /**
     * 是否包含字符串
     *
     * @param str  验证字符串
     * @param strs 字符串组
     * @return 包含返回true
     */
    private static boolean inStringIgnoreCase(String str, String... strs) {
        if (str != null && strs != null) {
            for (String s : strs) {
                if (str.equalsIgnoreCase(trim(s))) {
                    return true;
                }
            }
        }
        return false;
    }

    private static String trim(String str) {
        return (str == null ? "" : str.trim());
    }
}
