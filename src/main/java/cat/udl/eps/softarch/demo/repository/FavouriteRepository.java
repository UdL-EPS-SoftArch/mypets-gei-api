package cat.udl.eps.softarch.demo.repository;

import cat.udl.eps.softarch.demo.domain.Favourite;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface FavouriteRepository extends CrudRepository<Favourite, Long>, PagingAndSortingRepository<Favourite, Long> {
}
