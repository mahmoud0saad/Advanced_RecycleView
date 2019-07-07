package com.example.android.recycleview_0;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class MainHolder extends RecyclerView.ViewHolder {
    private ImageView mImageView;
    private TextView mTextView;

    public MainHolder(View itemView,final MainItemClickListener mMainItemClickListener ) {
        super(itemView);
        mImageView= itemView.findViewById(R.id.photo_product_image);
        mTextView= itemView.findViewById(R.id.price_text);

        mImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mMainItemClickListener.onImageClick(getAdapterPosition());
            }
        });
        mTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mMainItemClickListener.onPriceClick(getAdapterPosition());
            }
        });

    }
    public void onBind(Context context,int mImageResourseId){
        mImageView.setImageResource(mImageResourseId);
        mTextView.setText(context.getString(R.string.price,String.valueOf(mImageResourseId).substring(7)));
    }

}
