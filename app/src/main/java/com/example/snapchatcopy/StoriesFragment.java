package com.example.snapchatcopy;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class StoriesFragment extends Fragment {
    RecyclerView storiesRV;
    RecyclerView discoverRV;

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        storiesRV = view.findViewById(R.id.stories_rv);
        discoverRV = view.findViewById(R.id.discover_rv);

        setupDiscoverRV();
        setupStoriesRV();
    }

    private void setupStoriesRV() {
        StoriesRVAdapter storiesRVAdapter = new StoriesRVAdapter();
        storiesRV.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));
        storiesRV.setAdapter(storiesRVAdapter);
    }

    private void setupDiscoverRV() {
        DiscoverRVAdapter discoverRVAdapter = new DiscoverRVAdapter();
        discoverRV.setLayoutManager(new GridLayoutManager(getContext(), 2));
        discoverRV.setAdapter(discoverRVAdapter);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.stories_fragment, container, false);
    }
}
