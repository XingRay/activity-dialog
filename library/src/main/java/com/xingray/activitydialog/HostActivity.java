package com.xingray.activitydialog;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;

/**
 * Author      : leixing
 * Date        : 2017-02-20
 * Email       : leixing1012@gmail.cn
 * Version     : 0.0.1
 * <p>
 * Description : host activity for dialog
 */

public class HostActivity extends Activity {
    public static final String CODE = "code";

    private LayoutInflater mInflater;
    private ActivityDialog mDialog;

    static void showDialog(Context context, long code) {
        Intent intent = new Intent();
        intent.putExtra(CODE, code);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.setClass(context, HostActivity.class);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (!isParamsValid(getIntent())) {
            finish();
            return;
        }
        initVariables();
        initView();
    }

    private boolean isParamsValid(Intent intent) {
        long code = intent.getLongExtra(CODE, -1);
        mDialog = DialogManager.getInstance().get(code);
        return mDialog != null && mDialog.isShowing();
    }

    private void initVariables() {
        mDialog.bindHost(this);
        mInflater = LayoutInflater.from(getApplicationContext());
    }

    private void initView() {
        getWindow().setFlags(
                WindowManager.LayoutParams.FLAG_NOT_TOUCH_MODAL | WindowManager.LayoutParams.FLAG_WATCH_OUTSIDE_TOUCH,
                WindowManager.LayoutParams.FLAG_NOT_TOUCH_MODAL | WindowManager.LayoutParams.FLAG_WATCH_OUTSIDE_TOUCH);

        setFinishOnTouchOutside(mDialog.mCancelable);
        ViewGroup.LayoutParams layoutParams = new ViewGroup.LayoutParams(mDialog.mWidth, mDialog.mHeight);
        View rootView = mInflater.inflate(mDialog.mAdapter.getLayoutId(), null, false);
        setContentView(rootView, layoutParams);
        mDialog.mAdapter.bindView(rootView);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mDialog.unbindHost();
    }

    @Override
    public void onBackPressed() {
        mDialog.cancel();
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (MotionEvent.ACTION_OUTSIDE == event.getAction()) {
            mDialog.cancel();
            return true;
        }

        return super.onTouchEvent(event);
    }
}
