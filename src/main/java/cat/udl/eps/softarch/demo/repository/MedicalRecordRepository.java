package cat.udl.eps.softarch.demo.repository;

import cat.udl.eps.softarch.demo.domain.MedicalRecord;
import cat.udl.eps.softarch.demo.domain.Pet;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;

@RepositoryRestResource
public interface MedicalRecordRepository extends CrudRepository < MedicalRecord, Long > , PagingAndSortingRepository < MedicalRecord, Long > {

    List < MedicalRecord > findByPet(@Param("pet") Pet pet);
}