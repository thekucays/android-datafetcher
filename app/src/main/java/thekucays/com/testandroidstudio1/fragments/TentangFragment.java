package thekucays.com.testandroidstudio1.fragments;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import thekucays.com.testandroidstudio1.R;

/**
 * Created by thekucays on 6/16/16.
 */
public class TentangFragment extends Fragment {
    public TentangFragment() {}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        View viewTentang = inflater.inflate(R.layout.tentang_layout, container, false);
        return viewTentang;
    }
}
