package kr.hs.emirim.sagittta.DoitDoit.Fragment;

import android.content.Context;
import android.os.Bundle;
import android.view.GestureDetector;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import kr.hs.emirim.sagittta.DoitDoit.R;
import kr.hs.emirim.sagittta.DoitDoit.RecyclerAdapter;
import kr.hs.emirim.sagittta.DoitDoit.SelectActivity;
import kr.hs.emirim.sagittta.DoitDoit.Subjects;

public class SubjectFragment extends Fragment {

    private ArrayList<Subjects> sList;
    private RecyclerAdapter sAdapter;
    private int count = -1;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.fragment_subject, container, false);

        RecyclerView sRecyclerView = (RecyclerView) v.findViewById(R.id.subjectRecyclerView);
        sRecyclerView.setHasFixedSize(true);
        LinearLayoutManager sLinearLayoutManager = new LinearLayoutManager(getActivity());
        sRecyclerView.setLayoutManager(sLinearLayoutManager);

        sList = new ArrayList<>();

        sAdapter = new RecyclerAdapter(sList);

        sRecyclerView.setItemAnimator(new DefaultItemAnimator());
        sRecyclerView.setAdapter(sAdapter);

        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(sRecyclerView.getContext(), sLinearLayoutManager.getOrientation());
        sRecyclerView.addItemDecoration(dividerItemDecoration);

        sRecyclerView.addOnItemTouchListener(new RecyclerTouchListener(getActivity(), sRecyclerView, new ClickListener() {
            @Override
            public void onClick(View view, int position) {
                Subjects subj = sList.get(position);
                Toast.makeText(getActivity(), subj.getSubject(), Toast.LENGTH_LONG).show();
            }

            @Override
            public void onLongClick(View view, int position) {

            }
        }));


        Button btAddSubject = (Button) v.findViewById(R.id.btAddSubject);
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

        return v;
    }

    public interface ClickListener {
        void onClick(View view, int position);

        void onLongClick(View view, int position);
    }

    public static class RecyclerTouchListener implements RecyclerView.OnItemTouchListener {

        private GestureDetector gestureDetector;
        private ClickListener clickListener;

        public RecyclerTouchListener(Context context, final RecyclerView recyclerView, final ClickListener clickListener) {
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

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        prepareData();
    }

    private void prepareData() {

    }
}
