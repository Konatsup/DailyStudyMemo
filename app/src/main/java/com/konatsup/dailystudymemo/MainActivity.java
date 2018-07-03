package com.konatsup.dailystudymemo;

import android.app.Activity;
import android.os.Bundle;

import io.realm.Realm;

public class MainActivity extends Activity {

    private Realm realm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        realm.close();
    }

}
