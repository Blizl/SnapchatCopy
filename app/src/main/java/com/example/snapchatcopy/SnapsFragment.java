package com.example.snapchatcopy;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.LinkedList;
import java.util.List;

public class SnapsFragment extends Fragment {

    RecyclerView snapsRecyclerView;
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        snapsRecyclerView = view.findViewById(R.id.snaps_rv);
        setupSnapsRV();
    }

    private void setupSnapsRV() {
        List<Snap> snaps = new LinkedList<>();
        snaps.add(new Snap("Victor Liang", "Receiever1"));
        snaps.add(new Snap("Raylen Liang", "Receiever1"));
        snaps.add(new Snap("Kevin Liang", "Receiever1"));
        snaps.add(new Snap("Vivian Liang", "Receiever1"));
        SnapsRVAdapter adapter = new SnapsRVAdapter(snaps);
        snapsRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        snapsRecyclerView.setAdapter(adapter);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.snaps_fragment, container, false);
    }
}
