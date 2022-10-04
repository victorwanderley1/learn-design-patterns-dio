package wanderley.victor;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableFeignClients
@SpringBootApplication
public class LearnDesignPaternsDioApplication {

	public static void main(String[] args) {
		SpringApplication.run(LearnDesignPaternsDioApplication.class, args);
	}

}
