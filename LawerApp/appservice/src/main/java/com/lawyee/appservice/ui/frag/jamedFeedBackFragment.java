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
import android.widget.RadioButton;
import android.widget.TextView;

import com.lawyee.appservice.R;
import com.lawyee.appservice.util.DatePopWindons;

import net.lawyee.mobilelib.utils.T;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;


public class jamedFeedBackFragment extends Fragment {


    @Bind(R.id.tv_jamed_uploadfb)
    TextView tvJamedUploadfb;
    @Bind(R.id.rdb_jamed_fbsuccess)
    RadioButton rdbJamedFbsuccess;
    @Bind(R.id.rdb_jamed_fbfail)
    RadioButton rdbJamedFbfail;
    @Bind(R.id.tv_jaemd_finishData)
    TextView tvJaemdFinishData;
    @Bind(R.id.rdb_jamed_fbyes)
    RadioButton rdbJamedFbyes;
    @Bind(R.id.rdb_jamed_fbno)
    RadioButton rdbJamedFbno;
    @Bind(R.id.btn_jamed_Sumbit)
    Button btnJamedSumbit;

    private String mIsSureess;
    private String mSureess = "Sureess";
    private String mFail = "fail";
    private String mIsSure;
    private String mSure = "sure";
    private String mNo = "no";

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_jamed_feed_back, container, false);
        ButterKnife.bind(this, view);

        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initView();
    }

    private void initView() {
        rdbJamedFbsuccess.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    mIsSureess = mSureess;
                }

            }
        });
        rdbJamedFbfail.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                mIsSureess = mFail;
            }
        });
        rdbJamedFbyes.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    mIsSure = mSure;
                }
            }
        });
        rdbJamedFbno.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    mIsSure = mNo;
                }
            }
        });


    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }

    @OnClick({R.id.tv_jaemd_finishData, R.id.tv_jamed_uploadfb, R.id.btn_jamed_Sumbit})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tv_jaemd_finishData:
                DatePopWindons.showDialog(getContext(),tvJaemdFinishData);
                break;
            case R.id.tv_jamed_uploadfb:
                break;
            case R.id.btn_jamed_Sumbit:
                submit();
                break;
        }
    }

    private void submit() {
        if (TextUtils.isEmpty(mIsSureess)) {
            T.showShort(getContext(), getString(R.string.tos_pleaseJamedIsSureess));
            return;
        }

        String finishDate = tvJaemdFinishData.getText().toString().trim();
        if (TextUtils.isEmpty(finishDate)) {
            T.showShort(getContext(), getString(R.string.tos_pleaseJamedFinishDate));
            return;
        }

        if (TextUtils.isEmpty(mIsSure)) {
            T.showShort(getContext(), getString(R.string.tos_pleasejamedIsTure));
            return;
        }

    }
}
