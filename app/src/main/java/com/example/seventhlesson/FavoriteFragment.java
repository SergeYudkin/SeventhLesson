package com.example.seventhlesson;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;

public class FavoriteFragment extends Fragment {

    public static FavoriteFragment newInstance(){
        return  new FavoriteFragment();
    }


    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_favorite,container,false);
        return v;
    }
}