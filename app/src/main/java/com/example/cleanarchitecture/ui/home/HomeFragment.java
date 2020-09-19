package com.example.cleanarchitecture.ui.home;

import android.view.View;
import android.widget.Toast;

import androidx.lifecycle.ViewModelProvider;

import com.example.cleanarchitecture.R;
import com.example.cleanarchitecture.databinding.FragmentHomeBinding;
import com.example.core.base.BaseBindingFragment;
import com.example.core.utils.EventObserver;

import dagger.hilt.android.AndroidEntryPoint;


@AndroidEntryPoint
public class HomeFragment extends BaseBindingFragment<FragmentHomeBinding> {

    private HomeViewModel viewModel;

    public HomeFragment() {
        super(R.layout.fragment_home);
    }

    @Override
    protected void viewSetup() {
        binding.text.setOnClickListener(view -> {
            navController.navigate(R.id.action_homeFragment_to_detailsFragment);
        });
    }

    @Override
    protected void viewModelSetup() {
        viewModel = new ViewModelProvider(this).get(HomeViewModel.class);
        viewModel.getTodo();
        viewModel.todo.observe(this, todo -> binding.text.setText(todo.getTitle()));
        viewModel.errorLiveData.observe(this , new EventObserver<>(s -> {
            Toast.makeText(requireContext(), s, Toast.LENGTH_SHORT).show();
        }));
        viewModel.progressLiveData.observe(this , bool -> {
            if (bool){
                binding.flProgress.setVisibility(View.VISIBLE);
            }else{
                binding.flProgress.setVisibility(View.GONE);
            }
        });
    }
}
