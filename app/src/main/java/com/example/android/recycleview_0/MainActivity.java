package com.example.android.recycleview_0;

import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import java.util.List;

public class MainActivity extends AppCompatActivity implements MainItemClickListener{
    private Toast mToast;
    private MainAdapter mMainAdapter;
    private RecyclerView mRecyclerView;
    private GridLayoutManager mGridLayoutManager;
    SwipeRefreshLayout swiper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        swiper=findViewById(R.id.swiper);


        mMainAdapter=new MainAdapter(new Utils().getListImageId(this),this,swiper);

        mGridLayoutManager=new GridLayoutManager(this,1);

        mRecyclerView=findViewById(R.id.main_recycler);
        mRecyclerView.setAdapter(mMainAdapter);
        mRecyclerView.setLayoutManager(mGridLayoutManager);
        mRecyclerView.setHasFixedSize(true);


        new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(ItemTouchHelper.DOWN, ItemTouchHelper.RIGHT|ItemTouchHelper.LEFT) {
            @Override
            public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {
                mMainAdapter.move(viewHolder.getAdapterPosition(),target.getAdapterPosition());
                return true;
            }

            @Override
            public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction) {
                mMainAdapter.delete(viewHolder.getAdapterPosition());
            }
        }).attachToRecyclerView(mRecyclerView);

    }

    @Override
    public void onImageClick(int position) {
        if (mToast!=null){
            mToast.cancel();
        }
        mToast=Toast.makeText(this, "click on image"+position, Toast.LENGTH_SHORT);
        mToast.show();
    }

    @Override
    public void onPriceClick(int position) {
        if (mToast!=null){
            mToast.cancel();
        }
        mToast=Toast.makeText(this, "click on price"+position, Toast.LENGTH_SHORT);
        mToast.show();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.tools_main,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int idItem=item.getItemId();
        if (idItem==R.id.refresh_list_recycle){
            Toast.makeText(this, "done refresh", Toast.LENGTH_SHORT).show();
        }
        return true;
    }
}
