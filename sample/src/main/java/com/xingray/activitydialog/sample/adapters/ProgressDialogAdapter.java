package com.xingray.activitydialog.sample.adapters;

import android.view.View;
import android.widget.TextView;

import com.xingray.activitydialog.DialogAdapter;
import com.xingray.activitydialog.R;


/**
 * Author      : leixing
 * Date        : 2017-04-17
 * Email       : leixing1012@gmail.cn
 * Version     : 0.0.1
 * <p>
 * Description : xxx
 */

@SuppressWarnings({"WeakerAccess", "RedundantCast"})
public class ProgressDialogAdapter extends DialogAdapter {

    private CharSequence mContent;

    public ProgressDialogAdapter() {
        super();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.dialog_fragment_progress_dialog;
    }

    @Override
    protected void bindView(View rootView) {
        TextView textView = (TextView) rootView.findViewById(R.id.tv_message);
        textView.setText(mContent);
    }

    public ProgressDialogAdapter content(CharSequence text) {
        mContent = text;
        return this;
    }
}
