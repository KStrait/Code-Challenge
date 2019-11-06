package com.kylestrait.codechallenge;

import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;
import dagger.android.support.DaggerAppCompatActivity;

import android.os.Bundle;
import android.util.Log;

import com.kylestrait.codechallenge.databinding.ActivityMainBinding;
import com.kylestrait.codechallenge.login.LoginFragment;
import com.kylestrait.codechallenge.reposlist.RepoWebViewFragment;
import com.kylestrait.codechallenge.reposlist.ReposFragment;
import com.kylestrait.codechallenge.widget.MainViewModelProvider;

import javax.inject.Inject;

public class MainActivity extends DaggerAppCompatActivity implements MainViewModelProvider {
    private final static String TAG = MainActivity.class.getSimpleName();

    @Inject
    ViewModelProvider.Factory viewModelFactory;

    private MainViewModel viewModel;
    private ActivityMainBinding binding;
    private boolean clearingBackStack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        binding.setLifecycleOwner(this);

        viewModel = ViewModelProviders.of(this, viewModelFactory).get(MainViewModel.class);

        binding.setViewModel(viewModel);

        getSupportFragmentManager().addOnBackStackChangedListener(() -> {
            if (!clearingBackStack)
                viewModel.setMainView(detectView());
        });

        viewModel.getMainView().observe(this, mainView -> {
            Log.d(TAG, "mainView observed: " + mainView);
            Log.d(TAG, "mainView detected: " + detectView());

            FragmentManager fragmentManager = getSupportFragmentManager();
            @MainViewModel.MainView int mv = mainView != null ? mainView : MainViewModel.VIEW_LOGIN;
            @MainViewModel.MainView int detectedView = detectView();
            if (mv != detectedView) {
                switch (mv) {
                    case MainViewModel.VIEW_LOGIN:
                        showLoginFragment(new LoginFragment());
                        break;
                    case MainViewModel.VIEW_REPO_LIST:
                        showMainContent(new ReposFragment());
                        break;
                    case MainViewModel.VIEW_WEB:
                        showMainContent(RepoWebViewFragment.newInstance(viewModel.getUrl()));
                        break;
                }
            }
        });

        viewModel.getMainAction().observe(this, mainAction -> {
            Log.d(TAG, "mainAction observed: " + mainAction);
            if (mainAction != null) {
                @MainViewModel.MainAction int ma = mainAction;
                switch (ma) {
                    case MainViewModel.ACTION_GO_BACK:
                        if (getSupportFragmentManager().getBackStackEntryCount() > 0)
                            getSupportFragmentManager().popBackStackImmediate();
                        break;
                }
            }
        });
    }

    private void clearBackStack() {
            FragmentManager fragmentManager = getSupportFragmentManager();
            if (fragmentManager.getBackStackEntryCount() > 0) {
                clearingBackStack = true;
                FragmentManager.BackStackEntry entry = fragmentManager.getBackStackEntryAt(0);
                fragmentManager.popBackStackImmediate(entry.getId(), FragmentManager.POP_BACK_STACK_INCLUSIVE);
                clearingBackStack = false;
            }
    }

    // Leaving intial fragment off the stack, for nav purpose
    private void showLoginFragment(Fragment fragment) {
            getSupportFragmentManager().executePendingTransactions();
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.content_main, fragment)
                    .setPrimaryNavigationFragment(fragment)
                    .commit();

    }

    private void showMainContent(Fragment fragment) {
        getSupportFragmentManager().executePendingTransactions();
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.content_main, fragment)
                    .addToBackStack(null)
                .setPrimaryNavigationFragment(fragment)
                .commit();

    }

    @MainViewModel.MainView
    private int detectView() {
        FragmentManager fragmentManager = getSupportFragmentManager();
        Fragment fragment = fragmentManager.findFragmentById(R.id.content_main);

        if (fragment instanceof LoginFragment)
            return MainViewModel.VIEW_LOGIN;
        if (fragment instanceof ReposFragment)
            return MainViewModel.VIEW_REPO_LIST;
        if (fragment instanceof RepoWebViewFragment)
            return MainViewModel.VIEW_WEB;

        Log.d(TAG, "detectView did not detect fragment");
        return MainViewModel.VIEW_NONE;
    }

    @Override
    public MainViewModel provideMainViewModel() {
        return viewModel;
    }
}
