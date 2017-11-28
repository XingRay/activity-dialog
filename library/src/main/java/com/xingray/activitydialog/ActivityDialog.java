package com.xingray.activitydialog;

import android.content.Context;
import android.content.DialogInterface;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.view.View;
import android.view.ViewGroup;

import java.lang.ref.WeakReference;

/**
 * Author      : leixing
 * Date        : 2017-04-14
 * Email       : leixing1012@gmail.cn
 * Version     : 0.0.1
 * <p>
 * Description : dialog user for show a dialog at background
 */

public class ActivityDialog implements DialogInterface {
    private static final int DISMISS = 0xf1;
    private static final int CANCEL = 0xf2;
    private static final int SHOW = 0xf3;

    /**
     * context for dialog, it can be {@code Activity} or {@code Service} or {@code Application}
     */
    private final Context mContext;

    /**
     * layout resource id of dialog's content view to inflate
     */
    /*package*/ int mLayoutResId;

    /**
     * content view of dialog to show
     */
    /*package*/ View mContentView;

    /**
     * is dialog will dismiss when click outside of it
     */
    /*package*/ boolean mCancelable;

    /**
     * adapter for render UI by given data
     */
    /*package*/ ViewBinder mViewBinder;

    /**
     * width of the dialog
     */
    /*package*/ int mWidth;

    /**
     * height of the dialog
     */
    /*package*/ int mHeight;

    /**
     * is dialog showing
     */
    private boolean mIsShowing;

    /**
     * is dialog canceled
     */
    private boolean mIsCanceled;

    /**
     * the host where this dialog actually show at
     */
    private HostActivity mHost;

    /**
     * unique code of this dialog, update at
     */
    private long mCode;

    /**
     * handler for handle event on UI thread
     */
    private final ListenerHandler mHandler;

    private Message mShowMessage;
    private Message mCancelMessage;
    private Message mDismissMessage;

    public ActivityDialog(Context context) {
        if (context == null) {
            throw new IllegalArgumentException("context can not be null");
        }
        mContext = context;
        mCode = CodeGenerator.getInstance().next();
        mHandler = new ListenerHandler(this);

        // default values
        mCancelable = true;
        mLayoutResId = -1;
        mContentView = null;
        mWidth = ViewGroup.LayoutParams.MATCH_PARENT;
        mHeight = ViewGroup.LayoutParams.WRAP_CONTENT;
    }

    @SuppressWarnings("SameParameterValue")
    public ActivityDialog cancelable(boolean cancelable) {
        mCancelable = cancelable;
        return this;
    }

    public ActivityDialog ViewBinder(ViewBinder binder) {
        if (mViewBinder != null) {
            mViewBinder.unbindDialog();
        }
        mViewBinder = binder;
        mViewBinder.bindDialog(this);
        return this;
    }

    public ActivityDialog width(int width) {
        mWidth = width;
        return this;
    }

    public ActivityDialog height(int height) {
        mHeight = height;
        return this;
    }

    public ActivityDialog dismissListener(OnDismissListener listener) {
        if (listener != null) {
            mDismissMessage = mHandler.obtainMessage(DISMISS, listener);
        } else {
            mDismissMessage = null;
        }
        return this;
    }

    public ActivityDialog cancelListener(OnCancelListener listener) {
        if (listener != null) {
            mCancelMessage = mHandler.obtainMessage(CANCEL, listener);
        } else {
            mCancelMessage = null;
        }
        return this;
    }

    public ActivityDialog showListener(OnShowListener listener) {
        if (listener != null) {
            mShowMessage = mHandler.obtainMessage(SHOW, listener);
        } else {
            mShowMessage = null;
        }
        return this;
    }


    /**
     * show this dialog to user
     */
    public void show() {
        if (Looper.myLooper() == mHandler.getLooper()) {
            showDialog();
        } else {
            mHandler.post(new Runnable() {
                @Override
                public void run() {
                    showDialog();
                }
            });
        }
    }

    private void showDialog() {
        if (mIsShowing) {
            return;
        }

        mIsCanceled = false;

        if (mViewBinder == null) {
            throw new IllegalArgumentException("adapter can not be null");
        }

        DialogManager.getInstance().put(mCode, this);
        HostActivity.showDialog(mContext, mCode);
        sendShowMessage();
        mIsShowing = true;
    }

    @Override
    public void dismiss() {
        if (Looper.myLooper() == mHandler.getLooper()) {
            dismissDialog();
        } else {
            mHandler.post(new Runnable() {
                @Override
                public void run() {
                    dismissDialog();
                }
            });
        }
    }

    private void dismissDialog() {
        if (!mIsShowing) {
            return;
        }

        mIsShowing = false;

        if (mHost != null && !mHost.isFinishing()) {
            mHost.finish();
        }
        DialogManager.getInstance().remove(mCode);
        sendDismissMessage();
    }

    @Override
    public void cancel() {
        if (Looper.myLooper() == mHandler.getLooper()) {
            cancelDialog();
        } else {
            mHandler.post(new Runnable() {
                @Override
                public void run() {
                    cancelDialog();
                }
            });
        }
    }

    private void cancelDialog() {
        if (!mCancelable) {
            return;
        }

        if (!mIsCanceled) {
            sendCancelMessage();
        }

        dismiss();
    }

    @SuppressWarnings("WeakerAccess")
    public boolean isShowing() {
        return mIsShowing;
    }

    /*package*/ void bindHost(HostActivity host) {
        mHost = host;
    }

    /*package*/ void unbindHost() {
        mHost = null;
    }

    private void sendShowMessage() {
        if (mShowMessage != null) {
            Message.obtain(mShowMessage).sendToTarget();
        }
    }

    private void sendDismissMessage() {
        if (mDismissMessage != null) {
            Message.obtain(mDismissMessage).sendToTarget();
        }
    }

    private void sendCancelMessage() {
        if (mCancelMessage != null) {
            Message.obtain(mCancelMessage).sendToTarget();
        }
    }

    public ActivityDialog setContentView(int layoutResId) {
        mLayoutResId = layoutResId;
        mContentView = null;
        requestRefreshHostContentView();
        return this;
    }

    @SuppressWarnings("unused")
    public ActivityDialog setContentView(View contentView) {
        mContentView = contentView;
        mLayoutResId = -1;
        requestRefreshHostContentView();
        return this;
    }

    private void requestRefreshHostContentView() {
        if (mHost != null) {
            mHost.refreshContentView();
        }
    }

    private static final class ListenerHandler extends Handler {
        private WeakReference<DialogInterface> mDialog;

        private ListenerHandler(DialogInterface dialog) {
            super(Looper.getMainLooper());
            mDialog = new WeakReference<>(dialog);
        }

        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case DISMISS:
                    ((OnDismissListener) msg.obj).onDismiss(mDialog.get());
                    break;
                case CANCEL:
                    ((OnCancelListener) msg.obj).onCancel(mDialog.get());
                    break;
                case SHOW:
                    ((OnShowListener) msg.obj).onShow(mDialog.get());
                    break;
                default:
            }
        }
    }
}