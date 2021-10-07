package com.appsdeveloperblog.app.ws.service.impl;

import com.appsdeveloperblog.app.ws.entity.AddressEntity;
import com.appsdeveloperblog.app.ws.entity.UserEntity;
import com.appsdeveloperblog.app.ws.exceptions.UserServiceException;
import com.appsdeveloperblog.app.ws.shared.Utils;
import com.appsdeveloperblog.app.ws.shared.dto.AddressDto;
import com.appsdeveloperblog.app.ws.shared.dto.UserDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

import com.appsdeveloperblog.app.ws.repository.UserRepository;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Pawan on 19/09/21.
 */
class UserServiceImplTest {

    @InjectMocks
    UserServiceImpl userService;

    @Mock
    UserRepository userRepository;

    @Mock
    Utils utils;

    @Mock
    BCryptPasswordEncoder bCryptPasswordEncoder;

    String userId = "12guygu";
    String addressId = "ad123";
    String encryptedPassword = "pawan132";
    UserEntity userEntity;

    @BeforeEach
    void setUp() throws Exception {

        MockitoAnnotations.initMocks(this);

        userEntity = new UserEntity();
        userEntity.setId(1L);
        userEntity.setUserId(userId);
        userEntity.setFirstName("Pawan");
        userEntity.setLastName("Vishwakarma");
        userEntity.setEncryptedPassword(encryptedPassword);
        userEntity.setAddresses(getAddressesEntity());
    }

    @Test
    final void testGetUser() {
        when( userRepository.findByEmail( anyString() ) ).thenReturn( userEntity );

        UserDto userDto = userService.getUser("test@gmail.com");

        assertNotNull(userDto);
        assertEquals(userEntity.getFirstName(), userDto.getFirstName());
    }

    @Test
    final void testGetUser_UserNotFoundException() {
        when( userRepository.findByEmail( anyString() ) ).thenReturn( null );
        assertThrows(UserServiceException.class,
                () -> {
                    userService.getUser("test@gmail.com");
                }
        );
    }

    @Test
    final void testCreateUser_CreateUserServiceException() {
        when( userRepository.findByEmail( anyString() ) ).thenReturn( userEntity );

        UserDto userDto = new UserDto();
        userDto.setEmail("pawan1794@gmail.com");

        assertThrows(UserServiceException.class,
                () -> {
                    userService.createUser(userDto);
                }
        );
    }

    @Test
    final void testCreateUser() {
        when( userRepository.findByEmail( anyString() ) ).thenReturn( null );
        when(utils.generateAddressId(anyInt())).thenReturn(addressId);
        when(utils.generateUserId(anyInt())).thenReturn(userId);
        when(bCryptPasswordEncoder.encode(anyString())).thenReturn(encryptedPassword);
        when(userRepository.save(any(UserEntity.class))).thenReturn(userEntity);

        UserDto userDto = new UserDto();
        userDto.setFirstName("Pawan");
        userDto.setLastName("Vishwakarma");
        userDto.setEmail("pawan1794@gmail.com");
        userDto.setPassword("pawan132");
        userDto.setAddresses(getAddressesDto());

        UserDto returnedValue = userService.createUser(userDto);

        assertNotNull(returnedValue);
        assertEquals(userEntity.getFirstName(), returnedValue.getFirstName());
        assertEquals(userEntity.getLastName(), returnedValue.getLastName());
        assertNotNull(returnedValue.getUserId());
        assertEquals(userEntity.getAddresses().size(), returnedValue.getAddresses().size());
        verify(utils, times(userEntity.getAddresses().size())).generateAddressId(anyInt());
        verify(bCryptPasswordEncoder, times(1)).encode(userDto.getPassword());
        verify(userRepository, times(1)).save(any(UserEntity.class));
    }

    private List<AddressDto> getAddressesDto() {
        AddressDto shippingAddressDto = new AddressDto();
        shippingAddressDto.setCity("Varanasi");
        shippingAddressDto.setCountry("India");
        shippingAddressDto.setPostalCode("221002");
        shippingAddressDto.setStreetName("Hukulganj");
        shippingAddressDto.setType("shipping");

        AddressDto billingAddressDto = new AddressDto();
        billingAddressDto.setCity("Varanasi");
        billingAddressDto.setCountry("India");
        billingAddressDto.setPostalCode("221002");
        billingAddressDto.setStreetName("Hukulganj");
        billingAddressDto.setType("billing");

        List<AddressDto> addresses = new ArrayList<>();
        addresses.add(shippingAddressDto);
        addresses.add(billingAddressDto);

        return addresses;
    }

    private List<AddressEntity> getAddressesEntity() {
        AddressEntity shippingAddressEntity = new AddressEntity();
        shippingAddressEntity.setCity("Varanasi");
        shippingAddressEntity.setCountry("India");
        shippingAddressEntity.setPostalCode("221002");
        shippingAddressEntity.setStreetName("Hukulganj");
        shippingAddressEntity.setType("shipping");

        AddressEntity billingAddressEntity = new AddressEntity();

        billingAddressEntity.setCity("Varanasi");
        billingAddressEntity.setCountry("India");
        billingAddressEntity.setPostalCode("221002");
        billingAddressEntity.setStreetName("Hukulganj");
        billingAddressEntity.setType("billing");

        List<AddressEntity> addresses = new ArrayList<>();
        addresses.add(shippingAddressEntity);
        addresses.add(billingAddressEntity);

        return addresses;
    }
}
