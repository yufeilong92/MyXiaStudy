package com.lawyee.appservice.vo;

import android.content.Context;

import com.lawyee.appservice.config.Constants;

import net.lawyee.mobilelib.utils.SecurityUtil;
import net.lawyee.mobilelib.utils.StringUtil;
import net.lawyee.mobilelib.vo.BaseVO;

import static com.lawyee.appservice.config.Constants.CINT_PASSWORD_LEN_MIN;


/**
 * 公众用户VO
 */
public class PublicUserVO extends BaseVO {

    private static final long serialVersionUID = -7755982071141601864L;
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
    /*
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
     * 是否记住密码
     */
    private boolean isRememblePwd;

    public boolean isRememblePwd() {
        return isRememblePwd;
    }

    public void setRememblePwd(boolean rememblePwd) {
        isRememblePwd = rememblePwd;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getLoginId() {
        return loginId;
    }

    public void setLoginId(String loginId) {
        this.loginId = loginId;
    }

    public String getPassword() {
        return SecurityUtil.Decrypt(password,SecurityUtil.getLegalKey(String.valueOf(serialVersionUID)), Constants.CSTR_IVS);
    }

    public void setPassword(String password) {
        this.password = SecurityUtil.Encrypt(password,SecurityUtil.getLegalKey(String.valueOf(serialVersionUID)), Constants.CSTR_IVS);;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public String getRealName() {
        return realName;
    }

    public void setRealName(String realName) {
        this.realName = realName;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getOpenfireLoginId() {
        return openfireLoginId;
    }

    public void setOpenfireLoginId(String openfireLoginId) {
        this.openfireLoginId = openfireLoginId;
    }

    public String getOpenfirePassword() {
        return openfirePassword;
    }

    public void setOpenfirePassword(String openfirePassword) {
        this.openfirePassword = openfirePassword;
    }

    /**
     * 是否有效的密码
     * @param str
     * @return
     */
    public static boolean isEffPassword(String str)
    {
        if(StringUtil.isEmpty(str)||str.length()<CINT_PASSWORD_LEN_MIN)
            return false;
        return true;
    }

    public static String dataFileName(Context c) {
        return dataFileName(c,serialVersionUID);
    }

}
