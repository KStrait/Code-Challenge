package com.kylestrait.codechallenge.widget;

import com.kylestrait.codechallenge.BR;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.ViewDataBinding;
import androidx.lifecycle.LifecycleOwner;
import androidx.recyclerview.widget.RecyclerView;

public class BaseViewHolder extends RecyclerView.ViewHolder {
    public interface BaseItemClickListener {
        void onItemClick(int position);
    }

    public final ViewDataBinding binding;

    public BaseViewHolder(@NonNull ViewDataBinding binding, @Nullable LifecycleOwner lifecycleOwner, @Nullable final BaseItemClickListener clickListener) {
        super(binding.getRoot());
        this.binding = binding;
        if (lifecycleOwner != null)
            binding.setLifecycleOwner(lifecycleOwner);
        if (clickListener != null) {
            binding.getRoot().setOnClickListener(v -> clickListener.onItemClick(getLayoutPosition()));
        }
    }

    /**
     * Binds the given item to the variable named item and executes the bindings. If overloading this
     * be sure to call super(item) last so the bindings are only executed once. If calling from another
     * set call, also be sure to call this last.
     * @param item The item to set as the bound item variable.
     */
    public void bind(Object item) {
        binding.setVariable(BR.item, item);
        binding.executePendingBindings();
    }
}
