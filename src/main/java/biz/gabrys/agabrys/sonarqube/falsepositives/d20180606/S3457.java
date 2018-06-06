package biz.gabrys.agabrys.sonarqube.falsepositives.d20180606;

import java.util.logging.Level;
import java.util.logging.Logger;

import org.slf4j.LoggerFactory;

public class S3457 {

    private static final String NAME = S3457.class.getName();

    private static final String CONST_VALUE = "message";

    public void log(final Object param) {
        // OK - No issue
        Logger.getLogger(NAME).log(Level.INFO, "Log1 " + CONST_VALUE + ".");

        // False Positive - string evaluated at compile time
        Logger.getLogger(NAME).log(Level.INFO, "Log2 " + CONST_VALUE + ": {0}.", param);

        // False Positive - string evaluated at compile time
        LoggerFactory.getLogger(NAME).info("Log4 " + CONST_VALUE + ".");

        // False Positive - string evaluated at compile time
        LoggerFactory.getLogger(NAME).info("Log3 " + CONST_VALUE + ": {}", param);
    }
}
