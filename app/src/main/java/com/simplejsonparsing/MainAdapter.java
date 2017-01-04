package com.simplejsonparsing;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.simplejsonparsing.api.dao.MainDao;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by irfan on 04/01/17.
 */

public class MainAdapter extends RecyclerView.Adapter<MainAdapter.MainViewHolder> {
    public Context mContext;
    public List<MainDao.ResultsBean> mData = new ArrayList<>();
    public MainDao.ResultsBean mItem;

    public MainAdapter(Context mContext,
                       List<MainDao.ResultsBean> mData) {
        this.mContext = mContext;
        this.mData = mData;
    }

    @Override
    public MainViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View mView = LayoutInflater.from(parent.getContext()).inflate(R.layout.main_row, parent, false);
        MainViewHolder mHolder = new MainViewHolder(mView);
        return mHolder;
    }

    @Override
    public void onBindViewHolder(MainViewHolder holder, int position) {
        mItem = mData.get(position);
        holder.mTitle.setText(mItem.getOriginal_title());
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    public class MainViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.txt_movie_title)
        TextView mTitle;

        public MainViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
