package kr.hs.emirim.sagittta.DoitDoit;

import android.util.TypedValue;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.RecyclerViewHolder>{

    private ArrayList<Subjects> sList;

    public class RecyclerViewHolder extends RecyclerView.ViewHolder {
        protected TextView subject;

        public RecyclerViewHolder(View view) {
            super(view);
            this.subject = (TextView) view.findViewById(R.id.subjectName);
        }
    }

    public RecyclerAdapter(ArrayList<Subjects> list) {
        this.sList = list;
    }

    @NonNull
    @Override
    public RecyclerViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {

        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_list, viewGroup, false);

        RecyclerViewHolder viewHolder = new RecyclerViewHolder(view);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerViewHolder holder, int position) {
        holder.subject.setTextSize(TypedValue.COMPLEX_UNIT_SP, 25);

        holder.subject.setGravity(Gravity.CENTER);

        holder.subject.setText(sList.get(position).getSubject());
    }

    @Override
    public int getItemCount() {
        return (null != sList ? sList.size() : 0);
    }
}
