package com.kylestrait.codechallenge.reposlist;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebViewClient;

import com.kylestrait.codechallenge.databinding.FragmentRepoWebviewBinding;
import com.kylestrait.codechallenge.widget.BaseFragment;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class RepoWebViewFragment extends BaseFragment<ReposViewModel> {

    private FragmentRepoWebviewBinding binding;
    private String url;

    public RepoWebViewFragment() {
        super(ReposViewModel.class);
    }

    public static RepoWebViewFragment newInstance(String url) {
        RepoWebViewFragment frag = new RepoWebViewFragment();
        Bundle args = new Bundle();
        args.putString("url", url);
        frag.setArguments(args);
        return frag;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentRepoWebviewBinding.inflate(inflater, container, false);
        binding.setLifecycleOwner(this);

        if (getArguments() != null) {
            url = getArguments().getString("url");
            binding.webview.setWebViewClient(new WebViewClient());
            binding.webview.loadUrl(url);
        }

        return binding.getRoot();
    }
}
