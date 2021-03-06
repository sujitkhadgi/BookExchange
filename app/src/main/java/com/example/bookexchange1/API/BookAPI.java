package com.example.bookexchange1.API;

import com.example.bookexchange1.Model.Book;


import java.util.List;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.http.DELETE;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Part;
import retrofit2.http.Path;

public interface BookAPI {

    @Multipart
    @POST("book/add")
    Call<ResponseBody> add(@Part("name") RequestBody name,
                           @Part("author") RequestBody author,
                           @Part("description") RequestBody description,
                           @Part MultipartBody.Part image,
                           @Part("condition") RequestBody condition,
                           @Part("belongs_to") RequestBody userId,
                           @Part("status") RequestBody status);

    @FormUrlEncoded
    @PUT("book/{id}")
    Call<ResponseBody> update(@Path("id") Integer id,
                              @Field("name") String name,
                              @Field("author") String author,
                              @Field("description") String description,
                              @Field("condition") String condition,
                              @Field("status") String status);

    @GET("book")
    Call<ResponseBody> getAll();

    @GET("book")
    Call<ResponseBody> getMyBooks();

    @DELETE("book/{id}")
    Call<ResponseBody> delete(@Path("id") Integer id);

}
