package com.lawyee.appservice.config;

import android.text.TextUtils;

import com.lawyee.appservice.vo.BaseCommonDataVO;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.lawyee.appservice.config.Constants.PROVINCE_GUIZHOU_ID;


/**
 * All rights Reserved, Designed By www.lawyee.com
 *
 * @version V1.0.xxxxxxxx
 * @Package com.lawyee.apppublic.config
 * @Description: ${todo}(用一句话描述该文件做什么)
 * @author: lzh
 * @date: 2017/5/26 14:37
 * @verdescript 版本号 修改时间  修改人 修改的概要说明
 * @Copyright: ${year} www.lawyee.com Inc. All rights reserved.
 * 注意：本内容仅限于北京法意科技有限公司内部传阅，禁止外泄以及用于其他的商业目
 */

public class DataManage {
    private static DataManage mDataManage;
    private List<BaseCommonDataVO> mCityParentList = new ArrayList<>();//父级地区list
    private Map<String, List<BaseCommonDataVO>> mCityChild = new HashMap<>();//子级地区map
    private List<BaseCommonDataVO> mProfessionalFieldtList = new ArrayList<>();//专业领域List
    private List<BaseCommonDataVO> mServiceContentList = new ArrayList<>();//服务内容List


    private List<BaseCommonDataVO> mEpoaPeratioList = new ArrayList<>();//从业年限List
    private List<BaseCommonDataVO> mIsOnlineList = new ArrayList<>();//是否在线List
    private List<BaseCommonDataVO> mServiceAreaList = new ArrayList<>();//公证类型list
    private List<BaseCommonDataVO> mBusincessAreaList = new ArrayList<>();//服务范围list
    private List<BaseCommonDataVO> mApplyNationLists = new ArrayList<>();//民族list
    private List<BaseCommonDataVO> mApplyCertificatesLists = new ArrayList<>();//证件类型list
    private List<BaseCommonDataVO> mApplyyNotaTypeLists = new ArrayList<>();//证件类型list
    private List<BaseCommonDataVO> mApplyyAilityLists = new ArrayList<>();//证件类型list
    private List<BaseCommonDataVO> mApplyNoHandlerLists = new ArrayList<>();//人民调解不予处理原因

    /**
     * 获取父级城市
     *
     * @return
     */
    public List<BaseCommonDataVO> getmCityParentList() {
        if (mCityParentList == null || mCityParentList.size() == 0) {
            mCityParentList = getCityParent();
        }
        return mCityParentList;
    }

    private List<BaseCommonDataVO> getCityParent() {
        return BaseCommonDataVO.getDataVOListWithParentId(ApplicationSet.getInstance().getAreas(), PROVINCE_GUIZHOU_ID);
    }

    /**
     * 获取父级下面的子类城市
     *
     * @return
     */
    public Map<String, List<BaseCommonDataVO>> getmCityChild() {
        if (mCityChild == null || mCityChild.size() == 0) {
            for (int i = 0; i < getmCityParentList().size(); i++) {
                mCityChild.put(getmCityParentList().get(i).getOid(),
                        BaseCommonDataVO.getDataVOListWithParentId(ApplicationSet.getInstance().getAreas(), getmCityParentList().get(i).getOid()));
            }
        }
        return mCityChild;
    }


    public synchronized static DataManage getInstance() {
        if (mDataManage == null) {
            mDataManage = new DataManage();
        }
        return mDataManage;
    }

    public List<BaseCommonDataVO> getmServiceContentList() {
        if (mServiceContentList == null || mServiceContentList.size() == 0) {
            mServiceContentList = getServiceContent();
        }
        return mServiceContentList;
    }

    public void setmServiceContentList(List<BaseCommonDataVO> mServiceContentList) {
        this.mServiceContentList = mServiceContentList;
    }

    public List<BaseCommonDataVO> getServiceContent() {
        return BaseCommonDataVO.getDataVOListWithParentId(ApplicationSet.getInstance().getDataDictionarys(), Constants.SERVICE_CONTENT_OID);
    }

    public List<BaseCommonDataVO> getmProfessionalFieldtList() {
        if (mProfessionalFieldtList == null || mProfessionalFieldtList.size() == 0) {
            mProfessionalFieldtList = getProfessionalField();
        }
        return mProfessionalFieldtList;
    }


    private List<BaseCommonDataVO> getProfessionalField() {
        return BaseCommonDataVO.getDataVOListWithSuperParentId(ApplicationSet.getInstance().getDataDictionarys(), Constants.PROFESSIONAL_FIELD_OID);
    }

    public List<BaseCommonDataVO> getmEpoaPeratioList() {
        if (mEpoaPeratioList == null || mEpoaPeratioList.size() == 0) {
            mEpoaPeratioList = getEpoaPeratio();
        }
        return mEpoaPeratioList;
    }

    private List<BaseCommonDataVO> getEpoaPeratio() {
        return BaseCommonDataVO.getDataVOListWithParentId(ApplicationSet.getInstance().getDataDictionarys(), Constants.EPOAPERATION);
    }

    public List<BaseCommonDataVO> getmIsOnlineList() {
        if (mIsOnlineList == null || mIsOnlineList.size() == 0) {
            mIsOnlineList = getIsOnline();
        }
        return mIsOnlineList;
    }

    private List<BaseCommonDataVO> getIsOnline() {
        return BaseCommonDataVO.getDataVOListWithParentId(ApplicationSet.getInstance().getDataDictionarys(), Constants.ISONLINE);
    }


    /**
     * 获取(公证)服务范围数据
     *
     * @return
     */
    public List<BaseCommonDataVO> getJanotaServiceArae() {
        if (mServiceAreaList == null || mServiceAreaList.isEmpty()) {
            List<BaseCommonDataVO> dataVOs = BaseCommonDataVO.getDataVOListWithParentId(ApplicationSet.getInstance().getDataDictionarys(), Constants.JANOTA_SERVICE_AREA_OID);
            if (dataVOs != null && !dataVOs.isEmpty()) {
                mServiceAreaList = dataVOs;
            }
        }
        if (mServiceAreaList == null)
            mServiceAreaList = new ArrayList<>();
        return mServiceAreaList;
    }

    /**
     * 获取(公证)服务范围数据
     *
     * @return
     */
    public List<BaseCommonDataVO> getmApplyJamedNoHandler() {
        if (mApplyNoHandlerLists == null || mApplyNoHandlerLists.isEmpty()) {
            List<BaseCommonDataVO> dataVOs = BaseCommonDataVO.getDataVOListWithParentId(ApplicationSet.getInstance().getDataDictionarys(), Constants.APPLYJAMEDNOHANDLE);
            if (dataVOs != null && !dataVOs.isEmpty()) {
                mApplyNoHandlerLists = dataVOs;
            }
        }
        if (mApplyNoHandlerLists == null)
            mApplyNoHandlerLists = new ArrayList<>();
        return mApplyNoHandlerLists;
    }

    /**
     * 公证类型
     *
     * @return
     */
    public List<BaseCommonDataVO> getJanotaNotaTypeLists() {
        if (mApplyyNotaTypeLists == null || mApplyyNotaTypeLists.isEmpty()) {
            List<BaseCommonDataVO> dataVOs = BaseCommonDataVO.getDataVOListWithParentId(ApplicationSet.getInstance().getDataDictionarys(), Constants.APPLYNOTAOID);
            if (dataVOs != null && !dataVOs.isEmpty()) {
                mApplyyNotaTypeLists = dataVOs;
            }
        }
        if (mApplyyNotaTypeLists == null)
            mApplyyNotaTypeLists = new ArrayList<>();
        return mApplyyNotaTypeLists;
    }

    /**
     * 申请人行为能力
     *
     * @return
     */
    public List<BaseCommonDataVO> getJanotaAilityLists() {
        if (mApplyyAilityLists == null || mApplyyAilityLists.isEmpty()) {
            List<BaseCommonDataVO> dataVOs = BaseCommonDataVO.getDataVOListWithParentId(ApplicationSet.getInstance().getDataDictionarys(), Constants.APPLYAILITY);
            if (dataVOs != null && !dataVOs.isEmpty()) {
                mApplyyAilityLists = dataVOs;
            }
        }
        if (mApplyyAilityLists == null)
            mApplyyAilityLists = new ArrayList<>();
        return mApplyyAilityLists;
    }

    /**
     * 鉴定服务范围数据
     *
     * @return
     */
    public List<BaseCommonDataVO> getJaauthBusinessArae() {
        if (mBusincessAreaList == null || mBusincessAreaList.isEmpty()) {
            List<BaseCommonDataVO> dataVOs = BaseCommonDataVO.getDataVOListWithParentId(ApplicationSet.getInstance().getDataDictionarys(), Constants.JAUUTH_SERVICEAREA_OID);
            if (dataVOs != null && !dataVOs.isEmpty()) {
                mBusincessAreaList = dataVOs;
            }
        }
        if (mBusincessAreaList == null)
            mBusincessAreaList = new ArrayList<>();
        return mBusincessAreaList;
    }

    /**
     * 民族
     */
    public List<BaseCommonDataVO> getApplyNationDatas() {
        if (mApplyNationLists == null || mApplyNationLists.isEmpty()) {
            List<BaseCommonDataVO> dataVOs = BaseCommonDataVO.getDataVOListWithParentId(ApplicationSet.getInstance().getDataDictionarys(), Constants.APPLY_NATION_OID);
            if (dataVOs != null && !dataVOs.isEmpty()) {
                mApplyNationLists = dataVOs;
            }
        }
        if (mApplyNationLists == null)
            mApplyNationLists = new ArrayList<>();
        return mApplyNationLists;
    }

    /**
     * 证件类型
     */
    public List<BaseCommonDataVO> getApplyCertificatesDatas() {
        if (mApplyCertificatesLists == null || mApplyCertificatesLists.isEmpty()) {
            List<BaseCommonDataVO> dataVOs = BaseCommonDataVO.getDataVOListWithParentId(ApplicationSet.getInstance().getDataDictionarys(), Constants.APPLY_CERTIFICATES_TYPE_OID);
            if (dataVOs != null && !dataVOs.isEmpty()) {
                mApplyCertificatesLists = dataVOs;
            }
        }
        if (mApplyCertificatesLists == null)
            mApplyCertificatesLists = new ArrayList<>();
        return mApplyCertificatesLists;
    }

    public String getAddress(String oid) {
        if (oid == null || TextUtils.isEmpty(oid))
            return null;
        BaseCommonDataVO vo = BaseCommonDataVO.findDataVOWithOid(ApplicationSet.getInstance().getAreas(), oid);
        if (vo == null)
            return "";
        return vo.getName();
    }

    /**
     * 获取性别
     *
     * @param id
     * @return
     */
    public String getSex(String id) {
        if (id == null || TextUtils.isEmpty(id))
            return "";
        BaseCommonDataVO vo = BaseCommonDataVO.findDataVOWithOid(ApplicationSet.getInstance().getDataDictionarys(), id);
        if (vo == null)
            return "";
        return vo.getName();
    }

    /**
     * 获取省名称
     *
     * @param idprovince
     * @return
     */
    public String getProvinceStr(String idprovince) {
        if (TextUtils.isEmpty(idprovince))
            return "";
        BaseCommonDataVO vo = BaseCommonDataVO.findDataVOWithOid(ApplicationSet.getInstance().getAreas(), idprovince);
        if (vo == null)
            return "";
        return vo.getName();
    }

    /**
     * 获取市名称
     *
     * @param idCity
     * @return
     */
    public String getCityStr(String idCity) {
        if (TextUtils.isEmpty(idCity))
            return "";
        BaseCommonDataVO vo = BaseCommonDataVO.findDataVOWithOid(ApplicationSet.getInstance().getAreas(), idCity);
        if (vo == null)
            return "";
        return vo.getName();
    }

    /**
     * 获取地区名称
     *
     * @param idArea
     * @return
     */
    public String getAreaStr(String idArea) {
        if (TextUtils.isEmpty(idArea))
            return "";
        BaseCommonDataVO vo = BaseCommonDataVO.findDataVOWithOid(ApplicationSet.getInstance().getAreas(), idArea);
        if (vo == null)
            return "";
        return vo.getName();
    }

    /**
     * 获取地址
     *
     * @param idpriconver
     * @param idCity
     * @param idArea
     * @return
     */
    public String getAddress(String idpriconver, String idCity, String idArea, String address) {
        String provinceStr = getProvinceStr(idpriconver);
        if (TextUtils.isEmpty(provinceStr))
            provinceStr = "";
        String cityStr = getCityStr(idCity);
        if (TextUtils.isEmpty(cityStr))
            cityStr = "";
        String areaStr = getAreaStr(idArea);
        if (TextUtils.isEmpty(areaStr))
            areaStr = "";
        if (TextUtils.isEmpty(address)) {
            return provinceStr + cityStr + areaStr;
        }

        return provinceStr + cityStr + areaStr + address;
    }

    /**
     * 获取类型名字
     * @param oid
     * @return
     */
    public String getNameWithOid(String oid) {
        if (oid == null || TextUtils.isEmpty(oid)) {
            return "";
        }
        BaseCommonDataVO cardType = BaseCommonDataVO.findDataVOWithOid(ApplicationSet.getInstance().getDataDictionarys(), oid);
        if (cardType != null) {
            return cardType.getName();
        } else {
            return "";
        }
    }


}
