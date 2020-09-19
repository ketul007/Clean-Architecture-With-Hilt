package com.example.data.network;



import com.example.domain.models.Post;
import com.example.domain.models.Todo;

import io.reactivex.rxjava3.core.Single;
import retrofit2.http.GET;
import retrofit2.http.Path;


public interface ApiService {

    @GET("/todos/{id}")
    Single<Todo> getTodo(@Path("id") int id);


    @GET("/posts/{id}")
    Single<Post> getPost(@Path("id") int id);

}
