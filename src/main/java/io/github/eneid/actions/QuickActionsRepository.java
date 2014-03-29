package io.github.eneid.actions;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Repository;

@RepositoryRestResource(collectionResourceRel = "actions", path = "actions")
public interface QuickActionsRepository extends PagingAndSortingRepository<QuickAction, Long> {
}
