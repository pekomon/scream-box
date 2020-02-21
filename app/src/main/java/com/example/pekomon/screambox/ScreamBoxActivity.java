package com.example.pekomon.screambox;

import androidx.fragment.app.Fragment;

public class ScreamBoxActivity extends SingleFragmentActivity {
    @Override
    protected Fragment createFragment() {
        return ScreamBoxFragment.newInstance();
    }
}
