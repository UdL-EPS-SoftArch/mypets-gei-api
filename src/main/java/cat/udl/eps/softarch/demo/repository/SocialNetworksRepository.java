package cat.udl.eps.softarch.demo.repository;

import cat.udl.eps.softarch.demo.domain.SocialNetworks;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface SocialNetworksRepository extends CrudRepository<SocialNetworks,Long> {

}
