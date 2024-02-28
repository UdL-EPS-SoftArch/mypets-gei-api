package cat.udl.eps.softarch.demo.repository;

import cat.udl.eps.softarch.demo.domain.Admin;
import cat.udl.eps.softarch.demo.domain.User;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface AdminRepository extends CrudRepository<Admin, String>, PagingAndSortingRepository<Admin, String> {
    List<User> findByIdContaining(@Param("text") String text);
}
