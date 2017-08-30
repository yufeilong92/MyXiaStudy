package com.lawyee.appservice.ui.org;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.View;

import com.lawyee.appservice.R;
import com.lawyee.appservice.adpater.JaaidApplyViewPageAdapter;
import com.lawyee.appservice.ui.BaseActivity;
import com.lawyee.appservice.ui.frag.JaaidApplyInfomFragment;
import com.lawyee.appservice.ui.frag.JaaidAppointInfomFragment;
import com.lawyee.appservice.ui.frag.JaaidfirstTrialInfomFragment;
import com.lawyee.appservice.widget.NoScrollViewPager;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
/**
 * All rights Reserved, Designed By www.lawyee.com
 * @Title:  JaaidApplyActivity.java
 * @Package com.lawyee.appservice.ui
 * @Description:    法援业务
 * @author: YFL
 * @date:   2017/7/3 16:39
 * @version V 1.0 xxxxxxxx
 * @verdescript  版本号 修改时间  修改人 修改的概要说明
 * @Copyright: 2017/7/3 www.lawyee.com Inc. All rights reserved.
 * 注意：本内容仅限于北京法意科技有限公司内部传阅，禁止外泄以及用于其他的商业目
 */
public class JaaidApplyActivity extends BaseActivity {
    /**
     * 传参-申请人的oid
     */
    public static final String CSTR_EXTRA_APPLYOID="jaaiditemoid";
    /**
     * 传参- 申请流水号
     */
    public static final String CSTR_EXTRA_APPLYSERIANO="jaaidseriano";
    /**
     * 传参-申请审核状态
     */
    public static final String CSTR_EXTRA_AUDITSTATUS="jaaidauditstatus";
    /**
     * 传参-申请反馈状态
     */
    public static final String CSTR_EXTRA_FEEDBCAKSTATUS="jaaidfeedback";
    @Bind(R.id.tablayout)
    TabLayout tablayout;
    @Bind(R.id.jaaid_noscollviewpager)
    NoScrollViewPager jaaidNoscollviewpager;
    private ArrayList<Fragment> fragments;
    List<String> mTitleLists = null;
    //审核状态
    private int mAuditStatus;
    //反馈状态
    private int mFeedBackStatus;
    //申请oid
    private String mApplyOid;
    //申请流水号
    private String mSeriaIno;
    private FragmentManager fm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void initContentView(Bundle savedInstanceState) {
        setContentView(R.layout.activity_jaaid_handler_apply);
        ButterKnife.bind(this);
        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        if (bundle!=null){
            mAuditStatus = bundle.getInt(CSTR_EXTRA_AUDITSTATUS);
            mFeedBackStatus = bundle.getInt(CSTR_EXTRA_FEEDBCAKSTATUS);
            mApplyOid = bundle.getString(CSTR_EXTRA_APPLYOID);
            mSeriaIno = bundle.getString(CSTR_EXTRA_APPLYSERIANO);
        }
        addJaaidFragment();
    }

    /**
     * 法援业务
     */
    private void addJaaidFragment() {
        if (mTitleLists == null) {
            mTitleLists = new ArrayList<>();
            String[] mTitles = getResources().getStringArray(R.array.jaaidtitles);
            mTitleLists.add(mTitles[0]);
            mTitleLists.add(mTitles[1]);
            mTitleLists.add(mTitles[2]);
        }
        fragments = new ArrayList<>();
        JaaidApplyInfomFragment applyInfomFragment = new JaaidApplyInfomFragment();//预审页
        Bundle applyInfombundle = new Bundle();
        applyInfombundle.putString(JaaidApplyInfomFragment.CSTR_EXTRA_APPLYID,mApplyOid);
        applyInfomFragment.setArguments(applyInfombundle);

        JaaidfirstTrialInfomFragment jaaidfirstTrialFragment = new JaaidfirstTrialInfomFragment();//初审页

        Bundle bundle = new Bundle();
        bundle.putString(JaaidfirstTrialInfomFragment.CSTR_RXSTA_JAAIDOID,mApplyOid);
        bundle.putInt(JaaidfirstTrialInfomFragment.CSTR_RXSTA_JAAIDAUDITSTATUS,mAuditStatus);
        jaaidfirstTrialFragment.setArguments(bundle);

        JaaidAppointInfomFragment appointInfomFragment = new JaaidAppointInfomFragment();//指派页

        Bundle apponintbundle = new Bundle();
        apponintbundle.putString(JaaidAppointInfomFragment.INTENT_PARAMENT_OIDSTR,mApplyOid);
        appointInfomFragment.setArguments(apponintbundle);

        fragments.add(applyInfomFragment);
        fragments.add(jaaidfirstTrialFragment);
        fragments.add(appointInfomFragment);
        setViewPagerAdapter();
    }



    private void setViewPagerAdapter() {
        if (mTitleLists != null && !mTitleLists.isEmpty()) {
            JaaidApplyViewPageAdapter pageAdapter = new JaaidApplyViewPageAdapter(getSupportFragmentManager(), mTitleLists, fragments);
            jaaidNoscollviewpager.setPagingEnabled(false);
            jaaidNoscollviewpager.setAdapter(pageAdapter);
            tablayout.setupWithViewPager(jaaidNoscollviewpager);
        }
    }

    @OnClick({R.id.tablayout, R.id.jaaid_noscollviewpager})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tablayout:
                break;
            case R.id.jaaid_noscollviewpager:
                break;
        }
    }
}
