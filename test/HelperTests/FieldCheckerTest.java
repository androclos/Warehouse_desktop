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

import static Helper.FieldChecker.*;
import junit.framework.Assert;

/**
 *
 * @author Pifko
 */
public class FieldCheckerTest {
    
    public FieldCheckerTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Test
    public void DateCheckTest(){
        
        Assert.assertTrue(DateCheck("10-10-1920"));
        
        Assert.assertFalse(DateCheck("40-10-1920"));
        Assert.assertFalse(DateCheck("10-40-1920"));
        Assert.assertFalse(DateCheck("10-10-88920"));
        
        Assert.assertFalse(DateCheck("2003.10.03"));
    
    
    }
    
    @Test
    public void UserNameCheckTest(){
        
        Assert.assertTrue(UserNameCheck("Ujuser23"));
        
        Assert.assertFalse(UserNameCheck("a"));
        Assert.assertFalse(UserNameCheck("user23aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa"));
        Assert.assertFalse(UserNameCheck(""));

    
    }
    
    @Test
    public void PasswordCheckTest(){
        
        Assert.assertTrue(PasswordCheck("Ujuser23"));
        
        Assert.assertFalse(PasswordCheck(""));
        Assert.assertFalse(PasswordCheck("12342"));
        Assert.assertFalse(PasswordCheck("userusr"));
        Assert.assertFalse(PasswordCheck("userusrAA"));
        Assert.assertFalse(PasswordCheck("a3"));

    
    }

    
    @Test
    public void EmailCheckTest(){
        
        Assert.assertTrue(EmailCheck("nev@szolgaltato.hu"));
        
        Assert.assertFalse(EmailCheck(""));
        Assert.assertFalse(EmailCheck("nev"));
        Assert.assertFalse(EmailCheck("nevszolgaltato.hu"));
        Assert.assertFalse(EmailCheck("nev@nev2@szolgaltato.com"));
        Assert.assertFalse(EmailCheck("nev@szolgaltato.toolongstring"));

    
    }
    
    @Test
    public void AmountCheckTest(){
        
        Assert.assertTrue(AmountCheck("1024"));
        
        Assert.assertFalse(AmountCheck(""));
        Assert.assertFalse(AmountCheck("alma"));
        Assert.assertFalse(AmountCheck("123.3"));
        Assert.assertFalse(AmountCheck("0133"));
        Assert.assertFalse(AmountCheck("0xFF"));

    
    }
    

}
