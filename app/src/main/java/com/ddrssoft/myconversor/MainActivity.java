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

        //Observador para mutable_radioDolarEuro
        vm.getMutable_radioDolarEuro().observe(this, new Observer<Boolean>() {
            @Override
            public void onChanged(Boolean aBoolean) {

            }
        });

        // Observador para mutable_dolar
        vm.getMutable_dolar().observe(this, new Observer<String>() {
            @Override
            public void onChanged(String newValue) {
                // Actualizar la interfaz de usuario con el nuevo valor de mutable_dolar
            }
        });
        // Observador para mutable_euro
        vm.getMutable_euro().observe(this, new Observer<String>() {
            @Override
            public void onChanged(String newValue) {
                // Actualizar la interfaz de usuario con el nuevo valor de mutable_euro
            }
        });

        // Escuchar el boton Convertir
        binding.btnConvertir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                vm.selector();
            }
        });
        }


}

