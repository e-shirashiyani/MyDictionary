package com.example.mydictionary.controller.fragment;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.ShareCompat;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.mydictionary.R;
import com.example.mydictionary.model.DictionaryWord;
import com.example.mydictionary.repository.DictionaryDBRepository;
import com.example.mydictionary.repository.IRepository;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;


public class DictionaryDetailFragment extends Fragment {
    public static final String KEY_VALUE_WORD_ID = "key_value_WordId";
    public static final String KEY_VALUE_FROM = "key_value_FROM";
    public static final String KEY_VALUE_TO = "key_value_TO";
    public static final String FRAGMENT_TAG_EDIT = "Edit";
    public static final int REQUEST_CODE_EDIT = 0;
    public static final String FRAGMENT_TAG_DELETE = "Delete";
    public static final int REQUEST_CODE_DELETE = 1;
    private long mWordID;
    private String mFrom, mTo;
    private DictionaryWord mDictionaryWord;
    private IRepository mIRepository;
    private TextView mTextViewTitle, mTextViewMeaning;
    private BottomNavigationView mBottomNavigationView;



    public DictionaryDetailFragment() {
        // Required empty public constructor
    }


    // TODO: Rename and change types and number of parameters
    public static DictionaryDetailFragment newInstance(long wordId, String from, String to) {
        DictionaryDetailFragment fragment = new DictionaryDetailFragment();
        Bundle args = new Bundle();
        args.putLong(KEY_VALUE_WORD_ID, wordId);
        args.putString(KEY_VALUE_FROM, from);
        args.putString(KEY_VALUE_TO, to);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mWordID=getArguments().getLong(KEY_VALUE_WORD_ID);
        mFrom=getArguments().getString(KEY_VALUE_FROM);
        mTo=getArguments().getString(KEY_VALUE_TO);
        mIRepository= DictionaryDBRepository.getInstance(getActivity());



    }
    @Override
    public void onResume() {
        super.onResume();
        initView();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view=inflater.inflate(R.layout.fragment_dictionary_detail, container, false);
        findViews(view);
        initView();
        setListener();
        return view;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if(resultCode!= Activity.RESULT_OK|| data==null)
            return;
        if(requestCode==REQUEST_CODE_EDIT){
            initView();
        }else if(requestCode==REQUEST_CODE_DELETE){
            getActivity().finish();
        }
    }
    private void findViews(View view){
        mTextViewTitle=view.findViewById(R.id.textView_titleWord);
        mTextViewMeaning = view.findViewById(R.id.textView_meaningWord);
        mBottomNavigationView = view.findViewById(R.id.bottom_navigation);
    }
    private void setListener(){
        mBottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.share_menu:
                        shareReportIntent();
                        break;
                    case R.id.edit_menu:
                        EditFragment editFragment = EditFragment.newInstance(mWordID);

                        editFragment.setTargetFragment(
                                DictionaryDetailFragment.this,
                                REQUEST_CODE_EDIT);

                        editFragment.show(
                                getActivity().getSupportFragmentManager(),
                                FRAGMENT_TAG_EDIT);
                        break;
                    case R.id.delete_menu:
                        deleteFragment DeleteFragment = deleteFragment.newInstance(mWordID);

                        DeleteFragment.setTargetFragment(
                                DictionaryDetailFragment.this,
                                REQUEST_CODE_DELETE);

                        DeleteFragment.show(
                                getActivity().getSupportFragmentManager(),
                                FRAGMENT_TAG_DELETE);
                        break;
                }
                return true;
            }
        });
    }
    private String shareWord() {
        String title = mTextViewTitle.getText().toString();
        String meaning = mTextViewMeaning.getText().toString();

        String shareMassage = getString(
                R.string.share_word,
                title,
                mTo,
                meaning);

        return shareMassage;
    }
    private void shareReportIntent(){
        ShareCompat.IntentBuilder intentBuilder=ShareCompat.IntentBuilder.from(getActivity());
        Intent intent=intentBuilder
                .setType("text/plain")
                .setText(shareWord())
                .setChooserTitle(getString(R.string.dictionary_sharing_massage))
                .createChooserIntent();

        if (intent.resolveActivity(getActivity().getPackageManager()) != null) {
            startActivity(intent);
        }
    }
    private void setTextInView(String state, TextView textView) {
        switch (state) {
            case "Arabic":
                textView.setText(mDictionaryWord.getArabic());
                break;
            case "English":
                textView.setText(mDictionaryWord.getEnglish());
                break;
            case "French":
                textView.setText(mDictionaryWord.getFrench());
                break;
            default:
                textView.setText(mDictionaryWord.getPersian());
                break;
        }
    }
    private void initView(){
        mDictionaryWord=mIRepository.getWord(mWordID);
        setTextInView(mFrom,mTextViewTitle);
        setTextInView(mTo,mTextViewMeaning);
    }

}