package com.example.mydictionary.database;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.mydictionary.model.DictionaryWord;

@Database(entities = {DictionaryWord.class},version = 1)
public abstract class DictionaryDatabase extends RoomDatabase {
    public abstract DictionaryDatabaseDAO getDictionaryDatabaseDAO();
}
