package biz.gabrys.agabrys.sonarqube.falsepositives.d20170926;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;

/**
 * @see https://groups.google.com/d/topic/sonarqube/1BrKgd6meuw/discussion
 */
public class S1141 {

    private static final Logger LOG = Logger.getLogger(S1141.class.getName());
    private final ConcurrentMap<Set<Class<?>>, JAXBContext> contextCache = new ConcurrentHashMap<>();

    public JAXBContext createContext(final Class<?>... jaxbClasses) throws JAXBException {
        if (jaxbClasses == null || jaxbClasses.length == 0) {
            throw new IllegalArgumentException("JAXB class list for context must not be null");
        }
        final Set<Class<?>> key = new HashSet<>(Arrays.asList(jaxbClasses));
        try {
            return contextCache.computeIfAbsent(key, k -> {
                try {
                    return JAXBContext.newInstance(jaxbClasses);
                } catch (final JAXBException e) {
                    throw new JAXBRuntimeException(e);
                }
            });
        } catch (final JAXBRuntimeException e) {
            LOG.log(Level.WARNING, "Cannot create JAXB Context", e);
            throw e.getTypedCause();
        }
    }

    @SuppressWarnings("serial")
    private static class JAXBRuntimeException extends RuntimeException {

        public JAXBRuntimeException(final JAXBException cause) {
            super(cause);
        }

        public JAXBException getTypedCause() {
            return (JAXBException) getCause();
        }
    }
}
