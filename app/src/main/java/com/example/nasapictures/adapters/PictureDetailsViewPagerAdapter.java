package com.example.nasapictures.adapters;

import android.content.Context;
import android.graphics.Color;
import android.text.Html;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.nasapictures.R;
import com.example.nasapictures.models.PictureDetails;
import com.github.chrisbanes.photoview.PhotoView;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.swiperefreshlayout.widget.CircularProgressDrawable;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

public class PictureDetailsViewPagerAdapter extends PagerAdapter {

    private AppCompatActivity activity;
    private List<PictureDetails> mPictureDetailsList;
    private Context mContext;

    public PictureDetailsViewPagerAdapter(Context context, AppCompatActivity activity, List<PictureDetails> pictureDetailsList) {
        this.activity = activity;
        this.mPictureDetailsList = pictureDetailsList;
        this.mContext = context;
    }

    @NonNull
    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        LayoutInflater inflater = activity.getLayoutInflater();
        View viewItem = inflater.inflate(R.layout.item_picture_details, container, false);
        try {
            setPictureDetails(container, viewItem, position);
        } catch (Exception e) {
            Log.e("PictureDetailsViewPager", "Error while setting Picture Details");
            e.printStackTrace();
            Toast.makeText(mContext, "Unable to set NASA picture details, please try again later.", Toast.LENGTH_SHORT).show();
        }
        return viewItem;
    }

    private void setPictureDetails(ViewGroup container, View viewItem, int position) {
        // Circular Progress Drawable to show while Glide loads image
        CircularProgressDrawable circularProgressDrawable = new CircularProgressDrawable(mContext);
        circularProgressDrawable.setStrokeWidth(10f);
        circularProgressDrawable.setCenterRadius(48f);
        circularProgressDrawable.setColorSchemeColors(Color.WHITE);
        circularProgressDrawable.start();

        // Use PhotoView image view for in built photo zoom functions
        PhotoView nasaPictureView = (PhotoView) viewItem.findViewById(R.id.nasa_picture);

        // Set the image using Glide library
        Glide.with(mContext)
                .load(mPictureDetailsList.get(position).getHdurl())
                .apply(new RequestOptions()
                        .placeholder(circularProgressDrawable))
                .apply(new RequestOptions()
                        .fitCenter())
                .apply(new RequestOptions()
                        .error(R.drawable.ic_broken_image))
                .into(nasaPictureView);

        // Set picture details
        TextView pictureTitle = (TextView) viewItem.findViewById(R.id.picture_title);
        TextView pictureDate = (TextView) viewItem.findViewById(R.id.picture_date);
        TextView pictureCopyright = (TextView) viewItem.findViewById(R.id.picture_copyright);
        TextView pictureDescription = (TextView) viewItem.findViewById(R.id.picture_description);

        pictureTitle.setText(mPictureDetailsList.get(position).getTitle());
        pictureDate.setText(mPictureDetailsList.get(position).getDate());
        if (mPictureDetailsList.get(position).getCopyright() != null) {
            String copyrightText = activity.getString(R.string.copyright_icon).concat(mPictureDetailsList.get(position).getCopyright());
            pictureCopyright.setText(copyrightText);
        }
        pictureDescription.setText(Html.fromHtml(mPictureDetailsList.get(position).getExplanation()));
        ((ViewPager) container).addView(viewItem);
    }

    @Override
    public int getCount() {
        return mPictureDetailsList.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == ((View) object);
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        ((ViewPager) container).removeView((View) object);
    }
}