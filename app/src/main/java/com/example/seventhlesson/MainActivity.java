package com.example.seventhlesson;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();

        SharedPreferences sharedPreferences = getSharedPreferences(Settings.SHARED_PREFERENCE_NAME, Context.MODE_PRIVATE);
        Settings.isDeleteFragment = sharedPreferences.getBoolean(Settings.IS_DELETE_FRAGMENT_BEFORE_ADD ,false);
        Settings.isBackRemove = sharedPreferences.getBoolean(Settings.IS_BACK_REMOVE_FRAGMENT ,false);
        Settings.isBackStack = sharedPreferences.getBoolean(Settings.IS_BACK_STACK_USED ,false);
        Settings.isReplaceFragment = sharedPreferences.getBoolean(Settings.IS_REPLACE_FRAGMENT_USED ,false);
        Settings.isAddFragment = sharedPreferences.getBoolean(Settings.IS_ADD_FRAGMENT_USED ,false);
    }

    private void initView() {
        Button buttonBack = findViewById(R.id.buttonBack);
        Button buttonMain = findViewById(R.id.buttonMain);
        Button buttonFavorite = findViewById(R.id.buttonFavorite);
        Button buttonSettings = findViewById(R.id.buttonSettings);

        buttonBack.setOnClickListener(this);
        buttonMain.setOnClickListener(this);
        buttonFavorite.setOnClickListener(this);
        buttonSettings.setOnClickListener(this);



    }

    @Override
    public void onClick(View v) {

        switch (v.getId()){
            case R.id.buttonBack:
                FragmentManager fragmentManager = getSupportFragmentManager();
                if (fragmentManager.getFragments().size() <= 1)
                    break;
                if (Settings.isBackRemove){
                    Fragment fragmentForDelete =  getVisibleFragment(fragmentManager);
                    if(fragmentForDelete !=null){
                        fragmentManager.beginTransaction().remove(fragmentForDelete)
                                .commit();
                    }
                }else {
                    fragmentManager.popBackStack();
                }

                break;

            case R.id.buttonMain:
                showFragment(MainFragment.newInstance());
                break;

            case R.id.buttonFavorite:
                showFragment(FavoriteFragment.newInstance());
                break;

            case R.id.buttonSettings:
                showFragment(SettingsFragment.newInstance());
                break;

        }

    }

    Fragment getVisibleFragment(FragmentManager fragmentManager){
        List<Fragment> fList = fragmentManager.getFragments();
        for (int i = 0; i < fList.size(); i++ ){
            Fragment fragment = fList.get(i);
            if (fragment.isVisible()){
                return fragment;
            }
        }
        return  null;
    }

    private void showFragment(Fragment fragment) {

        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

        if (Settings.isDeleteFragment){
            Fragment fragmentForDelete = getVisibleFragment(fragmentManager);
            if (fragmentForDelete != null){
                fragmentTransaction.remove(fragmentForDelete);
            }
        }

        if(Settings.isAddFragment){
            fragmentTransaction
                    .add(R.id.fragmentContainer,fragment);

        } else if (Settings.isReplaceFragment){
            fragmentTransaction.replace(R.id.fragmentContainer,fragment);


        }

        if (Settings.isBackStack){
            fragmentTransaction.addToBackStack("");
        }

        fragmentTransaction.commit();


    }
}