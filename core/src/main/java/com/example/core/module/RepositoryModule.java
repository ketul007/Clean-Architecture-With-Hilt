package com.example.core.module;

import com.example.data.RepoImpl.TodoRepoImp;
import com.example.domain.repo.TodoRepository;

import javax.inject.Singleton;

import dagger.Binds;
import dagger.Module;
import dagger.hilt.InstallIn;
import dagger.hilt.android.components.ApplicationComponent;

@Module
@InstallIn(ApplicationComponent.class)
public interface RepositoryModule {

    @Binds
    @Singleton
    TodoRepository provideTodoRepo(TodoRepoImp todoRepoImp);
}
