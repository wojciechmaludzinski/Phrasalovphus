package com.slowingo;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.google.android.material.button.MaterialButton;

import io.reactivex.Observable;

public class StartFragment extends BaseFragment {

    private MaterialButton add;
    private Button browse;

    static final private String tagStartFragment = "StartFragment";

    public static StartFragment newInstance() {
        return new StartFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.start_fragment, container, false);

        add = view.findViewById(R.id.add);
        browse = view.findViewById(R.id.browse);

        add.setOnClickListener(v -> {
            openFragment(new AddFragment(), true, tagStartFragment);
        });
        browse.setOnClickListener(v -> {
            openFragment(new BrowseFragment(), true, tagStartFragment);
        });

        return view;
    }

    @Override
    Observable<String> getErrorObservable() {
        return Observable.empty();
    }
}
