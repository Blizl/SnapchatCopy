package com.example.snapchatcopy;

import android.content.Context;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class SnapsRVAdapter extends RecyclerView.Adapter<SnapsRVAdapter.SnapsViewHolder> {

    private final List<Snap> snaps;

    SnapsRVAdapter(List<Snap> snaps) {
        this.snaps = snaps;
    }

    @NonNull
    @Override
    public SnapsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.snaps_row_item, parent, false);
        return new SnapsViewHolder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull SnapsViewHolder holder, int position) {
        holder.senderTextView.setText(snaps.get(position).sender);
        if (snaps.get(position).read) {
            holder.unClickedBox.setVisibility(View.GONE);
            holder.clickedBox.setVisibility(View.VISIBLE);
        } else {
            holder.unClickedBox.setVisibility(View.VISIBLE);
            holder.clickedBox.setVisibility(View.GONE);
        }
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (holder.unClickedBox.getVisibility() == View.VISIBLE) {
                    holder.clickedBox.setVisibility(View.VISIBLE);
                    holder.unClickedBox.setVisibility(View.GONE);
                    snaps.get(position).read = true;
                    showSnapAndDismiss();


                }
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
        return snaps.size();
    }

    public class SnapsViewHolder extends RecyclerView.ViewHolder {
        TextView senderTextView;
        ImageView unClickedBox;
        ImageView clickedBox;

        public SnapsViewHolder(View view) {
            super(view);
            senderTextView = view.findViewById(R.id.sender_textview);
            unClickedBox = view.findViewById(R.id.unclicked_snap_image);
            clickedBox = view.findViewById(R.id.clicked_snap_image);
        }
    }
}
