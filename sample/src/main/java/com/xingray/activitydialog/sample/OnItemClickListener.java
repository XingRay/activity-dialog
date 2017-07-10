package com.xingray.activitydialog.sample;

import android.view.View;

/**
 * Author      : leixing
 * Date        : 2017-04-07
 * Email       : leixing1012@gmail.cn
 * Version     : 0.0.1
 * <p>
 * Description : 条目点击事件接口
 */

public interface OnItemClickListener {
    void onItemClick(View parent, View view, int position, int id);
}
