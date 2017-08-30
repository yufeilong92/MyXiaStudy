package com.lawyee.appservice.ui.org;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.widget.FrameLayout;

import com.lawyee.appservice.R;
import com.lawyee.appservice.ui.BaseActivity;
import com.lawyee.appservice.ui.frag.JaauthDetailFragment;
import com.lawyee.appservice.ui.frag.JanotaDetailFragment;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * All rights Reserved, Designed By www.lawyee.com
 *
 * @version V 1.0 xxxxxxxx
 * @Title: DetailActivity.java
 * @Package com.lawyee.appservice.ui
 * @Description: 业务详情查看页
 * @author: YFL
 * @date: 2017/6/30 11:27
 * @verdescript 版本号 修改时间  修改人 修改的概要说明
 * @Copyright: 2017/6/30 www.lawyee.com Inc. All rights reserved.
 * 注意：本内容仅限于北京法意科技有限公司内部传阅，禁止外泄以及用于其他的商业目
 */
public class DetailActivity extends BaseActivity {
    /**
     * 传参-申请的oid
     */
    public static final String INTENT_PARAMETER_OIDSTR = "OidStr";
    /**
     * 传参-公证
     */
    public static final String INTENT_PARAMETER_TYPESTR = "type";
    /**
     * 传参-公证
     */
    public static final String INTENT_PARAMETER_JANOTATYPESTR = "janotatype";
    /**
     * 传参-鉴定
     */
    public static final String INTENT_PARAMETER_JAAUTHTYPESTR = "jaauthtype";

    @Bind(R.id.frag_content)
    FrameLayout fragContent;
    private String mApplyOid;
    private String mType;
    private FragmentManager fm;

    @Override
    protected void initContentView(Bundle savedInstanceState) {
        setContentView(R.layout.activity_janota_detail);
        Intent intent = getIntent();
        mApplyOid = intent.getStringExtra(INTENT_PARAMETER_OIDSTR);
        mType = intent.getStringExtra(INTENT_PARAMETER_TYPESTR);
        initView();
    }

    private void initView() {
        fm = getSupportFragmentManager();
        if (mType.equals(INTENT_PARAMETER_JANOTATYPESTR)) {//公证详情
            requestJanotaDetail();
        } else if (mType.equals(INTENT_PARAMETER_JAAUTHTYPESTR)) {//鉴定详情
            requestJaauthDetail();
        }
    }

    /**
     * 请求公证详情
     */
    private void requestJanotaDetail() {
        FragmentTransaction beginTransaction = fm.beginTransaction();
        JanotaDetailFragment janotaDetailFragment = new JanotaDetailFragment();
        Bundle bundle = new Bundle();
        bundle.putString(JanotaDetailFragment.INTENT_PARAMETER_JANOTOID, mApplyOid);
        janotaDetailFragment.setArguments(bundle);
        beginTransaction.add(R.id.frag_content, janotaDetailFragment).commit();
    }
    /**
     * 请求鉴定详情
     */
    private void requestJaauthDetail() {
        FragmentTransaction beginTransaction = fm.beginTransaction();
        JaauthDetailFragment jaauthDetailFragment = new JaauthDetailFragment();
        Bundle bundle = new Bundle();
        bundle.putString(JaauthDetailFragment.INTENT_PARAMETER_JAAUTHOID, mApplyOid);
        jaauthDetailFragment.setArguments(bundle);
        beginTransaction.add(R.id.frag_content, jaauthDetailFragment).commit();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ButterKnife.bind(this);
    }
}
