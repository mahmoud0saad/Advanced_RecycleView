package com.example.android.recycleview_0;

import android.content.Intent;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class MainAdapter extends RecyclerView.Adapter<MainHolder> {
    private List<Integer> mIdImageResourse;
    private List<Integer> mRefreshImageResourse;
    private MainItemClickListener mMainItemClickListener;
    private SwipeRefreshLayout mSwiper;
    public MainAdapter(List<Integer> idImageResourse, MainItemClickListener MainItemClickListener, SwipeRefreshLayout swiper) {
        mMainItemClickListener=MainItemClickListener;
        mIdImageResourse=idImageResourse;
        mRefreshImageResourse=new ArrayList<>(mIdImageResourse);
        mSwiper=swiper;
    }

    @NonNull
    @Override
    public MainHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new MainHolder(
                LayoutInflater
                        .from(parent.getContext())
                        .inflate(R.layout.item_main,parent,false)
                ,mMainItemClickListener
        );
    }

    @Override
    public void onBindViewHolder(@NonNull MainHolder holder, int position) {
        holder.itemView.setTag(position);
        holder.onBind(holder.itemView.getContext(),mIdImageResourse.get(position));
        mSwiper.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                refresh();
            }
        });
    }

    private void refresh() {

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                mIdImageResourse.clear();
                mIdImageResourse.addAll(mRefreshImageResourse);


                notifyDataSetChanged();

                mSwiper.setRefreshing(false);
            }
        }, 3000);
    }
    @Override
    public int getItemCount() {
        return mIdImageResourse!=null ? mIdImageResourse.size():0 ;
    }

    public void delete(int adapterPosition) {
        mIdImageResourse.remove(adapterPosition);
        notifyDataSetChanged();
    }


    public void move(int newPositioin,int oldPosition) {

        Integer newItem=mIdImageResourse.get(oldPosition);
        mIdImageResourse.remove(oldPosition);
        mIdImageResourse.add(newPositioin,newItem);
        notifyItemMoved(oldPosition,newPositioin);
    }

}
