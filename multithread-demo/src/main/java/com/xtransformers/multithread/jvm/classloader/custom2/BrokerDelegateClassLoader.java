package com.xtransformers.multithread.jvm.classloader.custom2;

/**
 * @author daniel
 * @date 2021-12-07
 */
public class BrokerDelegateClassLoader extends ClassLoader {

    protected BrokerDelegateClassLoader() {
        super();
    }

    protected BrokerDelegateClassLoader(ClassLoader parent) {
        super(parent);
    }

    @Override
    protected Class<?> loadClass(String name, boolean resolve) throws ClassNotFoundException {
        // 1. 根据类的全路径名加锁，确保每一个类在多线程情况下只被加载一次
        synchronized (getClassLoadingLock(name)) {
            // 2. 到已加载类的缓存中查看该类是否已经被加载
            Class<?> klass = findLoadedClass(name);
            // 3. 缓存中没有被加载的类
            if (klass == null) {
                // 4. 全路径以 java 和 javax 开头的，直接委托给系统类加载器对其进行加载
                if (name.startsWith("java.") || name.startsWith("javax.")) {
                    try {
                        klass = getSystemClassLoader().loadClass(name);
                    } catch (Exception e) {
                        // ignoew
                    }
                } else {
                    // 5. 使用自定义类加载器进行加载
                    try {
                        klass = this.findClass(name);
                    } catch (ClassNotFoundException e) {
                        // ignore
                    }
                    // 6. 自定义类加载器仍旧没有完成对类的加载，则委托给其父类加载器进行加载或者系统类加载器进行加载
                    if (klass == null) {
                        if (getParent() != null) {
                            klass = getParent().loadClass(name);
                        } else {
                            klass = getSystemClassLoader().loadClass(name);
                        }
                    }
                }
            }
            // 7. 经过若干次尝试后，还是无法对该类进行加载，则抛出异常
            if (klass == null) {
                throw new ClassNotFoundException("The class " + name + " not found.");
            }
            if (resolve) {
                resolveClass(klass);
            }
            return klass;
        }
    }
}
