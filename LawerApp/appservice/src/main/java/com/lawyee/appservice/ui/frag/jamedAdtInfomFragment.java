package com.lawyee.appservice.ui.frag;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.RadioButton;
import android.widget.TextView;

import com.afollestad.materialdialogs.MaterialDialog;
import com.lawyee.appservice.R;
import com.lawyee.appservice.adpater.ApplyListPopAdapter;
import com.lawyee.appservice.config.DataManage;
import com.lawyee.appservice.vo.BaseCommonDataVO;
import com.lawyee.appservice.vo.SelectItemVo;
import com.lawyee.appservice.widget.ContentEditText;
import com.lawyee.appservice.widget.RecycleViewDivider;

import net.lawyee.mobilelib.utils.T;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static com.lawyee.appservice.util.RecyclerSelectItem.MoveToPostion;


public class jamedAdtInfomFragment extends Fragment {


    @Bind(R.id.rdb_jamedaccept)
    RadioButton rdbJamedaccept;
    @Bind(R.id.rdb_jemedNoaccept)
    RadioButton rdbJemedNoaccept;
    @Bind(R.id.tv_jamed_season)
    TextView tvJamedSeason;
    @Bind(R.id.et_jamedotherexplain)
    ContentEditText etJamedotherexplain;
    @Bind(R.id.btn_jamedsumbit)
    Button btnJamedsumbit;

    private String mIsAccept;
    private String mAccept = "accept";
    private String mNoaccept = "noaccept";

    private List<BaseCommonDataVO> mNoHandlerLists;
    private MaterialDialog mPopWindowsShow;

    private SelectItemVo mSelectNoHandlerItem;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_jamed_adt_infom, container, false);
        ButterKnife.bind(this, view);
        initNoHandler();
        return view;
    }

    private void initNoHandler() {
        if (mNoHandlerLists == null || mNoHandlerLists.isEmpty()) {
            mNoHandlerLists = new ArrayList<>();
            List<BaseCommonDataVO> mNoHandlers = DataManage.getInstance().getmApplyJamedNoHandler();
            if (mNoHandlers != null && !mNoHandlers.isEmpty()) {
                mNoHandlerLists = mNoHandlers;
            }
        }
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initView();

    }

    private void initView() {
        rdbJamedaccept.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    mIsAccept = mAccept;
                }
            }
        });
        rdbJemedNoaccept.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    mIsAccept = mNoaccept;
                }
            }
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }

    @OnClick({R.id.tv_jamed_season, R.id.btn_jamedsumbit})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tv_jamed_season:
                break;
            case R.id.btn_jamedsumbit:
                submit();
                break;
        }
    }

    /**
     * @param tv     显示控件
     * @param mData  数据
     * @param nation 显示文字
     * @param type   类型
     */
    private void handlerPopWindos(final TextView tv, final List<BaseCommonDataVO> mData, final String nation, final String type) {
        final ApplyListPopAdapter applyPopAdapter = new ApplyListPopAdapter(mData, getContext());
        final GridLayoutManager manager = new GridLayoutManager(getContext(), 1);
        manager.setOrientation(LinearLayoutManager.VERTICAL);
        if (mPopWindowsShow == null || !mPopWindowsShow.isShowing()) {
            mPopWindowsShow = new MaterialDialog.Builder(getContext())
                    .adapter(applyPopAdapter, manager)
                    .backgroundColorRes(R.color.activity_bg)
                    .show();
            mPopWindowsShow.getRecyclerView().addItemDecoration(new RecycleViewDivider(getContext(), GridLayoutManager.VERTICAL, R.drawable.bg_rlv_diving));
        }
        applyPopAdapter.setSeletsStr(nation);
        if (mSelectNoHandlerItem == null) {
            mSelectNoHandlerItem = new SelectItemVo();
        }
        int selectPosition = mSelectNoHandlerItem.getSelectPosition();
        if (selectPosition != -1) {
            applyPopAdapter.setSeletsPosition(selectPosition);
            MoveToPostion(manager, mPopWindowsShow.getRecyclerView(), selectPosition);
        }
        applyPopAdapter.setOnRecyclerItemClickListener(new ApplyListPopAdapter.OnRecyclerItemClickListener() {
            @Override
            public void OnItemClickListener(View view, BaseCommonDataVO itemVo, int position) {
                mSelectNoHandlerItem.setItemVo(itemVo);
                mSelectNoHandlerItem.setSelectPosition(position);
                tv.setText(itemVo.getName());
                mPopWindowsShow.dismiss();
            }
        });
    }

    private void submit() {
        if (TextUtils.isEmpty(mIsAccept)) {
            T.showShort(getContext(), getString(R.string.tos_pleaseAuditing));
            return;
        }

        if (mIsAccept.equals(mAccept)) {

        } else if (mIsAccept.equals(mNoaccept)) {
            String season = tvJamedSeason.getText().toString().trim();
            if (TextUtils.isEmpty(season)) {
                T.showShort(getContext(), getString(R.string.jamed_nohandleseason));
                return;
            }else {
                //填写不通过原因
            }
        }

        String otherInfom = etJamedotherexplain.getText().toString().trim();
        if (TextUtils.isEmpty(otherInfom)) {
            T.showShort(getContext(), getString(R.string.jamed_otherexplain));
            return;
        }
    }

}
