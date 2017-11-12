/*
* Copyright (C) 2017 The Android Open Source Project
*
* Licensed under the Apache License, Version 2.0 (the "License");
* you may not use this file except in compliance with the License.
* You may obtain a copy of the License at
*
*  	http://www.apache.org/licenses/LICENSE-2.0
*
* Unless required by applicable law or agreed to in writing, software
* distributed under the License is distributed on an "AS IS" BASIS,
* WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
* See the License for the specific language governing permissions and
* limitations under the License.
*/

package com.example.android.android_me.ui;

import android.content.Intent;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.android.android_me.R;
import com.example.android.android_me.data.AndroidImageAssets;

// This activity will display a custom Android image composed of three body parts: head, body, and legs
public class AndroidMeActivity extends AppCompatActivity {

    // TODO (1) Create a layout file that displays one body part image named fragment_body_part.xml
        // This layout should contain a single ImageView

    // TODO (2) Create a new class called BodyPartFragment to display an image of an Android-Me body part
        // In this class, you'll need to implement an empty constructor and the onCreateView method
        // TODO (3) Show the first image in the list of head images
            // Soon, you'll update this image display code to show any image you want


    private int headIndex;
    private int bodyIndex;
    private int legIndex;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_android_me);
        //Only create new fragments when there is no previously saved state

        if (savedInstanceState == null) {

            Intent intent = getIntent();
            Bundle extras = intent.getExtras();

            if(extras != null){
                headIndex = extras.getInt("headIndex");
                bodyIndex = extras.getInt("bodyIndex");
                legIndex = extras.getInt("legIndex");
            }


            // TODO (5) Create a new BodyPartFragment instance and display it using the FragmentManager
            BodyPartFragment headFragment = new BodyPartFragment();

            //set the list of image id's for the head fragment and set the position to the second image in the list
            headFragment.setmImageIds(AndroidImageAssets.getHeads());
            headFragment.setmListIndex(headIndex);

            //use FragmentManager and transaction to add the fragment to the screen
            FragmentManager fragmentManager = getSupportFragmentManager();

            //Fragment transaction
            fragmentManager.beginTransaction()
                    .add(R.id.head_container, headFragment)
                    .commit();


            BodyPartFragment bodyPartFragment = new BodyPartFragment();
            bodyPartFragment.setmImageIds(AndroidImageAssets.getBodies());
            bodyPartFragment.setmListIndex(bodyIndex);
            //body fragment
            fragmentManager.beginTransaction()
                    .add(R.id.body_container, bodyPartFragment)
                    .commit();


            BodyPartFragment legPartFragment = new BodyPartFragment();
            legPartFragment.setmImageIds(AndroidImageAssets.getLegs());
            legPartFragment.setmListIndex(legIndex);
            //leg Fragment
            fragmentManager.beginTransaction()
                    .add(R.id.leg_container, legPartFragment)
                    .commit();


        }
    }
}
