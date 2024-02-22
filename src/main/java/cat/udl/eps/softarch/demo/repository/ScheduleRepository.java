package cat.udl.eps.softarch.demo.repository;

import cat.udl.eps.softarch.demo.domain.Schedule;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.repository.PagingAndSortingRepository;


import java.util.List;

public interface ScheduleRepository extends CrudRepository<Schedule, Long>, PagingAndSortingRepository<Schedule, Long>{
    List<Schedule> findByIdContaining(@Param("text") String text);

}
