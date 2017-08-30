package com.lawyee.appservice.vo;

/**
 * All rights Reserved, Designed By www.lawyee.com
 *
 * @version V1.0.xxxxxxxx
 * @Title: 调解申请详情VO
 * @Package com.lawyee.apppublic.vo
 * @Description:
 * @author:wuzhu
 * @date: 2017/5/16
 * @verdescript 版本号 修改时间  修改人 修改的概要说明
 * @Copyright: 2017 www.lawyee.com Inc. All rights reserved.
 * 注意：本内容仅限于北京法意科技有限公司内部传阅，禁止外泄以及用于其他的商业目
 */
public class JamedApplyDetailVO extends JamedApplyVO {
    private static final long serialVersionUID = 2628576853391649534L;
    /**
     * 	调解机构id
     */
    private String tjOrgId;
    /**
     * 	调解机构名称
     */
    private String tjOrgName;
    /**
     * 申请人性别
     */
    private String applyGender;
    /**
     *民族
     */
    private String applyNation;
    /**
     *年龄
     */
    private String applyAge;
    /**
     * 申请人联系方式
     */
    private String applyTelephone;
    /**
     * 申请人职务
     */
    private String applyJob;
    /**
     * 申请人住址
     */
    private String applyAddress;
    /**
     * 证件类型
     */
    private String applyCardType;
    /**
     * 出生日期
     */
    private String applyBrithday;
    /**
     * 证件号码
     */
    private String applyIdCard;
    /**
     * 证件类型
     */
    private String beApplyCardType;
    /**
     * 出生日期
     */
    private String beApplyBrithday;
    /**
     * 证件号码
     */
    private String beApplyIdCard;
    /**
     *与被申请人关系
     */
    private String relation;
    /**
     *被申请人姓名
     */
    private String beApplyName;
    /**
     *被申请人性别
     */
    private String beApplyGender;
    /**
     *被申请人民族
     */
    private String beApplyNation;
    /**
     *被申请人年龄
     */
    private String  beApplyAge;
    /**
     * 被申请人联系方式
     */
    private String beApplyTelephone;
    /**
     * 被申请人住址
     */
    private String beApplyAddress;
    /**
     * 纠纷案情简介
     */
    private String introduction;
    /**
     * 当事人申请事项
     */
    private String matter;
	/**
	 *媒体审批意见
	 */
	private String mediaOpinion;
	/**
	 *媒体审批时间
	 */
	private String mediaApplyTime;
	/**
	 *节目预告通知
	 */
	private String mediaTVGuide;
	/**
	 *节目链接地址
	 */
	private String mediaPlayUrl;
	/**
	 *节目状态:0：录制中、1未播、2已播
	 */
	private int mediaPlayStatus;
    /**
     * 调解机构审核意见
     */
    private String orgAcceptOpinion;
    /**
     * 调解机构审批时间
     */
    private String orgAcceptTime;
    /**
     * 不受理理由
     */
    private String noAccpectReason;
    /**
     * 调解结束时间 yyyy-MM-dd HH:mm:ss
     */
    private String endTime;
    /**
     * 是否司法确认, 1：是：-1：否 0：待确认
     */
    private String judconfirmFlag;

    public String getBeApplyCardType() {
        return beApplyCardType;
    }

    public void setBeApplyCardType(String beApplyCardType) {
        this.beApplyCardType = beApplyCardType;
    }

    public String getBeApplyBrithday() {
        return beApplyBrithday;
    }

    public void setBeApplyBrithday(String beApplyBrithday) {
        this.beApplyBrithday = beApplyBrithday;
    }

    public String getBeApplyIdCard() {
        return beApplyIdCard;
    }

    public void setBeApplyIdCard(String beApplyIdCard) {
        this.beApplyIdCard = beApplyIdCard;
    }

    public String getApplyBrithday() {
        return applyBrithday;
    }

    public void setApplyBrithday(String applyBrithday) {
        this.applyBrithday = applyBrithday;
    }

    public String getApplyCardType() {
        return applyCardType;
    }

    public void setApplyCardType(String applyCardType) {
        this.applyCardType = applyCardType;
    }

    public String getTjOrgId() {
        return tjOrgId;
    }

    public void setTjOrgId(String tjOrgId) {
        this.tjOrgId = tjOrgId;
    }

    public String getTjOrgName() {
        return tjOrgName;
    }

    public void setTjOrgName(String tjOrgName) {
        this.tjOrgName = tjOrgName;
    }

    public String getApplyGender() {
        return applyGender;
    }

    public void setApplyGender(String applyGender) {
        this.applyGender = applyGender;
    }

    public String getApplyNation() {
        return applyNation;
    }

    public void setApplyNation(String applyNation) {
        this.applyNation = applyNation;
    }

    public String getApplyTelephone() {
        return applyTelephone;
    }

    public void setApplyTelephone(String applyTelephone) {
        this.applyTelephone = applyTelephone;
    }

    public String getApplyJob() {
        return applyJob;
    }

    public void setApplyJob(String applyJob) {
        this.applyJob = applyJob;
    }

    public String getApplyAddress() {
        return applyAddress;
    }

    public void setApplyAddress(String applyAddress) {
        this.applyAddress = applyAddress;
    }

    public String getApplyIdCard() {
        return applyIdCard;
    }

    public void setApplyIdCard(String applyIdCard) {
        this.applyIdCard = applyIdCard;
    }

    public String getRelation() {
        return relation;
    }

    public void setRelation(String relation) {
        this.relation = relation;
    }

    public String getBeApplyName() {
        return beApplyName;
    }

    public void setBeApplyName(String beApplyName) {
        this.beApplyName = beApplyName;
    }

    public String getBeApplyGender() {
        return beApplyGender;
    }

    public void setBeApplyGender(String beApplyGender) {
        this.beApplyGender = beApplyGender;
    }

    public String getBeApplyNation() {
        return beApplyNation;
    }

    public void setBeApplyNation(String beApplyNation) {
        this.beApplyNation = beApplyNation;
    }

    public String getBeApplyTelephone() {
        return beApplyTelephone;
    }

    public void setBeApplyTelephone(String beApplyTelephone) {
        this.beApplyTelephone = beApplyTelephone;
    }

    public String getBeApplyAddress() {
        return beApplyAddress;
    }

    public void setBeApplyAddress(String beApplyAddress) {
        this.beApplyAddress = beApplyAddress;
    }

    public String getIntroduction() {
        return introduction;
    }

    public void setIntroduction(String introduction) {
        this.introduction = introduction;
    }

    public String getMatter() {
        return matter;
    }

    public void setMatter(String matter) {
        this.matter = matter;
    }

    public String getMediaOpinion() {
        return mediaOpinion;
    }

    public void setMediaOpinion(String mediaOpinion) {
        this.mediaOpinion = mediaOpinion;
    }

    public String getMediaApplyTime() {
        return mediaApplyTime;
    }

    public void setMediaApplyTime(String mediaApplyTime) {
        this.mediaApplyTime = mediaApplyTime;
    }

    public String getMediaTVGuide() {
        return mediaTVGuide;
    }

    public void setMediaTVGuide(String mediaTVGuide) {
        this.mediaTVGuide = mediaTVGuide;
    }

    public String getMediaPlayUrl() {
        return mediaPlayUrl;
    }

    public void setMediaPlayUrl(String mediaPlayUrl) {
        this.mediaPlayUrl = mediaPlayUrl;
    }

    public int getMediaPlayStatus() {
        return mediaPlayStatus;
    }

    public void setMediaPlayStatus(int mediaPlayStatus) {
        this.mediaPlayStatus = mediaPlayStatus;
    }

    public String getOrgAcceptOpinion() {
        return orgAcceptOpinion;
    }

    public void setOrgAcceptOpinion(String orgAcceptOpinion) {
        this.orgAcceptOpinion = orgAcceptOpinion;
    }

    public String getNoAccpectReason() {
        return noAccpectReason;
    }

    public void setNoAccpectReason(String noAccpectReason) {
        this.noAccpectReason = noAccpectReason;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public String getJudconfirmFlag() {
        return judconfirmFlag;
    }

    public void setJudconfirmFlag(String judconfirmFlag) {
        this.judconfirmFlag = judconfirmFlag;
    }

    public String getOrgAcceptTime() {
        return orgAcceptTime;
    }

    public void setOrgAcceptTime(String orgAcceptTime) {
        this.orgAcceptTime = orgAcceptTime;
    }

    public String getApplyAge() {
        return applyAge;
    }

    public void setApplyAge(String applyAge) {
        this.applyAge = applyAge;
    }

    public String getBeApplyAge() {
        return beApplyAge;
    }

    public void setBeApplyAge(String beApplyAge) {
        this.beApplyAge = beApplyAge;
    }
}
