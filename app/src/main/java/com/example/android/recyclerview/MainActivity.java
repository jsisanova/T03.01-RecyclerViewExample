/*
 * Copyright (C) 2016 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.example.android.recyclerview;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

// (7) Implement GreenAdapter.ListItemClickListener from the MainActivity
public class MainActivity extends AppCompatActivity implements GreenAdapter.ListItemClickListener {

    private static final int NUM_LIST_ITEMS = 100;

    /*
     * References to RecyclerView and Adapter to reset the list to its
     * "pretty" state when the reset menu item is clicked.
     */
    private GreenAdapter mAdapter;
    private RecyclerView mNumbersList;

    /*
     * (8) Create a Toast variable called mToast to store the current Toast
     *
     * Will allow us to cancel Toast (if it's showing) to display a new Toast. If we didn't do this, Toasts would be delayed
     * in showing up if you clicked many list items in quick succession.
     */
    private Toast mToast;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        /*
         * Get a reference to our RecyclerView from xml. This allows us to
         * do things like set the adapter of the RecyclerView and toggle the visibility.
         */
        mNumbersList = (RecyclerView) findViewById(R.id.rv_numbers);

        /*
         * A LinearLayoutManager is responsible for measuring and positioning item views within a
         * RecyclerView into a linear list. This means that it can produce either a horizontal or
         * vertical list depending on which parameter you pass in to the LinearLayoutManager
         * constructor. By default, if you don't specify an orientation, you get a vertical list.
         * In our case, we want a vertical list, so we don't need to pass in an orientation flag to
         * the LinearLayoutManager constructor.
         *
         * There are other LayoutManagers available to display your data in uniform grids,
         * staggered grids, and more! See the developer documentation for more details.
         *
         * Use LinearLayoutManager.
         */
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        mNumbersList.setLayoutManager(layoutManager);

        /*
         * Use this setting to improve performance if you know that changes in content do not
         * change the child layout size in the RecyclerView
         */
        mNumbersList.setHasFixedSize(true);


        // (12) Pass in this as the ListItemClickListener to the GreenAdapter constructor
        /*
         * The GreenAdapter is responsible for displaying each item in the list.
         */
//        mAdapter = new GreenAdapter(NUM_LIST_ITEMS);
        mAdapter = new GreenAdapter(NUM_LIST_ITEMS, this);
        mNumbersList.setAdapter(mAdapter);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu
        getMenuInflater().inflate(R.menu.main, menu);
        // Return true to display this menu
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int itemId = item.getItemId();

        switch (itemId) {
            // When you click the reset menu item, we want to start all over and display the pretty gradient again.
            case R.id.action_refresh:
                // Create and set a new adapter on the RecyclerView and return true
                // (13) Pass in this as the ListItemClickListener to the GreenAdapter constructor
//                mAdapter = new GreenAdapter(NUM_LIST_ITEMS);
                mAdapter = new GreenAdapter(NUM_LIST_ITEMS, this);
                mNumbersList.setAdapter(mAdapter);
                return true;
        }

        // For all other IDs, return super.onOptionsItemSelected
        return super.onOptionsItemSelected(item);
    }

    // (9) Override ListItemClickListener's onListItemClick method
    /**
     * This is where we receive our callback from
     * {@link com.example.android.recyclerview.GreenAdapter.ListItemClickListener}
     *
     * This callback is invoked when you click on an item in the list.
     *
     * @param clickedItemIndex Index in the list of the item that was clicked.
     */
    @Override
    public void onListItemClick(int clickedItemIndex) {
        // (10) In the beginning of the method, cancel the Toast if it isn't null
        /*
         * Doing so ensures that new Toast will show immediately, rather than
         * being delayed while other pending Toasts are shown.
         */
        if (mToast != null) {
            mToast.cancel();
        }

        // (11) Show a Toast when an item is clicked, displaying that item number that was clicked
        String toastMessage = "Item #" + clickedItemIndex + " clicked.";
        mToast = Toast.makeText(this, toastMessage, Toast.LENGTH_LONG);

        mToast.show();
    }
}