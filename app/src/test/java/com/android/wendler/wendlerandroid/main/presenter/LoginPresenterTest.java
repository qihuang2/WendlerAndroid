package com.android.wendler.wendlerandroid.main.presenter;

import com.android.wendler.wendlerandroid.main.contract.LoginContract;
import com.android.wendler.wendlerandroid.main.model.Lift;
import com.android.wendler.wendlerandroid.main.model.User;
import com.android.wendler.wendlerandroid.testUtils.ImmediateScheduler;

import org.junit.Before;
import org.junit.Rule;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;

import java.util.concurrent.Callable;

import io.reactivex.Observable;
import io.reactivex.Scheduler;
import io.reactivex.android.plugins.RxAndroidPlugins;
import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Function;
import io.reactivex.plugins.RxJavaPlugins;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

/**
 * Created by QiFeng on 7/3/17.
 */
public class LoginPresenterTest {


    @Rule
    public MockitoRule mMockitoRule = MockitoJUnit.rule();

    @Mock
    public LoginContract.Interactor mInteractor;


    @Mock
    private User mUser;

    @Before
    public void setUp() {

        mUser = new User(
                "123",
                "Qi",
                "Huang",
                "email",
                new Lift(123,2),
                new Lift(12, 0),
                new Lift(140, 1),
                new Lift(100, 3)
        );


        when(mInteractor.attemptLogin(anyString())).thenReturn(
                Observable.just(mUser)
        );

        ImmediateScheduler.initSchedulers();
    }

}