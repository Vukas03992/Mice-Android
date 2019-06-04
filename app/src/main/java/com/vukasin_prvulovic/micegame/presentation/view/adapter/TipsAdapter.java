package com.vukasin_prvulovic.micegame.presentation.view.adapter;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.vukasin_prvulovic.micegame.R;
import com.vukasin_prvulovic.micegame.presentation.base.di.scope.PerFragment;
import com.vukasin_prvulovic.micegame.presentation.view.fragments.BoardFragment;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

@PerFragment
public class TipsAdapter extends PagerAdapter{

    private List<View> views;
    private LayoutInflater inflater;
    @Inject
    BoardFragment boardFragment;

    @Inject
    public TipsAdapter(Context context) {
        inflater=LayoutInflater.from(context);
        views=new ArrayList<>();
    }

    @Override
    public int getCount() {
        return 6;
    }

    public void release(){
        for (View view : views) {
            view.setOnLongClickListener(null);
        }
        views.clear();
        views=null;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        ViewGroup currentItem;
        if (views.size()>position && views.get(position)!=null){
            currentItem=(ViewGroup) views.get(position);
        }else{
            switch (position){
                case 0: currentItem=(ViewGroup) inflater.inflate(R.layout.fragment_board_tips_first,container,false);
                    break;
                case 1: currentItem=(ViewGroup) inflater.inflate(R.layout.fragment_board_tips_second,container,false);
                    break;
                case 2: currentItem=(ViewGroup) inflater.inflate(R.layout.fragment_board_tips_third,container,false);
                    break;
                case 3: currentItem=(ViewGroup) inflater.inflate(R.layout.fragment_board_tips_fourth,container,false);
                    break;
                case 4: currentItem=(ViewGroup) inflater.inflate(R.layout.fragment_board_tips_fifth,container,false);
                    break;
                default: currentItem=(ViewGroup) inflater.inflate(R.layout.fragment_board_tips_sixth,container,false);
                    break;
            }
            currentItem.setOnLongClickListener(boardFragment);
        }
        container.addView(currentItem);
        return currentItem;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        views.remove((View)object);
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view==(View)object;
    }
}
