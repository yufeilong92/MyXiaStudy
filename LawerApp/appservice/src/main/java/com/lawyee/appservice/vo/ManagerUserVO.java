package com.lawyee.appservice.vo;

import net.lawyee.mobilelib.vo.BaseVO;

import java.util.List;

/**
 * 服务管理用户VO
 */
public class ManagerUserVO extends BaseVO {

    private static final long serialVersionUID = 1565259443230928814L;
    /**
     * 用户ID
     */
    private String userId;
    /**
     * 登录帐号
     */
    private String loginId;
    /**
     * 登录密码
     */
    private String password;
    /**
     * 昵称
     */
    private String nickName;
    /**
     * 头像URL
     */
    private String photo;
    /**
     *真实姓名
     */
    private String realName;
    /**
     * 性别
     */
    private String sex;

    /**
     * 手机号
     */
    private String mobile;

    /**
     * openfire登入id
     */
    private String openfireLoginId;
    /**
     * openfire登入密码
     */
    private String openfirePassword;

    /**
     * 邮箱
     */
    private String email;


    /** 专业领域 */
    private List<JalawBusinessVO> business;

    /**
     * 用户角色列表
     */
    private List<ManagerRoleVO> roles;
}
