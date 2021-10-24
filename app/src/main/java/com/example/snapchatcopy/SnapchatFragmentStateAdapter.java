package com.example.snapchatcopy;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

public class SnapchatFragmentStateAdapter extends FragmentStatePagerAdapter {

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        switch (position) {
            case 0:
                return "Snaps";
            case 1:
                return "Camera";
            case 2:
                return "Stories/Discover";
        }
        return "";
    }

    public SnapchatFragmentStateAdapter(@NonNull FragmentManager fm, int behavior) {
        super(fm, behavior);
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        Fragment createdFragment;
        switch (position) {
            case 0:
                SnapsFragment snapsFragment = new SnapsFragment();
                createdFragment = snapsFragment;
                break;
            case 1:
                CameraFragment cameraFragment = new CameraFragment();
                createdFragment = cameraFragment;
                break;
            case 2:
                createdFragment = new StoriesFragment();
                break;
            default:
                throw new IllegalStateException("Unexpected value: " + position);
        }
        return createdFragment;
    }

    @Override
    public int getCount() {
        return 3;
    }
}
