package biz.gabrys.agabrys.sonarqube.falsepositives.d20170703;

import java.util.Collection;
import java.util.Map;
import java.util.Random;
import java.util.Set;

/**
 * @see https://groups.google.com/d/topic/sonarqube/-LSFJN6KPPg/discussion
 */
public class S2175 {

    @SuppressWarnings({ "rawtypes", "unchecked" })
    public void collectModifiedEntriesInternal(final Object value, final Collection<AbstractItemModel> result) {
        final boolean isItem = value instanceof AbstractItemModel;
        final boolean isCollection = !isItem && value instanceof Collection;
        final boolean isMap = !isCollection && value instanceof Map;
        if (isItem && canPersistSingleElement(value)) {
            if (!result.contains(value)) {
                result.add((AbstractItemModel) value);
                collectModifiedEntities((AbstractItemModel) value, result);
            }
        } else if (isCollection) {
            ((Collection) value).stream().filter(element -> element instanceof AbstractItemModel && !result.contains(element))
                    .forEach(element -> {
                        result.add((AbstractItemModel) element);
                        collectModifiedEntities((AbstractItemModel) element, result);
                    });
        } else if (isMap) {
            final Set<Map.Entry> set = ((Map) value).entrySet();
            for (final Map.Entry entry : set) {
                final Object keyValue = entry.getKey();
                if (canPersistSingleElement(keyValue)) {
                    if (!result.contains(keyValue)) {
                        result.add((AbstractItemModel) keyValue);
                        collectModifiedEntities((AbstractItemModel) keyValue, result);
                    }
                } else {
                    collectModifiedEntriesInternal(keyValue, result);
                }
                final Object mapValue = entry.getValue();
                if (mapValue instanceof AbstractItemModel) {
                    collectModifiedEntities((AbstractItemModel) mapValue, result);
                } else {
                    collectModifiedEntriesInternal(mapValue, result);
                }
            }
        }
    }

    // MOCKS
    private boolean canPersistSingleElement(final Object value) {
        return new Random().nextBoolean();
    }

    private void collectModifiedEntities(final AbstractItemModel value, final Collection<AbstractItemModel> result) {
        // do nothing
    }

    public interface AbstractItemModel {

    }
}
