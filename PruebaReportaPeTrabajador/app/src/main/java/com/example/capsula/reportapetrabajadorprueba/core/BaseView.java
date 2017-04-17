package com.example.capsula.reportapetrabajadorprueba.core;

/**
 * Created by linuxdesarrollo01 on 20/02/17.
 */

public interface BaseView<T> {
    void setPresenter(T presenter);
    boolean isActive();
    void setLoadingIndicator(boolean active);
}