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

public class DiscoverRVAdapter extends RecyclerView.Adapter<DiscoverRVAdapter.DiscoverViewHolder> {

    @NonNull
    @Override
    public DiscoverViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.discover_row_item, parent, false);
        return new DiscoverViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull DiscoverViewHolder holder, int position) {
        String url = "https://cdn.pixabay.com/photo/2017/08/22/21/00/special-forces-2670428_960_720.jpg";
        Picasso.get().load(url).fit().into(holder.discoverImageView);
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
        return 10;
    }

    public class DiscoverViewHolder extends RecyclerView.ViewHolder{
        ImageView discoverImageView;

        public DiscoverViewHolder(@NonNull View itemView) {
            super(itemView);
            discoverImageView = itemView.findViewById(R.id.discover_card_image_view);
        }
    }
}
