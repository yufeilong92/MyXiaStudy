package com.lawyee.appservice.vo;

import android.content.Context;

/**
 * All rights Reserved, Designed By www.lawyee.com
 *
 * @version V1.0.xxxxxxxx
 * @Title: 数据字典VO
 * @Package com.lawyee.apppublic.vo
 * @Description:
 * @author:wuzhu
 * @date: 2017/5/16
 * @verdescript 版本号 修改时间  修改人 修改的概要说明
 * @Copyright: 2017 www.lawyee.com Inc. All rights reserved.
 * 注意：本内容仅限于北京法意科技有限公司内部传阅，禁止外泄以及用于其他的商业目
 */
public class DataDictionaryVO extends BaseCommonDataVO {
    private static final long serialVersionUID = 8821342949657359709L;

    public static String dataListFileName(Context c) {
        return dataListFileName(c,serialVersionUID);
    }
}
