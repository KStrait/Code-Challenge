package com.kylestrait.codechallenge.widget.binding;

import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
public class AppDataBindingComponent implements androidx.databinding.DataBindingComponent {
    private final DateBindingAdapter dateBindingAdapter;

    @Inject
    AppDataBindingComponent(DateBindingAdapter dateBindingAdapter) {
        this.dateBindingAdapter = dateBindingAdapter;
    }

    @Override
    public DateBindingAdapter getDateBindingAdapter() {
        return dateBindingAdapter;
    }
}
