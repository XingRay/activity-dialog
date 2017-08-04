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
public class TitleContentButtonViewBinder extends ViewBinder {
    private CharSequence mTitle;
    private CharSequence mContent;
    private CharSequence mButtonText;
    private View.OnClickListener mListener;

    public TitleContentButtonViewBinder() {
        setContentView(R.layout.dialog_fragment_title_content_button);
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

    public TitleContentButtonViewBinder title(CharSequence title) {
        mTitle = title;
        return this;
    }

    public TitleContentButtonViewBinder content(CharSequence content) {
        mContent = content;
        return this;
    }

    public TitleContentButtonViewBinder buttonText(CharSequence buttonText) {
        mButtonText = buttonText;
        return this;
    }

    public TitleContentButtonViewBinder clickListener(View.OnClickListener listener) {
        mListener = listener;
        return this;
    }
}
