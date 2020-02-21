package com.example.pekomon.screambox;

import androidx.databinding.BaseObservable;
import androidx.databinding.Bindable;

public class ScreamViewModel extends BaseObservable {

    private Sound mSound;
    private ScreamBox mScreamBox;

    public ScreamViewModel(ScreamBox pScreamBox) {
        mScreamBox = pScreamBox;
    }

    public Sound getSound() {
        return mSound;
    }

    public void setSound(final Sound pSound) {
        mSound = pSound;
        notifyChange();
    }

    @Bindable
    public String getTitle() {
        return mSound.getName();
    }

    public void onButtonClicked() {
        mScreamBox.play(mSound);
    }


}
