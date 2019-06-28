package com.openclassrooms.entrevoisins.utils;

import android.support.test.espresso.UiController;
import android.support.test.espresso.ViewAction;
import android.view.View;

import com.openclassrooms.entrevoisins.R;

import org.hamcrest.Matcher;

public class DeleteViewActionFavourite implements ViewAction {
    @Override
    public Matcher<View> getConstraints() {
        return null;
    }

    @Override
    public String getDescription() {
        return "Click on the delete button of a favorite from the RecycleView";
    }

    @Override
    public void perform(UiController uiController, View view) {
        View button = view.findViewById(R.id.favourite_item_list_delete_button);
        if  (button != null) button.performClick();
    }
}