package com.example.pekomon.screambox;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.pekomon.screambox.databinding.FragmentScreamBoxBinding;
import com.example.pekomon.screambox.databinding.ListItemScreamBinding;

import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ScreamBoxFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ScreamBoxFragment extends Fragment {

    private ScreamBox mScreamBox;

    public static ScreamBoxFragment newInstance() {
        return new ScreamBoxFragment();
    }

    @Override
    public void onCreate(@Nullable final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);
        mScreamBox = new ScreamBox(getActivity());
    }

    public ScreamBoxFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        FragmentScreamBoxBinding binding = DataBindingUtil.inflate(
                inflater, R.layout.fragment_scream_box, container, false);
        binding.recyclerView.setLayoutManager(new GridLayoutManager(getActivity(), 3));
        binding.recyclerView.setAdapter(new ScreamAdapter(mScreamBox.getSounds()));

        return binding.getRoot();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mScreamBox.releaseSoundPool();
    }

    private class ScreamHolder extends RecyclerView.ViewHolder {
        private ListItemScreamBinding mBinding;

        // ListItemScreamBinding -> list_item_scream.xml
        private ScreamHolder(ListItemScreamBinding pBinding) {
            super(pBinding.getRoot());
            mBinding = pBinding;
            mBinding.setViewModel(new ScreamViewModel(mScreamBox));
        }

        public void bind(Sound pSound) {
            mBinding.getViewModel().setSound(pSound);
            mBinding.executePendingBindings();
        }
    }

    private class ScreamAdapter extends RecyclerView.Adapter<ScreamHolder> {

        private List<Sound> mSounds;

        public ScreamAdapter(List<Sound> pSounds) {
            mSounds = pSounds;
        }

        @NonNull
        @Override
        public ScreamHolder onCreateViewHolder(@NonNull final ViewGroup parent, final int viewType) {
            LayoutInflater inflater = LayoutInflater.from(getActivity());
            ListItemScreamBinding binding = DataBindingUtil
                    .inflate(inflater, R.layout.list_item_scream, parent, false);
            return new ScreamHolder(binding);
        }

        @Override
        public void onBindViewHolder(@NonNull final ScreamHolder holder, final int position) {

            Sound sound = mSounds.get(position);
            holder.bind(sound);
        }

        @Override
        public int getItemCount() {
            return mSounds.size();
        }
    }

}
