package com.example.mydictionary.database;

import androidx.room.Dao;
import androidx.room.Database;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.mydictionary.model.DictionaryWord;

import java.util.List;

@Dao
public interface  DictionaryDatabaseDAO {
    @Insert
    void InsertWord(DictionaryWord word);
    @Update
    void updateWord(DictionaryWord word);
    @Delete
    void deleteWord(DictionaryWord word);
    @Query("Select * FROM dictionaryTable")
    List<DictionaryWord> getWords();
    @Query("SELECT * FROM dictionaryTable WHERE id=:inputId")
    DictionaryWord getWord(Long inputId);
    @Query("Select * from dictionaryTable where arabic like :searchValue")
    List<DictionaryWord> searchArabic(String searchValue);
    @Query("Select * From dictionaryTable where english like:searchValue")
    List<DictionaryWord> searchEnglish(String searchValue);
    @Query("Select * from dictionaryTable where french like:searchValue ")
    List<DictionaryWord> searchFrench(String searchValue);
    @Query("select * from dictionaryTable where persian like:searchValue")
    List<DictionaryWord> searchPersian(String searchValue);
}
