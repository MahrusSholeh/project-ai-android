package ma.mobile.sistemrekomendasipusatoleh_olehmalang.adapter;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import ma.mobile.sistemrekomendasipusatoleh_olehmalang.activity.MainActivity.RecommendationsFragmentOleh;
import ma.mobile.sistemrekomendasipusatoleh_olehmalang.activity.MainActivity.addValue;
import ma.mobile.sistemrekomendasipusatoleh_olehmalang.activity.MainActivity.addStore;

public class ViewPagerAdapter extends FragmentStateAdapter {
    public ViewPagerAdapter(@NonNull FragmentActivity fragmentActivity) {
        super(fragmentActivity);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        switch (position) {
            case 0:
                return new addStore();
            case 1:
                return new addValue();
            case 2:
                return new RecommendationsFragmentOleh();
            default:
                return new addStore();
        }
    }

    @Override
    public int getItemCount() {
        return 3;
    }
}
