package com.example.movieapp.view.fragment;

import android.widget.Toast;

import androidx.fragment.app.Fragment;

import com.example.movieapp.di.HasComponent;

public abstract class BaseFragment extends Fragment {

    protected void showToast(String message) {
        Toast.makeText(getActivity(), message, Toast.LENGTH_SHORT).show();
    }

    @SuppressWarnings("unchecked")
    protected <C> C getComponent(Class<C> componentType) {
        return componentType.cast(((HasComponent<C>) getContext()).getComponent());
    }
}
