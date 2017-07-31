package todo.example.willis.myweather;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import todo.example.willis.myweather.utils.LogUtil;

public class MainActivity extends AppCompatActivity implements AppBarLayout.OnOffsetChangedListener{

    private static final String TAG = "wils-main";

    @BindView(R.id.main_toolbar)
    Toolbar mToobar;

    @BindView(R.id.main_appbar)
    AppBarLayout mAppBar;

    @BindView(R.id.main_tabs)
    TabLayout mTabLayout;

    @BindView(R.id.main_pager)
    ViewPager mPager;

    @BindView(R.id.weather_list_in_container)
    RecyclerView mHourlyForcaseList;

    @BindView(R.id.main_container)
    View mMainContainer;

    int mMainContainerX;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        setSupportActionBar(mToobar);
        mToobar.setNavigationIcon(null);

        getSupportActionBar().setTitle("");

        mAppBar.addOnOffsetChangedListener(this);
        initView();
        setupViewPager();
        setupHourlyForcastList();
    }

    private void initView(){
        mMainContainer.postDelayed(new Runnable() {
            @Override
            public void run() {
                mMainContainerX = (int)mMainContainer.getX();
                LogUtil.i(TAG, "mMainContainerX:" + mMainContainerX);

            }
        }, 100);
    }

    private static int TAB_ICON_RES[] = {R.drawable.tab_city_drawable, R.drawable.tab_weather_drawable, R.drawable.tab_user_drawable};
    private View getTabView(int position, ViewGroup parent){
        View v = getLayoutInflater().inflate(R.layout.tab_view, parent, false);
        ImageView imgView = v.findViewById(R.id.tab_image);
        imgView.setImageResource(TAB_ICON_RES[position]);

        return v;
    }

    private void setupViewPager(){
        TabsAdapter adapter = new TabsAdapter(getSupportFragmentManager());

        Fragment frag1 = new CityFragment();
        adapter.addFragment(frag1);
        Fragment frag2 = new WeatherFragment();
        adapter.addFragment(frag2);
        Fragment frag3 = new SettingsFragment();
        adapter.addFragment(frag3);

        mPager.setAdapter(adapter);
        mTabLayout.setupWithViewPager(mPager);
        mTabLayout.getTabAt(0).setCustomView(getTabView(0, mTabLayout));
        mTabLayout.getTabAt(1).setCustomView(getTabView(1, mTabLayout));
        mTabLayout.getTabAt(2).setCustomView(getTabView(2, mTabLayout));

        mPager.setCurrentItem(1);

    }

    private void setupHourlyForcastList(){
        HorizontalRecyclerViewAdapter adapter = new HorizontalRecyclerViewAdapter();
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        mHourlyForcaseList.setLayoutManager(linearLayoutManager);
        mHourlyForcaseList.setAdapter(adapter);

        ArrayList<WeatherListModel> dummyData = new ArrayList<>();
        WeatherListModel model = new WeatherListModel("11:00", "35", R.mipmap.weather_few_clouds);
        for(int i=0;i<24;i++){
            dummyData.add(model);
        }
        adapter.setmData(dummyData);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset) {
        int maxScrollRange = appBarLayout.getTotalScrollRange();
        float scrollPercentage = (float)Math.abs(verticalOffset) / maxScrollRange;
        handleScrollAnimation(scrollPercentage);
        LogUtil.i(TAG, "onOffsetChanged:" + verticalOffset + ", TotalScrollRange:" + appBarLayout.getTotalScrollRange());
        LogUtil.i(TAG, " scrollPercentage:" + scrollPercentage);


    }

    private void handleScrollAnimation(float percentage){
        mToobar.getBackground().mutate().setAlpha((int)(255 * percentage));
        mMainContainer.setAlpha(1-percentage);
        mMainContainer.setScaleX(1-percentage);
        mMainContainer.setScaleY(1-percentage);
        if(mMainContainerX > 0) {
            mMainContainer.setX(mMainContainerX * (1 - percentage));
        }
        mHourlyForcaseList.setAlpha(1-percentage);

    }
}
