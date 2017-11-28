package com.xingray.activitydialog;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
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

    private ActivityDialog mDialog;
    private Context mContext;

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
        if (mDialog == null) {
            return false;
        }
        //noinspection SimplifiableIfStatement
        if (mDialog.mLayoutResId == -1 && mDialog.mContentView == null) {
            return false;
        }
        return mDialog.isShowing();
    }

    private void initVariables() {
        mContext = getApplicationContext();
        mDialog.bindHost(this);
    }

    private void initView() {
        getWindow().setFlags(
                WindowManager.LayoutParams.FLAG_NOT_TOUCH_MODAL | WindowManager.LayoutParams.FLAG_WATCH_OUTSIDE_TOUCH,
                WindowManager.LayoutParams.FLAG_NOT_TOUCH_MODAL | WindowManager.LayoutParams.FLAG_WATCH_OUTSIDE_TOUCH);
        setFinishOnTouchOutside(mDialog.mCancelable);

        refreshContentView();
    }

    public void refreshContentView() {
        ActivityDialog dialog = mDialog;
        if (dialog == null) {
            throw new IllegalStateException("ActivityDialog can not be null");
        }

        ViewBinder binder = dialog.mViewBinder;
        if (binder == null) {
            throw new IllegalStateException("ViewBinder of ActivityDialog can not be null, must invoke ViewBinder() before show dialog");
        }

        if (dialog.mLayoutResId > 0) {
            binder.setContentView(dialog.mLayoutResId);
        } else {
            binder.setContentView(dialog.mContentView);
        }

        View contentView = binder.inflateContentView(mContext);
        if (contentView == null) {
            throw new IllegalStateException("can not inflate view");
        }

        ViewGroup.LayoutParams layoutParams = new ViewGroup.LayoutParams(dialog.mWidth, dialog.mHeight);
        setContentView(contentView, layoutParams);
        binder.bindView(contentView);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mDialog != null) {
            mDialog.unbindHost();
        }
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
