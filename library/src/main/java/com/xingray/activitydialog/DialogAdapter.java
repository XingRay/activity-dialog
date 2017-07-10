package com.xingray.activitydialog;

import android.view.View;

/**
 * Author      : leixing
 * Date        : 2017-04-14
 * Email       : leixing1012@gmail.cn
 * Version     : 0.0.1
 * <p>
 * Description : adapter for render UI for {@link ActivityDialog}
 */

public abstract class DialogAdapter {
    private ActivityDialog mDialog;

    public DialogAdapter() {
    }

    protected abstract int getLayoutId();

    protected abstract void bindView(View rootView);

    final void bindDialog(ActivityDialog dialog) {
        mDialog = dialog;
    }

    void unbindDialog() {
        mDialog = null;
    }

    protected final void dismiss() {
        if (mDialog != null) {
            mDialog.dismiss();
        }
    }
}
