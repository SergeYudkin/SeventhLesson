package com.example.seventhlesson;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.PopupMenu;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

public class MainFragment extends Fragment {

    public static MainFragment newInstance(){
        return new MainFragment();
    }


    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_main,container,false);
        ((TextView)v.findViewById(R.id.textView)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PopupMenu popupMenu = new PopupMenu(requireActivity(),v);
                requireActivity().getMenuInflater().inflate(R.menu.popup,popupMenu.getMenu());
                popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem item) {
                        switch (item.getItemId()){
                            case R.id.action_main:
                                ((MainActivity)requireActivity()).showFragment(MainFragment.newInstance());
                                return true; //TODO 1
                                //break;
                            case R.id.action_favorite:
                                ((MainActivity)requireActivity()).showFragment(FavoriteFragment.newInstance());
                                break;
                            case R.id.action_settings:
                                ((MainActivity)requireActivity()).showFragment(SettingsFragment.newInstance());
                                break;
                        }

                        return false;
                    }
                });
                popupMenu.show();

            }
        });
        return v;
    }
}