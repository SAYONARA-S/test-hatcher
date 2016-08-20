package com.hatcher.app.ui.fragment;

import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import com.hatcher.app.R;
import com.hatcher.app.ui.adapter.ViewPagerAdapter;
import com.hatcher.app.util.CommonUtil;
import com.hatcher.app.util.ViewInject;

/**
 * Created by wesley_s on 16/5/16.
 */
public class NewsListFragment extends BaseFragment implements ViewPager.OnPageChangeListener{

    private View view;
    @ViewInject
    private TextView tab_1;
    @ViewInject
    private TextView tab_2;
    @ViewInject
    private TextView tab_3;
    @ViewInject
    private ImageView shade_txt;
    @ViewInject
    private ImageView nav_line;
    @ViewInject
    private ViewPager news_view_pager;

    private FrameLayout fragment_1_container;

    private List<View> pageList;

    private View view1,view2,view3;
    private ViewPagerAdapter viewPagerAdapter;

    private int offset ;
    private int currIndex;
    private int bmpW;




    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.news_main_layout,null);

        view1 = inflater.inflate(R.layout.news_viewpager_tab_1,null);
        view2 = inflater.inflate(R.layout.news_viewpager_tab_2,null);
        view3 = inflater.inflate(R.layout.news_viewpager_tab_3,null);

        CommonUtil.initViewInject(this,NewsListFragment.class,getActivity());

        initView();
        initData();

        return view;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    @Override
    protected void initView() {
        pageList = new ArrayList<>();
        viewPagerAdapter = new ViewPagerAdapter(getActivity(),pageList);






    }

    @Override
    protected void initData() {

        bmpW = BitmapFactory.decodeResource(getResources(),R.mipmap.a).getWidth();
        DisplayMetrics dm = new DisplayMetrics();
        getActivity().getWindowManager().getDefaultDisplay().getMetrics(dm);
        int screenW = dm.widthPixels;
        offset = (screenW /3 - bmpW)/2;
        Matrix matrix = new Matrix();
        matrix.postTranslate(offset, 0);
        nav_line.setImageMatrix(matrix);

        pageList.add(view1);
        pageList.add(view2);
        pageList.add(view3);
        news_view_pager.setAdapter(viewPagerAdapter);
        news_view_pager.setCurrentItem(0);
        news_view_pager.addOnPageChangeListener(this);
        tab_1.setOnClickListener(this);
        tab_2.setOnClickListener(this);
        tab_3.setOnClickListener(this);

        Toast.makeText(getActivity(), "width is " + tab_1.getWidth(), Toast.LENGTH_SHORT).show();
        FrameLayout.LayoutParams params = new FrameLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT,
                ViewGroup.LayoutParams.MATCH_PARENT);
        params.width = screenW/3;

        shade_txt.setLayoutParams(params);





    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.tab_1:
                news_view_pager.setCurrentItem(0);
                break;
            case R.id.tab_2:
                news_view_pager.setCurrentItem(1);
                break;
            case R.id.tab_3:
                news_view_pager.setCurrentItem(2);
                break;
        }
    }

    @Override
    public View findViewByID(int id) {
        return view.findViewById(id);
    }


    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

        switch (position){
            case 0:
                fragment_1_container = (FrameLayout)view1.findViewById(R.id.fragment_1_container);

                fragmentManager.beginTransaction().replace(R.id.fragment_1_container,new Tab1Fragment()).commit();
                break;
            case 1:
                break;
            case 2:
                break;
        }
    }


    @Override
    public void onPageSelected(int position) {
        int tabW = offset * 2 + bmpW; // tab 宽度
        Animation animation = new TranslateAnimation(tabW*currIndex, tabW*position, 0, 0);
        currIndex = position;
        animation.setFillAfter(true);
        animation.setDuration(300);
        nav_line.startAnimation(animation);
        shade_txt.startAnimation(animation);

        /*if(position ==1 ){
            fragment_1_container = (FrameLayout)view1.findViewById(R.id.fragment_1_container);

            getFragmentManager().beginTransaction().replace(R.id.fragment_1_container,new Tab1Fragment()).commit();
        }*/

    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }
}
