package net.lawyee.mobilelib.utils;

import java.util.Random;

/**
 * All rights Reserved, Designed By www.lawyee.com
 *
 * @version V1.0.xxxxxxxx
 * @Title: 生成随机字符串工具类
 * @Package net.lawyee.mobilelib.utils
 * @Description:
 * @author:wuzhu
 * @date: 2017/5/23
 * @verdescript 版本号 修改时间  修改人 修改的概要说明
 * @Copyright: 2017 www.lawyee.com Inc. All rights reserved.
 * 注意：本内容仅限于北京法意科技有限公司内部传阅，禁止外泄以及用于其他的商业目
 */
public class RandomStrUtil {

    private static final String CSTR_CONSTANT="abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";

    /**
     * 生成给定长度的字符串，字符串包括大小写字母及数字
     * @param length
     * @return
     */
    public static String getRandomString(int length){

        Random random=new Random();
        StringBuffer sb=new StringBuffer();
        for(int i=0;i<length;i++){
            int number=random.nextInt(62);
            sb.append(CSTR_CONSTANT.charAt(number));
        }
        return sb.toString();
    }
}
