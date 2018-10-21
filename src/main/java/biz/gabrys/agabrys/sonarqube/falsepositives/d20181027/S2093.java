package biz.gabrys.agabrys.sonarqube.falsepositives.d20181027;

/**
 * @see https://community.sonarsource.com/t/java-resource-not-managed-with-try-with-resource/3546?u=agabrys
 */
public class S2093 {

    public void ok1() {
        try (final Auto auto = new Auto()) {
            auto.doSomething();
        }
    }

    public void ok2() throws OtherSpecificException {
        try (final Auto auto = new Auto()) {
            auto.doSomethingWithException();
        } catch (final SpecificException e) {
            throw new OtherSpecificException(e);
        }
    }

    public void issue1() {
        Auto auto = null;
        try {
            auto = new Auto();
            auto.doSomething();
        } finally {
            if (auto != null) {
                auto.close();
            }
        }
    }

    public void issue2() throws OtherSpecificException {
        Auto auto = null;
        try {
            auto = new Auto();
            auto.doSomethingWithException();
        } catch (final SpecificException e) {
            throw new OtherSpecificException(e);
        } finally {
            if (auto != null) {
                auto.close();
            }
        }
    }

    public void falseNagative1() {
        final Auto auto = new Auto();
        try {
            auto.doSomething();
        } finally {
            auto.close();
        }
    }

    public void falseNagative2() throws OtherSpecificException {
        final Auto auto = new Auto();
        try {
            auto.doSomethingWithException();
        } catch (final SpecificException e) {
            throw new OtherSpecificException(e);
        } finally {
            auto.close();
        }
    }

    public static class Auto implements AutoCloseable {

        public void doSomething() {
            // do nothing
        }

        public void doSomethingWithException() throws SpecificException {
            // do nothing with exception
        }

        @Override
        public void close() {
            // close it
        }
    }

    @SuppressWarnings("serial")
    public static class SpecificException extends Exception {

    }

    @SuppressWarnings("serial")
    public static class OtherSpecificException extends Exception {

        public OtherSpecificException(final Throwable cause) {
            super(cause);
        }
    }
}
