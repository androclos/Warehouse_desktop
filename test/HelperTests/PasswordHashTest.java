package HelperTests;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import Helper.PasswordHash;
import junit.framework.Assert;

/**
 *
 * @author Pifko
 */
public class PasswordHashTest {
    
    public PasswordHashTest() {
  
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }

    @Test
    public void HashTest(){
        
        String password1 = "alma";
        String salt1 =  "korte";
        
        String password2 = "elso";
        String salt2 =  "masodik";
        
        Assert.assertEquals(PasswordHash.GetHashedPassword(password1, salt1), "fd8e8bb75f2841399da95307201d61ec6c05d87c");
        Assert.assertEquals(PasswordHash.GetHashedPassword(password2, salt2), "8fe49a8ddf819520fdaddcfae6e7377ce485b6bd");

        
    }   
    
}
