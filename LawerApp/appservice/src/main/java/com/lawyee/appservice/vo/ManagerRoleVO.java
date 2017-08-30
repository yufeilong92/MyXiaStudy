package com.lawyee.appservice.vo;

import net.lawyee.mobilelib.vo.BaseVO;

/**
 * All rights Reserved, Designed By www.lawyee.com
 *
 * @version V1.0.xxxxxxxx
 * @Title: 服务管理人员角色VO
 * @Package com.lawyee.apppublic.vo
 * @Description:
 * @author:wuzhu
 * @date: 2017/5/17
 * @verdescript 版本号 修改时间  修改人 修改的概要说明
 * @Copyright: 2017 www.lawyee.com Inc. All rights reserved.
 * 注意：本内容仅限于北京法意科技有限公司内部传阅，禁止外泄以及用于其他的商业目
 */
public class ManagerRoleVO extends BaseVO {
    /**
     * 角色id（律师=0、调解员=1、鉴定机构工作人员=2、法援援助工作人员=3、公证处工作人员=4）
     */
    private String role;

    /**
     * 角色名称（包括律师、调解员、鉴定机构工作人员、法援援助工作人员、公证处工作人员）
     */
    private String roleName;

    /**
     * 角色所在机构id（律师为律所）
     */
    private String orgId;

    /**
     * 角色所在机构名称（律师为律所）
     */
    private String orgName;
}
