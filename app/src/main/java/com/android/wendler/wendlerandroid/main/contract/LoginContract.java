package com.android.wendler.wendlerandroid.main.contract;

import com.android.wendler.wendlerandroid.main.model.User;
import com.google.android.gms.auth.api.signin.GoogleSignInResult;

import io.reactivex.Observable;

/**
 * Created by QiFeng on 6/30/17.
 */

public class LoginContract {

    public interface Presenter{
        void bind(View view);
        void unbind();
        void activityResult(GoogleSignInResult result);
    }

    public interface View{
        void showProgress(boolean show);
        void showBadConnectionToast();
        void showServerErrorToast();
        void showGoogleSignInError();
        void login(User user);
    }

    public interface Interactor{
        Observable<User> attemptLogin(String gToken);
    }
}
