package com.example.snapchatcopy;

import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.squareup.picasso.Picasso;

public class SnapTakenFragment extends Fragment {

    private Uri savedUri;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        savedUri = getArguments().getParcelable("uri");
    }

    public static SnapTakenFragment newInstance(Uri uri) {
        SnapTakenFragment fragment = new SnapTakenFragment();
        Bundle bundle = new Bundle();
        bundle.putParcelable("uri", uri);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        ImageView snapTaken = view.findViewById(R.id.snap_taken_image_view);
        Picasso.get().load(savedUri).fit().into(snapTaken);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.snap_taken_fragment, container, false);
    }
}
