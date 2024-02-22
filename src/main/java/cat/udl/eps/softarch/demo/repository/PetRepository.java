package cat.udl.eps.softarch.demo.repository;

import cat.udl.eps.softarch.demo.domain.Pet;
import cat.udl.eps.softarch.demo.domain.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface PetRepository extends CrudRepository<Pet, Long>, PagingAndSortingRepository<Pet, Long> {
    List<User> findBySize(@Param("size") String size);
}
