package com.lawyee.appservice.ui;

import android.Manifest;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.afollestad.materialdialogs.MaterialDialog;
import com.lawyee.appservice.R;
import com.lawyee.appservice.config.ApplicationSet;
import com.lawyee.appservice.ui.org.OrgApplyListActivity;

import net.lawyee.mobilelib.Constants;
import net.lawyee.mobilelib.utils.FileUtil;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;


/**
 * All rights Reserved, Designed By www.lawyee.com
 *
 * @version V1.0.xxxxxxxx
 * @Title: 主Activity
 * @Package com.lawyee.apppublic.ui
 * @Description: 主activity
 * @author:wuzhu
 * @date: 2017-04-28
 * @verdescript 1.0 20170428  wuzhu 初建
 * @Copyright: 2017 www.lawyee.com Inc. All rights reserved.
 * 注意：本内容仅限于北京法意科技有限公司内部传阅，禁止外泄以及用于其他的商业目
 */
public class MainActivity extends BaseActivity {

    @Bind(R.id.btn_go)
    Button btnGo;
    @Bind(R.id.btn_to_Login)
    Button btnToLogin;

    @Override
    protected void initContentView(Bundle savedInstanceState) {
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        initView();
        checkStorage();

    }

    public void initView() {
    }


    /**
     * 检查是否有访问存储卡的权限
     */
    public void checkStorage() {
        String[] perms = {Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE,};
        performCodeWithPermission(getString(R.string.rationale_storage), RC_STORAGE_PERM, perms, new PermissionCallback() {
            @Override
            public void hasPermission(List<String> allPerms) {
                //创建缓存文件夹
                FileUtil.isExistFile(Constants.getDataStoreDir(getApplicationContext()));

            }

            @Override
            public void noPermission(List<String> deniedPerms, List<String> grantedPerms, Boolean hasPermanentlyDenied) {
                if (hasPermanentlyDenied) {
                    alertAppSetPermission(getString(R.string.rationale_ask_again), RC_SETTINGS_SCREEN);
                }
            }
        });
    }

    public void onToolbarClick(View view) {
        new MaterialDialog.Builder(this)
                .iconRes(R.mipmap.ic_launcher)
                .limitIconToDefaultSize() // limits the displayed icon size to 48dp
                .title("使用本风格的对话框")
                .content("使用此对话框，简单方便")
                .positiveText("YES")
                .negativeText("No")
                .show();
    }



    @Override
    protected void onResume() {
        super.onResume();
        new Thread(new Runnable() {
            @Override
            public void run() {
                if(ApplicationSet.getInstance().getDataDictionarys(false)==null||
                        ApplicationSet.getInstance().getDataDictionarys(false).isEmpty()) {
                    ApplicationSet.initDataDictionary();
                    ApplicationSet.initAreas();
                }
            }
        }).start();

    }

    @OnClick(R.id.btn_go)
    public void onClick() {
        startActivity(new Intent(MainActivity.this,OrgApplyListActivity.class));

    }

    @OnClick(R.id.btn_to_Login)
    public void ToLogin() {
        startActivity(new Intent(MainActivity.this, LoginActivity.class));

    }
}
