package com.kylestrait.codechallenge.reposlist;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.kylestrait.codechallenge.R;
import com.kylestrait.codechallenge.data.Repo;
import com.kylestrait.codechallenge.databinding.FragmentReposBinding;
import com.kylestrait.codechallenge.databinding.ItemRepoBinding;
import com.kylestrait.codechallenge.widget.BaseFragment;
import com.kylestrait.codechallenge.widget.BaseRecyclerViewAdapter;
import com.kylestrait.codechallenge.widget.BaseViewHolder;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.LifecycleOwner;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class ReposFragment extends BaseFragment<ReposViewModel> {

    private FragmentReposBinding binding;
    private ReposRecyclerViewAdapter adapter;
    private LinearLayoutManager linearLayoutManager;

    public ReposFragment() {
        super(ReposViewModel.class);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentReposBinding.inflate(inflater, container, false);
        binding.setLifecycleOwner(this);

        binding.setViewModel(viewModel);

        //RV setup
        adapter = new ReposRecyclerViewAdapter();
        binding.reposRv.addItemDecoration(new DividerItemDecoration(getContext(), DividerItemDecoration.VERTICAL));
        binding.reposRv.setAdapter(adapter);
        linearLayoutManager = new LinearLayoutManager(getContext(), RecyclerView.VERTICAL, false);
        binding.reposRv.setLayoutManager(linearLayoutManager);

        return binding.getRoot();
    }


    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        // get list from viewmodel and set adapter data
        viewModel.getReposList().observe(getViewLifecycleOwner(), repos -> {
            if (repos != null && !repos.isEmpty()) {
                adapter.setData(repos);
            }
        });
    }

    private class ReposRecyclerViewAdapter extends BaseRecyclerViewAdapter {
        private List<Repo> repoList = new ArrayList<>();

        @Override
        public void onBindViewHolder(@NonNull BaseViewHolder holder, int position) {
            // give each item access to the viewModel
            ((ItemRepoBinding) holder.binding).setViewModel(viewModel);
            super.onBindViewHolder(holder, position);
        }

        @Nullable
        @Override
        protected LifecycleOwner getLifecycleOwner() {
            return getViewLifecycleOwner();
        }

        @Override
        protected Object getItemForPosition(int position) {
            return repoList.get(position);
        }

        @Override
        protected int getLayoutIdForPosition(int position) {
            return R.layout.item_repo;
        }

        @Override
        public int getItemCount() {
            return repoList.size();
        }

        public void setData(List<Repo> repos) {
            if (repos != null && !repos.isEmpty()) {
                repoList.clear();
                repoList.addAll(repos);
                notifyDataSetChanged();
            }
        }
    }
}
