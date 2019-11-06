package com.kylestrait.codechallenge.login;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.kylestrait.codechallenge.R;
import com.kylestrait.codechallenge.data.Login;
import com.kylestrait.codechallenge.databinding.FragmentLoginBinding;
import com.kylestrait.codechallenge.widget.BaseFragment;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;

public class LoginFragment extends BaseFragment<LoginViewModel> {

    private FragmentLoginBinding binding;

    public LoginFragment() {
        super(LoginViewModel.class);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentLoginBinding.inflate(inflater, container, false);
        binding.setLifecycleOwner(this);

        binding.setViewModel(viewModel);

        // if user info is present in SharedPrefs populate email and password fields
        if (viewModel.getUserInfo() != null) {
            binding.setUser(viewModel.getUserInfo());
        }

        return binding.getRoot();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        // There are TextWatcher methods that can handle logic in the viewmodel, but for the sake of time I did it this way

        viewModel.getHelpClicked().observe(getViewLifecycleOwner(), help -> {
            if (help != null) {
                if (help) {
                    if (getActivity() != null) {
                        AlertDialog alertDialog = new AlertDialog.Builder(getActivity()).create();
                        alertDialog.setMessage(getResources().getString(R.string.password_reqs));
                        alertDialog.setButton(AlertDialog.BUTTON_POSITIVE, "OK", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                alertDialog.dismiss();
                            }
                        });
                        alertDialog.show();
                    }
                }
            }
        });

        viewModel.getLoginClicked().observe(getViewLifecycleOwner(), login -> {
            if (login != null && login) {
                if (viewModel.validate(binding.emailField.getText().toString(), binding.passwordField.getText().toString())) {
                    viewModel.onLoginSuccess(new Login(binding.emailField.getText().toString(), binding.passwordField.getText().toString()));
                } else {
                    if (getActivity() != null) {
                        AlertDialog alertDialog = new AlertDialog.Builder(getActivity()).create();
                        alertDialog.setMessage(getResources().getString(R.string.invalid_credentials));
                        alertDialog.setButton(AlertDialog.BUTTON_POSITIVE, "OK", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                alertDialog.dismiss();
                            }
                        });
                        alertDialog.show();
                    }
                }
            }
        });
    }
}
