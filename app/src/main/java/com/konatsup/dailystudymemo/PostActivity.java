package com.konatsup.dailystudymemo;

import android.content.ContentValues;
import android.content.Intent;
import android.media.MediaScannerConnection;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;

import java.util.Calendar;

import io.realm.Realm;
import io.realm.RealmResults;

public class PostActivity extends AppCompatActivity {

    private LinearLayout imageLayout;
    private Button postButton;
    private EditText titleEditText;
    private EditText contentEditText;

    private Realm realm;
    private Uri m_uri;
    private static final int REQUEST_CHOOSER = 1000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post);

        imageLayout = (LinearLayout)findViewById(R.id.imageLayout);
        postButton = (Button)findViewById(R.id.postButton);
        titleEditText = (EditText)findViewById(R.id.titleEditText);
        contentEditText = (EditText)findViewById(R.id.contentEditText);

        realm = Realm.getDefaultInstance();

        imageLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                showGallery();
            }
        });

        postButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                showGallery();
                add();
            }
        });

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        realm.close();
    }


    public void add(){
        String titleText,contentText;
        titleText = titleEditText.getText().toString();
        contentText = contentEditText.getText().toString();
        String createdAtText  = android.text.format.DateFormat.format("yyyy-MM-dd-kk-mm-ss", Calendar.getInstance()).toString();

        //データの新規登録
        realm.beginTransaction();
        Note note = realm.createObject(Note.class); // 新たなオブジェクトを作成
        note.setId(1);
        note.setNoteType(0);
        note.setSubject(0);
        note.setCategoryId(0);
        note.setPriority(5);
        note.setQuestionState(0);
        note.setTitle(titleText);
        note.setContent(contentText);
        note.setImagePath("/");
        note.setCreatedAt(createdAtText);
        realm.commitTransaction();

    }

    private void showGallery() {

        //カメラの起動Intentの用意
        String photoName = System.currentTimeMillis() + ".jpg";
        ContentValues contentValues = new ContentValues();
        contentValues.put(MediaStore.Images.Media.TITLE, photoName);
        contentValues.put(MediaStore.Images.Media.MIME_TYPE, "image/jpeg");
        m_uri = getContentResolver()
                .insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, contentValues);

        Intent intentCamera = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        intentCamera.putExtra(MediaStore.EXTRA_OUTPUT, m_uri);

        // ギャラリー用のIntent作成
        Intent intentGallery;
        if (Build.VERSION.SDK_INT < 19) {
            intentGallery = new Intent(Intent.ACTION_GET_CONTENT);
            intentGallery.setType("image/*");
        } else {
            intentGallery = new Intent(Intent.ACTION_OPEN_DOCUMENT);
            intentGallery.addCategory(Intent.CATEGORY_OPENABLE);
            intentGallery.setType("image/jpeg");
        }
        Intent intent = Intent.createChooser(intentCamera, "画像の選択");
        intent.putExtra(Intent.EXTRA_INITIAL_INTENTS, new Intent[] {intentGallery});
        startActivityForResult(intent, REQUEST_CHOOSER);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == REQUEST_CHOOSER) {

            if(resultCode != RESULT_OK) {
                // キャンセル時
                return ;
            }

            Uri resultUri = (data != null ? data.getData() : m_uri);

            if(resultUri == null) {
                // 取得失敗
                return;
            }

            // ギャラリーへスキャンを促す
            MediaScannerConnection.scanFile(
                    this,
                    new String[]{resultUri.getPath()},
                    new String[]{"image/jpeg"},
                    null
            );

            // 画像を設定
            ImageView imageView = (ImageView)findViewById(R.id.imageView);
            imageView.setImageURI(resultUri);
        }
    }
}
