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
public class TitleContentButtonDialogAdapter extends DialogAdapter {
    private CharSequence mTitle;
    private CharSequence mContent;
    private CharSequence mButtonText;
    private View.OnClickListener mListener;

    public TitleContentButtonDialogAdapter() {
    }

    @Override
    protected int getLayoutId() {
        return R.layout.dialog_fragment_title_content_button;
    }

    @Override
    protected void bindView(View rootView) {
        TextView textView = (TextView) rootView.findViewById(R.id.tv_title);
        textView.setText(mTitle);

        TextView textView1 = (TextView) rootView.findViewById(R.id.tv_content);
        textView1.setText(mContent);

        final TextView textView2 = (TextView) rootView.findViewById(R.id.tv_button);
        textView2.setText(mButtonText);
        textView2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mListener != null) {
                    mListener.onClick(textView2);
                }
                dismiss();
            }
        });
    }

    public TitleContentButtonDialogAdapter title(CharSequence title) {
        mTitle = title;
        return this;
    }

    public TitleContentButtonDialogAdapter content(CharSequence content) {
        mContent = content;
        return this;
    }

    public TitleContentButtonDialogAdapter buttonText(CharSequence buttonText) {
        mButtonText = buttonText;
        return this;
    }

    public TitleContentButtonDialogAdapter clickListener(View.OnClickListener listener) {
        mListener = listener;
        return this;
    }
}