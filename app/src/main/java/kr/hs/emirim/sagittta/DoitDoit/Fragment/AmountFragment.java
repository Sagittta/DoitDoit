package kr.hs.emirim.sagittta.DoitDoit.Fragment;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import kr.hs.emirim.sagittta.DoitDoit.R;

public class AmountFragment extends Fragment {
    private Context mContext;

    @Override
    public void onAttach(final Activity activity) {
        super.onAttach(activity);
        mContext = activity;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_amount, container, false);

//        Spinner cateSpinner = (Spinner)v.findViewById(R.id.cateSpinner);
//        ArrayAdapter cateAdapter = ArrayAdapter.createFromResource(mContext, R.array.category, android.R.layout.simple_spinner_item);
//        cateAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
//        cateSpinner.setAdapter(cateAdapter);

        return v;
    }
}
