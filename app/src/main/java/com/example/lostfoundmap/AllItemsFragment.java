package com.example.lostfoundmap;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.lostfoundmap.sqlitehelper.Advert;
import com.example.lostfoundmap.sqlitehelper.DatabaseHelper;

import java.util.ArrayList;

public class AllItemsFragment extends Fragment implements AdvertAdapter.ItemClickListener {
    private ArrayList<Advert> advertArrayList;
    private DatabaseHelper databaseHelper;
    RecyclerView recyclerView;
    RecyclerView.LayoutManager layoutManager;
    AdvertAdapter advertAdapter;
    public AllItemsFragment() {
        // Required empty public constructor
    }
    public static AllItemsFragment newInstance() {
        AllItemsFragment fragment = new AllItemsFragment();

        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {

        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_all_items, container, false);

        // Get Adverts
        databaseHelper = new DatabaseHelper(getContext());
        advertArrayList = databaseHelper.getAllAdverts();

        // RecyclerView
        recyclerView = view.findViewById(R.id.recyclerView);
        layoutManager = new LinearLayoutManager(getActivity(),LinearLayoutManager.VERTICAL, false);
        advertAdapter = new AdvertAdapter(getActivity(), advertArrayList, this);
        recyclerView.setAdapter(advertAdapter);
        recyclerView.setLayoutManager(layoutManager);

        return view;
    }

    @Override
    public void onItemClick(Advert advert) {
        Fragment fragment = ItemFragment.newInstance(advert.getName(), advert.getType(), advert.getPhone(), advert.getDesc(), advert.getDate(), advert.getLocation());
        FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
        transaction.hide(getActivity().getSupportFragmentManager().findFragmentByTag("item_fragment"));
        transaction.add(R.id.frame_container, fragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }
}