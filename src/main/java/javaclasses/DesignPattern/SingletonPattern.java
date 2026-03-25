/*
 *
 *  * SingletonPattern.java
 *  *
 *  * Created by Rafsan Ahmad on 03/13/26, 3:13 PM
 *  * Copyright (c) 2026. All rights reserved.
 *
 */

package javaclasses.DesignPattern;

import java.util.HashMap;
import java.util.Map;

public class SingletonPattern {

    // -------------------------
    // 1. Eager Initialization
    // -------------------------
    /*Thread-safe (class loading is thread-safe)
    Not lazy (created even if never used)*/
    static class EagerSingleton {

        private static final EagerSingleton INSTANCE = new EagerSingleton();

        private EagerSingleton() {
        }

        public static EagerSingleton getInstance() {
            return INSTANCE;
        }
    }

    // -------------------------
    // 2. Lazy Initialization (NOT thread safe)
    // -------------------------
    /*✔ Lazy initialization
    Not thread-safe
    Two threads may create two objects.*/
    static class LazySingleton {

        private static LazySingleton instance;

        private LazySingleton() {
        }

        public static LazySingleton getInstance() {
            if (instance == null) {
                instance = new LazySingleton();
            }
            return instance;
        }
    }

    // -------------------------
    // 3. Synchronized Singleton
    // -------------------------
    /*✔ Thread-safe
    Slower because every call locks*/
    static class SynchronizedSingleton {

        private static SynchronizedSingleton instance;

        private SynchronizedSingleton() {
        }

        public static synchronized SynchronizedSingleton getInstance() {
            if (instance == null) {
                instance = new SynchronizedSingleton();
            }
            return instance;
        }
    }

    // -------------------------
    // 4. Double Checked Locking
    // -------------------------
    /*✔ Thread-safe
    ✔ Lazy initialization
    ✔ Lock used only once*/
    static class DCLSingleton {

        private static volatile DCLSingleton instance;

        private DCLSingleton() {
        }

        public static DCLSingleton getInstance() {
            if (instance == null) {
                synchronized (DCLSingleton.class) {
                    if (instance == null) {
                        instance = new DCLSingleton();
                    }
                }
            }
            return instance;
        }
    }

    // -------------------------
    // 5. Holder Pattern
    // -------------------------
    /*✔ Lazy initialization
    ✔ Thread-safe
    ✔ No synchronization overhead*/
    static class HolderSingleton {

        private HolderSingleton() {
        }

        private static class Holder {
            private static final HolderSingleton INSTANCE = new HolderSingleton();
        }

        public static HolderSingleton getInstance() {
            return Holder.INSTANCE;
        }
    }

    // -------------------------
    // 6. Enum Singleton
    // -------------------------
    /*✔ Thread-safe
✔ Serialization-safe
✔ Reflection-safe
✔ Simplest implementation*/
    // Singleton implemented using enum
    enum ConfigManager {

        INSTANCE;   // the single instance

        private Map<String, String> config = new HashMap<>();

        public void put(String key, String value) {
            config.put(key, value);
        }

        public String get(String key) {
            return config.get(key);
        }
    }


    public static void main(String[] args) {

        // Eager
        EagerSingleton eager = EagerSingleton.getInstance();
        System.out.println("Eager: " + eager);

        // Lazy (not thread-safe)
        LazySingleton lazy = LazySingleton.getInstance();
        System.out.println("Lazy: " + lazy);

        // Synchronized
        SynchronizedSingleton sync = SynchronizedSingleton.getInstance();
        System.out.println("Synchronized: " + sync);

        // Double Checked Locking
        DCLSingleton dcl = DCLSingleton.getInstance();
        System.out.println("DCL: " + dcl);

        // Holder Pattern
        HolderSingleton holder = HolderSingleton.getInstance();
        System.out.println("Holder: " + holder);

        // Enum Singleton
        // Access singleton instance
        ConfigManager config = ConfigManager.INSTANCE;
        config.put("env", "production");
        config.put("region", "us-east");

        System.out.println(config.get("env"));

        // Prove it's the same instance
        ConfigManager another = ConfigManager.INSTANCE;

        System.out.println(config == another); // true
    }
}


