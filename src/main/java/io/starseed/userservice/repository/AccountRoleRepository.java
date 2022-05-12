package io.starseed.userservice.repository;

import io.starseed.userservice.domain.AccountRole;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountRoleRepository extends JpaRepository<AccountRole, Long> {
}
