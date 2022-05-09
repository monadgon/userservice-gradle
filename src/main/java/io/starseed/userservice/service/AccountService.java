package io.starseed.userservice.service;

import io.starseed.userservice.domain.Role;
import io.starseed.userservice.domain.Account;

import java.util.List;

public interface AccountService {
    Account saveAccount(Account account);
    Role saveRole(Role role);
    void addRoleToAccount(String username, String roleName);
    Account getAccount(String username);
    List<Account> getAccounts(); // must be pagination
}
