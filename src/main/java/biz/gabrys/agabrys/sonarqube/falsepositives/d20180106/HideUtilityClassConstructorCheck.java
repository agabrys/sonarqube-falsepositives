package biz.gabrys.agabrys.sonarqube.falsepositives.d20180106;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @see https://github.com/checkstyle/checkstyle/issues/5434
 */
@SpringBootApplication
public class HideUtilityClassConstructorCheck {

    public static void main(String[] args) {
        SpringApplication.run(HideUtilityClassConstructorCheck.class, args);
    }
}
