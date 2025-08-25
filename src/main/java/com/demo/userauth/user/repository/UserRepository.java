package com.demo.userauth.user.repository;

import com.demo.userauth.user.entity.UmUsrInf;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * User Repository
 */
@Repository
public interface UserRepository extends JpaRepository<UmUsrInf, Integer> {

    UmUsrInf findByIdentityAndIsDltStsFalse(String userIdentity);

    UmUsrInf findByUmEmlIdAndIsDltStsFalse(String umEmlId);

}
