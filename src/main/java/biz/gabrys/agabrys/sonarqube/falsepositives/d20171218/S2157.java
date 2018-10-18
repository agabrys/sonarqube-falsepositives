package biz.gabrys.agabrys.sonarqube.falsepositives.d20171218;

/**
 * @see https://groups.google.com/d/topic/sonarqube/4vbfYPaGT1s/discussion
 */
public class S2157 {

    public abstract static class ClassFromExternalFramework {

        private Object[] array;

        public ClassFromExternalFramework(final Object[] array) {
            this.array = array;
        }

        @Override
        public Object clone() {
            final ClassFromExternalFramework obj;
            try {
                obj = (ClassFromExternalFramework) super.clone();
            } catch (final CloneNotSupportedException e) {
                throw new IllegalStateException(e);
            }
            if (array != null) {
                obj.array = array.clone();
            }
            return obj;
        }

        public abstract void doSomething();
    }

    public static class OurClassWhichShouldBeCloneable extends ClassFromExternalFramework implements Cloneable {

        public OurClassWhichShouldBeCloneable(final Object[] array) {
            super(array);
        }

        @Override
        public void doSomething() {
            // impl
        }

        // squid:S1185 - Overriding methods should do more than simply call the same method in the super class
        // @Override
        // public Object clone() {
        // return super.clone();
        // }
    }
}
