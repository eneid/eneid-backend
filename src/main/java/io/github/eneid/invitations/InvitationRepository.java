package io.github.eneid.invitations;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(collectionResourceRel = "invitations", path = "invitations")
public interface InvitationRepository extends PagingAndSortingRepository<Invitation, Long>{

}
