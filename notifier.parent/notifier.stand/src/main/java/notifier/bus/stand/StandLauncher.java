package notifier.bus.stand;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = {"notifier.bus.controller", "notifier.bus.stand"})
public class StandLauncher {
    public static void main(String[] args) {
        SpringApplication.run(StandLauncher.class, args);
    }
}
