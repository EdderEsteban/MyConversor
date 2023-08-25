package com.ddrssoft.myconversor;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;

import com.ddrssoft.myconversor.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    private MainActivityViewModel vm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityMainBinding binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        vm = ViewModelProvider.AndroidViewModelFactory.getInstance(getApplication()).create(MainActivityViewModel.class);

        //Setear editDolar como seleccionado por defecto
        binding.radioDolarEuros.setChecked(true);
        binding.editDolar.setEnabled(true);
        binding.editEuros.setEnabled(false);
        binding.radioDolarEuros.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                binding.editDolar.setEnabled(true);
                binding.editEuros.setEnabled(false);
                binding.editEuros.setText("0");
                binding.editDolar.requestFocus();
            }
        });

        binding.radioEurosDollar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                binding.editDolar.setEnabled(false);
                binding.editEuros.setEnabled(true);
                binding.editDolar.setText("0");
                binding.editEuros.requestFocus();

            }
        });
        vm.getMutable_resultado().observe(this, new Observer<Double>() {
            @Override
            public void onChanged(Double mutable_resultado) {
                binding.textResultado.setText(String.valueOf(mutable_resultado));
            }
        });
        binding.btnConvertir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String euro = binding.editDolar.getText().toString();
                String dolar = binding.editDolar.getText().toString();
                boolean radioEstado = binding.radioDolarEuros.isChecked();
                vm.conversor(dolar, euro, radioEstado);
            }
        });
    }
}

