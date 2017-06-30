package com.android.wendler.wendlerandroid.main.contract;

/**
 * Created by QiFeng on 6/30/17.
 */

public class LoginContract {

    public interface Presenter{
        void bind(View view);
        void unbind();
    }

    public interface View{
        void showProgress();
        void showBadConnectionToast();
        void showServerErrorToast();
        void login();
    }
}
