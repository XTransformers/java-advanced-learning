package com.xtransformers.bytebuddy;

import net.bytebuddy.ByteBuddy;
import net.bytebuddy.dynamic.loading.ClassLoadingStrategy;
import org.junit.Assert;
import org.junit.Test;

public class Demo {

    @Test
    public void test() {
        Class<?> type = new ByteBuddy()
                .subclass(Object.class)
                .make()
                .load(Demo.class.getClassLoader(), ClassLoadingStrategy.Default.WRAPPER)
                .getLoaded();
        // class net.bytebuddy.renamed.java.lang.Object$ByteBuddy$uEMTLObl
        Assert.assertTrue(type.getName().startsWith("net.bytebuddy.renamed.java.lang.Object"));
    }

}
