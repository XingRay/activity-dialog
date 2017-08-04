package com.xingray.activitydialog.sample;

import android.app.Service;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.xingray.activitydialog.ActivityDialog;
import com.xingray.activitydialog.ViewBinder;


/**
 * Author      : leixing
 * Date        : 2017-04-14
 * Email       : leixing1012@gmail.cn
 * Version     : 0.0.1
 * <p>
 * Description : xxx
 */

public class DialogActivityTestService extends Service {
    private static final String TAG = "DialogService";
    private Context mContext;

    @Override
    public void onCreate() {
        super.onCreate();
        Log.i(TAG, "onCreate");
        mContext = this.getApplicationContext();
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        Log.i(TAG, "onBind");
        return null;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.i(TAG, "onStartCommand");

        test01();
        return super.onStartCommand(intent, flags, startId);
    }

    private void test01() {
        new ActivityDialog(this.getApplicationContext())
                .cancelable(true)
                .name("test")
                .width(ViewGroup.LayoutParams.MATCH_PARENT)
                .height(ViewGroup.LayoutParams.WRAP_CONTENT)
                .priority(10)
                .setContentView(R.layout.layout_dialog_alert)
                .ViewBinder(new ViewBinder() {
                    @Override
                    protected void bindView(View rootView) {
                        TextView tvMsg = (TextView) rootView.findViewById(R.id.tv_msg);
                        tvMsg.setText("test01 in service");

                        TextView tvConfirm = (TextView) rootView.findViewById(R.id.tv_confirm);
                        tvConfirm.setText("ok");

                        tvConfirm.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                dismiss();
                                Log.i("test", "onClick");
                            }
                        });
                    }
                })
                .showListener(new DialogInterface.OnShowListener() {
                    @Override
                    public void onShow(DialogInterface dialog) {
                        Log.i("test", "onShow");
                    }
                })
                .dismissListener(new DialogInterface.OnDismissListener() {
                    @Override
                    public void onDismiss(DialogInterface dialog) {
                        Log.i("test", "onDismiss");
                    }
                })
                .cancelListener(new DialogInterface.OnCancelListener() {
                    @Override
                    public void onCancel(DialogInterface dialog) {
                        Log.i("test", "onCancel");
                    }
                })
                .show();
    }

    @Override
    public boolean onUnbind(Intent intent) {
        Log.i(TAG, "onUnbind");
        return super.onUnbind(intent);
    }

    @Override
    public void onDestroy() {
        Log.i(TAG, "onDestroy");
        super.onDestroy();
    }
}
