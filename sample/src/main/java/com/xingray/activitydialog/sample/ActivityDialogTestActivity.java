package com.xingray.activitydialog.sample;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.TextView;


/**
 * Author      : leixing
 * Date        : 2017-04-14
 * Email       : leixing1012@gmail.cn
 * Version     : 0.0.1
 * <p>
 * Description : xxx
 */

public class ActivityDialogTestActivity extends Activity implements View.OnClickListener {
    TextView tvTest01;
    TextView tvTest02;
    private Context mContext;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initVariables();
        initView();
        loadData();
    }

    protected void initVariables() {
        mContext = this.getApplicationContext();
    }

    @SuppressWarnings("RedundantCast")
    protected void initView() {
        setContentView(R.layout.activity_dialog_activity_test);

        tvTest01 = (TextView) findViewById(R.id.tv_test01);
        tvTest01.setOnClickListener(this);
        tvTest02 = (TextView) findViewById(R.id.tv_test02);
    }

    protected void loadData() {

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    private void startTestService() {
        Intent intent = new Intent();
        intent.setClass(mContext, DialogActivityTestService.class);
        startService(intent);
    }

    @Override
    public void onClick(View view) {
        if (view == tvTest01) {
            test01();
        } else if (view == tvTest02) {
            test02();
        }
    }

    private void test01() {
        startTestService();
    }

    private void test02() {

    }
}
