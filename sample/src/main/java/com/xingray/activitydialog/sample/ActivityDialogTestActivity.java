package com.xingray.activitydialog.sample;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;


/**
 * Author      : leixing
 * Date        : 2017-04-14
 * Email       : leixing1012@gmail.cn
 * Version     : 0.0.1
 * <p>
 * Description : xxx
 */

public class ActivityDialogTestActivity extends Activity implements View.OnClickListener {
    Button btTest01;
    Button bvTest02;
    private Context mContext;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initVariables();
        initView();
    }

    protected void initVariables() {
        mContext = this.getApplicationContext();
    }

    @SuppressWarnings("RedundantCast")
    protected void initView() {
        setContentView(R.layout.activity_dialog_activity_test);

        btTest01 = (Button) findViewById(R.id.bt_test01);
        btTest01.setOnClickListener(this);

        bvTest02 = (Button) findViewById(R.id.bt_test02);
        bvTest02.setOnClickListener(this);
    }

    private void startTestService() {
        Intent intent = new Intent();
        intent.setClass(mContext, DialogActivityTestService.class);
        startService(intent);
    }

    @Override
    public void onClick(View view) {
        if (view == btTest01) {
            test01();
        } else if (view == bvTest02) {
            test02();
        }
    }

    private void test01() {
        startTestService();
    }

    private void test02() {
        // TODO: 2017-08-04 show dialog here
    }
}
