package com.xingray.activitydialog;

import android.os.Bundle;

/**
 * Author      : leixing
 * Date        : 2017-11-28
 * Email       : leixing@hecom.cn
 * Version     : 0.0.1
 * <p>
 * Description : xxx
 */

public interface LifeCycleListener {
    void onCreate(Bundle savedInstanceState);

    void onStart();

    void onRestart();

    void onResume();

    void onPause();

    void onStop();

    void onDestroy();


}
