package io.github.eneid.actions;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface QuickActionsRepository extends PagingAndSortingRepository<QuickAction, Long> {
}
