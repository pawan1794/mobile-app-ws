package com.appsdeveloperblog.app.ws.security;

import com.appsdeveloperblog.app.ws.entity.AuthorityEntity;
import com.appsdeveloperblog.app.ws.entity.RoleEntity;
import com.appsdeveloperblog.app.ws.entity.UserEntity;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;

/**
 * Created by Pawan on 05/10/22
 */
public class UserPrincipal implements UserDetails {
    private static final long serialVersionUID = 5760595249697579333L;

    private String userId;
    private UserEntity userEntity;
    public UserPrincipal(UserEntity userEntity) {
        this.userEntity = userEntity;
        this.userId = userEntity.getUserId();
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {

        Collection<GrantedAuthority> grantedAuthorities = new HashSet<>();
        Collection<AuthorityEntity> authorities = new HashSet<>();

        Collection<RoleEntity> roles = this.userEntity.getRoles();

        if(roles == null) {
            return grantedAuthorities;
        }

        roles.forEach((role) -> {
            grantedAuthorities.add(new SimpleGrantedAuthority(role.getRole()));
            authorities.addAll(role.getAuthorities());
        });

        authorities.forEach((authority) -> {
            grantedAuthorities.add(new SimpleGrantedAuthority(authority.getName()));
        });

        return grantedAuthorities;
    }

    @Override
    public String getPassword() {
        return this.userEntity.getEncryptedPassword();
    }

    @Override
    public String getUsername() {
        return this.userEntity.getEmail();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return this.userEntity.getEmailVerificationStatus();
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }
}
