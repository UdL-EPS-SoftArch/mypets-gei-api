package cat.udl.eps.softarch.demo.repository;

import cat.udl.eps.softarch.demo.domain.Location;
import cat.udl.eps.softarch.demo.domain.Shelter;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;


@RepositoryRestResource
public interface ShelterRepository extends CrudRepository<Shelter, Long>, PagingAndSortingRepository<Shelter, Long> {
    List<Shelter> findByName(@Param("name") String name);
    List<Shelter> findByLocatedAt(@Param("location") Location location);
}