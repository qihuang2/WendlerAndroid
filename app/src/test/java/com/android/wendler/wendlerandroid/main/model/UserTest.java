package com.android.wendler.wendlerandroid.main.model;

import android.annotation.SuppressLint;
import android.content.SharedPreferences;

import com.android.wendler.wendlerandroid.utils.SharedPrefUtils;
import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;

import java.util.ArrayList;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Created by QiFeng on 7/3/17.
 */
public class UserTest {

    @Rule
    public MockitoRule mMockitoRule = MockitoJUnit.rule();

    @Mock
    SharedPreferences mSharedPreferences;


    private User mUser;

    private Gson mGson;

    @Before
    public void setup() {

        mGson = new GsonBuilder().setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
                .excludeFieldsWithoutExposeAnnotation()
                .create();

        ArrayList<Lift> mLifts = new ArrayList<>();
        mLifts.add(new Lift("bench", 10, 0, 5));
        mLifts.add(new Lift("squat", 10, 0 ,10));
        mLifts.add(new Lift("overhead", 10, 0, 5));
        mLifts.add(new Lift("deadlift", 10, 0, 10));

        mUser = new User(
                "123",
                "Wi",
                "Huang",
                "qi@te.xome",
                mLifts
        );

        assertNull(mUser.getToken());

        mUser.setToken("12345");

        assertEquals("12345", mUser.getToken());
    }


    @Test
    public void loadUser(){
        when(mSharedPreferences.getString(SharedPrefUtils.KEY_USER, "{}")).thenReturn(json);
        when(mSharedPreferences.getString(SharedPrefUtils.KEY_TOKEN, null)).thenReturn("test");

        User.loadFromSP(mSharedPreferences, mGson);

        verify(mSharedPreferences).getString(SharedPrefUtils.KEY_USER, "{}");
        verify(mSharedPreferences).getString(SharedPrefUtils.KEY_TOKEN, null);
    }


    @SuppressLint("CommitPrefEdits")
    @Test
    public void testWrite() {

        SharedPreferences.Editor editor = mock(SharedPreferences.Editor.class);

        when(mSharedPreferences.edit()).thenReturn(editor);

        User.saveToSP(mSharedPreferences, mUser, mGson);

        verify(editor).putString(SharedPrefUtils.KEY_TOKEN, mUser.getToken());
        verify(editor).putString(SharedPrefUtils.KEY_USER, mGson.toJson(mUser));
    }

    private static final String json = "{\n" +
            "\"token\" : \"test\",\n" +
            "\"_id\": \"108034432866128207149\",\n" +
            "\"first_name\": \"py\",\n" +
            "\"last_name\": \"huang\",\n" +
            "\"email\": \"csc113project1@gmail.com\",\n" +
            "\"__v\": 0,\n" +
            "\"lifts\": [\n" +
            "{\n" +
            "\"week\": 2,\n" +
            "\"name\": \"deadlift\",\n" +
            "\"max\": 20,\n" +
            "\"_id\": \"5963dfd2df6f8b0011f2b68d\",\n" +
            "\"advance\": 10\n" +
            "},\n" +
            "{\n" +
            "\"week\": 0,\n" +
            "\"name\": \"bench\",\n" +
            "\"max\": 0,\n" +
            "\"_id\": \"5963dfd2df6f8b0011f2b68c\",\n" +
            "\"advance\": 5\n" +
            "},\n" +
            "{\n" +
            "\"week\": 0,\n" +
            "\"name\": \"squat\",\n" +
            "\"max\": 0,\n" +
            "\"_id\": \"5963dfd2df6f8b0011f2b68b\",\n" +
            "\"advance\": 10\n" +
            "},\n" +
            "{\n" +
            "\"week\": 1,\n" +
            "\"name\": \"overhead\",\n" +
            "\"max\": 0,\n" +
            "\"_id\": \"5963dfd2df6f8b0011f2b68a\",\n" +
            "\"advance\": 5\n" +
            "}\n" +
            "]\n" +
            "}";

    @Test
    public void gsonTest(){

        User user = mGson.fromJson(json, User.class);

        assertEquals(user.getId(), "108034432866128207149");
        assertEquals(user.getFirstName(), "py");
        assertEquals(user.getLastName(), "huang");
        assertEquals(user.getEmail(), "csc113project1@gmail.com");
        assertEquals(user.getToken(), "test");

        assertEquals(4, user.getLifts().size());

       // System.out.println(gson.toJson(user));
    }

}