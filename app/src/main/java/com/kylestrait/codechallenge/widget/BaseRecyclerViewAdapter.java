package com.kylestrait.codechallenge.widget;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.LayoutRes;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.lifecycle.LifecycleOwner;
import androidx.recyclerview.widget.RecyclerView;

public abstract class BaseRecyclerViewAdapter extends RecyclerView.Adapter<BaseViewHolder> implements BaseViewHolder.BaseItemClickListener {
    @Override
    @NonNull
    public BaseViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        ViewDataBinding binding = DataBindingUtil.inflate(layoutInflater, viewType, parent, false);
        return new BaseViewHolder(binding, getLifecycleOwner(), isViewTypeClickable(viewType) ? this : null);
    }

    @Override
    public void onBindViewHolder(@NonNull BaseViewHolder holder, int position) {
        Object item = getItemForPosition(position);
        holder.bind(item);
    }

    @Override
    public int getItemViewType(int position) {
        return getLayoutIdForPosition(position);
    }

    @Override
    public void onItemClick(int position) {
        // do nothing
    }

    protected boolean isViewTypeClickable(@SuppressWarnings("UnusedParameters") int viewType) {
        return false;
    }

    @Nullable
    protected abstract LifecycleOwner getLifecycleOwner();

    protected abstract Object getItemForPosition(int position);

    @LayoutRes
    protected abstract int getLayoutIdForPosition(int position);
}
