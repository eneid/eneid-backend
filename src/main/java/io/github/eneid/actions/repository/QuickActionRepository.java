package io.github.eneid.actions.repository;

import io.github.eneid.actions.domain.QuickAction;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface QuickActionRepository extends PagingAndSortingRepository<QuickAction, Long> {
}
