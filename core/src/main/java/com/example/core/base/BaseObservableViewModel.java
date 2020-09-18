package com.example.core.base;

import androidx.databinding.Observable;
import androidx.databinding.PropertyChangeRegistry;

import com.example.domain.useCases.UseCase;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public abstract class BaseObservableViewModel extends BaseViewModel implements Observable {


    protected List<UseCase> useCaseList = new ArrayList<>();

    public BaseObservableViewModel(UseCase ...useCase){
        useCaseList.addAll(Arrays.asList(useCase));
    }

    private PropertyChangeRegistry propertyChangeRegistry= new PropertyChangeRegistry();

    @Override
    public void addOnPropertyChangedCallback(OnPropertyChangedCallback callback) {
        propertyChangeRegistry.add(callback);
    }

    @Override
    public void removeOnPropertyChangedCallback(OnPropertyChangedCallback callback) {
        propertyChangeRegistry.remove(callback);
    }

    void notifyChange(){
        propertyChangeRegistry.notifyCallbacks(this , 0 , null);
    }

    void notifyPropertyChanged(int fieldId) {
        propertyChangeRegistry.notifyCallbacks(this, fieldId, null);
    }

    @Override
    protected void onCleared() {
        super.onCleared();
        for (UseCase useCase : useCaseList){
            useCase.dispose();
        }
    }
}
