package com.example.lab11fx;

public interface Subject {
    void registerObserver(Observer o);
    void  removeObserver(Observer o);

    void notifyObserver();
}
