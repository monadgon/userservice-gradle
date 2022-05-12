package io.starseed.userservice.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedHashSet;
import java.util.Set;

import static javax.persistence.GenerationType.AUTO;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Account {
    @Id
    @GeneratedValue(strategy = AUTO)
    private Long id;
    private String name;
    private String username; // email or something
    private String password;
    @ManyToMany(fetch = FetchType.EAGER)
    private Collection<Role> roles = new ArrayList<>();
    //@OneToMany(mappedBy = "account", fetch = FetchType.LAZY) //
    //private Collection<AccountRole> roles = new ArrayList<>(); // N + 1 문제
    //private Set<AccountRole> roles = new LinkedHashSet<>();
}
