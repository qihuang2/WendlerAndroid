package com.android.wendler.wendlerandroid.main.model;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by QiFeng on 7/3/17.
 */
public class LiftTest {

    Lift mLift;

    @Before
    public void setup(){
        mLift = new Lift("test", 130, 3, 5);
    }


    @Test
    public void checkStats(){
        assertEquals(mLift.getMax(), 130);
        assertEquals(mLift.getWeek(), 3);
    }

}