package com.example.domain.repo;

import com.example.domain.models.Todo;

import io.reactivex.rxjava3.core.Single;

public interface TodoRepository {

    Single<Todo> getTodo(int id);
}
