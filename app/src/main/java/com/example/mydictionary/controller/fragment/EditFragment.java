package com.example.mydictionary.controller.fragment;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.mydictionary.R;
import com.example.mydictionary.model.DictionaryWord;
import com.example.mydictionary.repository.DictionaryDBRepository;
import com.example.mydictionary.repository.IRepository;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;


public class EditFragment extends DialogFragment {
    public static final String KEY_VALUE_WORD_ID = "key_value_WordId";
    private TextInputEditText mEditTextArabic, mEditTextEnglish, mEditTextFrench, mEditTextPersian;
    private TextInputLayout mArabicForm, mEnglishForm, mFrenchForm, mPersianForm;
    private Button mButtonCancel, mButtonSave;
    private IRepository mIRepository;
    private long mWordId;
    private DictionaryWord mDictionaryWord;


    public EditFragment() {
        // Required empty public constructor
    }


    public static EditFragment newInstance(long WordId) {
        EditFragment fragment = new EditFragment();
        Bundle args = new Bundle();
        args.putLong(KEY_VALUE_WORD_ID, WordId);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mIRepository= DictionaryDBRepository.getInstance(getActivity());
        mWordId=getArguments().getLong(KEY_VALUE_WORD_ID);
        mDictionaryWord=mIRepository.getWord(mWordId);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_edit, container, false);
        findView(view);
        initView();
        setListener();
        return view;
    }
    private void findView(View view) {
        mButtonCancel = view.findViewById(R.id.btn_cancel_edit);
        mButtonSave = view.findViewById(R.id.btn_save_edit);
        mEditTextArabic = view.findViewById(R.id.arabic_edit);
        mEditTextEnglish = view.findViewById(R.id.english_edit);
        mEditTextFrench = view.findViewById(R.id.french_edit);
        mEditTextPersian = view.findViewById(R.id.persian_edit);
        mArabicForm = view.findViewById(R.id.arabic_form_edit);
        mEnglishForm = view.findViewById(R.id.english_form_edit);
        mFrenchForm = view.findViewById(R.id.french_form_edit);
        mPersianForm = view.findViewById(R.id.persian_form_edit);
    }
    private void setListener(){
        mButtonSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (validateInput()) {
                    sendResult();
                    dismiss();
                } else {
                    checkInput();
                }
            }
        });
        mButtonCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });

    }
    private void checkInput() {
        mArabicForm.setErrorEnabled(false);
        mEnglishForm.setErrorEnabled(false);
        mFrenchForm.setErrorEnabled(false);
        mPersianForm.setErrorEnabled(false);
        if (mEditTextArabic.getText().toString().trim().isEmpty()) {
            mArabicForm.setErrorEnabled(true);
            mArabicForm.setError("Field cannot be empty!");
        }
        if (mEditTextEnglish.getText().toString().trim().isEmpty()) {
            mEnglishForm.setErrorEnabled(true);
            mEnglishForm.setError("Field cannot be empty!");
        }
        if (mEditTextFrench.getText().toString().trim().isEmpty()) {
            mFrenchForm.setErrorEnabled(true);
            mFrenchForm.setError("Field cannot be empty!");
        }
        if (mEditTextPersian.getText().toString().trim().isEmpty()) {
            mPersianForm.setErrorEnabled(true);
            mPersianForm.setError("Field cannot be empty!");
        }
    }
    private boolean validateInput() {
        return !mEditTextArabic.getText().toString().trim().isEmpty() &&
                !mEditTextEnglish.getText().toString().trim().isEmpty() &&
                !mEditTextFrench.getText().toString().trim().isEmpty() &&
                !mEditTextPersian.getText().toString().trim().isEmpty();
    }
    private void sendResult(){
        Fragment fragment=getTargetFragment();
        int requestCode=getTargetRequestCode();
        int resultCode= Activity.RESULT_OK;
        Intent intent=new Intent();
        mDictionaryWord.setArabic(mEditTextArabic.getText().toString());
        mDictionaryWord.setEnglish(mEditTextEnglish.getText().toString());
        mDictionaryWord.setFrench(mEditTextFrench.getText().toString());
        mDictionaryWord.setPersian(mEditTextPersian.getText().toString());
        mIRepository.updateWord(mDictionaryWord);
        fragment.onActivityResult(requestCode,resultCode,intent);
    }
    private void initView() {
        mEditTextArabic.setText(mDictionaryWord.getArabic());
        mEditTextEnglish.setText(mDictionaryWord.getEnglish());
        mEditTextFrench.setText(mDictionaryWord.getFrench());
        mEditTextPersian.setText(mDictionaryWord.getPersian());
    }
}