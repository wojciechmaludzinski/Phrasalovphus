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
import androidx.lifecycle.ViewModelProviders;

import io.reactivex.Observable;

public class AddFragment extends BaseFragment {

    private AddViewModel mViewModel;
    private SeekBar seekBar;
    private TextView seekBarValue;
    private EditText txt1, txt2, txt3, txt4, txt5, txt6, txt7, txt8, txt9, txt10,
                    txt11, txt12, txt13, txt14, txt15, txt16, txt17, txt18, txt19, txt20,
                    txt21, txt22, txt23, txt24, txt25;
    private EditText [] editTexts;
    private EditText name;
    private Button add;



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

                for (EditText editText : editTexts) {
                    editText.setVisibility(View.VISIBLE);
                }

                if(seekBarValue.getText().toString().equals("4")) {
                    for(int i=4; i<editTexts.length; i+=5){
                        editTexts[i].setVisibility(View.GONE);
                    }
                    for(int i=19; i<editTexts.length; i++){
                        editTexts[i].setVisibility(View.GONE);
                    }
                }

                if(seekBarValue.getText().toString().equals("3")) {
                    for(int i=3; i<editTexts.length; i+=5){
                        editTexts[i].setVisibility(View.GONE);
                    }
                    for(int i=4; i<editTexts.length; i+=5){
                        editTexts[i].setVisibility(View.GONE);
                    }
                    for(int i=15; i<editTexts.length; i++){
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

        for (EditText editText : editTexts) {
            editText.setOnClickListener(v -> {
                //TODO: save to database
            });
        }

        add.setOnClickListener(v -> {
            //TODO: send to database
        });





        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        mViewModel = ViewModelProviders.of(this, factory).get(AddViewModel.class);
        //mViewModel = new ViewModelProvider(this, factory).get(AddViewModel.class);
        // TODO: Use the ViewModel
    }

    private void init(View view){
        seekBarValue = view.findViewById(R.id.seekbarvalue);
        seekBarValue.setText("5");

        seekBar = view.findViewById(R.id.seekbar);
        seekBar.incrementProgressBy(1);
        seekBar.setProgress(2);
        seekBar.setMax(2);



        editTexts = new EditText[]{
            txt1 = view.findViewById(R.id.txt1),
            txt2 = view.findViewById(R.id.txt2),
            txt3 = view.findViewById(R.id.txt3),
            txt4 = view.findViewById(R.id.txt4),
            txt5 = view.findViewById(R.id.txt5),
            txt6 = view.findViewById(R.id.txt6),
            txt7 = view.findViewById(R.id.txt7),
            txt8 = view.findViewById(R.id.txt8),
            txt9 = view.findViewById(R.id.txt9),
            txt10 = view.findViewById(R.id.txt10),
            txt11 = view.findViewById(R.id.txt11),
            txt12 = view.findViewById(R.id.txt12),
            txt13 = view.findViewById(R.id.txt13),
            txt14 = view.findViewById(R.id.txt14),
            txt15 = view.findViewById(R.id.txt15),
            txt16 = view.findViewById(R.id.txt16),
            txt17 = view.findViewById(R.id.txt17),
            txt18 = view.findViewById(R.id.txt18),
            txt19 = view.findViewById(R.id.txt19),
            txt20 = view.findViewById(R.id.txt20),
            txt21 = view.findViewById(R.id.txt21),
            txt22 = view.findViewById(R.id.txt22),
            txt23 = view.findViewById(R.id.txt23),
            txt24 = view.findViewById(R.id.txt24),
            txt25 = view.findViewById(R.id.txt25)
        };

        name = view.findViewById(R.id.name);
        add = view.findViewById(R.id.add);



    }

    @Override
    Observable<String> getErrorObservable() {
        return Observable.empty();
    }
}
