package cat.udl.eps.softarch.demo.repository;

import cat.udl.eps.softarch.demo.domain.Location;
import cat.udl.eps.softarch.demo.domain.Shelter;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface LocationRepository extends CrudRepository<Location, Long>, PagingAndSortingRepository<Location, Long> {

  Location findByShelterId(@Param("shelter_id") Long shelterId);
}
