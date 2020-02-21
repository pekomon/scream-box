package com.example.pekomon.screambox;

import android.content.Context;
import android.content.res.AssetFileDescriptor;
import android.content.res.AssetManager;
import android.media.AudioAttributes;
import android.media.AudioManager;
import android.media.SoundPool;
import android.util.Log;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ScreamBox {
    private static final String LOGTAG = "ScreamBox";

    private static final String SCREAM_SOUNDS_FOLDER = "sample_sounds";
    private static final int MAX_SOUNDS = 6;

    private AssetManager mAssetManager;
    private List<Sound> mSounds = new ArrayList<>();
    private SoundPool mSoundPool;


    public ScreamBox(final Context pContext) {
        mAssetManager = pContext.getAssets();
        SoundPool.Builder builder = new SoundPool.Builder().setAudioAttributes(
                new AudioAttributes.Builder()
                .setUsage(AudioAttributes.USAGE_MEDIA)
                .setContentType(AudioAttributes.CONTENT_TYPE_SONIFICATION)
                .build()
        ).setMaxStreams(MAX_SOUNDS);
        mSoundPool = builder.build();

/*
        mSoundPool = new SoundPool();
        mSoundPool = SoundPool().Builder().setAudioAttributes(new AudioAttributes.Builder()
                .setUsage(AudioAttributes.USAGE_MEDIA)
                .setContentType(AudioAttributes.CONTENT_TYPE_SONIFICATION)
                .build());
*/

        //mSoundPool = new SoundPool(MAX_SOUNDS, AudioManager.STREAM_MUSIC, 0);
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
            try {
                load(sound);
            } catch (IOException e) {
                Log.e(LOGTAG, "Sound asset loading failed", e);
            }
            mSounds.add(sound);
        }
    }

    private void load(final Sound pSound) throws  IOException {

        AssetFileDescriptor fd = null;
        fd = mAssetManager.openFd(pSound.getPath());
        int id = mSoundPool.load(fd, 1);
        pSound.setId(id);
    }

    public List<Sound> getSounds() {
        return mSounds;
    }

    public void play(Sound pSound) {
        Integer id = pSound.getId();
        if (id == null) {
            return;
        }
        mSoundPool.play(id,1f,1f,1, 0,1f);
    }

    public void releaseSoundPool() {
        mSoundPool.release();
    }
}
