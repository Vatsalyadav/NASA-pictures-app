package com.example.nasapictures.view;

import com.example.nasapictures.R;
import com.example.nasapictures.models.PictureDetails;
import com.example.nasapictures.viewmodels.PicturesGridActivityViewModel;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import java.util.List;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModelProvider;
import androidx.test.espresso.contrib.RecyclerViewActions;
import androidx.test.espresso.matcher.ViewMatchers;
import androidx.test.rule.ActivityTestRule;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.contrib.RecyclerViewActions.actionOnItemAtPosition;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.allOf;


public class PicturesGridActivityTest {

    @Rule
    public ActivityTestRule<PicturesGridActivity> mActivityTestRule = new ActivityTestRule<>(PicturesGridActivity.class);

    private PicturesGridActivity mPicturesGridActivity;
    private PicturesGridActivityViewModel mPicturesGridActivityViewModel;
    private int listItemInTest = 4;
    private PictureDetails pictureDetails;

    /**
     * Setup activity and fetch Pictures Details data that will be tested
     */
    @Before
    public void fetchPicturesDataToBeTested() {
        mPicturesGridActivity = mActivityTestRule.getActivity();
        mPicturesGridActivityViewModel = new ViewModelProvider(mPicturesGridActivity).get(PicturesGridActivityViewModel.class);
        mPicturesGridActivityViewModel.init(mPicturesGridActivity);
        LiveData<List<PictureDetails>> pictureDetailsList = mPicturesGridActivityViewModel.getPictureDetails();
        pictureDetails = pictureDetailsList.getValue().get(listItemInTest);

    }

    /**
     * Pictures Grid Activity opened
     */
    @Test
    public void testPicturesGridActivityInView() {
        onView(ViewMatchers.withId(R.id.parent_layout_picture_grid)).check(matches(isDisplayed()));
    }

    /**
     * RecyclerView comes into view
     */
    @Test
    public void testPicturesGridRecyclerViewVisible() {
        onView(ViewMatchers.withId(R.id.recycler_view_pictures_grid)).check(matches(isDisplayed()));
    }

    /**
     * RecyclerView comes into view
     * Scroll to position 20
     */
    @Test
    public void testPicturesGridRecyclerViewSwipeDown() {
        onView(ViewMatchers.withId(R.id.recycler_view_pictures_grid)).check(matches(isDisplayed()));
        onView(ViewMatchers.withId(R.id.recycler_view_pictures_grid)).perform(RecyclerViewActions.scrollToPosition(20));
    }

    /**
     * Select list item, nav to PictureDetailsActivity
     * Correct NASA picture in view?
     */
    @Test
    public void testPicturesGridRecyclerViewNavigate() {
        onView(ViewMatchers.withId(R.id.recycler_view_pictures_grid)).perform(actionOnItemAtPosition(listItemInTest, click()));
        onView(allOf(withId(R.id.picture_title), isDisplayed())).check(matches(withText(pictureDetails.getTitle())));
        onView(allOf(withId(R.id.picture_date), isDisplayed())).check(matches(withText(pictureDetails.getDate())));
        onView(allOf(withId(R.id.picture_description), isDisplayed())).check(matches(withText(pictureDetails.getExplanation())));
    }
}