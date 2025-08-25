package com.demo.userauth.user.service;

import com.demo.userauth.common.constants.CommonConstants;
import com.demo.userauth.common.exception.ApplicationException;
import com.demo.userauth.common.exception.ErrorCodeMessages;
import com.demo.userauth.user.constant.UserConstants;
import com.demo.userauth.user.dto.ApiBaseResponse;
import com.demo.userauth.user.dto.UserDto;
import com.demo.userauth.user.dto.UserDetailResponse;
import com.demo.userauth.user.entity.UmUsrInf;
import com.demo.userauth.common.enums.ResponseStatus;
import com.demo.userauth.user.repository.UserRepository;
import com.demo.userauth.common.utils.CommonUtils;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * User Service
 */
@Service
@Transactional
public class UserService {

    @Autowired
    UserRepository userRepository;

    /**
     * Create a user
     *
     * @param userDto
     * @return
     * @throws ApplicationException
     */
    public ApiBaseResponse createUser(UserDto userDto) throws ApplicationException {
        validateUser(userDto);
        UmUsrInf user = mapAndSaveUserDtoToEntity(userDto);
        return mapApiResponse(user);
    }

    /**
     * Validate User
     * yet to handle min, max, regex errors
     *
     * @param userDto
     */
    private void validateUser(UserDto userDto) throws ApplicationException {
        if (!CommonUtils.isValidObject(userDto)) {
            throw new ApplicationException(ErrorCodeMessages.INVALID_REQUEST.getCode(),
                    ErrorCodeMessages.INVALID_REQUEST.getMessage());
        }
        if (CommonUtils.isStringBlank(userDto.getUmEmlId())) {
            throw new ApplicationException(ErrorCodeMessages.EMAIL_MANDATORY_ERROR.getCode(),
                    ErrorCodeMessages.EMAIL_MANDATORY_ERROR.getMessage());
        }
        if (CommonUtils.isStringBlank(userDto.getUmUsrPwd())) {
            throw new ApplicationException(ErrorCodeMessages.PASSWORD_MANDATORY_ERROR.getCode(),
                    ErrorCodeMessages.PASSWORD_MANDATORY_ERROR.getMessage());
        }
        if (CommonUtils.isStringBlank(userDto.getUmAlsNme())) {
            throw new ApplicationException(ErrorCodeMessages.ALIASNAME_MANDATORY_ERROR.getCode(),
                    ErrorCodeMessages.ALIASNAME_MANDATORY_ERROR.getMessage());
        }
        if (CommonUtils.isStringBlank(userDto.getUmFstNme())) {
            throw new ApplicationException(ErrorCodeMessages.FIRSTNAME_MANDATORY_ERROR.getCode(),
                    ErrorCodeMessages.FIRSTNAME_MANDATORY_ERROR.getMessage());
        }
        if (CommonUtils.isStringBlank(userDto.getUmLstNme())) {
            throw new ApplicationException(ErrorCodeMessages.LASTNAME_MANDATORY_ERROR.getCode(),
                    ErrorCodeMessages.LASTNAME_MANDATORY_ERROR.getMessage());
        }
        UmUsrInf emailIdAlreadyExistUser = userRepository.findByUmEmlIdAndIsDltStsFalse(userDto.getUmEmlId());
        if (CommonUtils.isValidObject(emailIdAlreadyExistUser)) {
            throw new ApplicationException(ErrorCodeMessages.USER_ALREADY_EXIST_ERROR.getCode(),
                    ErrorCodeMessages.USER_ALREADY_EXIST_ERROR.getMessage());
        }
    }

    /**
     * Map user dto to entity and save
     *
     * @param userDto
     * @return
     */
    private UmUsrInf mapAndSaveUserDtoToEntity(UserDto userDto) {
        UmUsrInf user = new UmUsrInf();
        user.setUmEmlId(userDto.getUmEmlId());
        String encodedPassword = new BCryptPasswordEncoder().encode(userDto.getUmUsrPwd());
        user.setUmUsrPwd(encodedPassword);
        user.setUmAlsNme(userDto.getUmAlsNme());
        user.setUmFstNme(userDto.getUmFstNme());
        user.setUmLstNme(userDto.getUmLstNme());
        user.setUmUsrSts(CommonConstants.TRUE_BOOLEAN);
        user.setDltSts(CommonConstants.FALSE_BOOLEAN);
        user.setIdentity(CommonUtils.getUniqueIdentity());
        user.setCrtUsrId(UserConstants.DEFAULT_USER_ID);
        user.setCrtDteTme(new Date());
        return userRepository.save(user);
    }

    /**
     * Map base api fields in response
     *
     * @param user
     * @return
     */
    private ApiBaseResponse mapApiResponse(UmUsrInf user) {
        ApiBaseResponse apiBaseResponse = new ApiBaseResponse();
        apiBaseResponse.setResponseCode(CommonConstants.SUCCESS_RESPONSE_CODE);
        apiBaseResponse.setResponseStatus(ResponseStatus.SUCCESS.getStatus());
        apiBaseResponse.setResponseMessage(UserConstants.USER_CREATION_SUCCESS_MSG);
        apiBaseResponse.setUserIdentity(user.getIdentity());
        return  apiBaseResponse;
    }

    /**
     * Get user details by identity
     *
     * @param userIdentity
     * @return
     */
    public UserDetailResponse getUser(String userIdentity) throws ApplicationException {
        if (CommonUtils.isStringBlank(userIdentity)) {
            throw new ApplicationException(ErrorCodeMessages.USER_IDNTY_MANDATORY_ERROR.getCode(),
                    ErrorCodeMessages.USER_IDNTY_MANDATORY_ERROR.getMessage());
        }
        UmUsrInf user = userRepository.findByIdentityAndIsDltStsFalse(userIdentity);
        if (!CommonUtils.isValidObject(user)) {
            throw new ApplicationException(ErrorCodeMessages.USER_NOT_FOUND.getCode(),
                    ErrorCodeMessages.USER_NOT_FOUND.getMessage());
        }
        return mapGetUserResponse(user.getIdentity(), mapUserDto(user));
    }

    /**
     * Map user entity to dto
     *
     * @param user
     * @return
     */
    public UserDto mapUserDto(UmUsrInf user) {
        UserDto userDto = new UserDto();
        if (CommonUtils.isValidObject(user)) {
            userDto.setUmEmlId(user.getUmEmlId());
            userDto.setUmAlsNme(user.getUmAlsNme());
            userDto.setUmFstNme(user.getUmFstNme());
            userDto.setUmLstNme(user.getUmLstNme());
            userDto.setUmUsrSts(user.isUmUsrSts());
        }
        return userDto;
    }

    /**
     * Map user response
     *
     * @param userIdentity
     * @param userDto
     * @return
     */
    private static UserDetailResponse mapGetUserResponse(String userIdentity, UserDto userDto) {
        UserDetailResponse userResponse = new UserDetailResponse();
        userResponse.setResponseCode(CommonConstants.SUCCESS_RESPONSE_CODE);
        userResponse.setResponseStatus(ResponseStatus.SUCCESS.getStatus());
        userResponse.setResponseMessage(UserConstants.USER_RETRIVED_SUCCESS_MSG);
        userResponse.setUserIdentity(userIdentity);
        userResponse.setData(userDto);
        return userResponse;
    }

    /**
     * get user detail by email
     *
     * @param email
     * @return
     * @throws ApplicationException
     */
    public Object getUserByEmail(String email) throws ApplicationException {
        if (CommonUtils.isStringBlank(email)) {
            throw new ApplicationException(ErrorCodeMessages.EMAIL_MANDATORY_ERROR.getCode(),
                    ErrorCodeMessages.EMAIL_MANDATORY_ERROR.getMessage());
        }
        UmUsrInf user = userRepository.findByUmEmlIdAndIsDltStsFalse(email);
        if (CommonUtils.isValidObject(user)) {
            return mapGetUserResponse(user.getIdentity(), mapUserDto(user));
        }
        throw new ApplicationException(ErrorCodeMessages.USER_NOT_FOUND.getCode(),
                ErrorCodeMessages.USER_NOT_FOUND.getMessage());
    }
}
