package cat.udl.eps.softarch.demo.config;

import cat.udl.eps.softarch.demo.domain.Admin;
import cat.udl.eps.softarch.demo.domain.Shelter;
import cat.udl.eps.softarch.demo.domain.ShelterVolunteer;
import cat.udl.eps.softarch.demo.domain.User;
import cat.udl.eps.softarch.demo.repository.AdminRepository;
import cat.udl.eps.softarch.demo.repository.ShelterRepository;
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
    private final AdminRepository adminRepository;
    private final ShelterVolunteerRepository shelterVolunteerRepository;
    private final ShelterRepository shelterRepository;
    public DBInitialization(UserRepository userRepository,
                            AdminRepository adminRepository,
                            ShelterVolunteerRepository shelterVolunteerRepository,
                            ShelterRepository shelterRepository) {
        this.userRepository = userRepository;
        this.adminRepository = adminRepository;
        this.shelterVolunteerRepository = shelterVolunteerRepository;
        this.shelterRepository = shelterRepository;
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

        // Default admin
        if (!adminRepository.existsById("admin")) {
            Admin admin = new Admin();
            admin.setEmail("admin@sample.app");
            admin.setId("admin");
            admin.setPassword(defaultPassword);
            admin.encodePassword();
            userRepository.save(admin);
        }
        if (!adminRepository.existsById("admin")) {
            Admin admin = new Admin();
            admin.setEmail("admin@sample.app");
            admin.setId("admin");
            admin.setPassword(defaultPassword);
            admin.encodePassword();
            userRepository.save(admin);
        }

        // Default ShelterVolunteer
        if(shelterRepository.findByEmail("shelter@sample.app").isEmpty()) {
            Shelter shelter = new Shelter();
            shelter.setName("shelter");
            shelter.setEmail("shelter@sample.app");
            shelter.setMobile("999999990");
            shelter.setActive(true);
            shelterRepository.save(shelter);
        }
        if(shelterRepository.findByEmail("shelter1@sample.app").isEmpty()) {
            Shelter shelter = new Shelter();
            shelter.setName("shelter1");
            shelter.setEmail("shelter1@sample.app");
            shelter.setMobile("999999999");
            shelter.setActive(true);
            shelterRepository.save(shelter);

        if (!shelterVolunteerRepository.existsById("volunteer")) {
            ShelterVolunteer volunteer = new ShelterVolunteer();
            volunteer.setEmail("volunteer@sample.app");
            volunteer.setId("volunteer");
            volunteer.setPassword(defaultPassword);
            volunteer.encodePassword();
            volunteer.setUserShelter(shelter);
            shelterVolunteerRepository.save(volunteer);
        }
        if (!shelterVolunteerRepository.existsById("volunteer1")) {
            ShelterVolunteer volunteer = new ShelterVolunteer();
            volunteer.setEmail("volunteer1@sample.app");
            volunteer.setId("volunteer1");
            volunteer.setPassword(defaultPassword);
            volunteer.setUserShelter(shelter);
            volunteer.encodePassword();
            shelterVolunteerRepository.save(volunteer);
        }

        if (!shelterVolunteerRepository.existsById("volunteer2")) {
            ShelterVolunteer volunteer = new ShelterVolunteer();
            volunteer.setEmail("volunteer2@sample.app");
            volunteer.setId("volunteer2");
            volunteer.setPassword(defaultPassword);
            volunteer.setUserShelter(shelter);
            volunteer.encodePassword();
            shelterVolunteerRepository.save(volunteer);
        }


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
