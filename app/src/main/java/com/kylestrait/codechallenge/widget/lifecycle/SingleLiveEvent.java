package com.kylestrait.codechallenge.widget.lifecycle;

/*
 * From https://github.com/googlesamples/android-architecture/blob/dev-todo-mvvm-live/todoapp/app/src/main/java/com/example/android/architecture/blueprints/todoapp/SingleLiveEvent.java
 */

import android.util.Log;

import java.util.concurrent.atomic.AtomicBoolean;

import androidx.annotation.MainThread;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;

/**
 * A lifecycle-aware observable that sends only new updates after subscription, used for events like
 * navigation and Snackbar messages.
 * <p>
 * This avoids a common problem with events: on configuration change (like rotation) an update
 * can be emitted if the observer is active. This LiveData only calls the observable if there's an
 * explicit call to setValue() or call().
 * <p>
 * Note that only one observer is going to be notified of changes.
 */
public class SingleLiveEvent<T> extends MutableLiveData<T> {
    private static final String TAG = SingleLiveEvent.class.getSimpleName();

    private final AtomicBoolean mPending = new AtomicBoolean(false);

    public static <T> SingleLiveEvent<T> create(T value) {
        SingleLiveEvent<T> singleLiveEvent = new SingleLiveEvent<>();
        singleLiveEvent.setValue(value);
        return singleLiveEvent;
    }

    @Override
    @MainThread
    public void observe(@NonNull LifecycleOwner owner, @NonNull Observer<? super T> observer) {
//        Log.d(TAG, "Observe");

        if (hasActiveObservers()) {
            Log.w(TAG, "Multiple observers registered but only one will be notified of changes.");
        }

        // Observe the internal MutableLiveData
        super.observe(owner, t -> {
//            Log.d(TAG, "Observed with pending: " + mPending.get());
            if (mPending.compareAndSet(true, false)) {
                observer.onChanged(t);
            }
        });
    }

    @Override
    @MainThread
    public void setValue(@Nullable T t) {
//        Log.d(TAG, "Set");
        mPending.set(true);
        super.setValue(t);
    }

    /**
     * Used for cases where T is Void, to make calls cleaner.
     */
    @MainThread
    public void call() {
        setValue(null);
    }
}