package com.android.wendler.wendlerandroid.main.interactor;

import com.android.wendler.wendlerandroid.api.UpdateApi;
import com.android.wendler.wendlerandroid.main.contract.SetContract;
import com.android.wendler.wendlerandroid.main.model.User;

import io.reactivex.Observable;
import retrofit2.Retrofit;

/**
 * Created by QiFeng on 7/6/17.
 */

public class SetInteractor implements SetContract.Interactor {

    private UpdateApi mUpdateApi;

    public SetInteractor(Retrofit retrofit){
        mUpdateApi = retrofit.create(UpdateApi.class);
    }

    @Override
    public Observable<User> updateUser(User user) {
        return mUpdateApi.updateUser(user.getToken(), user.getId(), user);
    }
}
