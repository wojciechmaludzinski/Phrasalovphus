package com.slowingo;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModel;

import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

/**Every new ViewModel must be added here.
 * */
public class ViewModelFactory implements ViewModelProvider.Factory {

    FirebaseFirestore db = FirebaseFirestore.getInstance();
    private DocumentReference values = db.document("matrix/values");
    private DocumentReference matrixName = db.document("matrix/name");
    private DocumentReference matrixId = db.document("matrix/id");

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
