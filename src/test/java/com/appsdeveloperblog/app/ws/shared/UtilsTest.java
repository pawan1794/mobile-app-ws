package com.appsdeveloperblog.app.ws.shared;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.*;



/**
 * Created by Pawan on 21/10/21.
 */

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class UtilsTest {

    @Autowired
    Utils utils;

    @BeforeEach
    void setup() throws Exception {

    }

    @Test
    final void testGenerateUserId() {

        String userId = utils.generateAddressId(30);
        String userId2 = utils.generateAddressId(30);

        assertNotNull(userId);
        assertNotNull(userId2);
        assertTrue(userId.length() == 30);
        assertTrue(!userId.equals(userId2));
    }

    @Test
    @Disabled
    final void testHasTokenExpired() {

    }
}
