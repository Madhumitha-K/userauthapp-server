package com.demo.userauth.user.dto;

import lombok.Getter;
import lombok.Setter;

/**
 * User Detail Response
 */
@Getter
@Setter
public class UserDetailResponse extends ApiBaseResponse {

    private UserDto data;

}
