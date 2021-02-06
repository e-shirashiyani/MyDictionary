package com.example.mydictionary.repository;

import android.content.Context;

import androidx.room.Room;

import com.example.mydictionary.database.DictionaryDatabase;
import com.example.mydictionary.database.DictionaryDatabaseDAO;
import com.example.mydictionary.model.DictionaryWord;

import java.util.List;

public class DictionaryDBRepository implements IRepository {
    private static DictionaryDBRepository sInstance;
    private Context mContext;
    private DictionaryDatabaseDAO mDictionaryDAO;


    private void firstInsert() {
        DictionaryWord word = new DictionaryWord("مرحبا", "Hello", "Bonjour", "سلام");
        mDictionaryDAO.InsertWord(word);
    }

    public static DictionaryDBRepository getInstance(Context context) {
        if (sInstance == null)
            sInstance = new DictionaryDBRepository(context);
        return sInstance;
    }

    public DictionaryDBRepository(Context context) {
        mContext = context.getApplicationContext();

        DictionaryDatabase dictionaryDatabase = Room.databaseBuilder(mContext,
                DictionaryDatabase.class,
                "dictionary.db")
                .allowMainThreadQueries()
                .build();
        mDictionaryDAO = dictionaryDatabase.getDictionaryDatabaseDAO();
        firstInsert();
    }

    @Override
    public void insertWord(DictionaryWord word) {
        mDictionaryDAO.InsertWord(word);

    }

    @Override
    public void updateWord(DictionaryWord word) {
        mDictionaryDAO.updateWord(word);

    }

    @Override
    public void deleteWord(DictionaryWord word) {
        mDictionaryDAO.deleteWord(word);
    }

    @Override
    public List<DictionaryWord> getWords() {
        return mDictionaryDAO.getWords();
    }

    @Override
    public DictionaryWord getWord(long inputId) {
        return mDictionaryDAO.getWord(inputId);
    }

    @Override
    public List<DictionaryWord> searchArabic(String searchValue) {
        return mDictionaryDAO.searchArabic(searchValue);
    }

    @Override
    public List<DictionaryWord> searchEnglish(String searchValue) {
        return mDictionaryDAO.searchEnglish(searchValue);
    }

    @Override
    public List<DictionaryWord> searchFrench(String searchValue) {
        return mDictionaryDAO.searchFrench(searchValue);
    }

    @Override
    public List<DictionaryWord> searchPersian(String searchValue) {
        return mDictionaryDAO.searchPersian(searchValue);
    }
}
