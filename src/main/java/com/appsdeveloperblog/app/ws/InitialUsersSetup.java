package com.appsdeveloperblog.app.ws;

import com.appsdeveloperblog.app.ws.entity.AuthorityEntity;
import com.appsdeveloperblog.app.ws.entity.RoleEntity;
import com.appsdeveloperblog.app.ws.repository.AuthorityRepository;
import com.appsdeveloperblog.app.ws.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

/**
 * Created by Pawan on 05/10/22.
 */
@Component
public class InitialUsersSetup {

    @Autowired
    AuthorityRepository authorityRepository;

    @Autowired
    RoleRepository roleRepository;

    @EventListener
    public void onApplicationEvent(ApplicationReadyEvent event ) {
        System.out.println("ApplicationReadyEvent running");

        AuthorityEntity readAuthority = createAuthority("READ_AUTHORITY");
        AuthorityEntity writeAuthority = createAuthority("WRITE_AUTHORITY");
        AuthorityEntity deleteAuthority = createAuthority("DELETE_AUTHORITY");
    }

    private AuthorityEntity createAuthority(String name) {

        AuthorityEntity authorityEntity = authorityRepository.findAllByName(name);

        if(authorityEntity == null) {
            authorityEntity = new AuthorityEntity(name);
            authorityRepository.save(authorityEntity);
        }
        return authorityEntity;
    }

    private RoleEntity createRole(String role) {

        RoleEntity roleEntity = roleRepository.findAllByRole(role);

        if(roleEntity == null) {
            roleEntity = new RoleEntity(role);
            roleRepository.save(roleEntity);
        }
        return roleEntity;
    }
}
