package com.appsdeveloperblog.app.ws.repository;

import com.appsdeveloperblog.app.ws.entity.RoleEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by Pawan on 02/10/22
 */
@Repository
public interface RoleRepository extends CrudRepository<RoleEntity, Long> {
    RoleEntity findAllByRole(String role);
}
