package com.xingray.activitydialog;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;

/**
 * Author      : leixing
 * Date        : 2017-04-14
 * Email       : leixing1012@gmail.cn
 * Version     : 0.0.1
 * <p>
 * Description : binder of UI for {@link ActivityDialog}
 */

public abstract class ViewBinder {
    private ActivityDialog mDialog;
    private View mContentView;
    private int mLayoutResId;
    private LayoutInflater mInflater;

    @SuppressWarnings("WeakerAccess")
    public ViewBinder() {
    }

    @SuppressWarnings("WeakerAccess")
    protected final void setContentView(View contentView) {
        mContentView = contentView;
        mLayoutResId = -1;
    }

    @SuppressWarnings("WeakerAccess")
    protected final void setContentView(int layoutResId) {
        mLayoutResId = layoutResId;
        mContentView = null;
    }

    protected abstract void bindView(View rootView);

    /*package*/
    final void bindDialog(ActivityDialog dialog) {
        mDialog = dialog;
    }

    /*package*/
    final void unbindDialog() {
        mDialog = null;
    }

    protected final void dismiss() {
        if (mDialog != null) {
            mDialog.dismiss();
        }
    }

    /*package*/
    final View inflateContentView(Context context) {
        if (mLayoutResId > 0) {
            LayoutInflater inflater = getLayoutInflater(context);
            return inflater.inflate(mLayoutResId, null, false);
        } else {
            return mContentView;
        }
    }

    private LayoutInflater getLayoutInflater(Context context) {
        if (mInflater == null) {
            mInflater = LayoutInflater.from(context);
        }

        return mInflater;
    }
}
