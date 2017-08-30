package com.lawyee.appservice.ui.org;

import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.TextView;

import com.afollestad.materialdialogs.MaterialDialog;
import com.lawyee.appservice.R;
import com.lawyee.appservice.adpater.ApplyListPopAdapter;
import com.lawyee.appservice.config.DataManage;
import com.lawyee.appservice.ui.BaseActivity;
import com.lawyee.appservice.util.DatePopWindons;
import com.lawyee.appservice.vo.BaseCommonDataVO;
import com.lawyee.appservice.vo.JanotaApplyVo;
import com.lawyee.appservice.vo.SelectItemVo;
import com.lawyee.appservice.widget.ContentEditText;
import com.lawyee.appservice.widget.RecycleViewDivider;

import net.lawyee.mobilelib.utils.StringUtil;
import net.lawyee.mobilelib.utils.T;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static com.lawyee.appservice.R.id.et_janotaApplyCardnumber;
import static com.lawyee.appservice.R.id.et_janotaApplyName;
import static com.lawyee.appservice.R.id.et_janotaApplyPhone;
import static com.lawyee.appservice.R.id.et_janotaGongBookNum;
import static com.lawyee.appservice.util.RecyclerSelectItem.MoveToPostion;

/**
 * All rights Reserved, Designed By www.lawyee.com
 *
 * @version V 1.0 xxxxxxxx
 * @Title: JanotApplyNBVActivity.java
 * @Package com.lawyee.appservice.ui
 * @Description: 公证业务新增页
 * @author: YFL
 * @date: 2017/7/3 16:37
 * @verdescript 版本号 修改时间  修改人 修改的概要说明
 * @Copyright: 2017/7/3 www.lawyee.com Inc. All rights reserved.
 * 注意：本内容仅限于北京法意科技有限公司内部传阅，禁止外泄以及用于其他的商业目
 */
public class JanotaApplyNBVActivity extends BaseActivity {

    @Bind(et_janotaApplyName)
    EditText etJanotaApplyName;
    @Bind(R.id.tv_janotaApplyCardType)
    TextView tvJanotaApplyCardType;
    @Bind(et_janotaApplyCardnumber)
    EditText etJanotaApplyCardnumber;
    @Bind(et_janotaApplyPhone)
    EditText etJanotaApplyPhone;
    @Bind(R.id.tv_janotaGongCardType)
    TextView tvJanotaGongCardType;
    @Bind(R.id.tv_janotaGongItem)
    TextView tvJanotaGongItem;
    @Bind(R.id.chb_janotaApplyIsRight)
    CheckBox chbJanotaApplyIsRight;
    @Bind(et_janotaGongBookNum)
    EditText etJanotaGongBookNum;
    @Bind(R.id.tv_JanotaFinishDate)
    TextView tvJanotaFinishDate;
    @Bind(R.id.editText)
    ContentEditText editText;
    @Bind(R.id.tv_janotaUpload)
    TextView tvJanotaUpload;
    @Bind(R.id.btn_janotaSubmit)
    Button btnJanotaSubmit;
    private String CARDTYPE = "cardtype";

    private String NOTATYPE = "notatype";

    private String NOTAITEM = "notaitem";

    private String IsTrue = "";

    private SelectItemVo mSelectType;

    private SelectItemVo mSelectNotaType;

    private SelectItemVo mSelectNotaItem;
    private MaterialDialog mPopWindowsShow;

    private List<BaseCommonDataVO> mCardTypeLists;

    private List<BaseCommonDataVO> mNotaTypeLists;

    private List<BaseCommonDataVO> mNotaItemLists;

    private String Officer = "军官证";
    private String IdCard = "身份证";

    @Override
    protected void initContentView(Bundle savedInstanceState) {
        setContentView(R.layout.activity_janot_apply_nbv);
        ButterKnife.bind(this);
        initCardType();
        initNotaType();
        initNotaItem();
        initView();

    }

    /**
     * 公证事项
     */
    private void initNotaItem() {
        if (mNotaItemLists == null || mNotaItemLists.isEmpty()) {
            mNotaItemLists = new ArrayList<>();
            List<BaseCommonDataVO> vos = DataManage.getInstance().getJanotaServiceArae();
            if (vos != null && !vos.isEmpty()) {
                mNotaItemLists = vos;
            }
        }
    }

    /**
     * 公证类型
     */
    private void initNotaType() {
        if (mNotaTypeLists == null || mNotaTypeLists.isEmpty()) {
            mNotaTypeLists = new ArrayList<>();
            List<BaseCommonDataVO> notaTypeLists = DataManage.getInstance().getJanotaNotaTypeLists();
            if (notaTypeLists != null && !notaTypeLists.isEmpty()) {
                mNotaTypeLists = notaTypeLists;
            }
        }
    }

    /**
     * 证件类型
     */
    private void initCardType() {
        if (mCardTypeLists == null || mCardTypeLists.isEmpty()) {
            mCardTypeLists = new ArrayList<>();
            List<BaseCommonDataVO> cardtypes = DataManage.getInstance().getApplyCertificatesDatas();
            if (cardtypes != null && !cardtypes.isEmpty()) {
                mCardTypeLists = cardtypes;
            }
        }
    }

    @OnClick({R.id.tv_janotaApplyCardType, R.id.btn_janotaSubmit, R.id.tv_janotaGongCardType, R.id.tv_janotaGongItem, R.id.tv_JanotaFinishDate, R.id.tv_janotaUpload})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tv_janotaApplyCardType:
                String cardtype = getStringSrt(tvJanotaApplyCardType);
                if (mCardTypeLists != null && !mCardTypeLists.isEmpty()) {
                    handlerPopWindos(tvJanotaApplyCardType, mCardTypeLists, cardtype, CARDTYPE);
                }
                break;
            case R.id.tv_janotaGongCardType:
                String mNotaCardType = getStringSrt(tvJanotaGongCardType);
                if (mNotaTypeLists != null && !mNotaTypeLists.isEmpty()) {
                    handlerPopWindos(tvJanotaGongCardType, mNotaTypeLists, mNotaCardType, NOTATYPE);
                }
                break;
            case R.id.tv_janotaGongItem:
                String mNotaItem = getStringSrt(tvJanotaGongItem);
                if (mNotaItemLists != null && !mNotaItemLists.isEmpty()) {
                    handlerPopWindos(tvJanotaGongItem, mNotaItemLists, mNotaItem, NOTAITEM);
                }
                break;
            case R.id.tv_JanotaFinishDate:
                DatePopWindons.showDialog(JanotaApplyNBVActivity.this, tvJanotaFinishDate);
                break;
            case R.id.tv_janotaUpload:

                break;
            case R.id.btn_janotaSubmit:
                submit();
                break;
        }
    }

    private String getStringSrt(TextView textView) {
        String trim = textView.getText().toString().trim();
        if (TextUtils.isEmpty(trim))
            return "";
        return trim;
    }

    private void initView() {
        chbJanotaApplyIsRight.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    IsTrue = "true";
                } else {
                    IsTrue = "false";
                }
            }
        });
    }

    /**
     * @param tv     显示控件
     * @param mData  数据
     * @param nation 显示文字
     * @param type   类型
     */
    private void handlerPopWindos(final TextView tv, final List<BaseCommonDataVO> mData, final String nation, final String type) {
        final ApplyListPopAdapter applyPopAdapter = new ApplyListPopAdapter(mData, this);
        final GridLayoutManager manager = new GridLayoutManager(this, 1);
        manager.setOrientation(LinearLayoutManager.VERTICAL);
        if (mPopWindowsShow == null || !mPopWindowsShow.isShowing()) {
            mPopWindowsShow = new MaterialDialog.Builder(this)
                    .adapter(applyPopAdapter, manager)
                    .backgroundColorRes(R.color.activity_bg)
                    .show();
            mPopWindowsShow.getRecyclerView().addItemDecoration(new RecycleViewDivider(this, GridLayoutManager.VERTICAL, R.drawable.bg_rlv_diving));
        }
        applyPopAdapter.setSeletsStr(nation);
        selectItem(applyPopAdapter, type, manager);
        applyPopAdapter.setOnRecyclerItemClickListener(new ApplyListPopAdapter.OnRecyclerItemClickListener() {
            @Override
            public void OnItemClickListener(View view, BaseCommonDataVO itemVo, int position) {
                if (type.equals(CARDTYPE)) {//证件类型
                    mSelectType.setItemVo(itemVo);
                    mSelectType.setSelectPosition(position);
                    tv.setText(itemVo.getName());
                    mPopWindowsShow.dismiss();
                } else if (type.equals(NOTATYPE)) {//公证类型
                    mSelectNotaType.setSelectPosition(position);
                    mSelectNotaType.setItemVo(itemVo);
                    tv.setText(itemVo.getName());
                    mPopWindowsShow.dismiss();
                } else if (type.equals(NOTAITEM)) {//公证事项
                    mSelectNotaItem.setSelectPosition(position);
                    mSelectNotaItem.setItemVo(itemVo);
                    tv.setText(itemVo.getName());
                    mPopWindowsShow.dismiss();
                }
            }
        });
    }

    private void selectItem(ApplyListPopAdapter applyPopAdapter, String type, GridLayoutManager manager) {
        if (mSelectType == null) {
            mSelectType = new SelectItemVo();
        }
        if (mSelectNotaType == null) {
            mSelectNotaType = new SelectItemVo();
        }
        if (mSelectNotaItem == null) {
            mSelectNotaItem = new SelectItemVo();
        }
        if (type.equals(CARDTYPE)) {
            int selectPosition = mSelectType.getSelectPosition();
            if (selectPosition != -1) {
                applyPopAdapter.setSeletsPosition(selectPosition);
                MoveToPostion(manager, mPopWindowsShow.getRecyclerView(), selectPosition);
            }
        } else if (type.equals(NOTATYPE)) {
            int selectPosition = mSelectNotaType.getSelectPosition();
            if (selectPosition != -1) {
                applyPopAdapter.setSeletsPosition(selectPosition);
                MoveToPostion(manager, mPopWindowsShow.getRecyclerView(), selectPosition);
            }
        } else if (type.equals(NOTAITEM)) {
            int selectPosition = mSelectNotaItem.getSelectPosition();
            if (selectPosition != -1) {
                applyPopAdapter.setSeletsPosition(selectPosition);
                MoveToPostion(manager, mPopWindowsShow.getRecyclerView(), selectPosition);
            }
        }
    }

    private void submit() {
        JanotaApplyVo vo = new JanotaApplyVo();
        // validate
        String janotaApplyName = etJanotaApplyName.getText().toString().trim();
        if (TextUtils.isEmpty(janotaApplyName)) {
            T.showShort(this, getString(R.string.tos_pleaseApplyname));
            return;
        }
        vo.setApplyName(janotaApplyName);

        String cardTpye = getStringSrt(tvJanotaApplyCardType);
        if (mSelectType == null || mSelectType.getItemVo() == null || TextUtils.isEmpty(cardTpye)) {
            T.showShort(this, getString(R.string.tos_please_cardtype));
            return;
        }
        vo.setApplyCardType(mSelectType.getItemVo().getOid());

        String janotaApplyCardnumber = etJanotaApplyCardnumber.getText().toString().trim();
        if (TextUtils.isEmpty(janotaApplyCardnumber)) {
            T.showShort(this, getString(R.string.tos_pleaseIsCardNumber));
            return;
        }
        if (cardTpye.equals(Officer)) {
            boolean officer = StringUtil.validateidJGCard(janotaApplyCardnumber);
            if (officer) {
                vo.setApplyIdCard(janotaApplyCardnumber);
            } else {
                T.showShort(this, getString(R.string.tos_pleaseBeOfficerIsRight));
                return;
            }
        } else if (cardTpye.equals(IdCard)) {
            boolean card = StringUtil.validateidCard(janotaApplyCardnumber);
            if (card) {
                vo.setApplyIdCard(janotaApplyCardnumber);
            } else {
                T.showShort(this, getString(R.string.tos_pleaseBeIdIsRight));
                return;
            }
        }

        String janotaApplyPhone = etJanotaApplyPhone.getText().toString().trim();
        if (TextUtils.isEmpty(janotaApplyPhone)) {
            T.showShort(this, getString(R.string.tos_telephoneIsEmpty));
            return;
        }
        boolean moblie = StringUtil.validateMoblie(janotaApplyPhone);
        if (moblie) {
            vo.setApplyTelephone(janotaApplyPhone);
        } else {
            T.showShort(this, getString(R.string.tos_pleaseTelephoneIsRight));
            return;
        }

        String notaCardType = getStringSrt(tvJanotaGongCardType);
        if (mSelectNotaType == null || mSelectNotaType.getItemVo() == null || TextUtils.isEmpty(notaCardType)) {
            T.showShort(this, getString(R.string.tos_pleaseselectNotaType));
            return;
        }
        vo.setApplyNotaType(mSelectNotaType.getItemVo().getOid());

        String mNotaItem = getStringSrt(tvJanotaGongItem);
        if (mSelectNotaItem == null || mSelectNotaItem.getItemVo() == null || TextUtils.isEmpty(mNotaItem)) {
            T.showShort(this, getString(R.string.tos_pleaseNotaItem));
            return;
        }
        vo.setApplyNotaItem(mSelectNotaItem.getItemVo().getOid());

        if (TextUtils.isEmpty(IsTrue)) {
            T.showShort(this, getString(R.string.tos_pleaseIsTrue));
            return;
        }
        if (IsTrue.equals("true")) {
            vo.setApplyFalsifiedCertification(true);
        } else if (IsTrue.equals("false")) {
            vo.setApplyFalsifiedCertification(false);
        }

        String janotaGongBookNum = etJanotaGongBookNum.getText().toString().trim();
        if (!TextUtils.isEmpty(janotaGongBookNum)) {
            vo.setApplyNotaNumber(janotaGongBookNum);
        }

        String finisDate = getStringSrt(tvJanotaFinishDate);
        if (!TextUtils.isEmpty(finisDate)) {
            vo.setApplyFinishDate(finisDate);
        }

        String otherInfom = getStringSrt(editText);
        if (!TextUtils.isEmpty(otherInfom)) {
            vo.setApplyOhterExplain(otherInfom);
        }
        // TODO: 2017/7/6  附件上传

        // TODO validate success, do something


    }


}
