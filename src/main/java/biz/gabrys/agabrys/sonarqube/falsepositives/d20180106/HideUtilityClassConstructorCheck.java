package biz.gabrys.agabrys.sonarqube.falsepositives.d20180106;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class HideUtilityClassConstructorCheck {

    public static void main(String[] args) {
        SpringApplication.run(HideUtilityClassConstructorCheck.class, args);
    }
}
