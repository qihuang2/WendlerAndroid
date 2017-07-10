package com.android.wendler.wendlerandroid.main.contract;

import com.android.wendler.wendlerandroid.main.model.Lift;
import com.android.wendler.wendlerandroid.main.model.User;

import io.reactivex.Observable;

/**
 * Created by QiFeng on 7/6/17.
 */

public class SetContract {

    public interface Presenter {
        void advanceWeek(User user, Lift lift);
        void bindView(View view);
        void unbind();

    }

    public interface View{
        void advanceWeek();
        void showConnectionError();
    }

    public interface Interactor{
        Observable<User> updateUser(User user);
    }
}
