package com.xtransformers.multithread.jvm.classloader;

/**
 * @author daniel
 * @date 2021-12-02
 */
public class ApplicationClassLoader {
    public static void main(String[] args) {
        System.out.println(System.getProperty("java.class.path"));
        System.out.println(ApplicationClassLoader.class.getClassLoader());
    }
    /**
     * /Library/Java/JavaVirtualMachines/jdk1.8.0_131.jdk/Contents/Home/jre/lib/charsets.jar
     * /Library/Java/JavaVirtualMachines/jdk1.8.0_131.jdk/Contents/Home/jre/lib/deploy.jar
     * /Library/Java/JavaVirtualMachines/jdk1.8.0_131.jdk/Contents/Home/jre/lib/ext/a.jar
     * /Library/Java/JavaVirtualMachines/jdk1.8.0_131.jdk/Contents/Home/jre/lib/ext/cldrdata.jar
     * /Library/Java/JavaVirtualMachines/jdk1.8.0_131.jdk/Contents/Home/jre/lib/ext/dnsns.jar
     * /Library/Java/JavaVirtualMachines/jdk1.8.0_131.jdk/Contents/Home/jre/lib/ext/jaccess.jar
     * /Library/Java/JavaVirtualMachines/jdk1.8.0_131.jdk/Contents/Home/jre/lib/ext/jfxrt.jar
     * /Library/Java/JavaVirtualMachines/jdk1.8.0_131.jdk/Contents/Home/jre/lib/ext/localedata.jar
     * /Library/Java/JavaVirtualMachines/jdk1.8.0_131.jdk/Contents/Home/jre/lib/ext/nashorn.jar
     * /Library/Java/JavaVirtualMachines/jdk1.8.0_131.jdk/Contents/Home/jre/lib/ext/sunec.jar
     * /Library/Java/JavaVirtualMachines/jdk1.8.0_131.jdk/Contents/Home/jre/lib/ext/sunjce_provider.jar
     * /Library/Java/JavaVirtualMachines/jdk1.8.0_131.jdk/Contents/Home/jre/lib/ext/sunpkcs11.jar
     * /Library/Java/JavaVirtualMachines/jdk1.8.0_131.jdk/Contents/Home/jre/lib/ext/zipfs.jar
     * /Library/Java/JavaVirtualMachines/jdk1.8.0_131.jdk/Contents/Home/jre/lib/javaws.jar
     * /Library/Java/JavaVirtualMachines/jdk1.8.0_131.jdk/Contents/Home/jre/lib/jce.jar
     * /Library/Java/JavaVirtualMachines/jdk1.8.0_131.jdk/Contents/Home/jre/lib/jfr.jar
     * /Library/Java/JavaVirtualMachines/jdk1.8.0_131.jdk/Contents/Home/jre/lib/jfxswt.jar
     * /Library/Java/JavaVirtualMachines/jdk1.8.0_131.jdk/Contents/Home/jre/lib/jsse.jar
     * /Library/Java/JavaVirtualMachines/jdk1.8.0_131.jdk/Contents/Home/jre/lib/management-agent.jar
     * /Library/Java/JavaVirtualMachines/jdk1.8.0_131.jdk/Contents/Home/jre/lib/plugin.jar
     * /Library/Java/JavaVirtualMachines/jdk1.8.0_131.jdk/Contents/Home/jre/lib/resources.jar
     * /Library/Java/JavaVirtualMachines/jdk1.8.0_131.jdk/Contents/Home/jre/lib/rt.jar
     * /Library/Java/JavaVirtualMachines/jdk1.8.0_131.jdk/Contents/Home/lib/ant-javafx.jar
     * /Library/Java/JavaVirtualMachines/jdk1.8.0_131.jdk/Contents/Home/lib/dt.jar
     * /Library/Java/JavaVirtualMachines/jdk1.8.0_131.jdk/Contents/Home/lib/javafx-mx.jar
     * /Library/Java/JavaVirtualMachines/jdk1.8.0_131.jdk/Contents/Home/lib/jconsole.jar
     * /Library/Java/JavaVirtualMachines/jdk1.8.0_131.jdk/Contents/Home/lib/packager.jar
     * /Library/Java/JavaVirtualMachines/jdk1.8.0_131.jdk/Contents/Home/lib/sa-jdi.jar
     * /Library/Java/JavaVirtualMachines/jdk1.8.0_131.jdk/Contents/Home/lib/tools.jar
     * /Users/daniel/Documents/workspace/idea201903/java-advanced-learning/multithread-demo/target/classes
     * /Users/daniel/java/maven/repository/org/projectlombok/lombok/1.18.16/lombok-1.18.16.jar
     * /Users/daniel/java/maven/repository/org/slf4j/slf4j-api/1.7.30/slf4j-api-1.7.30.jar
     * /Users/daniel/java/maven/repository/junit/junit/4.12/junit-4.12.jar
     * /Users/daniel/java/maven/repository/org/hamcrest/hamcrest-core/2.2/hamcrest-core-2.2.jar
     * /Users/daniel/java/maven/repository/org/hamcrest/hamcrest/2.2/hamcrest-2.2.jar
     * /Users/daniel/Library/Application Support/JetBrains/Toolbox/apps/IDEA-U/ch-0/213.5744.223/IntelliJ IDEA.app/Contents/lib/idea_rt.jar
     * sun.misc.Launcher$AppClassLoader@18b4aac2
     */
}
