package kr.hs.emirim.sagittta.DoitDoit;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import kr.hs.emirim.sagittta.DoitDoit.Fragment.AmountFragment;
import kr.hs.emirim.sagittta.DoitDoit.Fragment.DateFragment;
import kr.hs.emirim.sagittta.DoitDoit.Fragment.SubjectFragment;

public class ContentsPagerAdapter extends FragmentStatePagerAdapter {

    private int mPageCount;

    public ContentsPagerAdapter(FragmentManager fm, int pageCount) {
        super(fm);
        this.mPageCount = pageCount;
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                SubjectFragment subjectFragment = new SubjectFragment();
                return subjectFragment;
            case 1:
                AmountFragment amountFragment = new AmountFragment();
                return amountFragment;
            case 2:
                DateFragment dateFragment = new DateFragment();
                return dateFragment;
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return mPageCount;
    }
}
