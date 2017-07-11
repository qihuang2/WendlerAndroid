package com.android.wendler.wendlerandroid.main.model;

import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by QiFeng on 7/3/17.
 */
public class LiftTest {

    Lift mLift;

    Gson mGson = new GsonBuilder().setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
                .excludeFieldsWithoutExposeAnnotation()
                .create();

    private String json = "{\n" +
            "\"week\": 2,\n" +
            "\"name\": \"deadlift\",\n" +
            "\"max\": 20,\n" +
            "\"_id\": \"5963dfd2df6f8b0011f2b68d\",\n" +
            "\"advance\": 10\n" +
            "}";
    @Before
    public void setup(){
        mLift = new Lift("test", 130, 3, 5);
        mLift.setId("123");
    }


    @Test
    public void checkStats(){
        assertEquals(130, mLift.getMax());
        assertEquals(3, mLift.getWeek());
        assertEquals(5, mLift.advance);
        assertEquals("test", mLift.getName());
        assertEquals("123", mLift.getId());
    }

    @Test
    public void checkGson(){
        Lift lift = mGson.fromJson(json, Lift.class);

        assertEquals(2, lift.getWeek());
        assertEquals(20, lift.getMax());
        assertEquals(10, lift.advance);
        assertEquals("deadlift", lift.getName());

    }

    @Test
    public void testSerialize() {
        String string = mGson.toJson(mLift);

        assertEquals("{\"_id\":\"123\",\"max\":130,\"week\":3,\"name\":\"test\",\"advance\":5}", string);
    }


    @Test
    public void testAdvance(){
        assertEquals(3, mLift.getWeek());
        assertEquals(130, mLift.getMax());

        mLift.advanceWeek();

        assertEquals(0, mLift.getWeek());
        assertEquals(135, mLift.getMax());
    }
}