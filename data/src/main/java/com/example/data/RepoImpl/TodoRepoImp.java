package com.example.data.RepoImpl;

import com.example.data.network.ApiService;
import com.example.domain.models.Todo;
import com.example.domain.repo.TodoRepository;

import javax.inject.Inject;

import io.reactivex.rxjava3.core.Single;

public class TodoRepoImp implements TodoRepository {

    ApiService apiService;

    @Inject
    public TodoRepoImp(ApiService apiService){
        this.apiService = apiService;
    }

    @Override
    public Single<Todo> getTodo(int id) {
        return apiService.getTodo(id);
    }
}
