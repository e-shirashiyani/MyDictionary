package com.example.mydictionary.controller.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.view.View;
import android.view.Menu;

import com.example.mydictionary.R;
import com.example.mydictionary.controller.fragment.DictionaryListFragment;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.navigation.NavigationView;

import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

public class DictionaryList extends SingleActivityFragment {

    private AppBarConfiguration mAppBarConfiguration;

    public static Intent newIntent(Context context) {
        Intent intent = new Intent(context, DictionaryList.class);
        return intent;
    }


    @Override
    public Fragment createFragment() {
        DictionaryListFragment dictionaryListFragment=DictionaryListFragment.newInstance();
        return dictionaryListFragment;
    }
   /* @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        return NavigationUI.navigateUp(navController, mAppBarConfiguration)
                || super.onSupportNavigateUp();
    }*/



}