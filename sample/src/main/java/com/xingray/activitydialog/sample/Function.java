package com.xingray.activitydialog.sample;

import android.app.Activity;
import android.content.Intent;

/**
 * Author      : leixing
 * Date        : 2017-04-14
 * Email       : leixing1012@gmail.cn
 * Version     : 0.0.1
 * <p>
 * Description : xxx
 */

public class Function {
    private String name;
    private String code;
    private Runnable task;
    private Class<? extends Activity> page;

    public Function(String name, String code, Runnable task, Class<? extends Activity> page) {
        this.name = name;
        this.code = code;
        this.task = task;
        this.page = page;
    }

    public Function(String name, Class<? extends Activity> page) {
        this(name, "", null, page);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Runnable getTask() {
        return task;
    }

    public void setTask(Runnable task) {
        this.task = task;
    }

    public Class<? extends Activity> getPage() {
        return page;
    }

    public void setPage(Class<? extends Activity> page) {
        this.page = page;
    }

    @Override
    public String toString() {
        return "Function{" +
                "name='" + name + '\'' +
                ", code='" + code + '\'' +
                ", task=" + task +
                ", page=" + page +
                '}';
    }

    public void exec(final Activity activity) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                if (task != null) {
                    task.run();
                }

                if (page != null) {
                    Intent intent = new Intent();
                    intent.setClass(activity, page);
                    activity.startActivity(intent);
                }
            }
        }).start();
    }
}
