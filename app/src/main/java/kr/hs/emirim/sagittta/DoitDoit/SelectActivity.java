package kr.hs.emirim.sagittta.DoitDoit;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.os.Bundle;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.TabHost;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;

public class SelectActivity extends AppCompatActivity {

    private TabLayout tabLayout;

    private ArrayList<Subjects> sList;
    private RecyclerAdapter sAdapter;
    private int count = -1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select);

        this.tabLayout = findViewById(R.id.tabLayout);

        tabLayout.addTab(tabLayout.newTab().setText("과목"));
        tabLayout.addTab(tabLayout.newTab().setText("분량"));
        tabLayout.addTab(tabLayout.newTab().setText("날짜"));

//        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
//            @Override
//            public void onTabSelected(TabLayout.Tab tab) {
//                int pos = tab.getPosition() ;
//                changeView(pos) ;
//            }
//
//            @Override
//            public void onTabUnselected(TabLayout.Tab tab) {
//
//            }
//
//            @Override
//            public void onTabReselected(TabLayout.Tab tab) {
//
//            }
//        });

        RecyclerView sRecyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        LinearLayoutManager sLinearLayoutManager = new LinearLayoutManager(this);
        sRecyclerView.setLayoutManager(sLinearLayoutManager);

        sList = new ArrayList<>();

        sAdapter = new RecyclerAdapter(sList);
        sRecyclerView.setAdapter(sAdapter);

        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(sRecyclerView.getContext(), sLinearLayoutManager.getOrientation());
        sRecyclerView.addItemDecoration(dividerItemDecoration);

        sRecyclerView.addOnItemTouchListener(new RecyclerTouchListener(getApplicationContext(), sRecyclerView, new ClickListener() {
            @Override
            public void onClick(View view, int position) {
                Subjects subj = sList.get(position);
                Toast.makeText(getApplicationContext(), subj.getSubject(), Toast.LENGTH_LONG).show();
            }

            @Override
            public void onLongClick(View view, int position) {

            }
        }));

        Button btAddSubject = (Button) findViewById(R.id.btAddSubject);
        //TODO 과목 입력받는 팝업창 띄우기
        btAddSubject.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                count++;
                Subjects data = new Subjects("과목" + count);

                sList.add(data);
                sAdapter.notifyDataSetChanged();
            }
        });
    }

    public interface ClickListener {
        void onClick(View view, int position);

        void onLongClick(View view, int position);
    }

    public static class RecyclerTouchListener implements RecyclerView.OnItemTouchListener {

        private GestureDetector gestureDetector;
        private SelectActivity.ClickListener clickListener;

        public RecyclerTouchListener(Context context, final RecyclerView recyclerView, final SelectActivity.ClickListener clickListener) {
            this.clickListener = clickListener;
            gestureDetector = new GestureDetector(context, new GestureDetector.SimpleOnGestureListener() {
                @Override
                public boolean onSingleTapUp(MotionEvent e) {
                    return true;
                }

                @Override
                public void onLongPress(MotionEvent e) {
                    View child = recyclerView.findChildViewUnder(e.getX(), e.getY());
                    if (child != null && clickListener != null) {
                        clickListener.onLongClick(child, recyclerView.getChildAdapterPosition(child));
                    }
                }
            });
        }

        @Override
        public boolean onInterceptTouchEvent(RecyclerView rv, MotionEvent e) {
            View child = rv.findChildViewUnder(e.getX(), e.getY());
            if (child != null && clickListener != null && gestureDetector.onTouchEvent(e)) {
                clickListener.onClick(child, rv.getChildAdapterPosition(child));
            }
            return false;
        }

        @Override
        public void onTouchEvent(RecyclerView rv, MotionEvent e) {
        }

        @Override
        public void onRequestDisallowInterceptTouchEvent(boolean disallowIntercept) {
        }

    }

//    private void changeView(int index) {
//        TextView textView1 = (TextView) findViewById(R.id.text1) ;
//        TextView textView2 = (TextView) findViewById(R.id.text2) ;
//        TextView textView3 = (TextView) findViewById(R.id.text3) ;
//
//        switch (index) {
//            case 0 :
//                textView1.setVisibility(View.VISIBLE) ;
//                textView2.setVisibility(View.INVISIBLE) ;
//                textView3.setVisibility(View.INVISIBLE) ;
//                break ;
//            case 1 :
//                textView1.setVisibility(View.INVISIBLE) ;
//                textView2.setVisibility(View.VISIBLE) ;
//                textView3.setVisibility(View.INVISIBLE) ;
//                break ;
//            case 2 :
//                textView1.setVisibility(View.INVISIBLE) ;
//                textView2.setVisibility(View.INVISIBLE) ;
//                textView3.setVisibility(View.VISIBLE) ;
//                break ;
//
//        }
//    }
}
