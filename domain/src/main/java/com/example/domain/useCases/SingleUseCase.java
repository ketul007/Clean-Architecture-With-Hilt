package com.example.domain.useCases;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.core.Single;
import io.reactivex.rxjava3.schedulers.Schedulers;

public abstract class SingleUseCase<T,Params> extends UseCase {
    protected abstract Single<T> buildUseCaseSingle(Params params);

    public void execute(Params params,SingleUseCaseCallback<T> singleUseCaseCallback)  {
        disposeLast();
        disposable = buildUseCaseSingle(params)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doAfterTerminate(singleUseCaseCallback::onFinished)
                .subscribe(singleUseCaseCallback::onSuccess , singleUseCaseCallback::onError);
        compositeDisposable.add(disposable);

    }

}
