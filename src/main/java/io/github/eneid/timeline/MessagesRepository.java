package io.github.eneid.timeline;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Repository;

@RepositoryRestResource(collectionResourceRel = "messages", path = "messages")
public interface MessagesRepository extends PagingAndSortingRepository<Message, Long> {
}
