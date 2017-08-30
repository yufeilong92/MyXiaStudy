package com.lawyee.appservice.ui.org;

import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.andview.refreshview.XRefreshView;
import com.lawyee.appservice.R;
import com.lawyee.appservice.ui.BaseActivity;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class JalawLawyerSelectActivity extends BaseActivity {
    @Bind(R.id.rlv_select_lalaw)
    RecyclerView rlvSelectLalaw;
    @Bind(R.id.select_content_empty_tv)
    TextView selectContentEmptyTv;
    @Bind(R.id.xfv_lalaw)
    XRefreshView mXrefreshView;
    /**
     * 数据是否处理中，用于服务端请求数据时标识，防止重复申请
     */
    boolean mInProgess;

    /**
     * 数据列表
     */
    private ArrayList mDataList;
    @Override
    protected void initContentView(Bundle savedInstanceState) {
        setContentView(R.layout.activity_jalaw_lawyer_select);
        ButterKnife.bind(this);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    /**
     * 刷新控件设置
     */
    private void initRefresh(View view) {
        //设置是否能上拉刷新
        mXrefreshView.setPullLoadEnable(false);
        //设置是否下拉刷新
        mXrefreshView.setPullRefreshEnable(true);
        mXrefreshView.restoreLastRefreshTime(0l);
        mXrefreshView.setEmptyView(view.findViewById(R.id.jaaidorg_content_empty_tv));
        mXrefreshView.setXRefreshViewListener(new XRefreshView.SimpleXRefreshListener() {
            @Override
            public void onRefresh(boolean isPullDown) {
                LoadNewData();
            }



            @Override
            public void onLoadMore(boolean isSilence) {
                loadMoreDatas();
            }
        });
    }

    private void LoadNewData() {

    }

    private void loadMoreDatas() {
    }

    @OnClick({R.id.rlv_select_lalaw, R.id.select_content_empty_tv})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.rlv_select_lalaw:
                break;
            case R.id.select_content_empty_tv:
                break;
        }
    }
}
