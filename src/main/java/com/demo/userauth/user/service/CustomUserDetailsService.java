package com.demo.userauth.user.service;

import com.demo.userauth.common.exception.ErrorCodeMessages;
import com.demo.userauth.common.utils.CommonUtils;
import com.demo.userauth.user.constant.UserConstants;
import com.demo.userauth.user.entity.UmUsrInf;
import com.demo.userauth.user.repository.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.security.core.userdetails.*;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

/**
 * Custom User Details Service
 */
@Service
@Transactional
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UmUsrInf user = userRepository.findByUmEmlIdAndIsDltStsFalse(username);
        if (!CommonUtils.isValidObject(user)) {
            throw new UsernameNotFoundException(ErrorCodeMessages.USER_NOT_FOUND.getMessage());
        }
        if (!user.isUmUsrSts()) {
            throw new InternalAuthenticationServiceException(ErrorCodeMessages.USER_INACTIVE_ERROR.getMessage());
        }
        return new User(user.getUmEmlId(), user.getUmUsrPwd(), new ArrayList<>());
    }
}



