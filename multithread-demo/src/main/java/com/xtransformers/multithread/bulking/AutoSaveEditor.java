package com.xtransformers.multithread.bulking;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * @author daniel
 * @date 2021-10-13
 */
public class AutoSaveEditor {

    // 文件是否被修改过
    boolean changed = false;

    // 定时任务线程池
    ScheduledExecutorService ses = Executors.newSingleThreadScheduledExecutor();

    // 定时执行自动保存
    void startAutoSave() {
        ses.scheduleWithFixedDelay(() -> autoSave(), 5, 5, TimeUnit.SECONDS);
    }

    // 自动存盘操作
    void autoSave() {
        synchronized (this) {
            if (!changed) {
                return;
            }
            changed = false;
        }
        // 执行存盘操作
        execSave();
    }

    private void execSave() {
    }

    // 编辑操作
    void edit() {
        // 省略编辑逻辑
        changed();
    }

    // 将 edit 方法中对共享变量 changed 的赋值操作抽取到了 change() 中
    // 将并发处理逻辑和业务逻辑分开
    private void changed() {
        synchronized (this) {
            changed = true;
        }
    }
}
