package biz.gabrys.agabrys.sonarqube.falsepositives.d20171026;

/**
 * @see https://groups.google.com/d/topic/sonarqube/YY9Zn1KrHbY/discussion
 */
public interface RedundantThrowsDeclarationCheck {

    // Ok, SonarQube doesn't complain about MyException
    Object doSomething(Object object) throws MyException;

    // False Positive, SonarQube complains about MyException
    default Object doSomethingDefault(final Object object) throws MyException {
        // default implementation - backward compatibility
        return object;
    }

    @SuppressWarnings("serial")
    class MyException extends Exception {

    }
}
