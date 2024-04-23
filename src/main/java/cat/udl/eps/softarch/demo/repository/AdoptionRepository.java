package cat.udl.eps.softarch.demo.repository;

import cat.udl.eps.softarch.demo.domain.Adoption;
import cat.udl.eps.softarch.demo.domain.Location;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface AdoptionRepository extends CrudRepository<Adoption, Long>, PagingAndSortingRepository<Adoption, Long> {
}
