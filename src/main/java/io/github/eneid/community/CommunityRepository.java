package io.github.eneid.community;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(collectionResourceRel = "communities", path = "communities")
public interface CommunityRepository extends CrudRepository<Community, Long>{
}
