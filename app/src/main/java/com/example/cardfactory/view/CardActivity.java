package com.example.cardfactory.view;

import android.content.Context;
import android.content.Intent;

import androidx.fragment.app.Fragment;

public class CardActivity extends SingleFragmentActivity {

    public static void start(Context context) {
        Intent starter = new Intent(context, CardFragment.class);
        context.startActivity(starter);
    }

    @Override
    public Fragment createFragment() {
        return CardFragment.newInstance();
    }
}