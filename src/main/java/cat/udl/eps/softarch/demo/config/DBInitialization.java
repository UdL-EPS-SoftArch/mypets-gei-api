package cat.udl.eps.softarch.demo.config;
import cat.udl.eps.softarch.demo.domain.Pet;
import cat.udl.eps.softarch.demo.domain.ShelterVolunteer;
import cat.udl.eps.softarch.demo.domain.User;
import cat.udl.eps.softarch.demo.repository.PetRepository;
import cat.udl.eps.softarch.demo.repository.ShelterVolunteerRepository;
import cat.udl.eps.softarch.demo.repository.UserRepository;
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
    private final PetRepository petRepository;

    public DBInitialization(UserRepository userRepository, ShelterVolunteerRepository shelterVolunteerRepository, PetRepository petRepository) {
        this.userRepository = userRepository;
        this.shelterVolunteerRepository = shelterVolunteerRepository;
        this.petRepository = petRepository;
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
        if (!shelterVolunteerRepository.existsById("volunteer")) {
            ShelterVolunteer user = new ShelterVolunteer();
            user.setEmail("volunteer@sample.app");
            user.setId("volunteer");
            user.setPassword(defaultPassword);
            user.encodePassword();
            shelterVolunteerRepository.save(user);
        }
        if (petRepository.findByName("pet").isEmpty()) {
            Pet pet = new Pet();
            pet.setAdopted(false);
            pet.setId((long)1);
            pet.setName("pet");
            pet.setColor("black");
            pet.setBreed("cat");
            pet.setIsIn();
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
