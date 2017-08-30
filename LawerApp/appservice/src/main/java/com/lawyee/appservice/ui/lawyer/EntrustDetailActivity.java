package com.lawyee.appservice.ui.lawyer;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import com.lawyee.appservice.R;
import com.lawyee.appservice.ui.BaseActivity;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class EntrustDetailActivity extends BaseActivity {


    @Bind(R.id.tv_principal)
    TextView mTvPrincipal;
    @Bind(R.id.tv_conmitment)
    TextView mTvConmitment;
    @Bind(R.id.tv_entrust_time)
    TextView mTvEntrustTime;
    @Bind(R.id.tv_entrust_detail)
    TextView mTvEntrustDetail;
    @Bind(R.id.tv_reply)
    TextView mTvReply;
    private Context mContext;
    @Override
    protected void initContentView(Bundle savedInstanceState) {
        setContentView(R.layout.activity_entrust_detail);
        mContext=this;
        ButterKnife.bind(this);
    }
    @OnClick(R.id.tv_reply)
    public  void reply(){
        startActivity(new Intent(mContext,EntrustReplyActivity.class));
    }

}
