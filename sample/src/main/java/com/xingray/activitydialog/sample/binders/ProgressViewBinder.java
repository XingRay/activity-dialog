package com.xingray.activitydialog.sample.binders;

import android.view.View;
import android.widget.TextView;

import com.xingray.activitydialog.ViewBinder;
import com.xingray.activitydialog.sample.R;


/**
 * Author      : leixing
 * Date        : 2017-04-17
 * Email       : leixing1012@gmail.cn
 * Version     : 0.0.1
 * <p>
 * Description : xxx
 */

@SuppressWarnings({"WeakerAccess", "RedundantCast"})
public class ProgressViewBinder extends ViewBinder {

    private CharSequence mContent;

    public ProgressViewBinder() {
        setContentView(R.layout.dialog_fragment_progress_dialog);
    }

    @Override
    protected void bindView(View rootView) {
        TextView textView = (TextView) rootView.findViewById(R.id.tv_message);
        textView.setText(mContent);
    }

    public ProgressViewBinder content(CharSequence text) {
        mContent = text;
        return this;
    }
}
