package kr.hs.emirim.sagittta.DoitDoit;

import android.util.TypedValue;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.DatabaseReference;

import java.util.ArrayList;

public class AmountAdapter extends RecyclerView.Adapter<AmountAdapter.RecyclerViewHolder>{

    private ArrayList<Subjects> sList;

    //    리사이클러뷰에 들어갈 뷰 홀더, 뷰 홀더에 들어갈 아이템 지정
    public class RecyclerViewHolder extends RecyclerView.ViewHolder {
        protected TextView subject;
        protected Spinner cateSpinner;
        protected EditText startPage;
        protected EditText endPage;

        public RecyclerViewHolder(View view) {
            super(view);
//            과목 이름 텍스트뷰로 받아오기
            this.subject = (TextView) view.findViewById(R.id.subjectAmountName);
            this.cateSpinner = (Spinner) view.findViewById(R.id.cateSpinner);
            this.startPage = (EditText) view.findViewById(R.id.startPage);
            this.endPage = (EditText) view.findViewById(R.id.endPage);
        }
    }

    public AmountAdapter(ArrayList<Subjects> list) {
        this.sList = list;
    }

    //    리사이클러뷰에 들어갈 뷰 홀더를 할당하는 함수, 뷰 홀더는 실제 레이아웃 파일과 매핑되어야 함
//    extends 의 Adapter<> 에서 <> 안에 들어가는 타입을 따름.
    @NonNull
    @Override
    public RecyclerViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.subject_amount_list, viewGroup, false);

        RecyclerViewHolder viewHolder = new RecyclerViewHolder(view);

        return viewHolder;
    }

    //    실제 각 뷰 홀더에 데이터를 연결해주는 함수
    @Override
    public void onBindViewHolder(@NonNull RecyclerViewHolder holder, int position) {
        holder.subject.setTextSize(TypedValue.COMPLEX_UNIT_SP, 25);
        holder.subject.setGravity(Gravity.CENTER);
        holder.subject.setText(sList.get(position).getSubject());
    }

    //    리사이클러뷰 안에 들어갈 뷰 홀더의 개수
    @Override
    public int getItemCount() {
        return (null != sList ? sList.size() : 0);
    }
}
