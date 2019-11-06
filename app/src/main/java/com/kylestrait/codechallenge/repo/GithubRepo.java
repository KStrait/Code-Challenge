package com.kylestrait.codechallenge.repo;

import com.kylestrait.codechallenge.data.Repo;
import com.kylestrait.codechallenge.network.WebService;
import com.kylestrait.codechallenge.widget.lifecycle.SingleLiveEvent;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

@Singleton
public class GithubRepo {

    @Inject
    WebService webService;

    public MutableLiveData<List<Repo>> repoList = new MutableLiveData<>();

    public SingleLiveEvent<Boolean> error = new SingleLiveEvent<>();

    @Inject
    GithubRepo() {

    }

    // uses webService retrofit call to get data from network and sets LiveData if reponse is successfull && not null
    public void getAllReposCall() {
        Call<List<Repo>> repoCall = webService.getAllRepos();

        repoCall.enqueue(new Callback<List<Repo>>() {
            @Override
            public void onResponse(@NonNull Call<List<Repo>> call, @NonNull Response<List<Repo>> response) {
                if (response.isSuccessful()) {
                    repoList.setValue(response.body());
                } else {
                    error.setValue(true);
                }
            }

            @Override
            public void onFailure(@NonNull Call<List<Repo>> call, @NonNull Throwable t) {
                t.printStackTrace();

                error.setValue(true);
            }
        });
    }

    public LiveData<List<Repo>> getAllRepos() {
        return repoList;
    }

    public LiveData<Boolean> getRepoError() {
        return error;
    }
}
