package com.appsdeveloperblog.app.ws;

import com.appsdeveloperblog.app.ws.entity.AuthorityEntity;
import com.appsdeveloperblog.app.ws.entity.RoleEntity;
import com.appsdeveloperblog.app.ws.entity.UserEntity;
import com.appsdeveloperblog.app.ws.repository.AuthorityRepository;
import com.appsdeveloperblog.app.ws.repository.RoleRepository;
import com.appsdeveloperblog.app.ws.repository.UserRepository;
import com.appsdeveloperblog.app.ws.shared.Authority;
import com.appsdeveloperblog.app.ws.shared.Roles;
import com.appsdeveloperblog.app.ws.shared.Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.Collection;

/**
 * Created by Pawan on 05/10/22.
 */
@Component
public class InitialUsersSetup {

    @Autowired
    AuthorityRepository authorityRepository;
    @Autowired
    RoleRepository roleRepository;
    @Autowired
    BCryptPasswordEncoder bCryptPasswordEncoder;
    @Autowired
    UserRepository userRepository;
    @Autowired
    Utils utils;

    @EventListener
    @Transactional
    public void onApplicationEvent(ApplicationReadyEvent event ) {
        System.out.println("ApplicationReadyEvent running");

        AuthorityEntity readAuthority = createAuthority(Authority.READ_AUTHORITY.name());
        AuthorityEntity writeAuthority = createAuthority(Authority.WRITE_AUTHORITY.name());
        AuthorityEntity deleteAuthority = createAuthority(Authority.DELETE_AUTHORITY.name());

        RoleEntity roleUser = createRole(Roles.ROLE_USER.name(), Arrays.asList(readAuthority, writeAuthority));
        RoleEntity roleAdmin = createRole(Roles.ROLE_ADMIN.name(), Arrays.asList(readAuthority, writeAuthority, deleteAuthority));

        if(roleAdmin != null) {
            UserEntity adminUser = userRepository.findByEmail("admin@gmail.com");
            if(adminUser == null) {
                adminUser = new UserEntity();
                adminUser.setFirstName("ADMIN");
                adminUser.setLastName("USER");
                adminUser.setEmail("admin@gmail.com");
                adminUser.setUserId(utils.generateUserId(30));
                adminUser.setEncryptedPassword(bCryptPasswordEncoder.encode("pawan132"));
                adminUser.setRoles(Arrays.asList(roleAdmin));

                userRepository.save(adminUser);
            }
        }
    }

    @Transactional
    private AuthorityEntity createAuthority(String name) {

        AuthorityEntity authorityEntity = authorityRepository.findAllByName(name);

        if(authorityEntity == null) {
            authorityEntity = new AuthorityEntity(name);
            authorityRepository.save(authorityEntity);
        }
        return authorityEntity;
    }

    @Transactional
    private RoleEntity createRole(String role, Collection<AuthorityEntity> authorities) {


        RoleEntity roleEntity = roleRepository.findAllByRole(role);

        if(roleEntity == null) {
            roleEntity = new RoleEntity(role);
            roleEntity.setAuthorities(authorities);
            roleRepository.save(roleEntity);
        }
        return roleEntity;
    }
}
