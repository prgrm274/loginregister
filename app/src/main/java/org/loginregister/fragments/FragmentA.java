package org.loginregister.fragments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import org.loginregister.androiddev.R;
import org.loginregister.adapters.Adapter;
import org.loginregister.models.Item;

import java.util.ArrayList;

public class FragmentA extends Fragment {

    private ArrayList<Item> items;
    private RecyclerView recyclerView;
    private Adapter adapter;
    private RecyclerView.LayoutManager layoutManager;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_a, container, false);

        items = new ArrayList<>();
        items.add(new Item(
                R.drawable.cover1,
                R.drawable.spirit_icon,
                "Some title",
                "A good application",
                "by prgrm274")
        );
        items.add(new Item(
                R.drawable.cover2,
                R.drawable.awareness_logo,
                "Some title 2",
                "A good application 2",
                "by prgrm274")
        );
        layoutManager = new LinearLayoutManager(getActivity());
        adapter = new Adapter(items);
        recyclerView = view.findViewById(R.id.recyclerview__fragment_a);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);

        return view;
    }
}