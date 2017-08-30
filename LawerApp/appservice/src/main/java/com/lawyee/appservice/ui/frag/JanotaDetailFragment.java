package com.lawyee.appservice.ui.frag;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.lawyee.appservice.R;
import com.lawyee.appservice.config.DataManage;
import com.lawyee.appservice.vo.JanotaApplyVo;

import butterknife.Bind;
import butterknife.ButterKnife;

public class JanotaDetailFragment extends Fragment {
    /**
     * 传参-公证oid
     */
    public static final String INTENT_PARAMETER_JANOTOID = "janotoid";
    @Bind(R.id.tv_janota_applyname)
    TextView tvJanotaApplyname;
    @Bind(R.id.tv_janota_applycardtype)
    TextView tvJanotaApplycardtype;
    @Bind(R.id.tv_janota_applycardnumber)
    TextView tvJanotaApplycardnumber;
    @Bind(R.id.tv_janota_applyphone)
    TextView tvJanotaApplyphone;
    @Bind(R.id.tv_janota_applyNotaCartType)
    TextView tvJanotaApplyNotaCartType;
    @Bind(R.id.tv_janota_applyNotaitem)
    TextView tvJanotaApplyNotaitem;
    @Bind(R.id.tv_janota_applysham)
    TextView tvJanotaApplysham;
    @Bind(R.id.tv_janota_applyNotaBooknumber)
    TextView tvJanotaApplyNotaBooknumber;
    @Bind(R.id.tv_janota_applyfinishDate)
    TextView tvJanotaApplyfinishDate;
    @Bind(R.id.tv_janota_applyotherInfom)
    TextView tvJanotaApplyotherInfom;
    @Bind(R.id.tv_janota_applyAppendix)
    TextView tvJanotaApplyAppendix;

    private String mJanotOid;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mJanotOid = getArguments().getString(INTENT_PARAMETER_JANOTOID);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_janoth_apply, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        RequestService();
    }

    private void RequestService() {
        JanotaApplyVo vo = new JanotaApplyVo();

        tvJanotaApplyname.setText(vo.getApplyName());

        tvJanotaApplycardtype.setText(DataManage.getInstance().getNameWithOid(vo.getApplyCardType()));

        tvJanotaApplycardnumber.setText(vo.getApplyIdCard());

        tvJanotaApplyphone.setText(vo.getApplyTelephone());

        tvJanotaApplyNotaCartType.setText(DataManage.getInstance().getNameWithOid(vo.getApplyNotaType()));

        tvJanotaApplyNotaitem.setText(DataManage.getInstance().getNameWithOid(vo.getApplyNotaItem()));

        tvJanotaApplysham.setText(vo.getApplyFalsifiedCertification() ? "是" : "否");

        tvJanotaApplyNotaBooknumber.setText(vo.getApplyNotaNumber());

        tvJanotaApplyfinishDate.setText(vo.getApplyFinishDate());

        String ohterExplain = vo.getApplyOhterExplain();

        if (ohterExplain == null || TextUtils.isEmpty(ohterExplain)) {
            tvJanotaApplyotherInfom.setText("无");
        } else {
            tvJanotaApplyotherInfom.setText(ohterExplain);
        }


    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }
}
