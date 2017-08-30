package com.lawyee.appservice.ui.frag;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.RadioButton;

import com.lawyee.appservice.R;
import com.lawyee.appservice.config.Constants;

import net.lawyee.mobilelib.utils.T;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;


public class JaaidfirstTrialInfomFragment extends Fragment {
    /**
     * 申请oid
     */
    public static final String CSTR_RXSTA_JAAIDOID = "jaaidapplyoid";
    /**
     * 申请审核状态
     */
    public static final String CSTR_RXSTA_JAAIDAUDITSTATUS = "jaaidapplyauditstatus";
    /**
     * 审核意见
     */
    public static final String CSTR_RXSTA_JAAIDAUDITOPINION = "jaaidapplyauditopinion";
    @Bind(R.id.rdb_jaaidpass)
    RadioButton rdbPass;
    @Bind(R.id.rdb_jaaidnopass)
    RadioButton rdbNopass;
    @Bind(R.id.et_nopass_reason)
    EditText etNopassReason;
    @Bind(R.id.btn_Auditingsumber)
    Button btnAuditingsumber;

    /**
     * 审核结果
     */
    private String mIsPass = null;

    public interface OnItemAuditingListener {
        void OnItemAuditionListener(String item, String reason);
    }

    private OnItemAuditingListener listener;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            String string = getArguments().getString(CSTR_RXSTA_JAAIDOID);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_jaaidfirst_trial, container, false);

        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initView();
    }

    private void initView() {

        rdbPass.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    mIsPass = Constants.APPLYPASS;
                }
            }
        });
        rdbNopass.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    mIsPass = Constants.APPLYNOPASS;

                }
            }
        });
    }

    private void submit() {
        if (TextUtils.isEmpty(mIsPass)) {
            T.showShort(getContext(), getString(R.string.jaaid_applyfruit));
            return;
        }

        String trim=null;
        if (mIsPass.equals(Constants.APPLYNOPASS)) {
             trim = etNopassReason.getText().toString().trim();
            if (TextUtils.isEmpty(trim)) {
                T.showShort(getContext(), getString(R.string.jaaid_pleaseNoPass));
                return;
            } else {
                //填写不通过原因
            }
        }
        listener.OnItemAuditionListener(mIsPass,trim);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }

    @OnClick(R.id.btn_Auditingsumber)
    public void onClick() {
        submit();

    }
}
