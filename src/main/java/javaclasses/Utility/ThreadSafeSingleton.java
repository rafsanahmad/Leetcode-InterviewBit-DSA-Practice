package javaclasses.Utility;

public final class ThreadSafeSingleton {
    private static volatile ThreadSafeSingleton INSTANCE = null;

    private long licenseNumber;

    public long getLicenseNumber() {
        return licenseNumber;
    }

    // by making the constructor private, we prevent instantiation
    private ThreadSafeSingleton() {
        this.licenseNumber = 1999;
    }

    public static ThreadSafeSingleton getInstance() {
        if (INSTANCE == null) {
            synchronized (ThreadSafeSingleton.class) {
                if (INSTANCE == null) {
                    INSTANCE = new ThreadSafeSingleton();
                }
            }
        }
        return INSTANCE;
    }
}
