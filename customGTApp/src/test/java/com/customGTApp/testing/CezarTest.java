package com.customGTApp.testing;

import org.junit.Test;

import java.util.Date;

import static org.junit.Assert.*;

public class CezarTest {
    private Cezar passChange;
    @Test
    public void cezarTestWeak1(){
        this.passChange = new Cezar("abc");
        assertEquals("bcd", passChange.encryptPass(PassType.WEAK));
    }
    @Test
    public void cezarTestWeak2(){
        this.passChange = new Cezar("ANA");
        assertEquals("BOB", passChange.encryptPass(PassType.WEAK));
    }
    @Test
    public void cezarTestWeak3(){
        this.passChange = new Cezar("ZY");
        assertEquals("AZ", passChange.encryptPass(PassType.WEAK));
    }
    @Test
    public void cezarTestWeak4(){
        this.passChange = new Cezar("bbbBBB");
        assertEquals("cccCCC", passChange.encryptPass(PassType.WEAK));
    }
    @Test
    public void cezarTestAverage1(){
        this.passChange = new Cezar("abc");
        assertEquals("efg", passChange.encryptPass(PassType.AVERAGE));
    }
    @Test
    public void cezarTestAverage2(){
        this.passChange = new Cezar("java");
        assertEquals("neze", passChange.encryptPass(PassType.AVERAGE));
    }
    @Test
    public void cezarTestAverage3(){
        this.passChange = new Cezar("xyz");
        assertEquals("bcd", passChange.encryptPass(PassType.AVERAGE));
    }
    @Test
    public void cezarTestComplex(){
        this.passChange = new Cezar("abc");
        Date date = new Date();
        assertEquals("bcd", passChange.encryptPass(PassType.WEAK));
    }
}