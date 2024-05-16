package cat.udl.eps.softarch.demo.config;

import cat.udl.eps.softarch.demo.domain.ShelterVolunteer;
import cat.udl.eps.softarch.demo.domain.User;
import cat.udl.eps.softarch.demo.repository.UserRepository;
import cat.udl.eps.softarch.demo.repository.ShelterVolunteerRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

import jakarta.annotation.PostConstruct;
import java.util.Arrays;

@Configuration
public class DBInitialization {
    @Value("${default-password}")
    String defaultPassword;
    @Value("${spring.profiles.active:}")
    private String activeProfiles;
    private final UserRepository userRepository;
    private final ShelterVolunteerRepository shelterVolunteerRepository;

    public DBInitialization(UserRepository userRepository, ShelterVolunteerRepository shelterVolunteerRepository) {
        this.userRepository = userRepository;
        this.shelterVolunteerRepository = shelterVolunteerRepository;
    }

    @PostConstruct
    public void initializeDatabase() {
        // Default user
        if (!userRepository.existsById("demo")) {
            User user = new User();
            user.setEmail("demo@sample.app");
            user.setId("demo");
            user.setPassword(defaultPassword);
            user.encodePassword();
            userRepository.save(user);
        }

        // Default ShelterVolunteer
        if (!shelterVolunteerRepository.existsById("volunteer")) {
            ShelterVolunteer volunteer = new ShelterVolunteer();
            volunteer.setEmail("volunteer@sample.app");
            volunteer.setId("volunteer");
            volunteer.setPassword(defaultPassword);
            volunteer.encodePassword();
            shelterVolunteerRepository.save(volunteer);
        }

        if (Arrays.asList(activeProfiles.split(",")).contains("test")) {
            // Testing instances
            if (!userRepository.existsById("test")) {
                User user = new User();
                user.setEmail("test@sample.app");
                user.setId("test");
                user.setPassword(defaultPassword);
                user.encodePassword();
                userRepository.save(user);
            }
        }
    }
}
