package com.lawyee.appservice.ui;

import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.afollestad.materialdialogs.MaterialDialog;
import com.lawyee.appservice.R;
import com.lawyee.appservice.adpater.FunctionAdpater;
import com.lawyee.appservice.widget.RecycleViewDivider;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class LoginActivity extends BaseActivity {


    @Bind(R.id.et_phone)
    EditText mEtPhone;
    @Bind(R.id.et_pwd)
    EditText mEtPwd;
    @Bind(R.id.tv_login)
    TextView mTvLogin;
    private MaterialDialog mPopWindowsShow;

    @Override
    protected void initContentView(Bundle savedInstanceState) {
        setContentView(R.layout.activity_login);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ButterKnife.bind(this);
    }
    @OnClick(R.id.tv_login)
    public void OnClick(View view){
        List<String> mData=new ArrayList<>();
        mData.add("0000");
        mData.add("1111");
        mData.add("2222");
        mData.add("3333");
        mData.add("4444");
        handlerPopWindos(mData);
    }
    /**
     * @param mData  数据
     */
    private void handlerPopWindos(final List<String> mData) {
        final FunctionAdpater applyPopAdapter = new FunctionAdpater(mData, this);
        final GridLayoutManager manager = new GridLayoutManager(this, 1);
        manager.setOrientation(LinearLayoutManager.VERTICAL);

        if (mPopWindowsShow == null || !mPopWindowsShow.isShowing()) {
            mPopWindowsShow = new MaterialDialog.Builder(this)
                    .adapter(applyPopAdapter, manager)
                    .backgroundColorRes(R.color.activity_bg)
                    .show();
            mPopWindowsShow.getRecyclerView().addItemDecoration(new RecycleViewDivider(this, GridLayoutManager.VERTICAL, R.drawable.bg_rlv_diving));
        }
        applyPopAdapter.setOnRecyclerItemClickListener(new FunctionAdpater.OnRecyclerItemClickListener() {
            @Override
            public void OnItemClickListener(View view, String itemVo, int position) {

            }
        });
    }

}
