package com.xtransformers.multithread.custom.hook;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.attribute.PosixFilePermission;
import java.nio.file.attribute.PosixFilePermissions;
import java.util.Set;
import java.util.concurrent.TimeUnit;

/**
 * @author daniel
 * @date 2021-11-30
 */
public class PreventDuplicated {

    private final static String LOCK_PATH = "/home/locks/";
    private final static String LOCK_FILE = ".lock";
    private final static String PERMISSIONS = "rw-------";

    public static void main(String[] args) throws IOException {
        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            System.out.println("The program received kill SIGNAL.");
            getLockFile().toFile().delete();
        }));

        checkRunning();

        for (; ; ) {
            try {
                TimeUnit.MILLISECONDS.sleep(500);
                System.out.println("Program is running.");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    private static void checkRunning() throws IOException {
        Path path = getLockFile();
        if (path.toFile().exists()) {
            throw new RuntimeException("The program already running.");
        }
        Set<PosixFilePermission> perms = PosixFilePermissions.fromString(PERMISSIONS);
        Files.createFile(path, PosixFilePermissions.asFileAttribute(perms));
    }

    private static Path getLockFile() {
        return Paths.get(LOCK_PATH, LOCK_FILE);
    }
    /**
     * Exception in thread "main" java.lang.RuntimeException: The program already running.
     * 	at com.xtransformers.multithread.custom.hook.PreventDuplicated.checkRunning(PreventDuplicated.java:43)
     * 	at com.xtransformers.multithread.custom.hook.PreventDuplicated.main(PreventDuplicated.java:28)
     * The program received kill SIGNAL.
     */
}
