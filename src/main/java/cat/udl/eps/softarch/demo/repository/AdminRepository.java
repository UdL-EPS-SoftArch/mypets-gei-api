package cat.udl.eps.softarch.demo.repository;

import cat.udl.eps.softarch.demo.domain.Admin;
import cat.udl.eps.softarch.demo.domain.User;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface AdminRepository extends CrudRepository<Admin, Long>, PagingAndSortingRepository<Admin, Long> {

    /* Interface provides automatically, as defined in
     * https://docs.spring.io/spring-data/commons/docs/current/api/org/springframework/data/repository/CrudRepository.html
     * and
     * https://docs.spring.io/spring-data/commons/docs/current/api/org/springframework/data/repository/PagingAndSortingRepository.html
     * the methods: count, delete, deleteAll, deleteById, existsById, findAll, findAllById, findById, save, saveAll,...
     *
     * Additional methods like findByUsernameContaining can be defined following:
     * https://docs.spring.io/spring-data/jpa/docs/current/reference/html/#jpa.query-methods.query-creation
     */

    List<User> findByIdContaining(@Param("text") String text);
}
