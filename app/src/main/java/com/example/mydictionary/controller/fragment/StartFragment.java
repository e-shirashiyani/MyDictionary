package com.example.mydictionary.controller.fragment;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.mydictionary.R;
import com.example.mydictionary.controller.activity.DictionaryList;


public class StartFragment extends Fragment {
    Animation mTopAnim,mBottomAnim;
    TextView mIn;
    View mImageA,mImageB,mImageC,mImageD,mImageE;
    private TextView mDescription;
    private static int SPLASH_SCREEN=5000;


    public StartFragment() {
        // Required empty public constructor
    }
    private void findViews(View view){
       // mImage=view.findViewById(R.id.view);
        mImageA=view.findViewById(R.id.viewA);
        mImageB=view.findViewById(R.id.viewB);
        mImageC=view.findViewById(R.id.viewC);
        mImageD=view.findViewById(R.id.viewD);
        mImageE=view.findViewById(R.id.viewE);
        mIn=view.findViewById(R.id.title2);
        //mDescription=view.findViewById(R.id.textView);
        mImageA.setAnimation(mBottomAnim);
        mImageB.setAnimation(mBottomAnim);
        mImageC.setAnimation(mBottomAnim);
        mImageD.setAnimation(mBottomAnim);
        mImageE.setAnimation(mBottomAnim);
        mIn.setAnimation(mTopAnim);
       // mDescription.setAnimation(mBottomAnim);


    }




    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_start, container, false);
        getActivity().getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);

        mTopAnim= AnimationUtils.loadAnimation(this.getActivity(), R.anim.top_animation);
        mBottomAnim= AnimationUtils.loadAnimation(this.getActivity(),R.anim.butom_animation);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(getActivity(), DictionaryList.class);
                getActivity().startActivity(intent);
/*
                Pair[]pairs=new Pair[2];
                pairs[0]=new Pair<View, String>(mImage,"logo_image");
                pairs[1]=new Pair<View, String>(mDescription,"logo_text");
                //wrap the call in API level 21 or higher
                if(android.os.Build.VERSION.SDK_INT>=android.os.Build.VERSION_CODES.LOLLIPOP)
                {
                    ActivityOptions options= ActivityOptions.makeSceneTransitionAnimation(getActivity(),pairs);
                    startActivity(intent,options.toBundle());
                }*/
            }



        },SPLASH_SCREEN);
        //Font();

        findViews(view);
        return view;
    }
}