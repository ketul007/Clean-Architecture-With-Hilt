package com.example.domain.useCases;

import com.example.domain.models.Post;
import com.example.domain.repo.TodoRepository;

import javax.inject.Inject;

import io.reactivex.rxjava3.core.Single;

public class GetPostUseCase extends SingleUseCase<Post, GetPostUseCase.Params>{

    private static TodoRepository todoRepository;

    @Inject
    public GetPostUseCase(TodoRepository repository){
        todoRepository = repository;
    }

    @Override
    protected Single<Post> buildUseCaseSingle(Params params) {
        return todoRepository.getPost(params.id);
    }


    public static class Params{
        int id;
        public Params(int id){
            this.id = id;
        }
    }
}
