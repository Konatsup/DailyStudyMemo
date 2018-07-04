package com.konatsup.dailystudymemo;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

public class Note extends RealmObject {

    private int id; //固有ID
    private int noteType; //ノートの種類(メモ、問題)
    private int subject; //科目名
    private int categoryId; //頻出、普通、難題
    private int priority; //優先度
    private int questionState; //正解、間違っていた、未着手
    private String title; //タイトル
    private String content; //メモ文章の中身
    private String imagePath; //画像へのパス
    private String createdAt; //作成日

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getNoteType() {
        return noteType;
    }

    public void setNoteType(int noteType) {
        this.noteType = noteType;
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

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }



}
