package biz.gabrys.agabrys.sonarqube.falsepositives.d20180305;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * @see https://groups.google.com/d/topic/sonarqube/spZLyLpxFf8/discussion
 */
public interface S2386 {

    Set<Object> SINGLETON_UNMODIFICABLE_SET = Collections.singleton(new Object());

    List<Object> SINGLETON_UNMODIFICABLE_LIST = Collections.singletonList(new Object());

    Map<Object, Object> SINGLETON_UNMODIFICABLE_MAP = Collections.singletonMap(new Object(), new Object());
}
