package com.android.wendler.wendlerandroid.main.contract;

/**
 * Created by QiFeng on 7/11/17.
 */

public class EditContract {

    public interface Presenter{
        public void saveClicked();

    }

    public interface View{
        public void showSavedToast();
    }
}
