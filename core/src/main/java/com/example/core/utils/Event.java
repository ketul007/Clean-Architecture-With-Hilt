package com.example.core.utils;
public class Event<T> {

    public Event(T content){
        this.content = content;
    }
    private T content;
    public boolean hasBeenHandled = false;
    T getContentIfNotHandled(){
         if (hasBeenHandled) {
           return null;
        } else {
            hasBeenHandled = true;
            return content;
        }
    }
    public T peekContent(){
        return content;
    }
}


