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

    @Before
    public void setup() {
        mUser = new User(
                "123",
                "Wi",
                "Huang",
                "qi@te.xome",
                new Lift(12, 1),
                new Lift(34, 4),
                new Lift(12, 4),
                new Lift(78, 3)
        );

        assertNull(mUser.getToken());

        mUser.setToken("12345");
    }


    @Test
    public void loadUser(){
        User.loadFromSP(mSharedPreferences);

        verify(mSharedPreferences).getString(SharedPrefUtils.KEY_ID, null);
        verify(mSharedPreferences).getString(SharedPrefUtils.KEY_FIRST_NAME, null);
        verify(mSharedPreferences).getString(SharedPrefUtils.KEY_LAST_NAME, null);
        verify(mSharedPreferences).getString(SharedPrefUtils.KEY_EMAIL_NAME, null);

        verify(mSharedPreferences).getInt(SharedPrefUtils.KEY_DL_MAX, 0);
        verify(mSharedPreferences).getInt(SharedPrefUtils.KEY_DL_WEEK, 0);

        verify(mSharedPreferences).getInt(SharedPrefUtils.KEY_SQ_WEEK, 0);
        verify(mSharedPreferences).getInt(SharedPrefUtils.KEY_SQ_MAX, 0);

        verify(mSharedPreferences).getInt(SharedPrefUtils.KEY_BE_MAX, 0);
        verify(mSharedPreferences).getInt(SharedPrefUtils.KEY_BE_WEEK, 0);

        verify(mSharedPreferences).getInt(SharedPrefUtils.KEY_OH_WEEK, 0);
        verify(mSharedPreferences).getInt(SharedPrefUtils.KEY_OH_MAX, 0);

        verify(mSharedPreferences).getString(SharedPrefUtils.KEY_TOKEN, null);
    }


    @SuppressLint("CommitPrefEdits")
    @Test
    public void testWrite() {

        SharedPreferences.Editor editor = mock(SharedPreferences.Editor.class);

        when(mSharedPreferences.edit()).thenReturn(editor);

        User.saveToSP(mSharedPreferences, mUser);

        verify(editor).putString(SharedPrefUtils.KEY_ID, mUser.getId());
        verify(editor).putString(SharedPrefUtils.KEY_FIRST_NAME, mUser.getFirstName());
        verify(editor).putString(SharedPrefUtils.KEY_LAST_NAME, mUser.getLastName());
        verify(editor).putString(SharedPrefUtils.KEY_EMAIL_NAME, mUser.getEmail());

        verify(editor).putInt(SharedPrefUtils.KEY_DL_WEEK, mUser.getDeadlift().getWeek());
        verify(editor).putInt(SharedPrefUtils.KEY_DL_MAX, mUser.getDeadlift().getMax());

        verify(editor).putInt(SharedPrefUtils.KEY_SQ_MAX, mUser.getSquat().getMax());
        verify(editor).putInt(SharedPrefUtils.KEY_SQ_WEEK, mUser.getSquat().getWeek());

        verify(editor).putInt(SharedPrefUtils.KEY_BE_MAX, mUser.getBench().getMax());
        verify(editor).putInt(SharedPrefUtils.KEY_BE_WEEK, mUser.getBench().getWeek());

        verify(editor).putInt(SharedPrefUtils.KEY_OH_MAX, mUser.getOverhead().getMax());
        verify(editor).putInt(SharedPrefUtils.KEY_OH_WEEK, mUser.getOverhead().getWeek());

        verify(editor).putString(SharedPrefUtils.KEY_TOKEN, mUser.getToken());
    }

    private static final String json = "{\n" +
            "\"_id\": \"108034432866128207149\",\n" +
            "\"first_name\": \"john\",\n" +
            "\"last_name\": \"conner\",\n" +
            "\"email\": \"rer@gmail.com\",\n" +
            "\"__v\": 0,\n" +
            "\"overhead\": {\"max\": 10, \"week\": 0},\n" +
            "\"bench\": {\"max\": 100, \"week\": 0},\n" +
            "\"squat\": {\"max\": 10, \"week\": 1},\n" +
            "\"deadlift\": {\"max\": 10, \"week\": 0},\n" +
            "\"token\": \"test_token\"\n" +
            "}";

    @Test
    public void gsonTest(){
        Gson gson = new GsonBuilder().setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
                .excludeFieldsWithoutExposeAnnotation()
                .create();

        User user = gson.fromJson(json, User.class);

        assertEquals(user.getId(), "108034432866128207149");
        assertEquals(user.getFirstName(), "john");
        assertEquals(user.getLastName(), "conner");
        assertEquals(user.getEmail(), "rer@gmail.com");
        assertEquals(user.getToken(), "test_token");

        assertEquals(user.getOverhead().getMax(), 10);
        assertEquals(user.getOverhead().getWeek(), 0);

        assertEquals(user.getDeadlift().getWeek(), 0);
        assertEquals(user.getDeadlift().getMax(), 10);

        assertEquals(user.getBench().getMax(), 100);
        assertEquals(user.getBench().getWeek(), 0);

        assertEquals(user.getSquat().getMax(), 10);
        assertEquals(user.getSquat().getWeek(), 1);

       // System.out.println(gson.toJson(user));
    }
}