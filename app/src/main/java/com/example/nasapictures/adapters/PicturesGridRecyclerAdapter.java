package com.example.nasapictures.adapters;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.nasapictures.R;
import com.example.nasapictures.models.PictureDetails;
import com.example.nasapictures.view.PictureDetailsActivity;

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
        try {
            // Set the name of the NASA Picture
            viewHolder.gridImageName.setText(mPictureDetails.get(position).getTitle());

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
                            .error(R.drawable.ic_broken_image))
                    .into(viewHolder.gridImage);
        } catch (Exception e) {
            Log.e("PicturesGridRecycler", "Error while setting NASA Pictures grid data");
            e.printStackTrace();
            Toast.makeText(mContext, "Unable to set NASA pictures grid data, please try again later.", Toast.LENGTH_SHORT).show();
        }

        try {
            final PictureDetailsActivity pictureDetailsActivity = new PictureDetailsActivity();
            viewHolder.gridParentLayout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Context context = view.getContext();
                    Intent intent = pictureDetailsActivity.launchPictureDetails(context, position, mPictureDetails);
                    context.startActivity(intent);
                }
            });
        } catch (Exception e) {
            Log.e("PicturesGridRecycler", "Error on launching PictureDetailsActivity");
            e.printStackTrace();
            Toast.makeText(mContext, "Unable to show selected NASA Picture details, please try again later.", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public int getItemCount() {
        return mPictureDetails.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private ImageView gridImage;
        private TextView gridImageName;
        private LinearLayout gridParentLayout;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            gridImage = itemView.findViewById(R.id.grid_picture);
            gridImageName = itemView.findViewById(R.id.grid_picture_title);
            gridParentLayout = itemView.findViewById(R.id.parent_layout_pictures_grid_item);
        }
    }
}
