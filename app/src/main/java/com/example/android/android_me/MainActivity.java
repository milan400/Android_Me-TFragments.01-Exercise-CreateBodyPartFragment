package com.example.android.android_me;

import android.content.Intent;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.GridView;
import android.widget.Toast;

import com.example.android.android_me.data.AndroidImageAssets;
import com.example.android.android_me.ui.AndroidMeActivity;
import com.example.android.android_me.ui.BodyPartFragment;
import com.example.android.android_me.ui.MasterListFragment;

public class MainActivity extends AppCompatActivity implements MasterListFragment.OnImageClickListener{

    //Variables to store the values for the list index of the selected images
    private int headIndex;
    private int bodyIndex;
    private int legIndex;

    //Create a variable to track whether to display  a two-pane or single-pane display
    private boolean mTwoPane;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if(findViewById(R.id.android_me_linear_layout) != null){
            //This linearlayout will only initially exist in the two-pane tablet case
            mTwoPane = true;

            //Getting rid of the "Next" button that appears on phones with bigger screen
            Button nextButton = (Button) findViewById(R.id.next_button);
            nextButton.setVisibility(View.GONE);

            //change the Gridview to space out the images more on tablet
            GridView gridView = (GridView) findViewById(R.id.gridview);
            gridView.setNumColumns(2);

            if (savedInstanceState == null) {

                //use FragmentManager and transaction to add the fragment to the screen
                FragmentManager fragmentManager = getSupportFragmentManager();

                //Creating a new head fragments
                BodyPartFragment headFragment = new BodyPartFragment();
                //set the list of image id's for the head fragment and set the position to the second image in the list
                headFragment.setmImageIds(AndroidImageAssets.getHeads());
                //Fragment transaction
                fragmentManager.beginTransaction()
                        .add(R.id.head_container, headFragment)
                        .commit();


                BodyPartFragment bodyPartFragment = new BodyPartFragment();
                bodyPartFragment.setmImageIds(AndroidImageAssets.getBodies());
                //body fragment
                fragmentManager.beginTransaction()
                        .add(R.id.body_container, bodyPartFragment)
                        .commit();


                BodyPartFragment legPartFragment = new BodyPartFragment();
                legPartFragment.setmImageIds(AndroidImageAssets.getLegs());
                //leg Fragment
                fragmentManager.beginTransaction()
                        .add(R.id.leg_container, legPartFragment)
                        .commit();
            }
        }else{
            mTwoPane = false;

            MasterListFragment masterListFragment = new MasterListFragment();
            FragmentManager fragmentManager = getSupportFragmentManager();
            fragmentManager.beginTransaction()
                    .add(R.id.master_list_fragment,masterListFragment)
                    .commit();
        }



    }

    @Override
    public void onImageSelected(int position) {
        Toast.makeText(this, "Position clicked = " + position, Toast.LENGTH_SHORT).show();

        //Based on where a user has clicked, store the selected list index for the head,body and leg
        int bodyPartNumber = position / 12;

        //store the correct index of the image form list of images
        int listIndex = position - 12 * bodyPartNumber;

        if (mTwoPane) {
            //handle twoPane case
            BodyPartFragment newFragment = new BodyPartFragment();

            //Set the currently displayed item for the correct body part fragment
            switch (bodyPartNumber){
                case 0:
                    //A head image has been clicked
                    //give the correct image resources to the new fragment
                    newFragment.setmImageIds(AndroidImageAssets.getHeads());
                    newFragment.setmListIndex(listIndex);
                    //Replace the old head fragment with a new one
                    getSupportFragmentManager().beginTransaction()
                            .replace(R.id.head_container,newFragment)
                            .commit();
                    break;
                case 1:
                    //A body image has been clicked
                    //give the correct image resources to the new fragment
                    newFragment.setmImageIds(AndroidImageAssets.getBodies());
                    newFragment.setmListIndex(listIndex);
                    //Replace the old body fragment with a new one
                    getSupportFragmentManager().beginTransaction()
                            .replace(R.id.body_container,newFragment)
                            .commit();
                    break;
                case 2:
                    //A leg image has been clicked
                    //give the correct image resources to the new fragment
                    newFragment.setmImageIds(AndroidImageAssets.getLegs());
                    newFragment.setmListIndex(listIndex);
                    //Replace the old leg fragment with a new one
                    getSupportFragmentManager().beginTransaction()
                            .replace(R.id.leg_container,newFragment)
                            .commit();
                    break;
                default:
                    break;
            }

        } else {

            //set the currently displayed item for the correct body part fragment
            switch (bodyPartNumber) {
                case 0:
                    headIndex = listIndex;
                    break;
                case 1:
                    bodyIndex = listIndex;
                    break;
                case 2:
                    legIndex = listIndex;
                    break;
                default:
                    break;
            }

            //put this information in a Bundle and attach it to an Intent that will launch an AndroidMe activity
            Bundle b = new Bundle();
            b.putInt("headIndex", headIndex);
            b.putInt("bodyIndex", bodyIndex);
            b.putInt("legIndex", legIndex);

            //attach the Bundle to an intent
            final Intent intent = new Intent(this, AndroidMeActivity.class);
            intent.putExtras(b);

            //The "Next" button launches a new AndroidMeActivity
            Button nextButton = (Button) findViewById(R.id.next_button);
            nextButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    startActivity(intent);
                }
            });

        }
    }
}
