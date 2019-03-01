package com.abhinav.klmdemoapp.base;

public class Event<T> {

    private T content;
    private boolean isAlreadyHandled = false;

    public Event(T content) {
        this.content = content;
    }

    public boolean isAlreadyHandled() {
        return isAlreadyHandled;
    }

    public T getContent() {
        if (!isAlreadyHandled) {
            isAlreadyHandled = true;
            return content;
        }
        return null;
    }

    public T peekContent() {
        return content;
    }
}