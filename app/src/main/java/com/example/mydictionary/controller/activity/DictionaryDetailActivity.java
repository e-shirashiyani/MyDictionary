package com.example.mydictionary.controller.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.example.mydictionary.R;
import com.example.mydictionary.controller.fragment.DictionaryDetailFragment;

public class DictionaryDetailActivity extends SingleActivityFragment {
    public static final String EXTRA_WORD_ID = "Extra_WordID.org.example.mydictionary.controller.activity";
    public static final String EXTRA_FROM = "Extra_FROM.org.example.mydictionary.controller.activity";
    public static final String EXTRA_TO = "Extra_TO.org.example.mydictionary.controller.activity";
    private static long mWordID;
    private static String mFrom,mTo;
    public static Intent newIntent(Context context, long wordId, String from, String to) {
        mWordID = wordId;
        mFrom = from;
        mTo = to;
        Intent intent = new Intent(context, DictionaryDetailActivity.class);
        intent.putExtra(EXTRA_WORD_ID,wordId);
        intent.putExtra(EXTRA_FROM,mFrom);
        intent.putExtra(EXTRA_TO,mTo);
        return intent;
    }

    @Override
    public Fragment createFragment() {
        DictionaryDetailFragment dictionaryDetailFragment = DictionaryDetailFragment.newInstance(mWordID,mFrom,mTo);
        return dictionaryDetailFragment;
    }
}