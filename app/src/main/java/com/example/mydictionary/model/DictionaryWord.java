package com.example.mydictionary.model;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "dictionaryTable")
public class DictionaryWord {
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    private long primaryId;
    @ColumnInfo(name = "arabic")
    private String mArabic;
    @ColumnInfo(name="english")
    private String mEnglish;
    @ColumnInfo(name = "french")
    private String mFrench;
    @ColumnInfo(name = "persian")
    private String mPersian;

    public long getPrimaryId() {
        return primaryId;
    }

    public void setPrimaryId(long primaryId) {
        this.primaryId = primaryId;
    }

    public String getArabic() {
        return mArabic;
    }

    public void setArabic(String mArabic) {
        this.mArabic = mArabic;
    }

    public String getEnglish() {
        return mEnglish;
    }

    public void setEnglish(String mEnglish) {
        this.mEnglish = mEnglish;
    }

    public String getFrench() {
        return mFrench;
    }

    public void setFrench(String mFrench) {
        this.mFrench = mFrench;
    }

    public String getPersian() {
        return mPersian;
    }

    public DictionaryWord(String arabic, String english, String french, String persian) {

        this.mArabic = arabic;
        this.mEnglish = english;
        this.mFrench = french;
        this.mPersian = persian;
    }

    public void setPersian(String mPersian) {
        this.mPersian = mPersian;
    }
}
