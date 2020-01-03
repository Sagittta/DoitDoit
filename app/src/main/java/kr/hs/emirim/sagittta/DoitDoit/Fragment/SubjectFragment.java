package kr.hs.emirim.sagittta.DoitDoit.Fragment;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.GestureDetector;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

import kr.hs.emirim.sagittta.DoitDoit.R;
import kr.hs.emirim.sagittta.DoitDoit.SubjectAdapter;
import kr.hs.emirim.sagittta.DoitDoit.Subjects;

public class SubjectFragment extends Fragment {

    private ArrayList<Subjects> sList;
    private SubjectAdapter sAdapter;

    public static final String TAG = "과목 추가창";
    private Context mContext;

    @Override
    public void onAttach(final Activity activity) {
        super.onAttach(activity);
        mContext = activity;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.fragment_subject, container, false);

        RecyclerView sRecyclerView = (RecyclerView) v.findViewById(R.id.subjectRecyclerView);
        sRecyclerView.setHasFixedSize(true);
        LinearLayoutManager sLinearLayoutManager = new LinearLayoutManager(getActivity());
        sRecyclerView.setLayoutManager(sLinearLayoutManager);

        // firebase
//        mFirebaseDatabaseReference = FirebaseDatabase.getInstance();

        sList = new ArrayList<>();

        sAdapter = new SubjectAdapter(sList);

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
                AlertDialog.Builder ad = new AlertDialog.Builder(mContext);

                ad.setTitle("과목 입력");

                final EditText et = new EditText(mContext);
                ad.setView(et);

                ad.setPositiveButton("추가", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int i) {

                        Log.v(TAG, "추가 버튼 클릭");

                        String value = et.getText().toString();
                        Log.v(TAG, value);

                        Subjects data = new Subjects(value);

                        if (data.equals("")) {
                            Log.d("Result : ", "과목 입력");
                        } else {

                            // Write a message to the database
//                        FirebaseDatabase database = FirebaseDatabase.getInstance();
//                        DatabaseReference myRef = database.getReference();
//                        DatabaseReference conditionRef = myRef.child("subject");
//
//                        myRef.setValue(data);

//                            Bundle bundle = new Bundle();
//                            bundle.putString("Subject", value);
//                            Log.d("INPUT TEXT : ", value);
//
//                            FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
//                            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
//
//                            AmountFragment amountFragment = new AmountFragment();
//                            amountFragment.setArguments(bundle);
//                            fragmentTransaction.replace(R.id.pager_content, amountFragment).commit();

                            sList.add(data);
                            sAdapter.notifyDataSetChanged();
                        }
                        dialog.dismiss();
                    }
                });

                ad.setNegativeButton("취소", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int i) {
                        Log.v(TAG, "No Button Click");
                        dialog.dismiss();
                    }
                });
                ad.show();
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
