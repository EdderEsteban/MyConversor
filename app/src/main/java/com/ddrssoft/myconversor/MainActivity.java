package com.ddrssoft.myconversor;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;

import com.ddrssoft.myconversor.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    private MainActivityViewModel vm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityMainBinding binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        vm = ViewModelProvider.AndroidViewModelFactory.getInstance(getApplication()).create(MainActivityViewModel.class);

        // Observador para habilitar/deshabilitar los EditText según la selección del RadioButton
        vm.getMutable_dolarEuro().observe(this, dolarEuro -> {
            binding.editDolar.setEnabled(dolarEuro);
            binding.editEuros.setEnabled(!dolarEuro);
        });

        // Observador para actualizar el valor en el ViewModel cuando cambian los EditText
        binding.editDolar.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                // No se utiliza en este caso
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                // Actualizar el valor en el ViewModel
                vm.setMutable_dolar(Double.parseDouble(charSequence.toString()));
            }

            @Override
            public void afterTextChanged(Editable editable) {
                // No se utiliza en este caso
            }
        });

        binding.editEuros.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                // No se utiliza en este caso
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                // Actualizar el valor en el ViewModel
                vm.setMutable_euro(Double.parseDouble(charSequence.toString()));
            }

            @Override
            public void afterTextChanged(Editable editable) {
                // No se utiliza en este caso
            }
        });

        // Observador para actualizar el resultado en la vista cuando cambia en el ViewModel
        vm.getMutable_resultado().observe(this, resultado -> {
            binding.textResultado.setText(String.format("%.2f", resultado)); // Mostrar el resultado con 2 decimales
        });

        // Configuración inicial
        binding.radioDolarEuros.setChecked(true); // Seleccionar por defecto
    }
}

