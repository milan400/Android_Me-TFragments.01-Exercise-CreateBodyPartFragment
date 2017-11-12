package com.example.android.android_me.ui;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;

import com.example.android.android_me.R;
import com.example.android.android_me.data.AndroidImageAssets;


public class MasterListFragment extends Fragment {

    //Define a new interface OnImageClickListener that triggers a callback in the host activity
    OnImageClickListener mCallback;

    //OnImageClickListener interface, calls a method inthe host activity named onImageSelected
    public interface OnImageClickListener{
        void onImageSelected(int position);
    }

    //Override onAttach to make sure that the container activity has implemented the callback
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        try{
            mCallback = (OnImageClickListener) context;
        }catch (ClassCastException e){
            throw new ClassCastException(context.toString()
                    + " must implement OnImageClickListener");
        }
    }

    public MasterListFragment() {
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        final View rootView = inflater.inflate(R.layout.fragment_master_list,container,false);

        GridView gridview = (GridView) rootView.findViewById(R.id.gridview);

        MasterListAdapter masterListAdapter = new MasterListAdapter(getContext(), AndroidImageAssets.getAll());
        gridview.setAdapter(masterListAdapter);

        //Set a click listener on the gridView and trigger the callback onImageSelected when an item is clicked
        gridview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //Trigger the callback method and pass in the position that was clicked
                mCallback.onImageSelected(position);
            }
        });

        return rootView;
    }
}






























































































