package com.android.wendler.wendlerandroid.main.edit;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;

import com.android.wendler.wendlerandroid.R;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by QiFeng on 7/11/17.
 */

public class EditActivity extends AppCompatActivity implements View.OnClickListener{

    @BindView(R.id.recycler_view)
    RecyclerView.RecyclerListener vRecyclerView;

    @BindView(R.id.save_button)
    Button vSaveButton;

    @BindView(R.id.toolbar)
    Toolbar vToolbar;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);

        ButterKnife.bind(this);

        vToolbar.setNavigationOnClickListener(this);
    }


    @Override
    public void onClick(View view) {
        onBackPressed();
    }
}
