package com.slowingo;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.ViewModelProvider;

import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;

import io.reactivex.Observable;

public class AddFragment extends BaseFragment {

    String tag = "AddFragmentTag";
    private AddViewModel mViewModel;

    private SeekBar seekBar;
    private TextView seekBarValue;
    private EditText txt1, txt2, txt3, txt4, txt5, txt6, txt7, txt8, txt9, txt10,
                    txt11, txt12, txt13, txt14, txt15, txt16, txt17, txt18, txt19, txt20,
                    txt21, txt22, txt23, txt24, txt25;
    private EditText [] editTexts;
    private EditText nameEditText;
    private Button add;

    private boolean correctData = false;
    private String name;
    public static long id;
    private ArrayList<String> txt = new ArrayList<>();




    public static AddFragment newInstance() {
        return new AddFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.add_fragment, container, false);
        init(view);


        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener(){
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                 seekBarValue.setText(String.valueOf(progress + 3));
                int size = Integer.parseInt(seekBarValue.getText().toString());
                for (EditText editText : editTexts) {editText.setVisibility(View.VISIBLE); }

                if(size!=5) {
                    for(int i=size*size; i<editTexts.length; i++){
                        editTexts[i].setVisibility(View.GONE);
                    }
                }

            }
            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
            }
            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
            }
        });


        add.setOnClickListener(v -> {
            int size = Integer.parseInt(seekBarValue.getText().toString());
            name = "";
            for(int i=0; i<=(size*size)-1; i++){
                if(editTexts[i].getText()!=null&&!editTexts[i].getText().toString().isEmpty()){
                    txt.add(editTexts[i].getText().toString());
                }
                else{
                    Snackbar.make(requireView(), R.string.snackbar_empty_field, Snackbar.LENGTH_SHORT).show();
                    correctData = false;
                }
            }

            //Data verification
            if(nameEditText.getText()!=null&&!nameEditText.getText().toString().isEmpty()){

                name = nameEditText.getText().toString();
                correctData = true;
            }
            else{
                Snackbar.make(requireView(), R.string.snackbar_empty_name, Snackbar.LENGTH_SHORT).show();
                correctData = false;
            }

            if(correctData){
                mViewModel.addToDatabase(txt, name, Integer.parseInt(seekBarValue.getText().toString()));

            }


        });


        return view;
    }


    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);


        mViewModel = new ViewModelProvider(this, factory).get(AddViewModel.class);



    }

    public void onStart() {
        super.onStart();
        bind(mViewModel.getAdded(), (added) -> {
            if(added){
                Snackbar.make(requireView(), R.string.i_added_matrix, Snackbar.LENGTH_SHORT).show();
                Snackbar.make(requireView(), mViewModel.getIdshort()+"is ID for this matrix", Snackbar.LENGTH_LONG).show();
                nameEditText.setText("");
                for(int i=0; i<=24; i++){
                    editTexts[i].setText("");
                }
            }
        });

        bind(mViewModel.getError(), (error) -> Snackbar.make(requireView(), error, Snackbar.LENGTH_SHORT).show());

    }


    private void init(View view){
        seekBarValue = view.findViewById(R.id.seekbarvalue);
        seekBarValue.setText("5");

        seekBar = view.findViewById(R.id.seekbar);
        seekBar.incrementProgressBy(1);
        seekBar.setProgress(2);
        seekBar.setMax(2);

        editTexts = new EditText[]{
                //VISIBLE FOR 1x1 MATRIX
            txt1 = view.findViewById(R.id.txt1),
                //VISIBLE FOR 2x2 MATRIX
            txt2 = view.findViewById(R.id.txt2),
            txt6 = view.findViewById(R.id.txt6),
            txt7 = view.findViewById(R.id.txt7),
                //VISIBLE FOR 3x3 MATRIX
            txt3 = view.findViewById(R.id.txt3),
            txt8 = view.findViewById(R.id.txt8),
            txt11 = view.findViewById(R.id.txt11),
            txt12 = view.findViewById(R.id.txt12),
            txt13 = view.findViewById(R.id.txt13),
                //+ALSO VISIBLE FOR 4x4 MATRIX
            txt4 = view.findViewById(R.id.txt4),
            txt9 = view.findViewById(R.id.txt9),
            txt14 = view.findViewById(R.id.txt14),
            txt16 = view.findViewById(R.id.txt16),
            txt17 = view.findViewById(R.id.txt17),
            txt18 = view.findViewById(R.id.txt18),
            txt19 = view.findViewById(R.id.txt19),
                //+ALSO VISIBLE FOR 5x5 MATRIX
            txt5 = view.findViewById(R.id.txt5),
            txt10 = view.findViewById(R.id.txt10),
            txt15 = view.findViewById(R.id.txt15),
            txt20 = view.findViewById(R.id.txt20),
            txt21 = view.findViewById(R.id.txt21),
            txt22 = view.findViewById(R.id.txt22),
            txt23 = view.findViewById(R.id.txt23),
            txt24 = view.findViewById(R.id.txt24),
            txt25 = view.findViewById(R.id.txt25),
        };

        nameEditText = view.findViewById(R.id.name);
        add = view.findViewById(R.id.add);

    }

    @Override
    Observable<String> getErrorObservable() {
        return Observable.empty();
    }
}
