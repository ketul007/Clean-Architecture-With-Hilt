package com.example.cleanarchitecture.ui.details;

import android.view.View;
import android.widget.Toast;

import androidx.lifecycle.ViewModelProvider;

import com.example.cleanarchitecture.R;
import com.example.cleanarchitecture.databinding.FragmentDetailsBinding;
import com.example.cleanarchitecture.viewModels.HomeViewModel;
import com.example.core.base.BaseBindingFragment;
import com.example.core.utils.EventObserver;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class DetailsFragment extends BaseBindingFragment<FragmentDetailsBinding> {

    private HomeViewModel viewModel;

    public DetailsFragment() {
        super(R.layout.fragment_details);
    }

    @Override
    protected void viewSetup() {
    }

    @Override
    protected void viewModelSetup() {
        viewModel = new ViewModelProvider(this).get(HomeViewModel.class);
        viewModel.getTodo();
        viewModel.todo.observe(this, todo -> {
            binding.tvId.setText(todo.getId().toString());
            binding.tvName.setText(todo.getTitle());
        });
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
