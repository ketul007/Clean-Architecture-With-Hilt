package com.example.cleanarchitecture.ui.test;

import androidx.lifecycle.ViewModelProvider;

import com.example.cleanarchitecture.R;
import com.example.cleanarchitecture.databinding.FragmentTestBinding;
import com.example.cleanarchitecture.ui.home.HomeViewModel;
import com.example.core.base.BaseBindingFragment;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class TestFragment extends BaseBindingFragment<FragmentTestBinding> {

    private HomeViewModel viewModel;


    public TestFragment() {
        super(R.layout.fragment_test);
    }
    private TestFragmentArgs args;
    @Override
    protected void viewSetup() {
        args = TestFragmentArgs.fromBundle(getArguments());
        binding.tvMain.setText(args.getName());
        binding.tvMain.setOnClickListener(view -> {
            navController.navigateUp();
        });
    }

    @Override
    protected void viewModelSetup() {
        viewModel = new ViewModelProvider(this).get(HomeViewModel.class);

    }
}
