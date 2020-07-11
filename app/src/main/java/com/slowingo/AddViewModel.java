package com.slowingo;


import android.annotation.SuppressLint;
import android.util.Log;

import androidx.lifecycle.ViewModel;

import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import io.reactivex.Completable;
import io.reactivex.Observable;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;
import io.reactivex.subjects.BehaviorSubject;




public class AddViewModel extends ViewModel {

    AddViewModel() {
    }

    public FirebaseFirestore getDb() {
        return db;
    }

    public String getIdshort() {
        return idshort;
    }

    FirebaseFirestore db =  FirebaseFirestore.getInstance();
    String idshort = "not yet declared";
    @SuppressLint("SimpleDateFormat")
    SimpleDateFormat formatter =  new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
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

    void addToDatabase(ArrayList txt, String nametxt, int size){
        Log.i("addToDatabase", "beggining");
        disposables.add(
                saveData(txt, nametxt, size)
                .subscribeOn(Schedulers.io())
                .subscribe(() -> addedSubject.onNext(true),(throwable -> errorSubject.onNext(throwable.getMessage()))));
    }
    private Completable saveData(ArrayList txt, String nametxt, int size){

        Map<String, Object> matrix = new HashMap<>();
        for(int i=0; i<=(size*size)-1; i++){
            matrix.put(""+i,txt.get(i));
        }

        Map<String,String> metadata = new HashMap<>();
        metadata.put("matrixName", nametxt);
        metadata.put("size", String.valueOf(size));
        metadata.put("date", formatter.format(new Date()));


        UUID id = UUID.randomUUID();
        idshort = id.toString().substring(0, 8);
        DocumentReference valuesRef = db.collection(idshort).document("values");
        DocumentReference nameRef = db.collection(idshort).document("name");

        valuesRef.set(matrix);
        nameRef.set(metadata);
        return Completable.complete();
    }


    @Override
    protected void onCleared() {
        disposables.clear();
        super.onCleared();
    }

}
