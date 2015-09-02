package com.anthony.deeplinkdemo.SectionFragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.anthony.deeplinkdemo.ItemActivity.ItemActivity;
import com.anthony.deeplinkdemo.R;

/**
 * Created by anthonyliu on 15/9/2.
 */
public class SectionFragment extends Fragment
{

    private RecyclerView mRecyclerView;
    private SectionAdapter mRecyclerAdapter;

    private String section;

    public static SectionFragment newInstance(String section) {
        SectionFragment fragment = new SectionFragment();
        Bundle bundle = new Bundle();
        bundle.putString("section", section);
        fragment.setArguments(bundle);
        return fragment;
    }

    public SectionFragment() {

    }


    @Override
    public void onPause(){

        super.onPause();

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){

        View view = inflater.inflate(R.layout.section_layout, container, false);

        mRecyclerView = (RecyclerView) view.findViewById(R.id.recycler_view);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        mRecyclerView.setAdapter(mRecyclerAdapter);

        return view;
    }


    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        section = getArguments().getString("section");

        mRecyclerAdapter = new SectionAdapter(getActivity(), itemClickListener);

    }


    @Override
    public void onResume(){

        super.onResume();

    }

    private RecyclerViewItemClickListener itemClickListener = new RecyclerViewItemClickListener() {
        @Override
        public void onItemClick(View view, int position) {

            Intent intent = new Intent(getActivity(), ItemActivity.class);
            intent.putExtra("section", section);
            intent.putExtra("item", position);
            startActivity(intent);

        }
    };

}