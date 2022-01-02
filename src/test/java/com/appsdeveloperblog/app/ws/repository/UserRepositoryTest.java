package com.appsdeveloperblog.app.ws.repository;

import com.appsdeveloperblog.app.ws.entity.AddressEntity;
import com.appsdeveloperblog.app.ws.entity.UserEntity;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Pawan on 30/12/21.
 */
@ExtendWith(SpringExtension.class)
@SpringBootTest
class UserRepositoryTest {

    @Autowired
    UserRepository userRepository;

    final String USER_ID = "12guygu";
    final String SHIPPING_ADDRESS_ID = "ad123";
    final String BILLING_ADDRESS_ID = "ad231";
    final String ENCRYPTED_PASSWORD = "pawan132";

    static boolean recordsCreated = false;

    @BeforeEach
    void setup() throws Exception {
        if(!recordsCreated) {
            createRecords();
        } else {
            System.out.println("Records already created.");
        }
    }

    @Test
    final void testGetVerifiedUsers() {

        Pageable pageable = PageRequest.of(0, 2);
        Page<UserEntity> pages = userRepository.findAllUsersWithConfirmedEmailAddress(pageable);
        assertNotNull(pages);

        List<UserEntity> userEntities = pages.getContent();
        assertNotNull(userEntities);
        assertTrue(userEntities.size() == 1);
    }

    @Test
    final void testFindUserByFirstName() {
        String firstName = "Aman";
        List<UserEntity> userEntities = userRepository.findUserByFirstName(firstName);
        assertNotNull(userEntities);
        assertTrue(userEntities.size() == 1);
        assertTrue(firstName.equals(userEntities.get(0).getFirstName()));

    }

    @Test
    final void testFindUserByLastName() {
        String lastName = "Vishwakarma";
        List<UserEntity> userEntities = userRepository.findUserByLastName(lastName);
        assertNotNull(userEntities);
        assertTrue(userEntities.size() == 1);
        assertTrue(lastName.equals(userEntities.get(0).getLastName()));

    }

    @Test
    final void testFindUserByKeyword() {
        String keyword = "man";
        List<UserEntity> userEntities = userRepository.findUserByKeyword(keyword);
        assertNotNull(userEntities);
        assertTrue(userEntities.size() == 1);
        assertTrue(userEntities.get(0).getFirstName().contains(keyword));

    }

    @Test
    final void testFindUserFirstNameAndLastNameByKeyword() {
        String keyword = "man";
        List<Object[]> list = userRepository.findUserFirstNameAndLastNameByKeyword(keyword);
        assertNotNull(list);
        assertTrue(list.size() == 1);
        Object[] objects = list.get(0);
        assertTrue(objects.length == 2);
        String firstName = String.valueOf(objects[0]);
        String lastName = String.valueOf(objects[1]);
        assertTrue("Aman".equals(firstName) && "Vishwakarma".equals(lastName));

    }

    @Test
    final void testPpdateUserEmailVerificationStatus() {
        boolean status = false;
        userRepository.updateUserEmailVerificationStatus(status, USER_ID);
        UserEntity storeDetails = userRepository.findByUserId(USER_ID);
        assertNotNull(storeDetails);
        assertTrue(storeDetails.getEmailVerificationStatus() == status);
    }

    private void createRecords() {
        UserEntity userEntity = new UserEntity();
        userEntity.setId(1L);
        userEntity.setUserId(USER_ID);
        userEntity.setFirstName("Aman");
        userEntity.setLastName("Vishwakarma");
        userEntity.setEmail("aman@gmail.com");
        userEntity.setEncryptedPassword(ENCRYPTED_PASSWORD);
        userEntity.setEmailVerificationStatus(true);

        AddressEntity shippingAddressEntity = new AddressEntity();

        shippingAddressEntity.setAddressId(SHIPPING_ADDRESS_ID);
        shippingAddressEntity.setCity("Varanasi");
        shippingAddressEntity.setCountry("India");
        shippingAddressEntity.setPostalCode("221002");
        shippingAddressEntity.setStreetName("Hukulganj");
        shippingAddressEntity.setType("shipping");

        AddressEntity billingAddressEntity = new AddressEntity();

        billingAddressEntity.setAddressId(BILLING_ADDRESS_ID);
        billingAddressEntity.setCity("Varanasi");
        billingAddressEntity.setCountry("India");
        billingAddressEntity.setPostalCode("221002");
        billingAddressEntity.setStreetName("Hukulganj");
        billingAddressEntity.setType("billing");

        List<AddressEntity> addresses = new ArrayList<>();
        addresses.add(shippingAddressEntity);
        addresses.add(billingAddressEntity);

        userEntity.setAddresses(addresses);

        userRepository.save(userEntity);

        recordsCreated = true;
    }
}
