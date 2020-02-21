package com.example.pekomon.screambox;

public class Sound {

    private String mPath;
    private String mName;

    public Sound(String pPath) {
        mPath = pPath;
        mName = parseNameFromPath(pPath);
    }

    private String parseNameFromPath(final String pPath) {
        String[] parts = pPath.split("/");
        final String filename = parts[parts.length - 1];
        return filename.replace(".wav", "");
    }

    public String getPath() {
        return mPath;
    }

    public String getName() {
        return mName;
    }
}
