package com.lawyee.appservice.ui.frag;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.lawyee.appservice.R;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * All rights Reserved, Designed By www.lawyee.com
 *
 * @version V 1.0 xxxxxxxx
 * @Title: LawerApp
 * @Package com.lawyee.appservice.ui.frag
 * @Description: $todo$
 * @author: YFL
 * @date: 2017/6/28 16:53
 * @verdescript 版本号 修改时间  修改人 修改的概要说明
 * @Copyright: 2017 www.lawyee.com Inc. All rights reserved.
 * 注意：本内容仅限于北京法意科技有限公司内部传阅，禁止外泄以及用于其他的商业目
 */
public class JaaidApplyInfomFragment extends Fragment {
    /**
     * 传参-法援申请id
     */
    public static final String CSTR_EXTRA_APPLYID = "jaaidapplyid";
    @Bind(R.id.tv_jaaid_applyname)
    TextView tvJaaidApplyname;
    @Bind(R.id.tv_jaaid_applysex)
    TextView tvJaaidApplysex;
    @Bind(R.id.tv_jaaid_applycardtype)
    TextView tvJaaidApplycardtype;
    @Bind(R.id.tv_jaaid_applycardnumber)
    TextView tvJaaidApplycardnumber;
    @Bind(R.id.tv_jaaid_applynation)
    TextView tvJaaidApplynation;
    @Bind(R.id.tv_jaaid_applyphone)
    TextView tvJaaidApplyphone;
    @Bind(R.id.tv_jaaid_applylocation)
    TextView tvJaaidApplylocation;
    @Bind(R.id.tv_jaaid_applyabode)
    TextView tvJaaidApplyabode;
    @Bind(R.id.tv_jaaid_applywork)
    TextView tvJaaidApplywork;
    @Bind(R.id.tv_jaaid_applyreason)
    TextView tvJaaidApplyreason;
    @Bind(R.id.tv_jaaid_applydainame)
    TextView tvJaaidApplydainame;
    @Bind(R.id.tv_jaaid_applydaitype)
    TextView tvJaaidApplydaitype;
    @Bind(R.id.tv_jaaid_applydaicardnumber)
    TextView tvJaaidApplydaicardnumber;
    @Bind(R.id.tv_jaaid_applypartyname)
    TextView tvJaaidApplypartyname;
    @Bind(R.id.tv_jaaid_applypartyhere)
    TextView tvJaaidApplypartyhere;
    @Bind(R.id.tv_jaaid_applycasehere)
    TextView tvJaaidApplycasehere;
    @Bind(R.id.tv_jaaid_applyhandlerhere)
    TextView tvJaaidApplyhandlerhere;
    @Bind(R.id.tv_jaaid_applyhandlerOrg)
    TextView tvJaaidApplyhandlerOrg;
    @Bind(R.id.tv_jaaid_applyidCard)
    TextView tvJaaidApplyidCard;
    @Bind(R.id.tv_jaaid_applybook)
    TextView tvJaaidApplybook;
    @Bind(R.id.tv_jaaid_applyjingji)
    TextView tvJaaidApplyjingji;
    @Bind(R.id.tv_jaaid_applyotherstuta)
    TextView tvJaaidApplyotherstuta;
    @Bind(R.id.btn_jaaidhandler)
    Button btnJaaidhandler;
    private String mApplyId;



    public interface OnFragmentHandle {
        void onfragmetnhandle();
    }

    private OnFragmentHandle onFragmentHandle;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mApplyId = getArguments().getString(CSTR_EXTRA_APPLYID);
        }
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_applyinfom, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initView();

    }

    private void initView() {


    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }

    @OnClick(R.id.btn_jaaidhandler)
    public void onClick() {

    }
    @OnClick({R.id.tv_jaaid_applyidCard, R.id.tv_jaaid_applybook, R.id.tv_jaaid_applyjingji, R.id.tv_jaaid_applyotherstuta})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tv_jaaid_applyidCard:
                break;
            case R.id.tv_jaaid_applybook:
                break;
            case R.id.tv_jaaid_applyjingji:
                break;
            case R.id.tv_jaaid_applyotherstuta:
                break;
        }
    }
}
