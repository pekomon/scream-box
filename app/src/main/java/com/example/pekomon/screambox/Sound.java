package com.example.pekomon.screambox;

public class Sound {

    private String mPath;
    private String mName;
    private Integer mId;  // Could be null... maybe(?)

    public Sound(String pPath) {
        mPath = pPath;
        mName = parseNameFromPath(pPath);
    }

    private String parseNameFromPath(final String pPath) {
        String[] parts = pPath.split("/");
        final String filename = parts[parts.length - 1];
        return filename.replace(".wav", "");
    }

    public Integer getId() {
        return mId;
    }

    public void setId(final Integer pId) {
        mId = pId;
    }

    public String getPath() {
        return mPath;
    }

    public String getName() {
        return mName;
    }
}
