package com.lawyee.appservice.ui.org;

import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.andview.refreshview.XRefreshView;
import com.andview.refreshview.XRefreshViewFooter;
import com.lawyee.appservice.R;
import com.lawyee.appservice.adpater.OrgRlvAdapter;
import com.lawyee.appservice.ui.BaseActivity;
import com.lawyee.appservice.vo.JaaidApplyVO;
import com.lawyee.appservice.widget.RecycleViewDivider;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * All rights Reserved, Designed By www.lawyee.com
 * @Title:  JaaidListActivity.java
 * @Package com.lawyee.appservice.ui
 * @Description:    法援业务信息列表页
 * @author: YFL
 * @date:   2017/7/3 17:00
 * @version V 1.0 xxxxxxxx
 * @verdescript  版本号 修改时间  修改人 修改的概要说明
 * @Copyright: 2017/7/3 www.lawyee.com Inc. All rights reserved.
 * 注意：本内容仅限于北京法意科技有限公司内部传阅，禁止外泄以及用于其他的商业目
 */
public class OrgApplyListActivity extends BaseActivity {
    /**
     * 传递参数
     */
    public static final String CSTR_EXTRA_TYPE_STR = "jaaidtype";
    @Bind(R.id.rlv_content)
    RecyclerView mRlvContent;
    @Bind(R.id.jaaidorg_content_empty_tv)
    TextView mJaaidorgContentEmptyTv;
    /**
     * 数据是否处理中，用于服务端请求数据时标识，防止重复申请
     */
    boolean mInProgess;
    /**
     * 数据列表
     */
    ArrayList mDataList = null;
    @Bind(R.id.mXrefreshView)
    XRefreshView mXrefreshView;


    @Override
    protected void initContentView(Bundle savedInstanceState) {
        setContentView(R.layout.activity_orgapply_list);
        ButterKnife.bind(this);
        initRefresh();
        loadData();
    }


    private void initRefresh() {
        //设置是否可以上拉刷新
        mXrefreshView.setPullLoadEnable(false);
        mXrefreshView.setPullRefreshEnable(true);
        mXrefreshView.setEmptyView(findViewById(R.id.jaaidorg_content_empty_tv));
        mXrefreshView.setXRefreshViewListener(new XRefreshView.SimpleXRefreshListener() {
            @Override
            public void onRefresh(boolean isPullDown) {
                LoadNewData();
            }

            @Override
            public void onLoadMore(boolean isSilence) {
                LoadMoreData();
            }
        });
    }


    private void LoadNewData() {
        mXrefreshView.stopRefresh();
    }
    private void LoadMoreData() {}

    /**
     * 清楚数据
     */
    private void clearDataList() {
        if (mDataList == null) {
            mDataList = new ArrayList();
        } else {
            mDataList.clear();
        }
    }


    /**
     * 增加列表数据
     *
     * @param list
     */
    @SuppressWarnings({"unchecked"})
    private void addDataList(List<?> list) {
        if (mDataList == null)
            clearDataList();
        if (list == null || list.isEmpty())
            return;
        mDataList.addAll(list);
    }

    private void loadData() {
        clearDataList();
        ArrayList<JaaidApplyVO> json = getJson();
        addDataList(json);
        setAdapterData();
        mXrefreshView.stopRefresh();
    }


    private ArrayList<JaaidApplyVO> getJson() {
        ArrayList<JaaidApplyVO> list = new ArrayList<>();
        JaaidApplyVO applyVO = new JaaidApplyVO();
        applyVO.setApplyDate("2012-12-8");
        applyVO.setAuditStatus(1);
        applyVO.setFeedbackStatus(1);
        applyVO.setName("小米");
        applyVO.setSerialNO("1111111111111111");
        applyVO.setOid("555555555555555");
        list.add(applyVO);
        JaaidApplyVO applyVO1 = new JaaidApplyVO();
        applyVO1.setApplyDate("2012-122-8");
        applyVO1.setAuditStatus(1);
        applyVO1.setFeedbackStatus(1);
        applyVO1.setName("小米2");
        applyVO1.setSerialNO("11111111111111211");
        applyVO1.setOid("5555555555552555");
        list.add(applyVO1);
        return list;
    }

    private void setAdapterData() {
        OrgRlvAdapter rlvAdapter = new OrgRlvAdapter(mDataList, this, OrgRlvAdapter.JAMEDTYPE);
        GridLayoutManager manager = new GridLayoutManager(this, 1);
        mRlvContent.setLayoutManager(manager);
        mRlvContent.addItemDecoration(new RecycleViewDivider(OrgApplyListActivity.this, GridLayoutManager.VERTICAL, R.drawable.bg_rlv_diving));
        rlvAdapter.setCustomLoadMoreView(new XRefreshViewFooter(this));
        mRlvContent.setAdapter(rlvAdapter);
        rlvAdapter.setOnRecyclerViewItemChickListener(new OrgRlvAdapter.OnRecyclerViewItemChickListener() {
            @Override
            public void OnItemChickListener(View view, JaaidApplyVO vo) {
   /*             Intent intent = new Intent(JaaidListActivity.this, JanotaApplyNBVActivity.class);
                Bundle bundle = new Bundle();
                bundle.putString(JaaidApplyActivity.CSTR_EXTRA_APPLYOID,vo.getOid());
                bundle.putString(JaaidApplyActivity.CSTR_EXTRA_APPLYSERIANO,vo.getSerialNO());
                bundle.putInt(JaaidApplyActivity.CSTR_EXTRA_AUDITSTATUS,vo.getAuditStatus());
                bundle.putInt(JaaidApplyActivity.CSTR_EXTRA_FEEDBCAKSTATUS,vo.getFeedbackStatus());
                intent.putExtra(JaaidApplyActivity.CSTR_EXTRA_TITLE_STR,getString(R.string.jaaid_Business));
                intent.putExtras(bundle);
                startActivity(intent);*/
            }
        });
    }

}