package com.xingray.activitydialog;

import android.annotation.SuppressLint;

import java.util.HashMap;
import java.util.Map;

/**
 * Author      : leixing
 * Date        : 2017-06-12
 * Email       : leixing1012@gmail.cn
 * Version     : 0.0.1
 * <p>
 * Description : manager for {@link ActivityDialog}, use {@code Map} to cache {@link ActivityDialog},
 * {@link HostActivity} get adapter by {@link DialogManager}
 */

/*package*/ class DialogManager {
    private static volatile DialogManager INSTANCE;

    private final Map<Long, ActivityDialog> mDialogs;

    /*package*/
    static DialogManager getInstance() {
        if (INSTANCE == null) {
            synchronized (DialogManager.class) {
                if (INSTANCE == null) {
                    INSTANCE = new DialogManager();
                }
            }
        }

        return INSTANCE;
    }


    @SuppressLint("UseSparseArrays")
    private DialogManager() {
        //LongSparseArray<DialogActivityAdapter> mAdapters = new LongSparseArray<>();
        mDialogs = new HashMap<>();
    }

    /**
     * save a {@link ActivityDialog}
     *
     * @param code   unique code for {@link ActivityDialog}
     * @param dialog dialog to save
     */
    /*package*/
    @SuppressWarnings("UnusedReturnValue")
    ActivityDialog put(long code, ActivityDialog dialog) {
        synchronized (mDialogs) {
            return mDialogs.put(code, dialog);
        }
    }

    /**
     * get {@link ActivityDialog} form cache by unique code
     *
     * @param code unique code for {@link ActivityDialog}
     * @return cached {@link ActivityDialog}
     */
    /*package*/ ActivityDialog get(long code) {
        synchronized (mDialogs) {
            return mDialogs.get(code);
        }
    }

    /**
     * remove {@link ActivityDialog} from cache
     *
     * @param code unique code for {@link ActivityDialog}
     * @return previous {@link ActivityDialog} saved by the {@code code}
     */
    /*package*/
    @SuppressWarnings("UnusedReturnValue")
    ActivityDialog remove(long code) {
        synchronized (mDialogs) {
            return mDialogs.remove(code);
        }
    }
}
