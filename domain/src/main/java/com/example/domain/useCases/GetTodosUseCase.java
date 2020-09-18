package com.example.domain.useCases;

import com.example.domain.models.Todo;
import com.example.domain.repo.TodoRepository;

import javax.inject.Inject;

import io.reactivex.rxjava3.core.Single;

public class GetTodosUseCase extends SingleUseCase<Todo,GetTodosUseCase.Params>{

    private static TodoRepository todoRepository;

    @Inject
    public GetTodosUseCase(TodoRepository repository){
        todoRepository = repository;
    }

    @Override
    protected Single<Todo> buildUseCaseSingle(Params params) {
        return todoRepository.getTodo(params.id);
    }


    public static class Params{
        int id;
        public Params(int id){
            this.id = id;
        }
    }
}
