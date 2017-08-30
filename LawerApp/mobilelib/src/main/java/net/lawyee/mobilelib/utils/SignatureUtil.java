package net.lawyee.mobilelib.utils;

import java.lang.reflect.Array;
import java.util.Arrays;

/**
 * All rights Reserved, Designed By www.lawyee.com
 *
 * @version V1.0.xxxxxxxx
 * @Title: 生成签名工具类
 * @Package net.lawyee.mobilelib.utils
 * @Description: signature（签名参数）由timespan（时间戳，yyyyMMddHHmmss），nonce（随机数，四位随机数字或字母），staffId（平台Id，android:dev01  ios:dev02），请求参数+参数值四个进行排序（从小到大）后连接起来的字符串进行MD5
 * @author:wuzhu
 * @date: 2017/5/23
 * @verdescript 版本号 修改时间  修改人 修改的概要说明
 * @Copyright: 2017 www.lawyee.com Inc. All rights reserved.
 * 注意：本内容仅限于北京法意科技有限公司内部传阅，禁止外泄以及用于其他的商业目
 */
public class SignatureUtil {

    /**
     *
     * @param timespan
     * @param noce
     * @param staffId
     * @param postjson
     * @return
     */
    public static String generateSignature(String timespan,String noce,String staffId,String postjson)
    {
        String[] sortStrs = sortStrs(timespan,noce,staffId,postjson);
        StringBuffer tmpStrs = new StringBuffer();
        for (String str:sortStrs
             ) {
            tmpStrs.append(str);
        }
        return MD5.encode(tmpStrs.toString());
    }

    /**
     * 字符串排序
     * @param strarray
     * @return
     */
    private  static String[] sortStrs(String... strarray)
    {
        Arrays.sort(strarray);
        return strarray;
    }
}
