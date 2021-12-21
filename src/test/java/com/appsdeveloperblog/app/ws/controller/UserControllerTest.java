package com.appsdeveloperblog.app.ws.controller;

import com.appsdeveloperblog.app.ws.model.response.UserRest;
import com.appsdeveloperblog.app.ws.service.UserService;
import com.appsdeveloperblog.app.ws.shared.dto.AddressDto;
import com.appsdeveloperblog.app.ws.shared.dto.UserDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Created by Pawan on 21/10/21.
 */
public class UserControllerTest {

    @InjectMocks
    UserController userController;

    @Mock
    UserService userService;

    UserDto userDto;

    final String USER_ID = "12guygu";

    @BeforeEach
    void setup() throws Exception {
        MockitoAnnotations.initMocks(this);

        userDto = new UserDto();
        userDto.setFirstName("Pawan");
        userDto.setLastName("Vishwakarma");
        userDto.setEmail("pawan1794@gmail.com");
        userDto.setPassword("pawan132");
        userDto.setUserId(USER_ID);
        userDto.setAddresses(getAddressesDto());
    }

    @Test
    final void testGetUser() {
        when(userService.getUserByUserId(anyString())).thenReturn(userDto);

        UserRest userRest = userController.getUser(USER_ID);
        System.out.println(userRest.getUserId());
        assertNotNull(userRest);
        assertEquals(USER_ID, userRest.getUserId());
        assertEquals(userDto.getFirstName(), userRest.getFirstName());
        assertTrue(userDto.getAddresses().size() == userRest.getAddresses().size());
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
}
