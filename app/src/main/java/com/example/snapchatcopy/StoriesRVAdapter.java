package com.example.snapchatcopy;

import android.content.Context;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

public class StoriesRVAdapter extends RecyclerView.Adapter<StoriesRVAdapter.StoriesViewHolder> {

    @NonNull
    @Override
    public StoriesViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.stories_row_item, parent, false);
        return new StoriesViewHolder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull StoriesViewHolder holder, int position) {
        String url = "https://cdn.pixabay.com/photo/2019/06/30/18/32/track-4308559_960_720.jpg";

        Picasso.get().load(url).fit().into(holder.profilePic);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showSnapAndDismiss();
            }

            private void showSnapAndDismiss() {
                Context context = holder.itemView.getContext();
                DialogFragment randomDialogFragment = new RandomDialogFragment();
                FragmentManager fragmentManager = ((AppCompatActivity) holder.itemView.getContext()).getSupportFragmentManager();
                randomDialogFragment.show(fragmentManager
                        , "tag");

                Handler handler = new Handler(context.getMainLooper());
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        randomDialogFragment.dismiss();
                    }
                }, 1000);

            }
        });
    }


    @Override
    public int getItemCount() {
        return 3;
    }

    public static class StoriesViewHolder extends RecyclerView.ViewHolder {
        ImageView profilePic;

        public StoriesViewHolder(@NonNull View itemView) {
            super(itemView);
            profilePic = itemView.findViewById(R.id.profile_pic);

        }
    }
}
