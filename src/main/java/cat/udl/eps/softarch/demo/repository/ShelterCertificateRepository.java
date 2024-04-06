package cat.udl.eps.softarch.demo.repository;

import cat.udl.eps.softarch.demo.domain.ShelterCertificate;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import cat.udl.eps.softarch.demo.domain.Shelter;
import org.springframework.data.repository.query.Param;

public interface ShelterCertificateRepository extends CrudRepository<ShelterCertificate, Long>, PagingAndSortingRepository<ShelterCertificate, Long> {
    ShelterCertificate findByShelterServed(@Param("shelterServed") Shelter shelter);
}
