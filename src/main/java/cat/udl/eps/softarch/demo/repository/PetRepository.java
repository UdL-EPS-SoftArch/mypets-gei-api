package cat.udl.eps.softarch.demo.repository;
import cat.udl.eps.softarch.demo.domain.Pet;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface PetRepository extends CrudRepository<Pet, Long>, PagingAndSortingRepository<Pet, Long> {
    List<Pet> findBySize(@Param("size") String size);
    List<Pet> findByName(@Param("name") String name);
    List<Pet> findByIsAdopted(@Param("isAdopted") Boolean isAdopted);
    List<Pet> findByBreed(@Param("breed") String breed);
    //Pet findByShelter(@Param("id") Long shelterId);

}
