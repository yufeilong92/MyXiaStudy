package com.lawyee.appservice.util;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.DatePicker;
import android.widget.TextView;

import com.afollestad.materialdialogs.DialogAction;
import com.afollestad.materialdialogs.MaterialDialog;
import com.lawyee.appservice.R;

/**
 * All rights Reserved, Designed By www.lawyee.com
 *
 * @version V 1.0 xxxxxxxx
 * @Title: LawerApp
 * @Package com.lawyee.appservice.util
 * @Description: $todo$
 * @author: YFL
 * @date: 2017/7/4 14:16
 * @verdescript 版本号 修改时间  修改人 修改的概要说明
 * @Copyright: 2017 www.lawyee.com Inc. All rights reserved.
 * 注意：本内容仅限于北京法意科技有限公司内部传阅，禁止外泄以及用于其他的商业目
 */
public class DatePopWindons {
    private Context mContent;

    public static void showDialog(Context context, final TextView textView){
        View view = LayoutInflater.from(context).inflate(R.layout.brithday, null);
        final DatePicker datePicker= (DatePicker) view.findViewById(R.id.datePick);
        MaterialDialog builder = new MaterialDialog.Builder(context)
                .customView(view,false)
                .positiveText(android.R.string.ok)
                .negativeText(android.R.string.cancel)
                .onPositive(new MaterialDialog.SingleButtonCallback() {
                    @Override
                    public void onClick(@NonNull MaterialDialog materialDialog, @NonNull DialogAction dialogAction) {
                        int year = datePicker.getYear();
                        int month = datePicker.getMonth()+1;
                        int dayOfMonth = datePicker.getDayOfMonth();
                        textView.setText(year+"-"+month+"-"+dayOfMonth);
                        materialDialog.dismiss();
                    }
                })
                .show();
    }

}
