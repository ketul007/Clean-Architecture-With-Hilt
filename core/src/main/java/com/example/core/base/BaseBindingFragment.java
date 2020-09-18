package com.example.core.base;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.LayoutRes;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;

public abstract class BaseBindingFragment<T extends ViewDataBinding> extends Fragment {


    public BaseBindingFragment(int layout){
        super(layout);
        this.contentView = layout;
    }

    @LayoutRes
    private int contentView;

    protected abstract void viewSetup();

    protected abstract void viewModelSetup();

    protected T binding ;
    protected NavController navController ;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater,contentView,container,false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        navController = NavHostFragment.findNavController(this);
        viewModelSetup();
        viewSetup();
    }


    @Override
    public void onDestroy() {
        super.onDestroy();
        if (binding != null){
            binding.unbind();
        }
    }
}
