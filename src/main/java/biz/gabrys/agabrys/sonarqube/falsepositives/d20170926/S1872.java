package biz.gabrys.agabrys.sonarqube.falsepositives.d20170926;

import java.util.Map;

public class S1872 {

    public boolean compareObjectsClassNamees(final Object object1, final Object object2) {
        // Issue (OK)
        return object1.getClass().getName().equals(object2.getClass().getName());
    }

    public boolean compareObjectClassName(final Object object) {
        // Issue (OK)
        return Object.class.getName().equals(object.getClass().getName());
    }

    public boolean compareStringClassNameWithClassName(final String type) {
        // Issue (OK)
        return Object.class.getName().equals(type.getClass().getName());
    }

    public boolean compareStringObjectWithClassName(final Map<String, String> context) {
        final String type = context.get("PARAMETER_NAME");
        // False Positive
        return Object.class.getName().equals(type);
    }

    public boolean compareStringParameterWithClassName(final String type) {
        // False Positive
        return Object.class.getName().equals(type);
    }
}
