package lt.techin.springboot.exam.karaoke;

import lt.techin.springboot.exam.karaoke.persistance.UserRepository;
import lt.techin.springboot.exam.karaoke.persistance.modal.UserRecord;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class KaraokeApplication {

    public static void main(String[] args) {
        SpringApplication.run(KaraokeApplication.class, args);
    }

    @Bean
    public CommandLineRunner commandLineRunner(UserRepository userRepository) {
        return runner -> {
            UserRecord user1 = new UserRecord("Ruta", "3f129b97-06b3-4b63-8fc6-e7ee4771f6a5");
            UserRecord user2 = new UserRecord("Saule", "1e8d9376-6be8-4c05-b7e3-fb2c36218813");
            UserRecord user3 = new UserRecord("Domas", "fd5788fd-ac2b-4fbc-ab73-dfe332d8c7be");

            userRepository.save(user1);
            userRepository.save(user2);
            userRepository.save(user3);
        };
    }
}
