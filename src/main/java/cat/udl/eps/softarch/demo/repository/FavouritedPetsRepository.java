package cat.udl.eps.softarch.demo.repository;

import cat.udl.eps.softarch.demo.domain.FavouritedPets;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface FavouritedPetsRepository extends CrudRepository<FavouritedPets, Long>, PagingAndSortingRepository<FavouritedPets, Long> {
    List<FavouritedPets> findByPetId(@Param("petId") Long petId);
    List<FavouritedPets> findByUserId(@Param("userId") String userId);

    boolean existsByPetId(Long petToCreate);
}
