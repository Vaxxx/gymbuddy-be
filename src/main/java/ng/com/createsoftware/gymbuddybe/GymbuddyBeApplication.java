package ng.com.createsoftware.gymbuddybe;

import ng.com.createsoftware.gymbuddybe.properties.FileStorageProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
@EnableConfigurationProperties({
        FileStorageProperties.class
})
public class GymbuddyBeApplication {

    public static void main(String[] args) {
        SpringApplication.run(GymbuddyBeApplication.class, args);
    }

}
