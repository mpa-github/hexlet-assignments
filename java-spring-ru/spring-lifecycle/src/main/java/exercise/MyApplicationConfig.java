package exercise;

import java.time.LocalDateTime;

import exercise.daytimes.Day;
import exercise.daytimes.Daytime;
import exercise.daytimes.Evening;
import exercise.daytimes.Morning;
import exercise.daytimes.Night;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

// BEGIN
@Configuration
public class MyApplicationConfig {

    private LocalDateTime localDateTime = LocalDateTime.now();

    @Bean
    public Daytime getDayTime() {
        Daytime daytime;
        int currentHour = localDateTime.getHour();
        if (currentHour >= 6 && currentHour < 12) {
            daytime = new Morning();
        } else if (currentHour >= 12 && currentHour < 18) {
            daytime = new Day();
        } else if (currentHour >= 18 && currentHour < 23) {
            daytime = new Evening();
        } else {
            daytime = new Night();
        }
        return daytime;
    }
}
// END
