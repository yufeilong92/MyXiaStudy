package com.lawyee.appservice.ui.frag;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.lawyee.appservice.R;

import net.lawyee.mobilelib.utils.T;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;


public class JaaidAppointInfomFragment extends Fragment  {

    public static final String INTENT_PARAMENT_OIDSTR = "jaaidoid";
    @Bind(R.id.tv_jaaidselectlawfirms)
    TextView tvJaaidselectlawfirms;
    @Bind(R.id.tv_jaaidselectlawyer)
    TextView tvJaaidselectlawyer;
    @Bind(R.id.btn_jaaidlawsumbit)
    Button btnJaaidlawsumbit;
    private String mApplyOid;


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mApplyOid = getArguments().getString(INTENT_PARAMENT_OIDSTR);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_jaaid_appoint_infom, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }

    @OnClick(R.id.btn_jaaidlawsumbit)
    public void onClick() {
        submit();

    }

    private void submit() {
        String trim = tvJaaidselectlawfirms.getText().toString().trim();
        if (TextUtils.isEmpty(trim)) {
            T.showShort(getContext(), getString(R.string.jaaid_pleasefirms));
            return;
        }


        String trim1 = tvJaaidselectlawyer.getText().toString().trim();
        if (TextUtils.isEmpty(trim1)) {
          T.showShort(getContext(),getString(R.string.jaaid_pleaselawyer));
            return;
        }

    }
}
