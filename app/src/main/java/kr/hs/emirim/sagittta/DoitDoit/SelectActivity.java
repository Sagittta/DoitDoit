package kr.hs.emirim.sagittta.DoitDoit;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import android.content.Context;
import android.os.Bundle;
import android.view.GestureDetector;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;

public class SelectActivity extends AppCompatActivity {

    private TabLayout sTabLayout;

    private ArrayList<Subjects> sList;
    private RecyclerAdapter sAdapter;
    private int count = -1;

    private ViewPager sViewPager;
    private ContentsPagerAdapter sContentPagerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select);
        
        this.sTabLayout = findViewById(R.id.tabLayout);

        sTabLayout.addTab(sTabLayout.newTab().setText("과목"));
        sTabLayout.addTab(sTabLayout.newTab().setText("분량"));
        sTabLayout.addTab(sTabLayout.newTab().setText("날짜"));

        sViewPager = (ViewPager) findViewById(R.id.pager_content);
        sContentPagerAdapter = new ContentsPagerAdapter(getSupportFragmentManager(), sTabLayout.getTabCount());
        sViewPager.setAdapter(sContentPagerAdapter);
        sViewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(sTabLayout));
        sTabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                sViewPager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
    }
}
