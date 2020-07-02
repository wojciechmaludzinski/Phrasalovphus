package com.slowingo;


import android.util.Log;

import androidx.lifecycle.ViewModel;

import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import io.reactivex.Completable;
import io.reactivex.Observable;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;
import io.reactivex.subjects.BehaviorSubject;




public class AddViewModel extends ViewModel {

    AddViewModel(FirebaseFirestore db) {
        this.values = db.document("matrix/values");
        this.name = db.document("matrix/name");
        this.id = db.document("matrix/id");
    }


    private DocumentReference values;
    private DocumentReference name;
    private DocumentReference id;
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

    DocumentReference getValues() {
        Log.i("getValues", "getValues");
        return values;
    }

    void addToDatabase(ArrayList txt, DocumentReference values, int size){
        Log.i("addToDatabase", "beggining");
        disposables.add(
                saveData(txt, values, size)
                .subscribeOn(Schedulers.io())
                .subscribe(() -> addedSubject.onNext(true),(throwable -> errorSubject.onNext(throwable.getMessage()))));
    }
    private Completable saveData(ArrayList txt, DocumentReference values, int size){
        Log.i("saveDate", "beggining");
        Map<String, Object> matrix = new HashMap<>();
        for(int i=0; i<=(size*size)-1; i++){
            matrix.put(""+i,txt.get(i));
        }
        values.set(matrix);
        Log.i("saveData", "ending");
        return Completable.complete();

    }


    @Override
    protected void onCleared() {
        disposables.clear();
        super.onCleared();
    }

}
