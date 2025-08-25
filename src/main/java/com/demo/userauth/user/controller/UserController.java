package com.demo.userauth.user.controller;

import com.demo.userauth.common.exception.ApplicationException;
import com.demo.userauth.user.dto.ApiBaseResponse;
import com.demo.userauth.user.dto.UserDto;
import com.demo.userauth.user.dto.UserDetailResponse;
import com.demo.userauth.user.repository.UserRepository;
import com.demo.userauth.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

/**
 * User Controller
 */
@RestController
@RequestMapping("/api/user")
public class UserController {

    @Autowired
    UserService userService;

    @Autowired
    UserRepository userRepository;

    /**
     * Create a user
     *
     * @param userDto
     * @return
     */
    @PostMapping
    public ResponseEntity<ApiBaseResponse> createUser(@RequestBody UserDto userDto) throws ApplicationException {
        return new ResponseEntity<>(userService.createUser(userDto), HttpStatus.OK);
    }

    /**
     * Get user detail by identity
     *
     * @param userIdentity
     * @return
     */
    @GetMapping("/{userIdentity}")
    public ResponseEntity<UserDetailResponse> getUserDetail(@PathVariable("userIdentity") String userIdentity)
            throws ApplicationException {
        return new ResponseEntity<>(userService.getUser(userIdentity), HttpStatus.OK);
    }

    /**
     * Get logged in user detail
     *
     * @return
     */
    @GetMapping()
    public ResponseEntity<?> getLoggedInUser() throws ApplicationException {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null || !authentication.isAuthenticated() ||
                authentication instanceof AnonymousAuthenticationToken) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
        return new ResponseEntity<>(userService.getUserByEmail(authentication.getName()), HttpStatus.OK);
    }

}
