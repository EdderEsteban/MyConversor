package com.ddrssoft.myconversor;

import android.app.Application;
import android.content.Context;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

public class MainActivityViewModel extends AndroidViewModel {
    private Context contexto;
    private MutableLiveData<Boolean> mutable_dolarEuro = new MutableLiveData<>(true); // Valor predeterminado a true
    private MutableLiveData<Double> mutable_dolar = new MutableLiveData<>(null); // Valor predeterminado a 0.0
    private MutableLiveData<Double> mutable_euro = new MutableLiveData<>(null); // Valor predeterminado a 0.0
    private MutableLiveData<Double> mutable_resultado = new MutableLiveData<>(0.0); // Valor predeterminado a 0.0
    private final double tasaDolarEuro = 0.92; // Tasa de cambio de dolar a euro
    private final double tasaEuroDolar = 1.09; // Tasa de cambio de euro a dolar

    public MainActivityViewModel(@NonNull Application application) {
        super(application);
        contexto = application.getApplicationContext();
    }

    // LiveData para obtener el resultado de la conversión
    public LiveData<Double> getMutable_resultado() {
        return mutable_resultado;
    }

    // LiveData para obtener si la conversión es de dólar a euro o viceversa
    public LiveData<Boolean> getMutable_dolarEuro() {
        return mutable_dolarEuro;
    }

    // LiveData para obtener la cantidad en dólares
    public LiveData<Double> getMutable_dolar() {
        return mutable_dolar;
    }

    // LiveData para obtener la cantidad en euros
    public LiveData<Double> getMutable_euro() {
        return mutable_euro;
    }

    // Método para actualizar si la conversión es de dólar a euro o viceversa
    public void setMutable_dolarEuro(boolean checked) {
        mutable_dolarEuro.setValue(checked);
        calcularResultado(); // Recalcula el resultado cuando cambia la conversión
    }

    // Método para actualizar la cantidad en dólares y recalcular el resultado
    public void setMutable_dolar(double cantidad) {
        mutable_dolar.setValue(cantidad);
        calcularResultado();
    }

    // Método para actualizar la cantidad en euros y recalcular el resultado
    public void setMutable_euro(double cantidad) {
        mutable_euro.setValue(cantidad);
        calcularResultado();
    }

    // Método para calcular el resultado de la conversión
    private void calcularResultado() {
        double cantidad = mutable_dolarEuro.getValue() ? mutable_dolar.getValue() : mutable_euro.getValue();
        double resultado = conversor(mutable_dolarEuro.getValue(), cantidad);
        mutable_resultado.setValue(resultado);
    }

    // Método para realizar la conversión entre dólares y euros
    private double conversor(boolean dolarEuro, double valor) {
        if (dolarEuro) {
            return valor * tasaDolarEuro; // Conversión de dólar a euro
        } else {
            return valor * tasaEuroDolar; // Conversión de euro a dólar
        }
    }
}
