package com.ddrssoft.myconversor;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;
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
        binding.editEuros.setText("0");

        //Observar el comportamiento de los Radio Button
        binding.radioDolarEuros.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                binding.editDolar.setEnabled(true);
                binding.editEuros.setEnabled(false);
                binding.editEuros.setText("0");
                binding.editDolar.setText("");
                binding.editDolar.requestFocus();
            }
        });

        binding.radioEurosDollar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                binding.editDolar.setEnabled(false);
                binding.editEuros.setEnabled(true);
                binding.editDolar.setText("0");
                binding.editEuros.setText("");
                binding.editEuros.requestFocus();

            }
        });

        // Setear el Observer del Mutable
        vm.getMutable_resultado().observe(this, new Observer<Double>() {
            @Override
            public void onChanged(Double mutable_resultado) {
                binding.textResultado.setText(String.valueOf(mutable_resultado));
            }
        });
        // Setear el inClick del Boton Convertir
        binding.btnConvertir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String euro = binding.editEuros.getText().toString();
                String dolar = binding.editDolar.getText().toString();
                boolean radioEstado = binding.radioDolarEuros.isChecked();
                vm.conversor(dolar, euro, radioEstado);
            }
        });

    }
}
