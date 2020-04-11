package com.slowingo;


import android.widget.EditText;

import androidx.lifecycle.ViewModel;

import com.google.firebase.firestore.DocumentReference;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import io.reactivex.Observable;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.subjects.BehaviorSubject;




public class AddViewModel extends ViewModel {

    private CompositeDisposable disposables = new CompositeDisposable();

    private BehaviorSubject<Boolean> addedSubject = BehaviorSubject.createDefault(false);
    private Observable<Boolean> added = addedSubject.hide();

    private BehaviorSubject<String> errorSubject = BehaviorSubject.create();
    private Observable<String> error = errorSubject.hide();

    public Observable<Boolean> getAdded() {
        return added;
    }

    public Observable<String> getError() {
        return error;
    }

//    void addToDatabase(EditText[] editTexts, DocumentReference values){
//        disposables.add(
//                saveData(editTexts, values)
//                .subscribeOn(Schedulers.io())
//                .subscribe(() -> {
//                    addedSubject.onNext(true);
//                },(throwable -> {
//                    errorSubject.onNext(throwable.getMessage());
//                })));
//    }

    private void saveData(EditText[] editTexts, DocumentReference values){
        Map<String, Object> matrix = new HashMap<>();

        for(int i=0; i<24; i++){
            matrix.put(""+i,editTexts[i].getText().toString());
        }

        values.set(matrix);
    }

   private ArrayList<String> numbers = new ArrayList<>();

    void fillArray() {
        for (int i = 0; i < 24; i++) {
            numbers.add(""+i);
        }
    }

    @Override
    protected void onCleared() {
        disposables.clear();
        super.onCleared();
    }

}
