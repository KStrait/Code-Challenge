package com.kylestrait.codechallenge.reposlist;

import com.kylestrait.codechallenge.data.Repo;
import com.kylestrait.codechallenge.repo.GithubRepo;
import com.kylestrait.codechallenge.widget.BaseViewModel;

import java.util.List;

import javax.inject.Inject;

import androidx.lifecycle.LiveData;

public class ReposViewModel extends BaseViewModel<ReposViewModel> {

    GithubRepo githubRepo;

    @Inject
    ReposViewModel(GithubRepo githubRepo) {
        this.githubRepo = githubRepo;
    }

    @Override
    public void resume() {
        // initilize the call to set the list of repos LiveData in the GithubRepo
        githubRepo.getAllReposCall();
    }

    // Gets a list of all repos from the webservice
    public LiveData<List<Repo>> getReposList() {
        return githubRepo.getAllRepos();
    }

    // Handles item click from repo list
    public void onItemClick(Repo repo) {
        if (repo != null) {
            mainViewModel.showRepoWebView(repo.getName(), repo.getHtmlUrl());
        }
    }

    //If there were actual api calls to an auth service, I'd do more than just pop the stack. In this case it works because it takes a user
    //back to the log-in fragment.
    public void onLogoutClicked() {
        mainViewModel.goBack();
    }
}
