package com.zxin.root.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.zxin.root.bean.TitleBean;
import com.zxin.root.util.AppManager;
import com.zxin.root.view.PagerSlidingTabStrip;

import java.util.ArrayList;
import java.util.List;

/**
 * 通用的ViewpageFragment 的Adapter
 */
public class ViewPageFragmentAdapter<T extends TitleBean> extends FragmentPagerAdapter implements PagerSlidingTabStrip.IconTabProvider{
    private List<Fragment> mFragments;
    private List<T> mTitles = new ArrayList<>();

    public ViewPageFragmentAdapter(List<Fragment> mFragments, List<T> title) {
        super(AppManager.getInstance().getFragmentManager());
        this.mFragments = mFragments;
        this.mTitles = title;
    }

    public ViewPageFragmentAdapter(List<Fragment> mFragments, List<T> title,FragmentManager fragmentManager) {
        super(fragmentManager);
        this.mFragments = mFragments;
        this.mTitles = title;
    }

    public T getItemDatas(int position){
        return mTitles.get(position);
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return mTitles.get(position).label;
    }

    @Override
    public Fragment getItem(int position) {
        return mFragments.get(position);
    }

    @Override
    public int getCount() {
        return mFragments.size();
    }

    @Override
    public int getPageIconResId(int position) {
        return mTitles.get(position).labImage;
    }

    public void refreshNotify(List<Fragment> mFragments, List<T> title){
        this.mFragments = mFragments;
        this.mTitles = title;
        notifyDataSetChanged();
    }

}
