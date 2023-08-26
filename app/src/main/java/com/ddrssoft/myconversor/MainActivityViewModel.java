package com.ddrssoft.myconversor;

import android.app.Application;
import android.content.Context;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import java.io.Closeable;

public class MainActivityViewModel extends AndroidViewModel {
    private Context contexto;

    private  MutableLiveData<Double> mutable_resultado;
    private final double tasaDolarEuro = 0.92; // Tasa de cambio de dolar a euro
    private final double tasaEuroDolar = 1.09; // Tasa de cambio de euro a dolar


    public MainActivityViewModel(@NonNull Application application) {
        super(application);
        contexto = application.getApplicationContext();
    }

    public LiveData<Double> getMutable_resultado(){
        if(mutable_resultado == null){
            mutable_resultado = new MutableLiveData<>();
        }
        return mutable_resultado;
    }

    public void conversor(String dolar, String euro, boolean estado) {
        try {
            Double valorDolar = Double.parseDouble(dolar);
            Double valorEuro = Double.parseDouble(euro);

            if (estado) {
                mutable_resultado.setValue(valorDolar * tasaDolarEuro);
            } else {
                mutable_resultado.setValue(valorEuro * tasaEuroDolar);
            }
        } catch (NumberFormatException e) {
            mutable_resultado.setValue(0.0);
            Toast.makeText(contexto, "Por Favor Ingrese un Número Valido", Toast.LENGTH_LONG).show();

        }
    }


}
