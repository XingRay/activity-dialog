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
public class TitleContentTwoButtonViewBinder extends ViewBinder {

    private CharSequence mTitle;
    private CharSequence mContent;
    private CharSequence mLeftText;
    private CharSequence mRightText;
    private View.OnClickListener mLeftListener;
    private View.OnClickListener mRightListener;

    public TitleContentTwoButtonViewBinder() {
        setContentView(R.layout.dialog_activity_title_content_two_button);
    }

    @Override
    protected void bindView(View rootView) {
        TextView textView = (TextView) rootView.findViewById(R.id.tv_title);
        textView.setText(mTitle);

        TextView textView1 = (TextView) rootView.findViewById(R.id.tv_content);
        textView1.setText(mContent);

        final TextView textView2 = (TextView) rootView.findViewById(R.id.tv_left_button);
        textView2.setText(mLeftText);
        textView2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mLeftListener != null) {
                    mLeftListener.onClick(textView2);
                }
                dismiss();
            }
        });

        final TextView textView3 = (TextView) rootView.findViewById(R.id.tv_right_button);
        textView3.setText(mRightText);
        textView3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mRightListener != null) {
                    mRightListener.onClick(textView3);
                }
                dismiss();
            }
        });
    }

    public TitleContentTwoButtonViewBinder title(CharSequence title) {
        mTitle = title;
        return this;
    }

    public TitleContentTwoButtonViewBinder content(CharSequence content) {
        mContent = content;
        return this;
    }

    public TitleContentTwoButtonViewBinder leftText(CharSequence leftButtonText) {
        mLeftText = leftButtonText;
        return this;
    }

    public TitleContentTwoButtonViewBinder rightText(CharSequence rightButtonText) {
        mRightText = rightButtonText;
        return this;
    }

    public TitleContentTwoButtonViewBinder leftListener(View.OnClickListener listener) {
        mLeftListener = listener;
        return this;
    }

    public TitleContentTwoButtonViewBinder rightListener(View.OnClickListener listener) {
        mRightListener = listener;
        return this;
    }
}
