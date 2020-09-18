package com.example.cleanarchitecture.viewModels;

import androidx.hilt.lifecycle.ViewModelInject;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import com.example.core.utils.Event;
import com.example.core.base.BaseObservableViewModel;
import com.example.domain.models.Todo;
import com.example.domain.useCases.GetTodosUseCase;
import com.example.domain.useCases.SingleUseCaseCallback;


public class HomeViewModel extends BaseObservableViewModel {

    private MutableLiveData<Todo> todoMutableLiveData = new MutableLiveData<>();
    public LiveData<Todo> todo = todoMutableLiveData;


    private MutableLiveData<Event<String>> _errorLiveData = new MutableLiveData<>();
    public LiveData<Event<String>> errorLiveData = _errorLiveData;


    private MutableLiveData<Boolean> _progressLiveData = new MutableLiveData<>(false);
    public LiveData<Boolean> progressLiveData = _progressLiveData;

    private GetTodosUseCase getTodosUseCase;

    @ViewModelInject
    public HomeViewModel(GetTodosUseCase getTodosUseCase) {
        super(getTodosUseCase);
        this.getTodosUseCase = getTodosUseCase;
    }

    public void getTodo() {
        _progressLiveData.postValue(true);
        getTodosUseCase.execute(new GetTodosUseCase.Params(5), new SingleUseCaseCallback<Todo>() {
            @Override
            public void onSuccess(Todo todo) {
                todoMutableLiveData.postValue(todo);
            }

            @Override
            public void onError(Throwable t) {
                _errorLiveData.postValue(new Event(t.getLocalizedMessage()));
            }

            @Override
            public void onFinished() {
                _progressLiveData.postValue(false);
            }
        });
    }
}
