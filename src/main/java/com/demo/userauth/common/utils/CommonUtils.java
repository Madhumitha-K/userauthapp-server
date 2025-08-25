package com.demo.userauth.common.utils;

import com.demo.userauth.common.constants.CommonConstants;
import org.springframework.stereotype.Component;

import java.util.UUID;

/**
 * Common Utils
 */
@Component
public class CommonUtils {

    /**
     * Check if object is valid
     *
     * @param obj
     * @return
     */
    public static boolean isValidObject(Object obj) {
        if (obj == null) {
            return false;
        }
        return true;
    }

    /**
     * Generate random 36 character string
     *
     * @return
     */
    public static String getUniqueIdentity() {
        return UUID.randomUUID().toString();
    }

    /**
     * Check if string is blank
     *
     * @param input
     * @return
     */
    public static boolean isStringBlank(String input) {
        return input == null || input.trim().isEmpty();
    }

    /**
     * Check if string is not blank
     *
     * @param input
     * @return
     */
    public static boolean isStringNotBlank(String input) {
        return !isStringBlank(input);
    }

}
