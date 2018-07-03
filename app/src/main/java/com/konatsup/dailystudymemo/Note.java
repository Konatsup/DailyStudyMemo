package com.konatsup.dailystudymemo;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

public class Note extends RealmObject{

    @PrimaryKey
    private int id; //固有ID
    private int subject; //科目名
    private int categoryId; //頻出、普通、難題
    private int priority; //優先度
    private int questionState; //正解、間違っていた、未着手
    private String imagePath; //画像へのパス
    private String memoText; //メモ文章
    private String createdAt; //作成日

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getSubject() {
        return subject;
    }

    public void setSubject(int subject) {
        this.subject = subject;
    }

    public int getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }

    public int getPriority() {
        return priority;
    }

    public void setPriority(int priority) {
        this.priority = priority;
    }

    public int getQuestionState() {
        return questionState;
    }

    public void setQuestionState(int questionState) {
        this.questionState = questionState;
    }

    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }

    public String getMemoText() {
        return memoText;
    }

    public void setMemoText(String memoText) {
        this.memoText = memoText;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

}
