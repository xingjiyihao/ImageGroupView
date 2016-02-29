package com.loopeer.android.librarys.imagegroupview.view;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.loopeer.android.librarys.imagegroupview.R;
import com.loopeer.android.librarys.imagegroupview.model.SquareImage;

import java.util.ArrayList;
import java.util.List;

class GridImageAdapter extends BaseAdapter {
    private Context mContext;
    private List<SquareImage> mData;
    private OnSquareClickListener mClickListener;

    public GridImageAdapter(Context c, OnSquareClickListener listener) {
        mContext = c;
        mData = new ArrayList<>();
        mClickListener = listener;
    }

    public void updateData(List<SquareImage> datas, boolean showAddButton) {
        setData(datas, showAddButton);
        notifyDataSetChanged();
    }

    public void setData(List<SquareImage> datas, boolean showAddButton) {
        mData.clear();
        mData.addAll(datas);
        if (showAddButton) {
            mData.add(null);
        }
    }

    public int getCount() {
        return mData.size();
    }

    public SquareImage getItem(int position) {
        return mData.get(position);
    }

    public long getItemId(int position) {
        return 0;
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        SquareImageView squareView;
        if (convertView == null) {
            squareView = new SquareImageView(mContext);
            squareView.setLayoutParams(new ViewGroup.LayoutParams(-1, -2));
        } else {
            squareView = (SquareImageView) convertView;
        }

        bindData(squareView, position);
        return squareView;
    }

    private void bindData(SquareImageView squareView, final int position) {
        if (getItem(position) == null) {
            squareView.setImageResource(R.drawable.ic_photo_default);
        } else {
            squareView.setImageData(getItem(position));
        }
        squareView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mClickListener.photoClick(v, getItem(position), position);
            }
        });
    }

    public interface OnSquareClickListener {
        void photoClick(View v, SquareImage squareImage, int position);
    }
}