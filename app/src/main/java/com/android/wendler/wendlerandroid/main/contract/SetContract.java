package com.android.wendler.wendlerandroid.main.contract;

/**
 * Created by QiFeng on 7/6/17.
 */

public class SetContract {

    public interface Presenter{
        void advanceWeek();
        void bindView(View view);
        void unbind();

    }

    public interface View{
        void advanceWeek();

    }

    public interface Interactor{

    }
}
