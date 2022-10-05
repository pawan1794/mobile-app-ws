package com.appsdeveloperblog.app.ws.repository;

import com.appsdeveloperblog.app.ws.entity.AuthorityEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by Pawan on 02/10/22
 */
@Repository
public interface

AuthorityRepository extends CrudRepository<AuthorityEntity, Long> {

    AuthorityEntity findAllByName(String name);
}
