
package com.openclassrooms.entrevoisins.neighbour_list;

import android.support.test.espresso.action.ViewActions;
import android.support.test.espresso.contrib.RecyclerViewActions;
import android.support.test.espresso.matcher.ViewMatchers;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import com.openclassrooms.entrevoisins.R;
import com.openclassrooms.entrevoisins.model.Neighbour;
import com.openclassrooms.entrevoisins.service.DummyNeighbourGenerator;
import com.openclassrooms.entrevoisins.ui.neighbour_list.ListNeighbourActivity;
import com.openclassrooms.entrevoisins.utils.DeleteViewActionFavourite;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.List;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.intent.Intents.intended;
import static android.support.test.espresso.intent.matcher.IntentMatchers.hasComponent;
import static android.support.test.espresso.matcher.ViewMatchers.assertThat;
import static android.support.test.espresso.matcher.ViewMatchers.hasMinimumChildCount;
import static android.support.test.espresso.matcher.ViewMatchers.withContentDescription;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static com.openclassrooms.entrevoisins.utils.RecyclerViewItemCountAssertion.withItemCount;
import static org.hamcrest.core.IsNull.notNullValue;


/**
 * Test class for list of Favourites
 */
@RunWith(AndroidJUnit4.class)
public class FavouritesListTest {

    private ListNeighbourActivity mActivity;

    @Rule
    public ActivityTestRule<ListNeighbourActivity> mActivityRule =
            new ActivityTestRule(ListNeighbourActivity.class);

    @Before
    public void setUp() {
        mActivity = mActivityRule.getActivity();
        assertThat(mActivity, notNullValue());

        // Perform a click on Favourite
        onView(ViewMatchers.withContentDescription(R.string.tab_favorites_title))
                .perform(ViewActions.click());
    }

    /**
     * Make sur the number of favourite neighbours is right
     */
    @Test
    public void myFavouriteList_checkNeighbours_shouldBeFavourite(){
        // Make sure there are the right number of favourite neighbours
        onView(withId(R.id.list_favourites)).check(withItemCount(DummyNeighbourGenerator.DUMMY_FAVORITES.size()));
    }

    /**
     * When we delete an item in favourite, the item is no more shown
     */
    @Test
    public void myFavouritesList_deleteAction_shouldRemoveItem() {
        int favouritesCount = DummyNeighbourGenerator.DUMMY_FAVORITES.size();
        // When perform a click on a delete icon
        onView(withId(R.id.list_favourites))
                .perform(RecyclerViewActions.actionOnItemAtPosition(1, new DeleteViewActionFavourite()));
        // Then : the number of element is favouritesCount - 1
        onView(withId(R.id.list_favourites)).check(withItemCount(favouritesCount - 1));
    }
}