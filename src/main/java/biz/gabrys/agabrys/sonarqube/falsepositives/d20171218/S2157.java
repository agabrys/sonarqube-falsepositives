package biz.gabrys.agabrys.sonarqube.falsepositives.d20171218;

public class S2157 {

    public abstract static class ClassFromExternalFramework {

        private Object[] array;

        public ClassFromExternalFramework(final Object[] array) {
            this.array = array;
        }

        public Object clone() {
            final ClassFromExternalFramework mapple;
            try {
                mapple = (ClassFromExternalFramework) super.clone();
            } catch (CloneNotSupportedException e) {
                throw new IllegalStateException(e);
            }
            if (array != null) {
                mapple.array = array.clone();
            }
            return mapple;
        }

        public abstract void doSomething();
    }

    public static class OurClassWhichShouldBeCloneable extends ClassFromExternalFramework implements Cloneable {

        public OurClassWhichShouldBeCloneable(Object[] array) {
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
