package com.ddrssoft.myconversor;

import android.app.Application;
import android.content.Context;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import java.io.Closeable;

public class MainActivityViewModel extends AndroidViewModel {
    private Context contexto;
    private MutableLiveData<Boolean> mutable_radioDolarEuro;
    private MutableLiveData<String> mutable_dolar;
    private MutableLiveData<String> mutable_euro;
    private  MutableLiveData<Double> mutable_resultado;
    private final double tasaDolarEuro = 0.92; // Tasa de cambio de dolar a euro
    private final double tasaEuroDolar = 1.09; // Tasa de cambio de euro a dolar


    public MainActivityViewModel(@NonNull Application application) {
        super(application);
        contexto = application.getApplicationContext();
    }
    // Instancias de Mutables
    public LiveData<Boolean> getMutable_radioDolarEuro(){
        if (mutable_radioDolarEuro == null){
            mutable_radioDolarEuro = new MutableLiveData<>(true); // Valor predeterminado a true
        }
        return mutable_radioDolarEuro;
    }
    public LiveData<String> getMutable_dolar(){
        if(mutable_dolar == null){
            mutable_dolar = new MutableLiveData<>(null); // Valor predeterminado
        }
        return mutable_dolar;
    }
    public LiveData<String> getMutable_euro(){
        if(mutable_euro == null){
            mutable_euro = new MutableLiveData<>(null); // Valor predeterminado
        }
        return mutable_euro;
    }
    public LiveData<Double> getMutable_resultado(){
        if(mutable_resultado == null){
            mutable_resultado = new MutableLiveData<>();
        }
        return mutable_resultado;
    }
    public void selector (){
        if(mutable_radioDolarEuro.getValue()){
            conversor(true,mutable_dolar.getValue());
        }else{
            conversor(false,mutable_euro.getValue());
        }
    }
    public void conversor (boolean camino, String string){
        Double valor = Double.parseDouble(string);
        Double total;
        if(camino){
            total = valor * tasaDolarEuro;
            mutable_resultado.setValue(total);
        }else{
            total = valor * tasaEuroDolar;
            mutable_resultado.setValue(total);
        }
    }
}
