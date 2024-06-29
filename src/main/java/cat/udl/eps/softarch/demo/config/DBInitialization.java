package cat.udl.eps.softarch.demo.config;

import cat.udl.eps.softarch.demo.domain.*;
import cat.udl.eps.softarch.demo.repository.*;
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
    private final PetRepository petRepository;
    private final FavouritedPetsRepository favouritedPetsRepository;
    public DBInitialization(UserRepository userRepository,
                            AdminRepository adminRepository,
                            ShelterVolunteerRepository shelterVolunteerRepository,
                            ShelterRepository shelterRepository,
                            PetRepository petRepository,
                            FavouritedPetsRepository favouritedPetsRepository) {
        this.userRepository = userRepository;
        this.adminRepository = adminRepository;
        this.shelterVolunteerRepository = shelterVolunteerRepository;
        this.shelterRepository = shelterRepository;
        this.petRepository = petRepository;
        this.favouritedPetsRepository = favouritedPetsRepository;
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
        if(shelterRepository.findByEmail("shelter@dbsample.app").isEmpty()) {
            Shelter shelter = new Shelter();
            shelter.setName("shelter");
            shelter.setEmail("shelter@dbsample.app");
            shelter.setMobile("420420420");
            shelter.setActive(true);
            shelterRepository.save(shelter);
        }
        if(shelterRepository.findByEmail("shelter1@dbsample.app").isEmpty()) {
            Shelter shelter = new Shelter();
            shelter.setName("shelter1");
            shelter.setEmail("shelter1@dbsample.app");
            shelter.setMobile("420420421");
            shelter.setActive(true);
            shelterRepository.save(shelter);


            if (!shelterVolunteerRepository.existsById("volunteer")) {
                ShelterVolunteer volunteer = new ShelterVolunteer();
                volunteer.setEmail("volunteer@dbsample.app");
                volunteer.setId("volunteer");
                volunteer.setPassword(defaultPassword);
                volunteer.encodePassword();
                volunteer.setUserShelter(shelter);
                shelterVolunteerRepository.save(volunteer);
            }
            if (!shelterVolunteerRepository.existsById("volunteer1")) {
                ShelterVolunteer volunteer = new ShelterVolunteer();
                volunteer.setEmail("volunteer1@dbsample.app");
                volunteer.setId("volunteer1");
                volunteer.setPassword(defaultPassword);
                volunteer.setUserShelter(shelter);
                volunteer.encodePassword();
                shelterVolunteerRepository.save(volunteer);
            }

            if (!shelterVolunteerRepository.existsById("volunteer2")) {
                ShelterVolunteer volunteer = new ShelterVolunteer();
                volunteer.setEmail("volunteer2@dbsample.app");
                volunteer.setId("volunteer2");
                volunteer.setPassword(defaultPassword);
                volunteer.setUserShelter(shelter);
                volunteer.encodePassword();
                shelterVolunteerRepository.save(volunteer);
            }
        }
        if(shelterRepository.findByEmail("shelter2@dbsample.app").isEmpty()) {
            Shelter shelter = new Shelter();
            shelter.setName("shelter2");
            shelter.setEmail("shelter2@dbsample.app");
            shelter.setMobile("420420422");
            shelter.setActive(true);
            shelterRepository.save(shelter);
            if (!shelterVolunteerRepository.existsById("volunteer3")) {
                ShelterVolunteer volunteer = new ShelterVolunteer();
                volunteer.setEmail("volunteer3@dbsample.app");
                volunteer.setId("volunteer3");
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

        // User for favouriteButton tests
        if (!userRepository.existsById("userFavourite")) {
            User user = new User();
            user.setEmail("userFavourite@sample.app");
            user.setId("userFavourite");
            user.setPassword(defaultPassword);
            user.encodePassword();
            userRepository.save(user);
        }
        // Pets for favouriteButton tests
        if (!petRepository.existsById(1L)) {
            Pet pet = new Pet();
            pet.setName("Rex");
            pet.setColor("Black/White");
            pet.setSize("Big");
            pet.setWeight(15D);
            pet.setAge("4 years old");
            pet.setDescription("Very active");
            pet.setBreed("Dalmatian");
            pet.setImg("https://www.webconsultas.com/sites/default/files/styles/wc_adaptive_image__small/public/temas/caracteristicas_dalmata.jpg");
            petRepository.save(pet);
        }
        if (!petRepository.existsById(2L)) {
            Pet pet = new Pet();
            pet.setName("Max");
            pet.setColor("Brown/Black");
            pet.setSize("Big");
            pet.setWeight(12D);
            pet.setAge("10 years old");
            pet.setDescription("Likes to relax and cuddle");
            pet.setBreed("German Shepherd");
            pet.setImg("https://www.aon.es/personales/seguro-perro-gato/wp-content/uploads/sites/2/2021/06/pastor-aleman-3.jpg");
            petRepository.save(pet);
        }
        if (!petRepository.existsById(3L)) {
            Pet pet = new Pet();
            pet.setName("Fifi");
            pet.setColor("White");
            pet.setSize("Small");
            pet.setWeight(12D);
            pet.setAge("3 years old");
            pet.setDescription("Likes to relax and cuddle");
            pet.setBreed("Croatishe pupi");
            pet.setImg("https://hips.hearstapps.com/hmg-prod/images/bichon-frise-1660897169.jpg?crop=0.6666666666666666xw:1xh;center,top&resize=980:*");
            petRepository.save(pet);
        }
        //One of them favourited to test functionality
        if(!favouritedPetsRepository.existsById(1L)){
            FavouritedPets favPets = new FavouritedPets();
            favPets.setUserId("userFavourite");
            favPets.setPetId(1L);
            favouritedPetsRepository.save(favPets);
        }
    }
}
