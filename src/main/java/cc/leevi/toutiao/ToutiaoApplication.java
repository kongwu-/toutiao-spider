package cc.leevi.toutiao;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class ToutiaoApplication implements CommandLineRunner{

	public static void main(String[] args) {
		SpringApplication.run(ToutiaoApplication.class, args);
	}

	@Override
	public void run(String... strings) throws Exception {
		Thread.currentThread().join();
	}
}
