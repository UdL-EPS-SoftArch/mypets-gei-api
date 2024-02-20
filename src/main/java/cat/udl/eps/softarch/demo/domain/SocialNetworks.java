package cat.udl.eps.softarch.demo.domain;

public class SocialNetworks extends UriEntity<Long>{
    Long id;
    String instagram;
    String twitter;


    @Override
    public Long getId() {
        return id;
    }
}
