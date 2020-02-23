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
import androidx.test.espresso.matcher.ViewMatchers;
import androidx.test.rule.ActivityTestRule;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.Espresso.pressBack;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.swipeDown;
import static androidx.test.espresso.action.ViewActions.swipeLeft;
import static androidx.test.espresso.action.ViewActions.swipeRight;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.contrib.RecyclerViewActions.actionOnItemAtPosition;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.allOf;

public class PictureDetailsActivityTest {

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
        onView(ViewMatchers.withId(R.id.recycler_view_pictures_grid)).perform(actionOnItemAtPosition(listItemInTest, click()));
    }

    /**
     * PictureDetails Activity visible after navigation
     */
    @Test
    public void testPictureDetailsActivityInView() {
        onView(ViewMatchers.withId(R.id.parent_layout_picture_details)).check(matches(isDisplayed()));
    }

    /**
     * PictureDetails view pager visible
     */
    @Test
    public void testPictureDetailsViewPagerVisible() {
        onView(ViewMatchers.withId(R.id.view_pager_picture_details)).check(matches(isDisplayed()));
    }

    /**
     * Tab selector visible
     */
    @Test
    public void testPictureDetailsTabSelectorVisible() {
        onView(ViewMatchers.withId(R.id.tab_selector)).check(matches(isDisplayed()));
    }

    /**
     * Select list item, nav to PictureDetailsActivity
     * Correct NASA picture in view?
     * Press back
     */
    @Test
    public void testNavigatePictureDetailsAndPressBack() {
        onView(allOf(withId(R.id.picture_title), isDisplayed())).check(matches(withText(pictureDetails.getTitle())));
        pressBack();
        onView(ViewMatchers.withId(R.id.parent_layout_picture_grid)).check(matches(isDisplayed()));
    }

    /**
     * Select list item, nav to PictureDetailsActivity
     * Swipe to Right
     */
    @Test
    public void testNavigatePictureDetailsAndSwipeRight() {
        onView(allOf(withId(R.id.picture_title), isDisplayed())).check(matches(withText(pictureDetails.getTitle())));
        onView(withId(R.id.view_pager_picture_details)).perform(swipeRight());
    }

    /**
     * Select list item, nav to PictureDetailsActivity
     * Swipe to Left
     */
    @Test
    public void testNavigatePictureDetailsAndSwipeLeft() {
        onView(allOf(withId(R.id.picture_title), isDisplayed())).check(matches(withText(pictureDetails.getTitle())));
        onView(withId(R.id.view_pager_picture_details)).perform(swipeLeft());
    }

    /**
     * Select list item, nav to PictureDetailsActivity
     * Scroll Down
     */
    @Test
    public void testNavigatePictureDetailsAndSwipeDown() {
        onView(allOf(withId(R.id.picture_title), isDisplayed())).check(matches(withText(pictureDetails.getTitle())));
        onView(withId(R.id.view_pager_picture_details)).perform(swipeDown());
    }
}