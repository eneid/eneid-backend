package io.github.eneid.auth;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Repository;

@RepositoryRestResource(collectionResourceRel = "users", path = "users")
public interface AccountsRepository extends CrudRepository<Account, String> {

    Account findByEmail(String login);
}
