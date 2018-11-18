package com.moommim.moommim_web.service;

import com.moommim.moommim_web.model.UserAccount;
import javax.servlet.http.HttpSession;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Nuntuch Thongyoo
 */
public class AuthenticationServiceImplTest {
    
    public AuthenticationServiceImplTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of login method, of class AuthenticationServiceImpl.
     */
    @Test
    public void testLogin() {
        System.out.println("login");
        String username = "";
        String password = "";
        AuthenticationServiceImpl instance = new AuthenticationServiceImpl();
        UserAccount expResult = null;
        UserAccount result = instance.login(username, password);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of logout method, of class AuthenticationServiceImpl.
     */
    @Test
    public void testLogout() {
        System.out.println("logout");
        HttpSession session = null;
        AuthenticationServiceImpl instance = new AuthenticationServiceImpl();
        boolean expResult = false;
        boolean result = instance.logout(session);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of isLogin method, of class AuthenticationServiceImpl.
     */
    @Test
    public void testIsLogin() {
        System.out.println("isLogin");
        HttpSession session = null;
        AuthenticationServiceImpl instance = new AuthenticationServiceImpl();
        boolean expResult = false;
        boolean result = instance.isLogin(session);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of ForgoutPassword method, of class AuthenticationServiceImpl.
     */
    @Test
    public void testForgoutPassword() {
        System.out.println("ForgoutPassword");
        String username = "";
        AuthenticationServiceImpl instance = new AuthenticationServiceImpl();
        boolean expResult = false;
        boolean result = instance.ForgoutPassword(username);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
