package com.example.pekomon.screambox;

import android.content.Context;
import android.content.res.AssetManager;
import android.util.Log;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ScreamBox {
    private static final String LOGTAG = "ScreamBox";

    private static final String SCREAM_SOUNDS_FOLDER = "sample_sounds";

    private AssetManager mAssetManager;
    private List<Sound> mSounds = new ArrayList<>();

    public ScreamBox(final Context pContext) {
        mAssetManager = pContext.getAssets();
        loadAssets();
    }

    private void loadAssets() {

        final String[] assets;
        try {
            assets = mAssetManager.list(SCREAM_SOUNDS_FOLDER);
            Log.i(LOGTAG, "Found assets " + assets.length);
        } catch (IOException e) {
            Log.e(LOGTAG, "Asset listing failed", e);
            return;
        }

        for (String asset : assets) {
            String path = SCREAM_SOUNDS_FOLDER + "/" + asset;
            Sound sound = new Sound(path);
            mSounds.add(sound);
        }
    }

    public List<Sound> getSounds() {
        return mSounds;
    }
}
