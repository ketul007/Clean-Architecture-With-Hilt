package com.example.domain.useCases;

public interface SingleUseCaseCallback<T> {
    void onSuccess(T t);
    void onError(Throwable t);
    void onFinished();
}
