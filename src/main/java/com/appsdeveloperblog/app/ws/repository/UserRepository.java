package com.appsdeveloperblog.app.ws.repository;

import com.appsdeveloperblog.app.ws.entity.UserEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface UserRepository extends PagingAndSortingRepository<UserEntity, Long> {

    UserEntity findByEmail(String email);
    UserEntity findByUserId(String userId);
    UserEntity findUserByEmailVerificationToken(String token);

    @Query(value = "select *from users u where u.EMAIL_VERIFICATION_STATUS = 'true'",
            countQuery = "select count(*) from users u where u.EMAIL_VERIFICATION_STATUS = 'true'",
            nativeQuery = true)
    Page<UserEntity> findAllUsersWithConfirmedEmailAddress(Pageable pageable);

    @Query(value = "select *from users u where u.first_name = ?1",
            nativeQuery = true)
    List<UserEntity> findUserByFirstName(String firstName);

    @Query(value = "select *from users u where u.last_name = :ln",
            nativeQuery = true)
    List<UserEntity> findUserByLastName( @Param("ln") String lastName);

    @Query(value = "select *from users u where u.first_name like %:keyword%",
            nativeQuery = true)
    List<UserEntity> findUserByKeyword( @Param("keyword") String keyword);

    @Query(value = "select first_name, last_name from users u where first_name like %:keyword% or last_name like %:keyword%",
            nativeQuery = true)
    List<Object[]> findUserFirstNameAndLastNameByKeyword( @Param("keyword") String keyword);

    @Transactional
    @Modifying
    @Query(value = "update users u set u.email_verification_status =:status where u.user_id =:userId",
            nativeQuery = true)
    void updateUserEmailVerificationStatus(@Param("status") boolean emailVerificationToken, @Param("userId") String userId);
}
