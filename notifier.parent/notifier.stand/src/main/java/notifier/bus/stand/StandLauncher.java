package notifier.bus.stand;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@ComponentScan(basePackages = {"notifier.bus.controller", "notifier.bus.stand"})
@EnableScheduling
public class StandLauncher {
    public static void main(String[] args) {
        SpringApplication.run(StandLauncher.class, args);
    }
}
