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
    private String name, url;

    public RepoWebViewFragment() {
        super(ReposViewModel.class);
    }

    // Passing in arguments when starting the fragment
    public static RepoWebViewFragment newInstance(String name, String url) {
        RepoWebViewFragment frag = new RepoWebViewFragment();
        Bundle args = new Bundle();
        args.putString("name", name);
        args.putString("url", url);
        frag.setArguments(args);
        return frag;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentRepoWebviewBinding.inflate(inflater, container, false);
        binding.setLifecycleOwner(this);

        // Getting arguments and using them where needed
        if (getArguments() != null) {
            name = getArguments().getString("name");
            url = getArguments().getString("url");
            binding.setName(name);
            binding.webview.setWebViewClient(new WebViewClient());
            binding.webview.loadUrl(url);
        }

        return binding.getRoot();
    }
}
