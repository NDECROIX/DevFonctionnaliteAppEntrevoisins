package com.openclassrooms.entrevoisins.utils;

import android.support.test.espresso.UiController;
import android.support.test.espresso.ViewAction;
import android.view.View;
import android.widget.TextView;

import com.openclassrooms.entrevoisins.R;

import org.hamcrest.Matcher;

public class ShowDetailNeighbourActivity implements ViewAction {

    private String name;

    @Override
    public Matcher<View> getConstraints() {
        return null;
    }

    @Override
    public String getDescription() {
        return "Takes the name and clicks on the item";
    }

    @Override
    public void perform(UiController uiController, View view) {

        TextView tv = view.findViewById(R.id.item_list_name);
        name = tv.getText().toString();
        view.performClick();
    }

    public String getName(){ return this.name ;}
}
