package com.lawyee.appservice.ui.frag;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.lawyee.appservice.R;
import com.lawyee.appservice.config.DataManage;
import com.lawyee.appservice.vo.JaauthApplyVo;

import butterknife.Bind;
import butterknife.ButterKnife;

public class JaauthDetailFragment extends Fragment {
    /**
     * 传参-鉴定oid
     */
    public static final String INTENT_PARAMETER_JAAUTHOID = "jaauthoid";
    @Bind(R.id.tv_jaauth_Jaauthcardtype)
    TextView tvJaauthJaauthcardtype;
    @Bind(R.id.tv_jaauth_applyEntrustWork)
    TextView tvJaauthApplyEntrustWork;
    @Bind(R.id.tv_jaauth_applyname)
    TextView tvJaauthApplyname;
    @Bind(R.id.tv_jaauth_applysex)
    TextView tvJaauthApplysex;
    @Bind(R.id.tv_jaauth_applycardtype)
    TextView tvJaauthApplycardtype;
    @Bind(R.id.tv_jaauth_applycardnumber)
    TextView tvJaauthApplycardnumber;
    @Bind(R.id.tv_jaauth_applybrithday)
    TextView tvJaauthApplybrithday;
    @Bind(R.id.tv_jaauth_applyApplyAbility)
    TextView tvJaauthApplyApplyAbility;
    @Bind(R.id.tv_jaauth_applyDaiName)
    TextView tvJaauthApplyDaiName;
    @Bind(R.id.tv_jaauth_applyDaiCardNumber)
    TextView tvJaauthApplyDaiCardNumber;
    @Bind(R.id.tv_jaauth_applyitem)
    TextView tvJaauthApplyitem;
    @Bind(R.id.tv_jaauth_applyIntroduction)
    TextView tvJaauthApplyIntroduction;
    @Bind(R.id.tv_jaauth_applyJieLunInfom)
    TextView tvJaauthApplyJieLunInfom;
    @Bind(R.id.tv_jaauth_applyMateriallist)
    TextView tvJaauthApplyMateriallist;
    @Bind(R.id.tv_jaauth_applyBookNumber)
    TextView tvJaauthApplyBookNumber;
    @Bind(R.id.tv_jaauth_applyfinishDate)
    TextView tvJaauthApplyfinishDate;

    private String mJaauthOid;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mJaauthOid = getArguments().getString(INTENT_PARAMETER_JAAUTHOID);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_jaauth_detail, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        RequestService();
    }

    private void RequestService() {
        JaauthApplyVo vo = new JaauthApplyVo();
        tvJaauthJaauthcardtype.setText(DataManage.getInstance().getNameWithOid(vo.getApplyAppraisalType()));

        tvJaauthApplyEntrustWork.setText(vo.getApplyOrg());

        tvJaauthApplyname.setText(vo.getApplyName());

        tvJaauthApplysex.setText(DataManage.getInstance().getSex(vo.getApplyGender()));


        tvJaauthApplycardtype.setText(DataManage.getInstance().getNameWithOid(vo.getApplyCardType()));

        tvJaauthApplycardnumber.setText(vo.getApplyIdCard());

        tvJaauthApplybrithday.setText(vo.getApplyBrithday());

        tvJaauthApplyApplyAbility.setText(DataManage.getInstance().getNameWithOid(vo.getApplyActionAbility()));

        tvJaauthApplyDaiName.setText(vo.getDaApplyName());

        tvJaauthApplyDaiCardNumber.setText(vo.getDaApplyIdCard());

        tvJaauthApplyitem.setText(DataManage.getInstance().getNameWithOid(vo.getApplyAppraiselItem()));

        tvJaauthApplyIntroduction.setText(vo.getApplyCaseSummary());

        tvJaauthApplyJieLunInfom.setText(vo.getApplyAppraiselInfom());

        tvJaauthApplyBookNumber.setText(vo.getApplyAppraisalNumber());

        tvJaauthApplyfinishDate.setText(vo.getApplyAppraisalFinishDate());
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }
}
