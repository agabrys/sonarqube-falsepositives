package biz.gabrys.agabrys.sonarqube.falsepositives.d20170703;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

import org.apache.commons.collections.CollectionUtils;
import org.mockito.Mockito;

public class S2222 {

    private final ReadWriteLock cacheLock = new ReentrantReadWriteLock(false);
    private final Map<String, DataType> typeCache = new HashMap<>();
    private final Map<String, DataType> lightTypeCache = new HashMap<>();

    private TypeService typeService;

    public DataType loadAndCache(final String code, final Context context, final boolean lightWeight) throws TypeNotFoundException {
        DataType dataType;
        Lock readLock = null;
        try {
            readLock = cacheLock.readLock();
            readLock.lock();
            dataType = lightWeight ? lightTypeCache.get(code) : typeCache.get(code);
        } finally {
            if (readLock != null) {
                readLock.unlock();
            }
        }
        if (dataType == null) {
            try {
                boolean staticType = true;
                final TypeModel typeForCode = typeService.getTypeForCode(code);
                if (CollectionUtils.isNotEmpty(getDynamicTypesBlacklist())) {
                    for (final String type : getDynamicTypesBlacklist()) {
                        if (typeService.isAssignableFrom(type, code)
                                || ViewTypeModel._TYPECODE.equals(type) && typeService.getTypeForCode(code) instanceof ViewTypeModel) {
                            staticType = false;
                            break;
                        }
                    }
                }
                Lock lock = null;
                if (staticType) {
                    lock = cacheLock.writeLock();
                    lock.lock();
                }
                try {
                    dataType = convertType(typeForCode, lightWeight, context);
                    if (dataType == null) {
                        throw new TypeNotFoundException(code);
                    }
                    if (staticType) {
                        if (lightWeight) {
                            lightTypeCache.put(code, dataType);
                        } else {
                            typeCache.put(code, dataType);
                        }
                    }
                } finally {
                    if (lock != null) {
                        lock.unlock();
                    }
                }
            } catch (final UnknownIdentifierException e) {
                throw new TypeNotFoundException(code, e);
            }
        }
        return dataType;

    }

    // MOCKS
    private List<String> getDynamicTypesBlacklist() {
        return new Random().nextBoolean() ? null : new ArrayList<>();
    }

    private DataType convertType(final TypeModel typeForCode, final boolean lightWeight, final Context context) {
        return new Random().nextBoolean() ? null : new DataType();
    }

    public static class Context {

    }

    public static class DataType {

    }

    @SuppressWarnings("serial")
    public static class TypeNotFoundException extends Exception {

        public TypeNotFoundException(final String message, final Throwable cause) {
            super(message, cause);
        }

        public TypeNotFoundException(final String message) {
            super(message);
        }
    }

    private static class TypeService {

        public TypeModel getTypeForCode(final String code) {
            return new Random().nextBoolean() ? Mockito.mock(TypeModel.class) : Mockito.mock(ViewTypeModel.class);
        }

        public boolean isAssignableFrom(final String type, final String code) {
            return type.equals(code);
        }
    }

    public interface ViewTypeModel extends TypeModel {
        final String _TYPECODE = "typecode";
    }

    public interface TypeModel {

    }

    @SuppressWarnings("serial")
    private class UnknownIdentifierException extends RuntimeException {

    }
}
