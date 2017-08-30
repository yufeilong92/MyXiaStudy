package com.lawyee.appservice.vo;

import net.lawyee.mobilelib.vo.BaseVO;

/**
 * All rights Reserved, Designed By www.lawyee.com
 *
 * @version V 1.0 xxxxxxxx
 * @Title: LawerApp
 * @Package com.lawyee.appservice.vo
 * @Description: 鉴定申请
 * @author: YFL
 * @date: 2017/7/5 11:24
 * @verdescript 版本号 修改时间  修改人 修改的概要说明
 * @Copyright: 2017 www.lawyee.com Inc. All rights reserved.
 * 注意：本内容仅限于北京法意科技有限公司内部传阅，禁止外泄以及用于其他的商业目
 */
public class JaauthApplyVo extends BaseVO {
    private static final long serialVersionUID = 5384405071329236044L;
    /**
     * 鉴定类型
     */
    private String applyAppraisalType;
    /**
     * 鉴定单位
     */
    private String applyOrg;
    /**
     * 申请人姓名
     */
    private String applyName;
    /**
     * 申请人性别
     */
    private String applyGender;
    /**
     * 证件类型
     */
    private String applyCardType;
    /**
     * 证件号码
     */
    private String applyIdCard;
    /**
     * 出生日期
     */
    private String applyBrithday;
    /**
     * 申请人行为能力
     */
    private String applyActionAbility ;
    /**
     * 代理人姓名
     */
    private String DaApplyName;
    /**
     * 代理人证件号码
     */
    private String DaApplyIdCard;
    /**
     * 鉴定事项
     */
    private String applyAppraiselItem;
    /**
     * 案情简介
     */
    private String applyCaseSummary;
    /**
     * 鉴定结论信息
     */
    private String applyAppraiselInfom;
    /**
     * 鉴定书编号
     */
    private String applyAppraisalNumber;
    /**
     * 鉴定完成时间
     */
    private String applyAppraisalFinishDate;


    public String getApplyAppraisalType() {
        return applyAppraisalType;
    }

    public void setApplyAppraisalType(String applyAppraisalType) {
        this.applyAppraisalType = applyAppraisalType;
    }

    public String getApplyOrg() {
        return applyOrg;
    }

    public void setApplyOrg(String applyOrg) {
        this.applyOrg = applyOrg;
    }

    public String getApplyName() {
        return applyName;
    }

    public void setApplyName(String applyName) {
        this.applyName = applyName;
    }

    public String getApplyGender() {
        return applyGender;
    }

    public void setApplyGender(String applyGender) {
        this.applyGender = applyGender;
    }

    public String getApplyCardType() {
        return applyCardType;
    }

    public void setApplyCardType(String applyCardType) {
        this.applyCardType = applyCardType;
    }

    public String getApplyIdCard() {
        return applyIdCard;
    }

    public void setApplyIdCard(String applyIdCard) {
        this.applyIdCard = applyIdCard;
    }

    public String getApplyBrithday() {
        return applyBrithday;
    }

    public void setApplyBrithday(String applyBrithday) {
        this.applyBrithday = applyBrithday;
    }

    public String getApplyActionAbility() {
        return applyActionAbility;
    }

    public void setApplyActionAbility(String applyActionAbility) {
        this.applyActionAbility = applyActionAbility;
    }

    public String getDaApplyName() {
        return DaApplyName;
    }

    public void setDaApplyName(String daApplyName) {
        this.DaApplyName = daApplyName;
    }

    public String getDaApplyIdCard() {
        return DaApplyIdCard;
    }

    public void setDaApplyIdCard(String daApplyIdCard) {
        this.DaApplyIdCard = daApplyIdCard;
    }

    public String getApplyAppraiselItem() {
        return applyAppraiselItem;
    }

    public void setApplyAppraiselItem(String applyAppraiselItem) {
        this.applyAppraiselItem = applyAppraiselItem;
    }

    public String getApplyCaseSummary() {
        return applyCaseSummary;
    }

    public void setApplyCaseSummary(String applyCaseSummary) {
        this.applyCaseSummary = applyCaseSummary;
    }

    public String getApplyAppraiselInfom() {
        return applyAppraiselInfom;
    }

    public void setApplyAppraiselInfom(String applyAppraiselInfom) {
        this.applyAppraiselInfom = applyAppraiselInfom;
    }

    public String getApplyAppraisalNumber() {
        return applyAppraisalNumber;
    }

    public void setApplyAppraisalNumber(String applyAppraisalNumber) {
        this.applyAppraisalNumber = applyAppraisalNumber;
    }

    public String getApplyAppraisalFinishDate() {
        return applyAppraisalFinishDate;
    }

    public void setApplyAppraisalFinishDate(String applyAppraisalFinishDate) {
        this.applyAppraisalFinishDate = applyAppraisalFinishDate;
    }

}
