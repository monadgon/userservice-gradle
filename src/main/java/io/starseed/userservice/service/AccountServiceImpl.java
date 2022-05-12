package io.starseed.userservice.service;

import io.starseed.userservice.domain.AccountRole;
import io.starseed.userservice.domain.Role;
import io.starseed.userservice.domain.Account;
import io.starseed.userservice.repository.AccountRoleRepository;
import io.starseed.userservice.repository.RoleRepository;
import io.starseed.userservice.repository.AccountRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class AccountServiceImpl implements AccountService, UserDetailsService {
    private final AccountRepository accountRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;
    private final AccountRoleRepository accountRoleRepository;  // 사용하지 않는다.

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Account account = accountRepository.findByUsername(username);
        if(account == null){
            log.error("User not found in the database");
            throw new UsernameNotFoundException("User not found in the database");
        } else {
            log.info("User found in the database:{}", username);
        }
        Collection<SimpleGrantedAuthority> authorities = new ArrayList<>();
        account.getRoles().forEach(role -> {
            authorities.add( new SimpleGrantedAuthority(role.getName()));
            //authorities.add( new SimpleGrantedAuthority(accountRole.getRole().getName()));
        });
        return new org.springframework.security.core.userdetails.User(account.getUsername(), account.getPassword(), authorities);
    }

    @Override
    public Account saveAccount(Account account) {
        log.info("Saving new user {} to the database", account.getName());
        account.setPassword(passwordEncoder.encode(account.getPassword()));
        return accountRepository.save(account);
    }

    @Override
    public Role saveRole(Role role) {
        log.info("Saving new role {} to the database", role.getName());
        return roleRepository.save(role);
    }

    @Override
    public void addRoleToAccount(String username, String roleName) {
        log.info("Adding role {} to user {} to the database", roleName, username);
        Account user = accountRepository.findByUsername(username);
        Role role = roleRepository.findByName(roleName);
        user.getRoles().add(role);
        /*
        AccountRole accountRole = new AccountRole();
        accountRole.setAccount(user);
        accountRole.setRole(role);

        accountRoleRepository.save(accountRole);*/
    }

    @Override
    public Account getAccount(String username) {
        log.info("Fetching user {}", username);
        return accountRepository.findByUsername(username);
    }

    @Override
    public List<Account> getAccounts() {
        log.info("Fetching all users");
        return accountRepository.findAll();
    }


}
