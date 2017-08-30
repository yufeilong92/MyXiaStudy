package com.lawyee.appservice.vo;

import net.lawyee.mobilelib.vo.BaseVO;

/**
 * All rights Reserved, Designed By www.lawyee.com
 *
 * @version V 1.0 xxxxxxxx
 * @Title: LawerApp
 * @Package com.lawyee.appservice.vo
 * @Description: $todo$
 * @author: YFL
 * @date: 2017/7/5 11:26
 * @verdescript 版本号 修改时间  修改人 修改的概要说明
 * @Copyright: 2017 www.lawyee.com Inc. All rights reserved.
 * 注意：本内容仅限于北京法意科技有限公司内部传阅，禁止外泄以及用于其他的商业目
 */
public class JanotaApplyVo extends BaseVO{
    private static final long serialVersionUID = -1496642630350762722L;
    /**
     * 申请人姓名
     */
    private String applyName;
    /**
     * 证件类型
     */
    private String applyCardType;
    /**
     * 证件号码
     */
    private String applyIdCard;
    /**
     * 联系电话
     */
    private String applyTelephone;
    /**
     * 公证类型
     */
    private String applyNotaType;
    /**
     * 公证事项
     */
    private String applyNotaItem;
    /**
     * 是存在虚假证明，false 不存在，true 存在
     */
    private boolean applyFalsifiedCertification;
    /**
     * 公证书编号
     */
    private String applyNotaNumber;
    /**
     * 结案时间
     */
    private String applyFinishDate ;
    /**
     * 其他说明
     */
    private String applyOhterExplain;


    public String getApplyName() {
        return applyName;
    }

    public void setApplyName(String applyName) {
        this.applyName = applyName;
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

    public String getApplyTelephone() {
        return applyTelephone;
    }

    public void setApplyTelephone(String applyTelephone) {
        this.applyTelephone = applyTelephone;
    }

    public String getApplyNotaType() {
        return applyNotaType;
    }

    public void setApplyNotaType(String applyNotaType) {
        this.applyNotaType = applyNotaType;
    }

    public String getApplyNotaItem() {
        return applyNotaItem;
    }

    public void setApplyNotaItem(String applyNotaItem) {
        this.applyNotaItem = applyNotaItem;
    }

    public boolean getApplyFalsifiedCertification() {
        return applyFalsifiedCertification;
    }

    public void setApplyFalsifiedCertification(boolean applyFalsifiedCertification) {
        this.applyFalsifiedCertification = applyFalsifiedCertification;
    }

    public String getApplyNotaNumber() {
        return applyNotaNumber;
    }

    public void setApplyNotaNumber(String applyNotaNumber) {
        this.applyNotaNumber = applyNotaNumber;
    }

    public String getApplyFinishDate() {
        return applyFinishDate;
    }

    public void setApplyFinishDate(String applyFinishDate) {
        this.applyFinishDate = applyFinishDate;
    }

    public String getApplyOhterExplain() {
        return applyOhterExplain;
    }

    public void setApplyOhterExplain(String applyOhterExplain) {
        this.applyOhterExplain = applyOhterExplain;
    }


}
