package com.vukasin_prvulovic.micegame.presentation.view.activities.abstraction;

import com.vukasin_prvulovic.micegame.presentation.presenters.Transaction;

public interface MainActivityView {
    void initMenu();
    void doTransaction(Transaction transaction);
    void onBack();
}
