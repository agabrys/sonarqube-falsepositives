package biz.gabrys.agabrys.sonarqube.falsepositives.d20180305;

import static com.google.common.base.Preconditions.checkArgument;
import static java.util.Objects.nonNull;

import java.util.Map;
import java.util.Objects;

import com.google.common.base.Preconditions;

public class S2259 {

    public static final String KEY = "key";

    public String doSomething(final Map<String, Object> map) {
        Preconditions.checkArgument(Objects.nonNull(map), "map cannot be null");
        return (String) map.get(KEY);
    }

    public String doSomething2(final Map<String, Object> map) {
        checkArgument(nonNull(map), "map cannot be null");
        return (String) map.get(KEY);
    }
}
