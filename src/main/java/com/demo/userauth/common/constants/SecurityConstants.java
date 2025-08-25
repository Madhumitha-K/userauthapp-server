package com.demo.userauth.common.constants;

import java.util.Arrays;
import java.util.List;

/**
 * Security Constants
 */
public class SecurityConstants {

    public static final String[] OPEN_URLS = { "/api/auth/login", "/api/auth/logout" };

    public static final List<String> ALLOWED_METHODS = Arrays.asList("GET", "POST", "PUT", "DELETE", "OPTIONS");

    public static final String CORS_ALL_HEADERS = "*";

    public static final String CORS_ALL_PATHS_MAPPING = "/**";

    public static final String JSESSIONID_COOKIE_NAME = "JSESSIONID";

    public static final String COOKIE_PATH_ROOT = "/";

    public static final String CONFIG_CORS_PREFIX = "config.cors";

}
