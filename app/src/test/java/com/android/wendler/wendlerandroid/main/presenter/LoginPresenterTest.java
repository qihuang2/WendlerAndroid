package com.android.wendler.wendlerandroid.main.presenter;

import com.android.wendler.wendlerandroid.main.contract.LoginContract;
import com.android.wendler.wendlerandroid.main.model.User;
import com.android.wendler.wendlerandroid.testUtils.ImmediateScheduler;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInResult;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.mockito.InOrder;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;

import io.reactivex.Observable;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.inOrder;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.verify;
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
    LoginContract.View mView;

    @InjectMocks
    LoginPresenter mLoginPresenter;

    @Mock
    GoogleSignInResult mGoogleSignInResult;

    @Before
    public void setUp() {
        ImmediateScheduler.initSchedulers();

        assertNull(mLoginPresenter.mView);

        mLoginPresenter.bind(mView);
        assertNotNull(mLoginPresenter.mView);

        assertNotNull(mLoginPresenter.mInteractor);
    }

    @After
    public void cleanUp(){
        mLoginPresenter.unbind();
        assertNull(mLoginPresenter.mView);
        assertNull(mLoginPresenter.mDisposable);
    }

    @Test
    public void activityResultBad(){

        //isSuccess returns false
        mLoginPresenter.activityResult(mGoogleSignInResult);
        verify(mView).showGoogleSignInError();
    }


    @Test
    public void activityResultAccountNull(){

        // account is null
        when(mGoogleSignInResult.isSuccess()).thenReturn(true);
        when(mGoogleSignInResult.getSignInAccount()).thenReturn(null);

        mLoginPresenter.activityResult(mGoogleSignInResult);

        verify(mView).showGoogleSignInError();
    }

    @Test
    public void activityResultGood(){

        GoogleSignInAccount account = mock(GoogleSignInAccount.class);
        LoginPresenter loginSpy = spy(mLoginPresenter);

        when(mGoogleSignInResult.isSuccess()).thenReturn(true);
        when(mGoogleSignInResult.getSignInAccount()).thenReturn(account);
        when(account.getIdToken()).thenReturn("token");
        doNothing().when(loginSpy).attemptLogin(anyString());

        loginSpy.activityResult(mGoogleSignInResult);

        verify(mView, never()).showGoogleSignInError();
        verify(loginSpy).attemptLogin(anyString());
    }


    @Test
    public void attemptLoginIn(){
        User user = mock(User.class);

        when(mInteractor.attemptLogin(anyString())).thenReturn(
                Observable.just(user)
        );

        mLoginPresenter.attemptLogin(anyString());

        assertNotNull(mLoginPresenter.mDisposable);

        InOrder inOrder = inOrder(mView);
        inOrder.verify(mView).showProgress(true);
        inOrder.verify(mView).login(user);
        inOrder.verify(mView).showProgress(false);
    }

    @Test
    public void attemptLoginFail(){
        when(mInteractor.attemptLogin(anyString()))
                .thenReturn(Observable.<User>error(new Throwable("Fake error")));

        mLoginPresenter.attemptLogin(anyString());

        InOrder inOrder = inOrder(mView);
        inOrder.verify(mView).showProgress(true);
        inOrder.verify(mView).showBadConnectionToast();
        inOrder.verify(mView).showProgress(false);
    }
}