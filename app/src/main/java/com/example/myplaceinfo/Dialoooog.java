package com.example.myplaceinfo;

import android.os.Bundle;
import androidx.fragment.app.DialogFragment;

/**
 * Created by Alexandr Mikhalev on 27.05.2019.
 *
 * @author Alexandr Mikhalev
 */
public class Dialoooog extends DialogFragment {

    public static Dialoooog newInstance(String message) {

        Bundle args = new Bundle();

        Dialoooog fragment = new Dialoooog();
        fragment.setArguments(args);
        return fragment;
    }
}
