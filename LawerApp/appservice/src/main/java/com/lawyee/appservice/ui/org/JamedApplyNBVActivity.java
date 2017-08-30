package com.lawyee.appservice.ui.org;

import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;

import com.afollestad.materialdialogs.MaterialDialog;
import com.lawyee.appservice.R;
import com.lawyee.appservice.adpater.ApplyListPopAdapter;
import com.lawyee.appservice.config.Constants;
import com.lawyee.appservice.config.DataManage;
import com.lawyee.appservice.ui.BaseActivity;
import com.lawyee.appservice.util.DatePopWindons;
import com.lawyee.appservice.vo.BaseCommonDataVO;
import com.lawyee.appservice.vo.JamedApplyDetailVO;
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

import static com.lawyee.appservice.util.RecyclerSelectItem.MoveToPostion;

public class JamedApplyNBVActivity extends BaseActivity {

    @Bind(R.id.et_jamed_Applynumber)
    EditText etJamedApplyNumber;
    @Bind(R.id.tv_jamedApplyorg)
    TextView tvJamedApplyorg;
    @Bind(R.id.tv_jamedApplyname)
    EditText tvJamedApplyname;
    @Bind(R.id.rdb_jamed_ApplyBoy)
    RadioButton rdbJamedApplyBoy;
    @Bind(R.id.rdb_jamed_ApplyGril)
    RadioButton rdbJamedApplyGril;
    @Bind(R.id.tv_jamed_applycardType)
    TextView tvJamedApplycardType;
    @Bind(R.id.et_jamed_applybeicardnumber)
    EditText etJamedApplybeicardnumber;
    @Bind(R.id.tv_jamed_applybrithday)
    TextView tvJamedApplybrithday;
    @Bind(R.id.tv_jamed_applynation)
    TextView tvJamedApplynation;
    @Bind(R.id.et_jamed_applyphone)
    EditText etJamedApplyphone;
    @Bind(R.id.et_jamed_applywork)
    EditText etJamedApplywork;
    @Bind(R.id.et_jamed_applyrelation)
    EditText etJamedApplyrelation;
    @Bind(R.id.et_jamed_applybename)
    EditText etJamedApplybename;
    @Bind(R.id.rdb_jamed_beiApplyBoy)
    RadioButton rdbJamedBeiApplyBoy;
    @Bind(R.id.rdb_jamed_beiApplyGril)
    RadioButton rdbJamedBeiApplyGril;
    @Bind(R.id.tv_jamed_applybeiCardType)
    TextView tvJamedApplybeiCardType;
    @Bind(R.id.et_jamed_applycardnumber)
    EditText etJamedApplycardnumber;
    @Bind(R.id.tv_jamed_applyBeibrithday)
    TextView tvJamedApplyBeibrithday;
    @Bind(R.id.tv_jamed_applybeiNation)
    TextView tvJamedApplybeiNation;
    @Bind(R.id.et_jamed_applybeiphone)
    EditText etJamedApplybeiphone;
    @Bind(R.id.et_jamed_applybeiwork)
    EditText etJamedApplybeiwork;
    @Bind(R.id.et_jamed_applycase)
    ContentEditText etJamedApplycase;
    @Bind(R.id.et_jamed_applyitem)
    ContentEditText etJamedApplyitem;
    @Bind(R.id.jamed_submit)
    Button jamedSubmit;
    private MaterialDialog mPopWindowsShow;

    private SelectItemVo mSelectType;

    private SelectItemVo mSelectNation;

    private SelectItemVo mBeiSelectType;

    private SelectItemVo mBeiSelectNation;


    private String CARDTYPE = "cardtype";

    private String CARDNATION = "nation";

    private String BEICARDTYPE = "beicardtype";

    private String BEICARDNATION = "beination";

    private String mSex = "";

    private String mBeiSex = "";

    private List<BaseCommonDataVO> mCardTypeLists;

    private List<BaseCommonDataVO> mNationLists;

    private String Officer = "军官证";

    private String IdCard = "身份证";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void initContentView(Bundle savedInstanceState) {
        setContentView(R.layout.activity_jamed_apply_nbv);
        ButterKnife.bind(this);
        initView();
        initCardType();
        initNation();

    }

    private void initNation() {
        if (mNationLists == null || mNationLists.isEmpty()) {
            mNationLists = new ArrayList<>();
            List<BaseCommonDataVO> nationDatas = DataManage.getInstance().getApplyNationDatas();
            if (nationDatas != null && !nationDatas.isEmpty()) {
                mNationLists = nationDatas;
            }
        }
    }

    private void initCardType() {
        if (mCardTypeLists == null || mCardTypeLists.isEmpty()) {
            mCardTypeLists = new ArrayList<>();
            List<BaseCommonDataVO> cardlists = DataManage.getInstance().getApplyCertificatesDatas();
            if (cardlists != null && !cardlists.isEmpty()) {
                mCardTypeLists = cardlists;
            }
        }
    }

    private void initView() {
        rdbJamedApplyBoy.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked)
                    mSex = Constants.APPLYBOY;
            }
        });
        rdbJamedApplyGril.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked)
                    mSex = Constants.APPLYGIRL;
            }
        });
        rdbJamedBeiApplyBoy.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked)
                    mBeiSex = Constants.APPLYBOY;
            }
        });
        rdbJamedBeiApplyGril.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked)
                    mBeiSex = Constants.APPLYGIRL;
            }
        });

    }


    @OnClick({R.id.tv_jamedApplyorg, R.id.tv_jamed_applycardType, R.id.tv_jamed_applybrithday, R.id.tv_jamed_applynation, R.id.jaauth_ApplyBoy, R.id.jaauth_ApplyGril, R.id.tv_jamed_applybeiCardType, R.id.tv_jamed_applyBeibrithday, R.id.tv_jamed_applybeiNation})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tv_jamedApplyorg:
                break;
            case R.id.tv_jamed_applycardType:
                String cardtype = getTextStr(tvJamedApplybeiCardType);
                if (mCardTypeLists != null && !mCardTypeLists.isEmpty()) {
                    handlerPopWindos(tvJamedApplycardType, mCardTypeLists, cardtype, CARDTYPE);
                }
                break;
            case R.id.tv_jamed_applybrithday:
                DatePopWindons.showDialog(JamedApplyNBVActivity.this, tvJamedApplybrithday);
                break;
            case R.id.tv_jamed_applynation:
                String nation = getTextStr(tvJamedApplybeiNation);
                if (mNationLists != null && !mNationLists.isEmpty()) {
                    handlerPopWindos(tvJamedApplynation, mNationLists, nation, CARDNATION);
                }
                break;

            case R.id.tv_jamed_applybeiCardType:
                String beicardtype = getTextStr(tvJamedApplybeiCardType);
                if (mCardTypeLists != null && mCardTypeLists.isEmpty()) {
                    handlerPopWindos(tvJamedApplybeiCardType, mCardTypeLists, beicardtype, BEICARDTYPE);
                }
                break;
            case R.id.tv_jamed_applyBeibrithday:
                DatePopWindons.showDialog(JamedApplyNBVActivity.this, tvJamedApplyBeibrithday);
                break;
            case R.id.tv_jamed_applybeiNation:
                String beination = getTextStr(tvJamedApplybeiNation);
                if (mNationLists != null && mNationLists.isEmpty()) {
                    handlerPopWindos(tvJamedApplybeiNation, mNationLists, beination, BEICARDNATION);
                }
                break;
            case R.id.jamed_submit:
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
                } else if (type.equals(CARDNATION)) {//鉴定事项
                    mSelectNation.setSelectPosition(position);
                    mSelectNation.setItemVo(itemVo);
                    tv.setText(itemVo.getName());
                    mPopWindowsShow.dismiss();
                } else if (type.equals(BEICARDTYPE)) {//被申请人证件类型
                    mBeiSelectType.setSelectPosition(position);
                    mBeiSelectType.setItemVo(itemVo);
                    tv.setText(itemVo.getName());
                    mPopWindowsShow.dismiss();
                } else if (type.equals(BEICARDNATION)) {//被申请人民族
                    mBeiSelectNation.setSelectPosition(position);
                    mBeiSelectNation.setItemVo(itemVo);
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
        if (mSelectNation == null) {
            mSelectNation = new SelectItemVo();
        }
        if (mBeiSelectType == null) {
            mBeiSelectType = new SelectItemVo();
        }
        if (mBeiSelectNation == null) {
            mBeiSelectNation = new SelectItemVo();
        }
        if (type.equals(CARDTYPE)) {
            int selectPosition = mSelectType.getSelectPosition();
            if (selectPosition != -1) {
                applyPopAdapter.setSeletsPosition(selectPosition);
                MoveToPostion(manager, mPopWindowsShow.getRecyclerView(), selectPosition);
            }
        } else if (type.equals(CARDNATION)) {
            int selectPosition = mSelectNation.getSelectPosition();
            if (selectPosition != -1) {
                applyPopAdapter.setSeletsPosition(selectPosition);
                MoveToPostion(manager, mPopWindowsShow.getRecyclerView(), selectPosition);
            }
        } else if (type.equals(BEICARDTYPE)) {
            int selectPosition = mBeiSelectType.getSelectPosition();
            if (selectPosition != -1) {
                applyPopAdapter.setSeletsPosition(selectPosition);
                MoveToPostion(manager, mPopWindowsShow.getRecyclerView(), selectPosition);
            }
        } else if (type.equals(BEICARDNATION)) {
            int selectPosition = mBeiSelectNation.getSelectPosition();
            if (selectPosition != -1) {
                applyPopAdapter.setSeletsPosition(selectPosition);
                MoveToPostion(manager, mPopWindowsShow.getRecyclerView(), selectPosition);
            }
        }
    }

    private String getTextStr(TextView textView) {
        String trim = textView.getText().toString().trim();
        if (TextUtils.isEmpty(trim)) {
            return "";
        }
        return trim;
    }
    private void submit() {

        JamedApplyDetailVO vo = new JamedApplyDetailVO();
        // validate
        String JamedApplyNumber = etJamedApplyNumber.getText().toString().trim();
        if (TextUtils.isEmpty(JamedApplyNumber)) {
            T.showShort(this, getString(R.string.tos_jamedIsEmpty));
            return;
        }
        vo.setSerialNo(JamedApplyNumber);

        String mOrg = getTextStr(tvJamedApplyorg);
        if (TextUtils.isEmpty(mOrg)) {
            T.showShort(this, getString(R.string.tos_jamedOrgIsEmpty));
        }
        vo.setTjOrgName(mOrg);

        String jamedApplyname = tvJamedApplyname.getText().toString().trim();
        if (TextUtils.isEmpty(jamedApplyname)) {
            T.showShort(this, getString(R.string.tos_pleaseApplyname));
            return;
        }
        vo.setApplyName(jamedApplyname);

        if (TextUtils.isEmpty(mSex)) {
            T.showShort(this, getString(R.string.tos_please_sex));
            return;
        }
        vo.setApplyGender(mSex);

        String cardtype = getTextStr(tvJamedApplycardType);
        if (mSelectType == null || mSelectType.getItemVo() == null || TextUtils.isEmpty(cardtype)) {
            T.showShort(this, getString(R.string.tos_please_cardtype));
            return;
        }
        vo.setApplyCardType(mSelectType.getItemVo().getOid());

        String applycardnumber = etJamedApplycardnumber.getText().toString().trim();
        if (TextUtils.isEmpty(applycardnumber)) {
            T.showShort(this, getString(R.string.tos_pleaseIsCardNumber));
            return;
        }
        if (cardtype.equals(Officer)) {
            boolean officer = StringUtil.validateidJGCard(applycardnumber);
            if (officer) {
                vo.setApplyIdCard(applycardnumber);
            } else {
                T.showShort(this, getString(R.string.tos_pleaseIsCardNumber));
                return;
            }
        } else if (cardtype.equals(IdCard)) {
            boolean card = StringUtil.validateidCard(applycardnumber);
            if (card) {
                vo.setApplyIdCard(applycardnumber);
            } else {
                T.showShort(this, getString(R.string.tos_pleaseIsCardNumber));
                return;
            }
        }

        String mbrithday = getTextStr(tvJamedApplybrithday);
        if (TextUtils.isEmpty(mbrithday)) {
            T.showShort(this, getString(R.string.tos_BrithdayIsEmpty));
        }
        vo.setApplyBrithday(mbrithday);

        String mNation = getTextStr(tvJamedApplynation);
        if (mSelectNation == null || mSelectNation.getItemVo() == null || TextUtils.isEmpty(mNation)) {
            T.showShort(this, getString(R.string.tos_pleaseNation));
        }
        vo.setApplyNation(mNation);

        String applyphone = etJamedApplyphone.getText().toString().trim();
        if (TextUtils.isEmpty(applyphone)) {
            T.showShort(this, getString(R.string.tos_telephoneIsEmpty));
            return;
        }
        boolean phone = StringUtil.validateMoblie(applyphone);
        if (phone) {
            vo.setApplyTelephone(applyphone);
        } else {
            T.showShort(this, getString(R.string.tos_pleaseTelephoneIsRight));
            return;
        }

        String applywork = etJamedApplywork.getText().toString().trim();
        if (!TextUtils.isEmpty(applywork)) {//单位或住址
            vo.setApplyAddress(applywork);
        }

        String applyrelation = etJamedApplyrelation.getText().toString().trim();
        if (!TextUtils.isEmpty(applyrelation)) {//申请人关系
            vo.setRelation(applyrelation);
        }


        String applybename = etJamedApplybename.getText().toString().trim();
        if (TextUtils.isEmpty(applybename)) {
            T.showShort(this, getString(R.string.tos_beNameIsEmpty));
            return;
        }
        vo.setBeApplyName(applybename);

        if (TextUtils.isEmpty(mBeiSex)) {
            T.showShort(this, getString(R.string.tos_pleasebeiApplySex));
        }
        vo.setBeApplyGender(mBeiSex);

        String beiCardType = getTextStr(tvJamedApplybeiCardType);
        if (mBeiSelectType == null || mBeiSelectType.getItemVo() == null || TextUtils.isEmpty(beiCardType)) {
            T.showShort(this, getString(R.string.tos_pleaseBeiApplyCardType));
        }
        vo.setBeApplyCardType(mBeiSelectType.getItemVo().getOid());

        String beiapplycardnumber = etJamedApplybeicardnumber.getText().toString().trim();
        if (TextUtils.isEmpty(beiapplycardnumber)) {
            T.showShort(this, getString(R.string.tos_pleasebeCardNumber));
            return;
        }

        if (beiCardType.equals(Officer)) {
            boolean officer = StringUtil.validateidJGCard(beiapplycardnumber);
            if (officer) {
                vo.setBeApplyIdCard(beiapplycardnumber);
            } else {
                T.showShort(this, getString(R.string.tos_pleaseBeOfficerIsRight));
                return;
            }
        } else if (beiCardType.equals(IdCard)) {
            boolean card = StringUtil.validateidCard(beiapplycardnumber);
            if (card) {
                vo.setBeApplyIdCard(beiapplycardnumber);
            } else {
                T.showShort(this, getString(R.string.tos_pleaseBeIdIsRight));
                return;
            }
        }

        String beiNation = getTextStr(tvJamedApplybeiNation);
        if (mBeiSelectNation == null || mBeiSelectNation.getItemVo() == null || TextUtils.isEmpty(beiNation)) {
            T.showShort(this, getString(R.string.tos_pleaseBeiNation));
            return;
        }
        vo.setBeApplyNation(mBeiSelectNation.getItemVo().getOid());

        String applybeiphone = etJamedApplybeiphone.getText().toString().trim();
        if (TextUtils.isEmpty(applybeiphone)) {
            T.showShort(this, getString(R.string.tos_pleasebePhone));
            return;
        }
        boolean moblie = StringUtil.validateMoblie(applybeiphone);
        if (moblie){
            vo.setBeApplyTelephone(applybeiphone);
        }else {
            T.showShort(this,getString(R.string.tos_pleaseBeTeleploneIsRight));
            return;
        }

        String applybeiwork = etJamedApplybeiwork.getText().toString().trim();
        if (!TextUtils.isEmpty(applybeiwork)) {
            vo.setBeApplyAddress(applybeiwork);
        }

        String applycase = etJamedApplycase.getText().toString().trim();
        if (TextUtils.isEmpty(applycase)) {
            T.showShort(this, getString(R.string.tos_pleaseCasebrief));
            return;
        }
        vo.setIntroduction(applycase);

        String applyitem = etJamedApplyitem.getText().toString().trim();
        if (TextUtils.isEmpty(applyitem)) {
            T.showShort(this, getString(R.string.tos_pleaseMatterIsEmpty));
            return;
        }
        vo.setMatter(applyitem);
        // TODO validate success, do something


    }
}
