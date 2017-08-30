package com.lawyee.appservice.vo;

import net.lawyee.mobilelib.utils.StringUtil;
import net.lawyee.mobilelib.utils.TimeUtil;

/**
 * All rights Reserved, Designed By www.lawyee.com
 *
 * @version V1.0.xxxxxxxx
 * @Title: 资讯详情VO
 * @Package com.lawyee.apppublic.vo
 * @Description:
 * @author:wuzhu
 * @date: 2017/5/16
 * @verdescript 版本号 修改时间  修改人 修改的概要说明
 * @Copyright: 2017 www.lawyee.com Inc. All rights reserved.
 * 注意：本内容仅限于北京法意科技有限公司内部传阅，禁止外泄以及用于其他的商业目
 */

public class InfomationDetailVO extends InfomationVO {
    private static final long serialVersionUID = -7065955131920402260L;
    /**
     * 发布机构名称
     */
    private String publishOrganizationName;

    /**
     * 资讯内容
     */
    private String content;

    public String getPublishOrganizationName() {
        return publishOrganizationName;
    }

    public void setPublishOrganizationName(String publishOrganizationName) {
        this.publishOrganizationName = publishOrganizationName;
    }

    public String getContent() {
       // return testData()+content+testData2();
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }



    /**
     * 返回详情HTML子标题
     *
     * @return
     */
    @Override
    public String getHtmlSubTitle() {
        String result = "<div align=\"center\" style=\"margin-top: 8px;margin-bottom: 8px;\">" +
                "<font color=\"#666666\" size=\"2pt\">发布机构:</font>" +
                "<font color=\"#999999\" size=\"2pt\">"+((StringUtil.isEmpty(getPublishOrganizationName())) ? ""
                : getPublishOrganizationName())
                +"&nbsp;&nbsp;</font>" +
                "<font color=\"#666666\" size=\"2pt\">发布时间:</font>" +
                "<font color=\"#999999\" size=\"2pt\">"+ TimeUtil.dateToString(TimeUtil.strToDate(getPublishDate(),null),"yyyy-MM-dd")+"</font>" +
                "</div>" +
                "<div style=\"height:0;border-bottom:1px solid #999999;margin-bottom: 8px;margin-right: 8px;margin-left: 8px\"></div>";

       /* String result = "<div align=\"center\"><font color=\"#666666\" size=\"2pt\">"
                 + ((StringUtil.isEmpty(getPublishOrganizationName())) ? ""
                 : ("发布机构:" + getPublishOrganizationName()))
                + "&nbsp;&nbsp; "
                + getPublishDate()
                + "</font></div><div style=\"height:0;border-bottom:1px solid #f00\"></div>";*/
        return result;
    }
/*
    private  String testData()
    {
        return "<p style=\"text-align:center\"><img src=\"http://static.cnbetacdn.com/article/2017/0603/a9d5e70bd22c889.jpg\"></p>";
    }
    private String testData2()
    {
        return "<p><img src=\"http://static.cnbetacdn.com/article/2017/0603/0d5a43e2030bf57.jpg\"></p>";
    }*/
}
