package com.example.cleanarchitecture.ui.home;

import androidx.hilt.lifecycle.ViewModelInject;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.core.base.BaseObservableViewModel;
import com.example.core.utils.Event;
import com.example.domain.models.Post;
import com.example.domain.models.Todo;
import com.example.domain.useCases.GetPostUseCase;
import com.example.domain.useCases.GetTodosUseCase;
import com.example.domain.useCases.SingleUseCaseCallback;


public class HomeViewModel extends BaseObservableViewModel {

    private MutableLiveData<Todo> todoMutableLiveData = new MutableLiveData<>();
    public LiveData<Todo> todo = todoMutableLiveData;


    private MutableLiveData<Post> _postMutableLiveData = new MutableLiveData<>();
    public LiveData<Post> postMutableLiveData = _postMutableLiveData;


    private MutableLiveData<Event<String>> _errorLiveData = new MutableLiveData<>();
    public LiveData<Event<String>> errorLiveData = _errorLiveData;


    private MutableLiveData<Boolean> _progressLiveData = new MutableLiveData<>(false);
    public LiveData<Boolean> progressLiveData = _progressLiveData;

    private GetTodosUseCase getTodosUseCase;
    private GetPostUseCase getPostUseCase;

    @ViewModelInject
    public HomeViewModel(GetTodosUseCase getTodosUseCase, GetPostUseCase getPostUseCase) {
        super(getTodosUseCase, getPostUseCase);
        this.getTodosUseCase = getTodosUseCase;
        this.getPostUseCase = getPostUseCase;
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

    public void getPost() {
        _progressLiveData.postValue(true);
        getPostUseCase.execute(new GetPostUseCase.Params(5), new SingleUseCaseCallback<Post>() {
            @Override
            public void onSuccess(Post post) {
                _postMutableLiveData.postValue(post);
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
