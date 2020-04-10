package com.slowingo;

import androidx.fragment.app.Fragment;

import com.google.android.material.snackbar.Snackbar;

import java.util.function.Consumer;

import io.reactivex.Completable;
import io.reactivex.Observable;
import io.reactivex.Single;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
public abstract class BaseFragment extends Fragment {

    private CompositeDisposable disposables = new CompositeDisposable();
    protected ViewModelFactory factory = new ViewModelFactory();


    public <T> void bind(Observable<T> observable, Consumer<? super T> consumer) {
        disposables
                .add(observable
                        .subscribeOn(Schedulers.computation())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(consumer::accept));
    }

    public <T> void bind(Single<T> observable, Consumer<? super T> consumer) {
        disposables
                .add(observable
                        .subscribeOn(Schedulers.computation())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(consumer::accept));
    }

    public void bind(Completable observable) {
        disposables
                .add(observable
                        .subscribeOn(Schedulers.computation())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe());
    }

    public void addToDisposables(Disposable d) {
        disposables.add(d);
    }

    public void openFragment(Fragment fragment, boolean addToBackStack, String tag) {
        if (addToBackStack) {
            requireActivity().getSupportFragmentManager()
                    .beginTransaction()
                    .addToBackStack(tag)
                    .replace(R.id.container, fragment)
                    .commit();
        } else {
            requireActivity().getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.container, fragment)
                    .commit();
        }
    }

    /**
     * return Observable.empty(); if don't want to throw anything
     */
    abstract Observable<String> getErrorObservable();


    @Override
    public void onStart() {
        super.onStart();

        bind(getErrorObservable(), (error) -> {
            Snackbar.make(requireView(), error, Snackbar.LENGTH_SHORT).show();
        });

    }


    @Override
    public void onStop() {
        super.onStop();
        disposables.clear();
    }
}
