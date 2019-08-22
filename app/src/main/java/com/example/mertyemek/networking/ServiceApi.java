package com.example.mertyemek.networking;
import com.example.mertyemek.model.UserModel;

import java.util.List;
import io.reactivex.Observable;
import retrofit2.http.GET;

public interface ServiceApi {
/*
    @GET("moviesData.txt")
    Observable<List<FilmModel>> getFilms();
    // https://jsonparsingdemo-cec5b.firebaseapp.com/jsonData/moviesData.txt
*/
    @GET("posts")
    Observable<List<UserModel>> getUsers();
    // https://jsonplaceholder.typicode.com/posts
/*
    @GET("users?page=2")
    Observable<List<DataModel>> getData();
    // https://reqres.in/api/users?page=2
*/
/*
    @GET(".json")
    Observable<List<DataModel>> getData();
    // https://null-86c43.firebaseio.com/.json
*/





}