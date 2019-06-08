
package com.openclassrooms.entrevoisins.neighbour_list;

import android.support.test.espresso.contrib.RecyclerViewActions;
import android.support.test.espresso.intent.Intents;
import android.support.test.espresso.matcher.ViewMatchers;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import com.openclassrooms.entrevoisins.R;
import com.openclassrooms.entrevoisins.ui.neighbour_detail.DetailNeighbourActivity;
import com.openclassrooms.entrevoisins.ui.neighbour_list.FavouriteFragment;
import com.openclassrooms.entrevoisins.ui.neighbour_list.ListNeighbourActivity;
import com.openclassrooms.entrevoisins.utils.DeleteViewAction;
import com.openclassrooms.entrevoisins.utils.ShowDetailNeighbourActivity;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.intent.Intents.intended;
import static android.support.test.espresso.intent.matcher.IntentMatchers.hasComponent;
import static android.support.test.espresso.matcher.ViewMatchers.assertThat;
import static android.support.test.espresso.matcher.ViewMatchers.hasMinimumChildCount;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static com.openclassrooms.entrevoisins.utils.RecyclerViewItemCountAssertion.withItemCount;
import static org.hamcrest.core.IsNull.notNullValue;



/**
 * Test class for list of neighbours
 */
@RunWith(AndroidJUnit4.class)
public class NeighboursListTest {

    // This is fixed
    private static int NEIGHBOURS_ITEMS_COUNT = 12;
    private static int FAVOURITES_ITEMS_COUNT = 5;

    private ListNeighbourActivity mActivity;

    @Rule
    public ActivityTestRule<ListNeighbourActivity> mActivityRule =
            new ActivityTestRule(ListNeighbourActivity.class);

    @Before
    public void setUp() {
        mActivity = mActivityRule.getActivity();
        assertThat(mActivity, notNullValue());
    }

    /**
     * We ensure that our recyclerview is displaying at least on item
     */
    @Test
    public void myNeighboursList_shouldNotBeEmpty() {
        // First scroll to the position that needs to be matched and click on it.
        onView(ViewMatchers.withId(R.id.list_neighbours))
                .check(matches(hasMinimumChildCount(1)));
    }

    /**
     * When we delete an item, the item is no more shown
     */
    @Test
    public void myNeighboursList_deleteAction_shouldRemoveItem() {
        // Given : We remove the element at position 2

        onView(ViewMatchers.withId(R.id.list_neighbours)).check(withItemCount(NEIGHBOURS_ITEMS_COUNT));
        // When perform a click on a delete icon
        onView(ViewMatchers.withId(R.id.list_neighbours))
                .perform(RecyclerViewActions.actionOnItemAtPosition(1, new DeleteViewAction()));
        // Then : the number of element is 11
        onView(ViewMatchers.withId(R.id.list_neighbours)).check(withItemCount(NEIGHBOURS_ITEMS_COUNT -1));
    }

    /**
     * When we click on an item, DetailNeighbourActivity is launched
     * and the TextView name is correct
     */
    @Test
    public void myNeighbourList_clickAction_shouldShowDetailNeighbourActivity(){

        Intents.init();
        ShowDetailNeighbourActivity sDNA = new ShowDetailNeighbourActivity();

        // Perform a click on an item
        onView(ViewMatchers.withId(R.id.list_neighbours))
                .perform(RecyclerViewActions.actionOnItemAtPosition(1, sDNA));
        // Check if DetailNeighbourActivity running
        intended(hasComponent(DetailNeighbourActivity.class.getName()));
        // Check the TextView value is correct
        onView(ViewMatchers.withId(R.id.detail_name)).check(matches(withText(sDNA.getName())));

    }

    /**
     * When we click on favourite, make sur the number
     * of favourite neighbours is right
     */
    @Test
    public void myFavouriteList_checkNeighbours_shouldBeFavourite(){

        Intents.init();
        // Perform a click on Favourite
        onView(ViewMatchers.withId(R.id.tabItem2))
                .perform(click());
        // Check if FavouriteFragment is running
        intended(hasComponent(FavouriteFragment.class.getName()));
        // Make sure there are the right number of favourite neighbours
        onView(ViewMatchers.withId(R.id.list_favourites)).check(withItemCount(FAVOURITES_ITEMS_COUNT));
    }

}