package com.lawyee.appservice.adpater;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.andview.refreshview.recyclerview.BaseRecyclerAdapter;
import com.lawyee.appservice.R;
import com.lawyee.appservice.ui.org.JaaidApplyActivity;
import com.lawyee.appservice.ui.org.JanotaApplyNBVActivity;
import com.lawyee.appservice.vo.JaaidApplyVO;

import net.lawyee.mobilelib.utils.T;

import java.util.List;

/**
 * All rights Reserved, Designed By www.lawyee.com
 *
 * @version V 1.0 xxxxxxxx
 * @Title: LawerApp
 * @Package com.lawyee.appservice.adpater
 * @Description: $todo$
 * @author: YFL
 * @date: 2017/6/28 12:36
 * @verdescript 版本号 修改时间  修改人 修改的概要说明
 * @Copyright: 2017 www.lawyee.com Inc. All rights reserved.
 * 注意：本内容仅限于北京法意科技有限公司内部传阅，禁止外泄以及用于其他的商业目
 */
public class OrgRlvAdapter extends BaseRecyclerAdapter<OrgRlvAdapter.ViewHolder> implements View.OnClickListener {


    private List<?> mData;
    private Context mContext;
    private final LayoutInflater mInflater;

    private OnRecyclerViewItemChickListener onRecyclerViewItemChickListener = null;

    public static String JAAIDTYPE = "jaaidtype";

    public static String JAMEDTYPE = "jamedtype";

    public static String JANOTATYPE = "janotadtype";

    public static String JAAUTHTYPE = "jaauthtype";

    private String mType;


    public OrgRlvAdapter(List<?> mData, Context mContext, String type) {
        this.mData = mData;
        this.mContext = mContext;
        mInflater = LayoutInflater.from(mContext);
        mType = type;
    }

    @Override
    public void onClick(View v) {
        if (onRecyclerViewItemChickListener != null) {
            onRecyclerViewItemChickListener.OnItemChickListener(v, (JaaidApplyVO) v.getTag());
        }
    }

    public interface OnRecyclerViewItemChickListener {
        void OnItemChickListener(View view, JaaidApplyVO vo);
    }

    public OnRecyclerViewItemChickListener getOnRecyclerViewItemChickListener() {
        return onRecyclerViewItemChickListener;
    }

    public void setOnRecyclerViewItemChickListener(OnRecyclerViewItemChickListener onRecyclerViewItemChickListener) {
        this.onRecyclerViewItemChickListener = onRecyclerViewItemChickListener;
    }

    @Override
    public ViewHolder getViewHolder(View view) {
        return new ViewHolder(view);
    }


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i, boolean b) {
        View view = mInflater.inflate(R.layout.item_jaaid, null);
        view.setOnClickListener(this);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, int i, boolean b) {
        if (mType.equals(JAAIDTYPE)) {
            jaaidData(viewHolder, i);
        } else if (mType.equals(JAAUTHTYPE)) {
            jaauthData(viewHolder, i);
        } else if (mType.equals(JAMEDTYPE)) {
            jamedData(viewHolder, i);
        } else if (mType.equals(JANOTATYPE)) {
            janotaData(viewHolder, i);
        }

    }

    /**
     * 公证
     *
     * @param viewHolder
     * @param i
     */
    private void janotaData(ViewHolder viewHolder, int i) {
        final JaaidApplyVO vo = (JaaidApplyVO) mData.get(i);
        viewHolder.mLinearRight.setVisibility(View.INVISIBLE);
        viewHolder.mTvApplytime.setText(vo.getApplyDate());
        viewHolder.mTvApplyname.setText(vo.getName());
        viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("===", "onClick: " + vo.getName());
                startIntent(vo);
            }
        });
    }

    /**
     * 人民
     *
     * @param viewHolder
     * @param i
     */
    private void jamedData(ViewHolder viewHolder, int i) {
        final JaaidApplyVO vo = (JaaidApplyVO) mData.get(i);
        viewHolder.mBtnApplyfeedback.setVisibility(View.INVISIBLE);
        viewHolder.mTvApplytime.setText(vo.getApplyDate());
        viewHolder.mTvApplyname.setText(vo.getName());
        int status = vo.getFeedbackStatus();
        switch (status) {
            case 0:
                viewHolder.mBtnApplyfeedback.setText("未反馈");
                break;
            case 1:
                viewHolder.mBtnApplyfeedback.setText("已反馈");
                break;

            default:
                break;

        }
        viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("===", "onClick: " + vo.getName());
                startIntent(vo);
            }
        });
    }

    /**
     * 鉴定
     *
     * @param viewHolder
     * @param i
     */
    private void jaauthData(ViewHolder viewHolder, int i) {
        final JaaidApplyVO vo = (JaaidApplyVO) mData.get(i);
        viewHolder.mLinearRight.setVisibility(View.INVISIBLE);
        viewHolder.mTvApplytime.setText(vo.getApplyDate());
        viewHolder.mTvApplyname.setText(vo.getName());
        viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("===", "onClick: " + vo.getName());
                startIntent(vo);
            }
        });
    }

    /**
     * 法援
     *
     * @param viewHolder
     * @param i
     */
    private void jaaidData(ViewHolder viewHolder, int i) {
        final JaaidApplyVO vo = (JaaidApplyVO) mData.get(i);
        viewHolder.mTvApplytime.setText(vo.getApplyDate());
        viewHolder.mTvApplyname.setText(vo.getName());
        int status = vo.getFeedbackStatus();
        switch (status) {
            case 0:
                viewHolder.mBtnApplyfeedback.setText("未反馈");
                break;
            case 1:
                viewHolder.mBtnApplyfeedback.setText("已反馈");
                break;

            default:
                break;
        }
        int auditStatus = vo.getAuditStatus();
        switch (auditStatus) {
            case -1:
                viewHolder.mTvApplystatus.setText("审核未通过");
                break;
            case 0:
                viewHolder.mTvApplystatus.setText("未审核");
                break;
            case 1:
                viewHolder.mTvApplystatus.setText("审核通过");
                break;
            default:
                break;
        }
        viewHolder.itemView.setTag(vo);
        viewHolder.mTvApplystatus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        viewHolder.mBtnApplyfeedback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                T.showShort(mContext, "按钮");
            }
        });
        viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("===", "onClick: " + vo.getName());
                startIntent(vo);
            }
        });
    }


    @Override
    public int getAdapterItemCount() {
        return mData.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView mTvApplytime;
        public TextView mTvApplyname;
        public TextView mTvApplystatus;
        public Button mBtnApplyfeedback;
        private LinearLayout mLinearRight;

        public ViewHolder(View itemView) {
            super(itemView);
            this.mTvApplytime = (TextView) itemView.findViewById(R.id.tv_applytime);
            this.mTvApplyname = (TextView) itemView.findViewById(R.id.tv_applyname);
            this.mTvApplystatus = (TextView) itemView.findViewById(R.id.tv_applystatus);
            this.mBtnApplyfeedback = (Button) itemView.findViewById(R.id.btn_applyfeedback);
            this.mLinearRight = (LinearLayout) itemView.findViewById(R.id.linear_right);
        }
    }


    private void startIntent(JaaidApplyVO vo) {
        Intent intent = new Intent(mContext, JanotaApplyNBVActivity.class);
        Bundle bundle = new Bundle();
        bundle.putString(JaaidApplyActivity.CSTR_EXTRA_APPLYOID, vo.getOid());
        bundle.putString(JaaidApplyActivity.CSTR_EXTRA_APPLYSERIANO, vo.getSerialNO());
        bundle.putInt(JaaidApplyActivity.CSTR_EXTRA_AUDITSTATUS, vo.getAuditStatus());
        bundle.putInt(JaaidApplyActivity.CSTR_EXTRA_FEEDBCAKSTATUS, vo.getFeedbackStatus());
        intent.putExtra(JaaidApplyActivity.CSTR_EXTRA_TITLE_STR, mContext.getString(R.string.jaaid_Business));
        intent.putExtras(bundle);
        mContext.startActivity(intent);
    }
}
