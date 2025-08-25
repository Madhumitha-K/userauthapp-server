package com.demo.userauth.user.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.Date;

/**
 * User Entity
 */
@Entity
@Table(name = "um_usr_inf")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UmUsrInf {

    @Id
    @GeneratedValue(strategy =  GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer Id;

    @Column(name = "um_eml_id")
    private String umEmlId;

    @Column(name = "um_usr_pwd")
    private String umUsrPwd;

    @Column(name = "um_als_nme")
    private String umAlsNme;

    @Column(name = "um_fst_nme")
    private String umFstNme;

    @Column(name = "um_lst_nme")
    private String umLstNme;

    @Column(name = "um_usr_sts")
    private boolean umUsrSts;

    @Column(name = "identity")
    private String identity;

    @Column(name = "is_dlt_sts")
    private boolean isDltSts;

    @Column(name = "crt_dte_tme")
    private Date crtDteTme;

    @Column(name = "crt_usr_id")
    private Integer crtUsrId;

    @Column(name = "mdy_dte_tme")
    private Date mdyDteTme;

    @Column(name = "mdy_usr_id")
    private Integer mdyUsrId;

}
