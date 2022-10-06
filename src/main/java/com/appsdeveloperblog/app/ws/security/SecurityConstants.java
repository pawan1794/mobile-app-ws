package com.appsdeveloperblog.app.ws.security;

import com.appsdeveloperblog.app.ws.SpringApplicationContext;

public class SecurityConstants {

    public static final long EXPIRATION_TIME = 7*24*60*60*1000; //864000000; //one week
    public static final String TOKEN_PREFIX = "Bearer ";
    public static final String HEADER_STRING = "Authorization";
    public static final String TOKEN = "token";
    public static final String SIGN_UP_URL = "/users";
    public static final String H2_CONSOLE = "/h2-console/**";
//    public static final String TOKEN_SECRET = "jf9i4jgu83nfl0";

    public static String getTokenSecret() {
        System.out.println("SecurityConstants getTokenSecret");
        AppProperties properties = (AppProperties) SpringApplicationContext.getBean("appProperties");
        return properties.getTokenSecret();
    }


}
