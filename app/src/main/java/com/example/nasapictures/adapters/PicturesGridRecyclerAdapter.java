package com.example.nasapictures.adapters;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.nasapictures.R;
import com.example.nasapictures.models.PictureDetails;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.CircularProgressDrawable;

public class PicturesGridRecyclerAdapter extends RecyclerView.Adapter<PicturesGridRecyclerAdapter.ViewHolder> {

    private List<PictureDetails> mPictureDetails = new ArrayList<>();
    private Context mContext;

    public PicturesGridRecyclerAdapter(Context context, List<PictureDetails> pictureDetails) {
        mPictureDetails = pictureDetails;
        mContext = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.list_item_pictures_grid, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, final int position) {

        // Set the name of the NASA Picture
        viewHolder.mName.setText(mPictureDetails.get(position).getTitle());

        // Circular Progress Drawable to show while Glide loads image
        CircularProgressDrawable circularProgressDrawable = new CircularProgressDrawable(mContext);
        circularProgressDrawable.setStrokeWidth(10f);
        circularProgressDrawable.setCenterRadius(48f);
        circularProgressDrawable.setColorSchemeColors(Color.WHITE);
        circularProgressDrawable.start();

        // Set the image using Glide library
        Glide.with(mContext)
                .load(mPictureDetails.get(position).getUrl())
                .apply(new RequestOptions()
                        .placeholder(circularProgressDrawable))
                .apply(new RequestOptions()
                        .fitCenter())
                .apply(new RequestOptions()
                        .error(R.drawable.ic_sad))
                .into(viewHolder.mImage);
    }

    @Override
    public int getItemCount() {
        return mPictureDetails.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private ImageView mImage;
        private TextView mName;
        private LinearLayout parentLayout;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            mImage = itemView.findViewById(R.id.grid_image);
            mName = itemView.findViewById(R.id.grid_image_name);
            parentLayout = itemView.findViewById(R.id.parent_layout);
        }
    }
}
