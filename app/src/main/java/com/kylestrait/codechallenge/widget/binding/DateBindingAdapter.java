package com.kylestrait.codechallenge.widget.binding;

import android.widget.TextView;

import com.kylestrait.codechallenge.R;
import com.kylestrait.codechallenge.data.Repo;
import com.kylestrait.codechallenge.util.DateFormatter;

import javax.inject.Inject;
import javax.inject.Singleton;

import androidx.databinding.BindingAdapter;

@Singleton
public class DateBindingAdapter {
    private DateFormatter dateFormatter;

    @Inject
    DateBindingAdapter(DateFormatter dateFormatter) {
        this.dateFormatter = dateFormatter;
    }

    @BindingAdapter({"repoDate"})
    public void loadDate(TextView view, Repo repo) {
        if (repo != null) {
            view.setText(view.getResources().getString(R.string.last_updated, dateFormatter.getFormattedDate(repo.getUpdatedAt())));
        }
    }
}
