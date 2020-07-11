package com.slowingo;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.google.firebase.firestore.FirebaseFirestore;

/**Every new ViewModel must be added here.
 * */
public class ViewModelFactory implements ViewModelProvider.Factory {

    public FirebaseFirestore db = FirebaseFirestore.getInstance();
//    DocumentReference values = db.document("matrix/values");
//    DocumentReference matrixName = db.document("matrix/name");
//    DocumentReference matrixId = db.document("matrix/id");

    @SuppressWarnings("unchecked")
    @NonNull
    @Override
    public <T extends ViewModel> T create(Class<T> modelClass) {

        if(modelClass.isAssignableFrom(AddViewModel.class)) {
            return (T) new AddViewModel();
        }
        if(modelClass.isAssignableFrom(BrowseViewModel.class)) {
            return (T) new BrowseViewModel();
        }


        else {
            throw new IllegalArgumentException("ViewModel " + modelClass.getName() +" not found");
        }
    }
}
