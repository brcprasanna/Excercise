package com.prasanna.yelpreviewapp.viewmodel;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import static com.prasanna.yelpreviewapp.utils.AppConstants.EMPTY;
import static org.junit.Assert.*;

/**
 * Created by Prasanna V on 2018-10-17.
 */
public class SearchViewModelTest {

    private SearchViewModel mSearchViewModel;

    @Before
    public void setUp() {
        mSearchViewModel = new SearchViewModel();
    }

    @Test
    public void testGetSpinnerRangeText_with1_Equals() {
        String result = mSearchViewModel.getSpinnerRangeText("1", "All");
        assertEquals(result, "1");
    }

    @Test
    public void testGetSpinnerRangeText_with2_Equals() {
        String result = mSearchViewModel.getSpinnerRangeText("2", "All");
        assertEquals(result, "2");
    }

    @Test
    public void testGetSpinnerRangeText_with3_Equals() {
        String result = mSearchViewModel.getSpinnerRangeText("3", "All");
        assertEquals(result, "3");
    }

    @Test
    public void testGetSpinnerRangeText_with4_Equals() {
        String result = mSearchViewModel.getSpinnerRangeText("4", "All");
        assertEquals(result, "4");
    }

    @Test
    public void testGetSpinnerRangeText_withAll_Equals() {
        String result = mSearchViewModel.getSpinnerRangeText("All", "All");
        assertEquals(result, EMPTY);
    }

    @Test
    public void testGetSpinnerRangeText_with1_NotEquals() {
        String result = mSearchViewModel.getSpinnerRangeText("1", "All");
        assertNotEquals(result, "2");
    }

    @Test
    public void testGetSpinnerRangeText_with2_NotEquals() {
        String result = mSearchViewModel.getSpinnerRangeText("2", "All");
        assertNotEquals(result, "3");
    }

    @Test
    public void testGetSpinnerRangeText_with3_NotEquals() {
        String result = mSearchViewModel.getSpinnerRangeText("3", "All");
        assertNotEquals(result, "4");
    }

    @Test
    public void testGetSpinnerRangeText_with4_NotEquals() {
        String result = mSearchViewModel.getSpinnerRangeText("4", "All");
        assertNotEquals(result, "All");
    }

    @Test
    public void testGetSpinnerRangeText_withAll_NotEquals() {
        String result = mSearchViewModel.getSpinnerRangeText("All", "All");
        assertNotEquals(result, "All");
    }

}