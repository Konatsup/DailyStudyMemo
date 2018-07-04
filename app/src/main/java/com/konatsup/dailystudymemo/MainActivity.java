package com.konatsup.dailystudymemo;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TabLayout;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ListView;

import java.util.Calendar;

import io.realm.Realm;
import io.realm.RealmResults;

public class MainActivity extends AppCompatActivity {

    private Realm realm;
    private Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setViews();

        realm = Realm.getDefaultInstance();
        RealmResults<Note> result = realm.where(Note.class).findAll();

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), PostActivity.class);
                startActivity(intent);
            }
        });


    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.filter:
                // ボタンをタップした際の処理を記述
                break;
            case R.id.switchButton:
                // ボタンをタップした際の処理を記述
                add();
                break;

        }
        return true;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        realm.close();
    }

    private void setViews() {
        toolbar = (Toolbar) findViewById(R.id.main_toolbar);
        setSupportActionBar(toolbar);
        FragmentManager manager = getSupportFragmentManager();
        ViewPager viewPager = (ViewPager) findViewById(R.id.main_viewpager);
        NotesFragmentPagerAdapter adapter = new NotesFragmentPagerAdapter(manager);
        viewPager.setAdapter(adapter);
        TabLayout tabLayout = (TabLayout) findViewById(R.id.main_tab);
        tabLayout.setupWithViewPager(viewPager);
    }

    public void add(){
        String createdAtText  = android.text.format.DateFormat.format("yyyy-MM-dd-kk-mm-ss", Calendar.getInstance()).toString();

        //データの新規登録
        realm.beginTransaction();
        Note note = realm.createObject(Note.class); // 新たなオブジェクトを作成
        note.setId(1);
        note.setSubject(0);
        note.setCategoryId(0);
        note.setPriority(5);
        note.setQuestionState(0);
        note.setTitle("楕円その2");
        note.setContent("よくわからないけど公式覚えるぞい");
        note.setImagePath("/");
        note.setCreatedAt(createdAtText);
        realm.commitTransaction();

    }
    public void deleteAll(){
        final RealmResults<Note> result = realm.where(Note.class).findAll();
        realm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                // すべてのオブジェクトを削除
                result.deleteAllFromRealm();
            }
        });
    }

}
