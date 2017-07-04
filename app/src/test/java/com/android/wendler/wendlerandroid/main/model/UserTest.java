package com.android.wendler.wendlerandroid.main.model;

import android.annotation.SuppressLint;
import android.content.SharedPreferences;

import com.android.wendler.wendlerandroid.utils.SharedPrefUtils;

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

        verify(editor).putInt(SharedPrefUtils.KEY_DL_WEEK, mUser.getDeadLift().getWeek());
        verify(editor).putInt(SharedPrefUtils.KEY_DL_MAX, mUser.getDeadLift().getMax());

        verify(editor).putInt(SharedPrefUtils.KEY_SQ_MAX, mUser.getSquat().getMax());
        verify(editor).putInt(SharedPrefUtils.KEY_SQ_WEEK, mUser.getSquat().getWeek());

        verify(editor).putInt(SharedPrefUtils.KEY_BE_MAX, mUser.getBench().getMax());
        verify(editor).putInt(SharedPrefUtils.KEY_BE_WEEK, mUser.getBench().getWeek());

        verify(editor).putInt(SharedPrefUtils.KEY_OH_MAX, mUser.getOverhead().getMax());
        verify(editor).putInt(SharedPrefUtils.KEY_OH_WEEK, mUser.getOverhead().getWeek());

        verify(editor).putString(SharedPrefUtils.KEY_TOKEN, mUser.getToken());
    }
}