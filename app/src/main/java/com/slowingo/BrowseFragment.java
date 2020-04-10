package com.slowingo;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.ViewModelProvider;

import io.reactivex.Observable;

public class BrowseFragment extends BaseFragment {

    private BrowseViewModel mViewModel;

    public static BrowseFragment newInstance() {
        return new BrowseFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.browse_fragment, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = new ViewModelProvider(this, factory).get(BrowseViewModel.class);
        //mViewModel = ViewModelProviders.of(this, factory).get(BrowseViewModel.class);
        // TODO: Use the ViewModel
    }

    @Override
    Observable<String> getErrorObservable() {
        return Observable.empty();
    }
}
