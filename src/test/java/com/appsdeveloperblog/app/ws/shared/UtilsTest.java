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
//    @Disabled
    final void testHasTokenNotExpired() {

        String token = "eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJwYXdhbkBlYXN5b3BzLmluIiwiZXhwIjoxNjQwOTYxODYzfQ.Zkj_xu45M_1dQAYs94ObclIusT2XvcAQgEd2t3TY1EZhzOdOrSRivVnOBMk904MQJk_C8OmhcgXgBjGcBYz8fw";
        String token2 = utils.generateEmailVerificationToken("chdkl");
        assertNotNull(token2);
        boolean hasTokenExpired = Utils.hasTokenExpired(token2);
        assertFalse(hasTokenExpired);
    }


    @Test
//    @Disabled
    final void testHasTokenExpired() {

        String token = "eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJwYXdhbkBlYXN5b3BzLmluIiwiZXhwIjoxNjMyOTE0NzI2fQ.PjVRXwWeO9-PrC9nUd_6o4-pvtosVPyiFCPkB-jzoOBKAWqA49y6dvp3Pkv-EO5Agfn4HQMsJha8z_oIYaKBRw";
        boolean hasTokenExpired = Utils.hasTokenExpired(token);
        assertTrue(hasTokenExpired);
    }
}
