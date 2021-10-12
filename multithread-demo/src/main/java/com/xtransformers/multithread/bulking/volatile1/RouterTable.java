package com.xtransformers.multithread.bulking.volatile1;

import java.util.Set;
import java.util.concurrent.*;

/**
 * @author daniel
 * @date 2021-10-13
 */
public class RouterTable {
    // key 接口名 value 路由集合
    ConcurrentHashMap<String, CopyOnWriteArraySet<Router>> rt = new ConcurrentHashMap<>();
    // 路由表是否发生变化
    volatile boolean changed;
    // 将路由表写入本地文件的线程池
    ScheduledExecutorService ses = Executors.newSingleThreadScheduledExecutor();

    // 启动定时任务
    public void startLocalSaver() {
        ses.scheduleWithFixedDelay(() -> autoSave(), 1, 1, TimeUnit.MINUTES);
    }

    void autoSave() {
        if (!changed) {
            return;
        }
        changed = false;
        save2Local();
    }

    // 将路由表写入本地文件
    private void save2Local() {
    }

    // 删除路由
    public void remove(Router router) {
        Set<Router> set = rt.get(router.iface);
        if (set != null) {
            set.remove(router);
            changed = true;
        }
    }

    // 增加路由
    public void add(Router router) {
        Set<Router> set = rt.computeIfAbsent(router.iface, r -> new CopyOnWriteArraySet<Router>());
        set.add(router);
        changed = true;
    }
}

class Router {
    String iface;
}
