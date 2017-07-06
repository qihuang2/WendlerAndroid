package com.android.wendler.wendlerandroid.main.model;

import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by QiFeng on 7/6/17.
 */
public class LoginBodyTest {


    @Test
    public void testSerialize(){
        Gson gson = new GsonBuilder().setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
                .excludeFieldsWithoutExposeAnnotation()
                .create();

        LoginBody body = new LoginBody("TEST");
        assertEquals(gson.toJson(body),"{\"token\":\"TEST\"}");
    }

}