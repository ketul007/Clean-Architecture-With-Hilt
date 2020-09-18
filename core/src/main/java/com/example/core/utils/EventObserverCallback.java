package com.example.core.utils;

public interface EventObserverCallback<T> {
    void onEventUnhandledContent(T t);
}
