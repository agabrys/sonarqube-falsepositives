package biz.gabrys.agabrys.sonarqube.falsepositives.d20200513;

/**
 * @see https://community.sonarsource.com/t/s2259-false-positive-when-null-check-in-a-different-method-in-the-same-class/24610?u=agabrys
 */
public class S2259
{
	private String propertyName;
	
	public S2259(String propertyName) {
		this.propertyName = propertyName;
	}
	
	public int compare(final MockedClass pipeline1, final MockedClass pipeline2) {
		final Integer pipelineNullsCompareResult = compareNulls(pipeline1, pipeline2);
		if (pipelineNullsCompareResult != null) {
			return pipelineNullsCompareResult.intValue();
		}

		final String prop1 = pipeline1.getProperty(propertyName);
		final String prop2 = pipeline2.getProperty(propertyName);

		return prop1.compareTo(prop2);
	}

	private static Integer compareNulls(Object obj1, Object obj2) {
		if (obj1 == null && obj2 == null) {
			return 0;
		}
		if (obj1 == null) {
			return 1;
		}
		if (obj2 == null) {
			return -1;
		}
		return null;
	}
	
	public interface MockedClass {
		
		String getProperty(String propertyName);
	}
}
