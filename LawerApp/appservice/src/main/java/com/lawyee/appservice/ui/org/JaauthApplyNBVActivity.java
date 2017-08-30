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
import com.lawyee.appservice.vo.JaauthApplyVo;
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

/**
 * All rights Reserved, Designed By www.lawyee.com
 *
 * @version V 1.0 xxxxxxxx
 * @Title: JanotApplyNBVActivity.java
 * @Package com.lawyee.appservice.ui
 * @Description: 鉴定业务新增页
 * @author: YFL
 * @date: 2017/7/3 16:37
 * @verdescript 版本号 修改时间  修改人 修改的概要说明
 * @Copyright: 2017/7/3 www.lawyee.com Inc. All rights reserved.
 * 注意：本内容仅限于北京法意科技有限公司内部传阅，禁止外泄以及用于其他的商业目
 */
public class JaauthApplyNBVActivity extends BaseActivity {

    @Bind(R.id.rdb_jaauthPersonApply)
    RadioButton rdbJaauthPersonApply;
    @Bind(R.id.rdb_jaauthOrgWeiTuo)
    RadioButton rdbJaauthOrgWeiTuo;
    @Bind(R.id.et_jaauthApplyEntrustWork)
    EditText etJaauthApplyEntrustWork;
    @Bind(R.id.et_jaauthApplyName)
    EditText etJaauthApplyName;
    @Bind(R.id.jaauth_ApplyBoy)
    RadioButton jaauthApplyBoy;
    @Bind(R.id.jaauth_ApplyGril)
    RadioButton jaauthApplyGril;
    @Bind(R.id.tv_jaauthApplyCardType)
    TextView tvJaauthApplyCardType;
    @Bind(R.id.et_jaauthApplyCardNumber)
    EditText etJaauthApplyCardNumber;
    @Bind(R.id.tv_jaauthApplybrithday)
    TextView tvJaauthApplybrithday;
    @Bind(R.id.tv_jaauthApplyAbility)
    TextView tvJaauthApplyAbility;
    @Bind(R.id.et_jaauthApplyDaiName)
    EditText etJaauthApplyDaiName;
    @Bind(R.id.et_jaauthApplyDaiCardNumber)
    EditText etJaauthApplyDaiCardNumber;
    @Bind(R.id.tv_jaauthApplyitem)
    TextView tvJaauthApplyitem;
    @Bind(R.id.et_jaauthApplyintroduction)
    ContentEditText etJaauthApplyintroduction;
    @Bind(R.id.et_jaauthApplyJieLunInfom)
    ContentEditText etJaauthApplyJieLunInfom;
    @Bind(R.id.tv_jaauthApplyMaterial_list)
    TextView tvJaauthApplyMaterialList;
    @Bind(R.id.tv_jaauthApplyBookNumber)
    EditText tvJaauthApplyBookNumber;
    @Bind(R.id.tv_jaauthApplyfinishDate)
    TextView tvJaauthApplyfinishDate;
    @Bind(R.id.jaauth_submit)
    Button jaauthSubmit;

    /**
     * 鉴定类型
     */
    private String mJaauthApplraisalType = "";
    /**
     * 性别
     */
    private String mSex = "";
    /**
     * 证件类型
     */
    private BaseCommonDataVO mCardType;

    private DataManage dataManage;

    private List<BaseCommonDataVO> mCardTypeLists;
    private List<BaseCommonDataVO> mCardItemLists;
    private List<BaseCommonDataVO> mAilityLists;

    private MaterialDialog mPopWindowsShow;

    //证件类型
    private String CARDTYPE = "cardtype";
    // 鉴定事项
    private String JAAUTHCARDITEM = "jaauthcarditem";
    //时间
    private String BRITHDAY = "brithday";
    //申请人行为能力
    private String AILITY = "aility";
    /**
     * 证件类型
     */
    private SelectItemVo mSelectType;
    /**
     * 鉴定事项
     */
    private SelectItemVo mSelectItemVO;
    /**
     * 申请人行为能力
     */
    private SelectItemVo mSelectAility;

    private String Officer = "军官证";

    private String IdCard = "身份证";

    @Override
    protected void initContentView(Bundle savedInstanceState) {
        setContentView(R.layout.activity_jaauth_apply);
        ButterKnife.bind(this);
        dataManage = DataManage.getInstance();
        initCardType();
        initJaauthItem();
        initAility();
        initView();
    }

    private void initAility() {
        if (mAilityLists == null || mAilityLists.isEmpty()) {
            mAilityLists = new ArrayList<>();
            List<BaseCommonDataVO> janotaAilityLists = dataManage.getJanotaAilityLists();
            if (janotaAilityLists != null && !janotaAilityLists.isEmpty()) {
                mAilityLists = janotaAilityLists;
            }
        }
    }

    /**
     * 初始化鉴定事项
     */
    private void initJaauthItem() {
        if (mCardItemLists == null || mCardItemLists.isEmpty()) {
            mCardItemLists = new ArrayList<>();
            List<BaseCommonDataVO> jaauthBusinessArae = dataManage.getJaauthBusinessArae();
            if (jaauthBusinessArae != null && !jaauthBusinessArae.isEmpty()) {
                mCardItemLists = jaauthBusinessArae;
            }
        }
    }


    /**
     * 初始化证件类型
     */
    private void initCardType() {
        if (mCardTypeLists == null || mCardTypeLists.isEmpty()) {
            mCardTypeLists = new ArrayList<>();
            List<BaseCommonDataVO> mCardTypeList = dataManage.getApplyCertificatesDatas();
            if (mCardTypeList != null && !mCardTypeList.isEmpty()) {
                mCardTypeLists = mCardTypeList;
            }
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
        selectItem(applyPopAdapter, type, manager);
        applyPopAdapter.setSeletsStr(nation);
        applyPopAdapter.setOnRecyclerItemClickListener(new ApplyListPopAdapter.OnRecyclerItemClickListener() {
            @Override
            public void OnItemClickListener(View view, BaseCommonDataVO itemVo, int position) {
                if (type.equals(CARDTYPE)) {//证件类型
                    mSelectType.setItemVo(itemVo);
                    mSelectType.setSelectPosition(position);
                    tv.setText(itemVo.getName());
                    mPopWindowsShow.dismiss();
                } else if (type.equals(JAAUTHCARDITEM)) {//鉴定事项
                    mSelectItemVO.setSelectPosition(position);
                    mSelectItemVO.setItemVo(itemVo);
                    tv.setText(itemVo.getName());
                    mPopWindowsShow.dismiss();
                } else if (type.equals(AILITY)) {//行为能力
                    mSelectAility.setItemVo(itemVo);
                    mSelectAility.setSelectPosition(position);
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
        if (mSelectItemVO == null) {
            mSelectItemVO = new SelectItemVo();
        }
        if (mSelectAility == null) {
            mSelectAility = new SelectItemVo();
        }
        if (type.equals(CARDTYPE)) {
            int selectPosition = mSelectType.getSelectPosition();
            if (selectPosition != -1) {
                applyPopAdapter.setSeletsPosition(selectPosition);
                MoveToPostion(manager, mPopWindowsShow.getRecyclerView(), selectPosition);
            }
        } else if (type.equals(JAAUTHCARDITEM)) {
            int selectPosition = mSelectItemVO.getSelectPosition();
            if (selectPosition != -1) {
                applyPopAdapter.setSeletsPosition(selectPosition);
                MoveToPostion(manager, mPopWindowsShow.getRecyclerView(), selectPosition);
            }
        } else if (type.equals(AILITY)) {
            int selectPosition = mSelectAility.getSelectPosition();
            if (selectPosition != -1) {
                applyPopAdapter.setSeletsPosition(selectPosition);
                MoveToPostion(manager, mPopWindowsShow.getRecyclerView(), selectPosition);
            }
        }
    }

    private String getTextStr(TextView mtv) {
        String trim = mtv.getText().toString().trim();
        if (TextUtils.isEmpty(trim)) {
            return "";
        }
        return trim;
    }

    private void initView() {
        rdbJaauthPersonApply.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked)
                    mJaauthApplraisalType = Constants.PERSONAPPLY;
            }
        });
        rdbJaauthOrgWeiTuo.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked)
                    mJaauthApplraisalType = Constants.ORGWEITUO;
            }
        });
        jaauthApplyBoy.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked)
                    mSex = Constants.APPLYBOY;
            }
        });
        jaauthApplyGril.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked)
                    mSex = Constants.APPLYGIRL;
            }
        });
    }

    @OnClick({R.id.rdb_jaauthPersonApply, R.id.rdb_jaauthOrgWeiTuo, R.id.jaauth_ApplyBoy, R.id.jaauth_ApplyGril, R.id.tv_jaauthApplyCardType, R.id.tv_jaauthApplybrithday, R.id.tv_jaauthApplyAbility, R.id.tv_jaauthApplyitem, R.id.tv_jaauthApplyMaterial_list, R.id.tv_jaauthApplyfinishDate, R.id.jaauth_submit})
    public void onClick(View view) {
        switch (view.getId()) {

            case R.id.tv_jaauthApplyCardType:
                String trim = getTextStr(tvJaauthApplyCardType);
                if (mCardTypeLists != null && !mCardTypeLists.isEmpty()) {
                    handlerPopWindos(tvJaauthApplyCardType, mCardTypeLists, trim, CARDTYPE);
                }
                break;
            case R.id.tv_jaauthApplybrithday:
                DatePopWindons.showDialog(JaauthApplyNBVActivity.this, tvJaauthApplybrithday);
                break;
            case R.id.tv_jaauthApplyAbility:
                String ability = getTextStr(tvJaauthApplyAbility);
                if (mAilityLists != null && !mAilityLists.isEmpty()) {
                    handlerPopWindos(tvJaauthApplyAbility, mAilityLists, ability, AILITY);
                }
                break;
            case R.id.tv_jaauthApplyitem:
                String textStr = getTextStr(tvJaauthApplyitem);
                if (mCardItemLists != null && !mCardItemLists.isEmpty()) {
                    handlerPopWindos(tvJaauthApplyitem, mCardItemLists, textStr, JAAUTHCARDITEM);
                }
                break;
            case R.id.tv_jaauthApplyMaterial_list:

                break;
            case R.id.tv_jaauthApplyfinishDate:
                DatePopWindons.showDialog(this, tvJaauthApplyfinishDate);
                break;
            case R.id.jaauth_submit:
                submit();
                break;
        }
    }

    private void submit() {
        JaauthApplyVo vo = new JaauthApplyVo();
        if (TextUtils.isEmpty(mJaauthApplraisalType)) {
            T.showShort(this, getString(R.string.tos_pleasejaauthCardType));
            return;
        }
        vo.setApplyAppraisalType(mJaauthApplraisalType);

        if (mJaauthApplraisalType.equals(Constants.ORGWEITUO)) {
            String jaauthApplyEntrustWork = etJaauthApplyEntrustWork.getText().toString().trim();
            if (TextUtils.isEmpty(jaauthApplyEntrustWork)) {
                T.showShort(this, getString(R.string.tos_please_orgweituo));
                return;
            } else {
                vo.setApplyOrg(jaauthApplyEntrustWork);
            }
        }

        String jaauthApplyName = etJaauthApplyName.getText().toString().trim();
        if (TextUtils.isEmpty(jaauthApplyName)) {
            T.showShort(this, getString(R.string.tos_pleaseApplyname));
            return;
        }
        vo.setApplyName(jaauthApplyName);

        if (TextUtils.isEmpty(mSex)) {
            T.showShort(this, getString(R.string.tos_please_sex));
            return;
        }
        vo.setApplyGender(mSex);

        String mCardType = tvJaauthApplyCardType.getText().toString().trim();
        if (mSelectType == null || mSelectType.getItemVo() == null || TextUtils.isEmpty(mCardType)) {
            T.showShort(this, getString(R.string.tos_please_cardtype));
            return;
        }
        vo.setApplyCardType(mSelectType.getItemVo().getOid());

        String jaauthApplyCardNumber = etJaauthApplyCardNumber.getText().toString().trim();
        if (TextUtils.isEmpty(jaauthApplyCardNumber)) {
            T.showShort(this, getString(R.string.tos_pleaseIsCardNumber));
            return;
        }

        if (mCardType.equals(IdCard)) {
            boolean card = StringUtil.validateidCard(mCardType);
            if (card) {
                vo.setApplyIdCard(jaauthApplyCardNumber);
            } else {
                T.showShort(this, getString(R.string.tos_pleaseIdCardIsRight));
                return;
            }
        } else if (mCardType.equals(Officer)) {
            boolean officer = StringUtil.validateidJGCard(mCardType);
            if (officer) {
                vo.setApplyIdCard(mCardType);
            } else {
                T.showShort(this, getString(R.string.tos_pleaseOfficerIsRight));
                return;
            }
        }

        String mBrithday = getTextStr(tvJaauthApplybrithday);
        if (!TextUtils.isEmpty(mBrithday)) {
            vo.setApplyBrithday(mBrithday);
        }

        String mability = getTextStr(tvJaauthApplyAbility);
        if (mSelectAility == null || mSelectAility.getItemVo() == null || TextUtils.isEmpty(mability)) {
            T.showShort(this, getString(R.string.tos_pleasaselectabilit));
            return;
        }
        vo.setApplyActionAbility(mSelectAility.getItemVo().getOid());

        String jaauthApplyDaiName = etJaauthApplyDaiName.getText().toString().trim();
        if (!TextUtils.isEmpty(jaauthApplyDaiName)) {
            vo.setDaApplyName(jaauthApplyDaiName);
        }

        String jaauthApplyDaiCardNumber = etJaauthApplyDaiCardNumber.getText().toString().trim();
        if (!TextUtils.isEmpty(jaauthApplyDaiCardNumber)) {
            boolean daiCardNumber = StringUtil.validateidCard(jaauthApplyDaiCardNumber);
            if (daiCardNumber) {
                vo.setDaApplyIdCard(jaauthApplyDaiCardNumber);
            } else {
                T.showShort(this, getString(R.string.tos_pleaseIdCardIsRight));
                return;
            }
        }

        String ability = getTextStr(tvJaauthApplyAbility);
        if (mSelectItemVO == null || mSelectItemVO.getItemVo() == null || TextUtils.isEmpty(ability)) {
            T.showShort(this, getString(R.string.tos_pleaseAatuhItem));
            return;
        }
        vo.setApplyAppraiselItem(mSelectItemVO.getItemVo().getOid());

        String jaauthApplyintroduction = etJaauthApplyintroduction.getText().toString().trim();
        if (TextUtils.isEmpty(jaauthApplyintroduction)) {
            T.showShort(this, getString(R.string.jaauth_pleaseCaseJianjie));
            return;
        }
        vo.setApplyCaseSummary(jaauthApplyintroduction);

        String jaauthApplyJielunInfom = etJaauthApplyJieLunInfom.getText().toString().trim();
        if (TextUtils.isEmpty(jaauthApplyJielunInfom)) {
            T.showShort(this, getString(R.string.jaauth_pleasejielunInfom));
            return;
        }
        vo.setApplyAppraiselInfom(jaauthApplyJielunInfom);

        String list = tvJaauthApplyMaterialList.getText().toString().trim();
        if (TextUtils.isEmpty(list)) {
            T.showShort(this, getString(R.string.jaauth_pleaseListStatu));

            return;
        }
        // TODO: 2017/7/5  材料上传

        String jaauthApplyBookNumber = tvJaauthApplyBookNumber.getText().toString().trim();
        if (TextUtils.isEmpty(jaauthApplyBookNumber)) {
            T.showShort(this, getString(R.string.jaauth_please_bookNumber));
            return;
        }
        vo.setApplyAppraisalNumber(jaauthApplyBookNumber);

        String finishDate = getTextStr(tvJaauthApplyfinishDate);
        if (TextUtils.isEmpty(finishDate)) {
            T.showShort(this, getString(R.string.jaauth_finishDateIsEmpty));
            return;
        }
        vo.setApplyAppraisalFinishDate(finishDate);

        // TODO validate success, do something


    }
}
