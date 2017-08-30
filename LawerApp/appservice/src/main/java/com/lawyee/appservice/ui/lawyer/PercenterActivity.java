package com.lawyee.appservice.ui.lawyer;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.lawyee.appservice.R;
import com.lawyee.appservice.ui.BaseActivity;
import com.nostra13.universalimageloader.core.ImageLoader;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import me.nereo.multi_image_selector.MultiImageSelectorActivity;

public class PercenterActivity extends BaseActivity {

    public static final int IMAGE_PICKER = 100;
    @Bind(R.id.tv_save)
    TextView mTvSave;
    @Bind(R.id.iv_head)
    ImageView mIvHead;
    @Bind(R.id.tv_name)
    TextView mTvName;
    @Bind(R.id.tv_sex)
    TextView mTvSex;
    @Bind(R.id.tv_phone)
    TextView mTvPhone;
    @Bind(R.id.tv_lawfirm)
    TextView mTvLawfirm;
    @Bind(R.id.et_email)
    EditText mEtEmail;
    @Bind(R.id.tv_speciality_tip1)
    TextView mTvSpecialityTip1;
    @Bind(R.id.tv_speciality_tip2)
    TextView mTvSpecialityTip2;
    @Bind(R.id.tv_speciality_tip3)
    TextView mTvSpecialityTip3;
    @Bind(R.id.ll_speciality)
    LinearLayout mLlSpeciality;
    @Bind(R.id.tv_speciality_tip4)
    TextView mTvSpecialityTip4;
    @Bind(R.id.tv_speciality_tip5)
    TextView mTvSpecialityTip5;
    @Bind(R.id.rl_head)
    RelativeLayout mRlHead;
    private Context mContext;
    @Override
    protected void initContentView(Bundle savedInstanceState) {
        setContentView(R.layout.activity_percenter);
        ButterKnife.bind(this);
        mContext=this;
    }

    @OnClick(R.id.iv_head)
    public  void onClick(View view){
        Intent intent = new Intent(mContext, MultiImageSelectorActivity.class);
        intent.putExtra(MultiImageSelectorActivity.EXTRA_SHOW_CAMERA, true);
        intent.putExtra(MultiImageSelectorActivity.EXTRA_SELECT_COUNT, 1);
        intent.putExtra(MultiImageSelectorActivity.EXTRA_SELECT_MODE, MultiImageSelectorActivity.MODE_SINGLE);
        startActivityForResult(intent, IMAGE_PICKER);
    }
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode){
            case IMAGE_PICKER://图片回调
                if(data==null||data.getStringArrayListExtra(MultiImageSelectorActivity.EXTRA_RESULT)==null){
                    return;
                }
                ArrayList<String> path = data.getStringArrayListExtra(MultiImageSelectorActivity.EXTRA_RESULT);
                if (path != null||path.size()>0) {
                    //是否发送原图
                    for (String name : path
                            ) {
                        //   sendImagesMsg(name,false);
                        ImageLoader loader = ImageLoader.getInstance();
                        loader.displayImage(Uri.parse("file://" + name).toString() ,mIvHead);
                    }
                }
                break;
        }
    }

}
