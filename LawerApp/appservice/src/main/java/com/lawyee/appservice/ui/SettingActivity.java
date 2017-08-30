package com.lawyee.appservice.ui;

import android.os.Bundle;
import android.widget.CheckBox;
import android.widget.RelativeLayout;

import com.lawyee.appservice.R;

import butterknife.Bind;
import butterknife.ButterKnife;

public class SettingActivity extends BaseActivity {


    @Bind(R.id.cb_free)
    CheckBox mCbFree;
    @Bind(R.id.rl_aid)
    RelativeLayout mRlAid;
    @Bind(R.id.rl_swich_account)
    RelativeLayout mRlSwichAccount;
    @Bind(R.id.rl_logout)
    RelativeLayout mRlLogout;

    @Override
    protected void initContentView(Bundle savedInstanceState) {
        setContentView(R.layout.activity_setting);
        ButterKnife.bind(this);
    }


}
