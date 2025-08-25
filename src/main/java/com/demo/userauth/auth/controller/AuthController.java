package com.demo.userauth.auth.controller;

import com.demo.userauth.auth.dto.LoginRequest;
import com.demo.userauth.common.constants.CommonConstants;
import com.demo.userauth.common.dto.ApiErrorResponse;
import com.demo.userauth.common.exception.ErrorCodeMessages;
import com.demo.userauth.common.utils.CommonUtils;
import com.demo.userauth.user.constant.UserConstants;
import com.demo.userauth.user.dto.ApiBaseResponse;
import com.demo.userauth.common.enums.ResponseStatus;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.http.*;
import org.springframework.security.authentication.*;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.context.HttpSessionSecurityContextRepository;
import org.springframework.web.bind.annotation.*;

/**
 * Auth Controller
 */
@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final AuthenticationManager authenticationManager;

    public AuthController(AuthenticationManager authenticationManager) {
        this.authenticationManager = authenticationManager;
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest loginRequest, HttpServletRequest request) {
        try {
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword())
            );

            SecurityContext context = SecurityContextHolder.createEmptyContext();
            context.setAuthentication(authentication);
            SecurityContextHolder.setContext(context);

            HttpSession session = request.getSession(true);
            session.setAttribute(HttpSessionSecurityContextRepository.SPRING_SECURITY_CONTEXT_KEY, context);

            ApiBaseResponse apiBaseResponse = new ApiBaseResponse();
            apiBaseResponse.setResponseCode(CommonConstants.SUCCESS_RESPONSE_CODE);
            apiBaseResponse.setResponseStatus(ResponseStatus.SUCCESS.getStatus());
            apiBaseResponse.setResponseMessage(UserConstants.LOGIN_SUCCESS_MSG);
            return new ResponseEntity<>(apiBaseResponse, HttpStatus.OK);
        } catch (Exception e) {
            ApiErrorResponse apiErrorResponse = new ApiErrorResponse();
            apiErrorResponse.setResponseCode(ErrorCodeMessages.INVALID_CREDENTIAL.getCode());
            apiErrorResponse.setResponseStatus(ResponseStatus.FAILURE.getStatus());
            apiErrorResponse.setResponseMessage(ErrorCodeMessages.INVALID_CREDENTIAL.getMessage());
            return new ResponseEntity<>(apiErrorResponse, HttpStatus.UNAUTHORIZED);
        }
    }

    @PostMapping("/logout")
    public ResponseEntity<?> logout(HttpServletRequest request) {
        HttpSession session = request.getSession(CommonConstants.FALSE_BOOLEAN);
        if (CommonUtils.isValidObject(session)) {
            session.invalidate();
        }
        SecurityContextHolder.clearContext();

        ApiBaseResponse apiBaseResponse = new ApiBaseResponse();
        apiBaseResponse.setResponseCode(CommonConstants.SUCCESS_RESPONSE_CODE);
        apiBaseResponse.setResponseStatus(ResponseStatus.SUCCESS.getStatus());
        apiBaseResponse.setResponseMessage(UserConstants.LOGOUT_SUCCESS_MSG);
        return ResponseEntity.ok(apiBaseResponse);
    }

}

