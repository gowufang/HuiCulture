package ahu.bigdata.huiculture.fragment.home;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import java.util.ArrayList;
import java.util.List;
import ahu.bigdata.huiculture.R;
import ahu.bigdata.huiculture.fragment.BaseFragment;
import ahu.bigdata.huiculture.fragment.cloud.CeleFrag;
import ahu.bigdata.huiculture.fragment.cloud.DrawFrag;
import ahu.bigdata.huiculture.fragment.cloud.HisFrag;
import ahu.bigdata.huiculture.fragment.cloud.LitFrag;
import ahu.bigdata.huiculture.fragment.cloud.RmFrag;
import ahu.bigdata.huiculture.fragment.cloud.TechFrag;

/**
 * Created by ych10 on 2017/9/21.
 * Function:徽云Fragment
 */
public class CloudFragment extends BaseFragment {


    /**
     * 徽商、徽州宗族、新安理学、新安医学、新安画派、徽州文书、徽派朴学、徽派版画、徽派篆刻、
     *徽州戏剧、徽州教育、徽州刻书、徽州科技、徽派建筑、徽州三雕、徽州民俗、徽州方言、徽菜、新安围棋、
     *文房四宝、徽派盆景、徽漆及各种竹、木编织工艺、徽州历史人物
     */

    /**
     * UI
     */
    private View mContentView;
    //TabLayout
    private TabLayout mTabLayout;
    //ViewPager
    private ViewPager mViewPager;
    //Tittle
    private List<String> mTitle;
    //Fragment
    private List<Fragment> mFragment;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        mContext = getActivity();
        mContentView= inflater.inflate(R.layout.fragment_cloud,null);
        initView();
        return mContentView;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initData();
    }

    private void initView() {

        mViewPager = (ViewPager) mContentView.findViewById(R.id.vp);
        mTabLayout = (TabLayout) mContentView.findViewById(R.id.tb);
        //预加载
        mViewPager.setOffscreenPageLimit(mFragment.size());
        mViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

        //设置适配器
        mViewPager.setAdapter(new FragmentPagerAdapter(getFragmentManager()) {
            //选中的Item
            @Override
            public Fragment getItem(int position) {
                return mFragment.get(position);
            }

            @Override
            public int getCount() {
                return mFragment.size();
            }
            //设置标题

            @Override
            public CharSequence getPageTitle(int position) {
                return mTitle.get(position);
            }

        });
        mTabLayout.setupWithViewPager(mViewPager);

    }

    private void initData() {

        mTitle = new ArrayList<>();
        mTitle.add("推荐");
        mTitle.add("历史");
        mTitle.add("名人");
        mTitle.add("文学");
        mTitle.add("画派");
        mTitle.add("科技");

        mFragment = new ArrayList<>();
        mFragment.add(new RmFrag());
        mFragment.add(new HisFrag());
        mFragment.add(new CeleFrag());
        mFragment.add(new LitFrag());
        mFragment.add(new DrawFrag());
        mFragment.add(new TechFrag());
    }
}
