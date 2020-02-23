package com.sunofbeaches.taobaounion.ui.adapter;

import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.sunofbeaches.taobaounion.model.domain.HomePagerContent;
import com.sunofbeaches.taobaounion.utils.UrlUtils;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;

public class LooperPagerAdapter extends PagerAdapter {

    private List<HomePagerContent.DataBean> mData = new ArrayList<>();

    @Override
    public void destroyItem(@NonNull ViewGroup container,int position,@NonNull Object object) {
        container.removeView((View) object);
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container,int position) {
        //处理一下越界问题
        int realPosition = position % mData.size();
        //size = 5 == > 0,1,2,3,4
        // 0  --> 0
        //1 -- > 1
        //2 -- > 2
        //3 -- > 3
        //4 -- > 4
        //5 -- > 0
        //6 -- > 1
        HomePagerContent.DataBean dataBean = mData.get(realPosition);
        String coverUrl = UrlUtils.getCoverPath(dataBean.getPict_url());
        ImageView iv = new ImageView(container.getContext());
        ViewGroup.LayoutParams layoutParams = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.MATCH_PARENT);
        iv.setLayoutParams(layoutParams);
        iv.setScaleType(ImageView.ScaleType.CENTER_CROP);
        Glide.with(container.getContext()).load(coverUrl).into(iv);
        container.addView(iv);
        return iv;
    }

    @Override
    public int getCount() {
        return Integer.MAX_VALUE;
    }

    @Override
    public boolean isViewFromObject(@NonNull View view,@NonNull Object object) {
        return view == object;
    }

    public void setData(List<HomePagerContent.DataBean> contents) {
        mData.clear();
        mData.addAll(contents);
        notifyDataSetChanged();
    }
}
