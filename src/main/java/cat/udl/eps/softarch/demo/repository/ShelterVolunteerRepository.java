package cat.udl.eps.softarch.demo.repository;

import cat.udl.eps.softarch.demo.domain.ShelterVolunteer;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface ShelterVolunteerRepository extends CrudRepository<ShelterVolunteer, Long>, PagingAndSortingRepository<ShelterVolunteer, Long> {
}
