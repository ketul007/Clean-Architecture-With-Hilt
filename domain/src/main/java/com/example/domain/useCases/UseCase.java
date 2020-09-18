package com.example.domain.useCases;

import io.reactivex.rxjava3.disposables.CompositeDisposable;
import io.reactivex.rxjava3.disposables.Disposable;

public abstract class UseCase {

    protected Disposable disposable;
    protected CompositeDisposable compositeDisposable = new CompositeDisposable();

    public void disposeLast(){
        if (disposable != null && !disposable.isDisposed()){
            disposable.dispose();
        }
    }

    public void dispose(){
        compositeDisposable.clear();
    }
}
