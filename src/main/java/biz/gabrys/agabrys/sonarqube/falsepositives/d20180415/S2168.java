package biz.gabrys.agabrys.sonarqube.falsepositives.d20180415;

/**
 * @see https://stackoverflow.com/q/49741933/4944847
 * @see https://groups.google.com/d/topic/sonarqube/1UXBR9eZydU/discussion
 */
public interface S2168 {
    // do nothing
}

class VolatileAssigmentInTheSameMethod {

    private static volatile Object instance;
    private static final Object lock = new Object();

    public static Object getInstance() {
        if (instance == null) {
            // No issue (OK)
            synchronized (lock) {
                if (instance == null) {
                    instance = new Object();
                }
            }
        }
        return instance;
    }
}

class NonVolatileAssigmentInTheSameMethod {

    private static Object instance;
    private static final Object lock = new Object();

    public static Object getInstance() {
        if (instance == null) {
            // Issue (OK)
            synchronized (lock) {
                if (instance == null) {
                    instance = new Object();
                }
            }
        }
        return instance;
    }
}

class VolatileAssigmentInOtherMethod {

    private static volatile Object instance;
    private static final Object lock = new Object();

    public static Object getInstance() {
        // No issue (OK)
        if (instance == null) {
            synchronized (lock) {
                if (instance == null) {
                    create();
                }
            }
        }
        return instance;
    }

    private static void create() {
        instance = new Object();
    }
}

class NonVolatileAssigmentInOtherMethod {

    private static Object instance;
    private static final Object lock = new Object();

    public static Object getInstance() {
        if (instance == null) {
            // No issue (False Negative)
            synchronized (lock) {
                if (instance == null) {
                    create();
                }
            }
        }
        return instance;
    }

    private static void create() {
        instance = new Object();
    }
}
