package com.example.bookexchange1.BLL;

import com.example.bookexchange1.Model.Book;
import com.example.bookexchange1.Response.GeneralResponse;
import com.example.bookexchange1.Response.UserResponse;

import java.util.List;

import retrofit2.Call;
import retrofit2.Response;

import static com.example.bookexchange1.URL.URL.bookAPI;
import static com.example.bookexchange1.URL.URL.userAPI;

public class BookBLL {

    public boolean add (String name, String author,String image)
    {
        boolean isSuccess=false;
        Call<GeneralResponse> call=bookAPI.add(name,author,image);
        try {
            Response<GeneralResponse> response=call.execute();
            GeneralResponse generalResponse=response.body();
            if(generalResponse.getStatus()=="201")
            {
                isSuccess=true;
            }
            else
            {
                isSuccess=false;
            }
        }
        catch (Exception ex)
        {
            ex.printStackTrace();
        }
        return isSuccess;
    }

    public List<Book> getAllBook ()
    {
        List<Book>books=null;
        Call<List<Book>> call =bookAPI.getAll();
        try{
           Response <List<Book>>response =call.execute();
           books=response.body();
        }
        catch (Exception ex)
        {
            ex.printStackTrace();
        }
        return books;
    }

    public List<Book> getMyBook()
    {
        List<Book>books=null;
        Call<List<Book>> call =bookAPI.getMyBooks(UserResponse.Token);
        try{
            Response <List<Book>>response =call.execute();
            books=response.body();
        }
        catch (Exception ex)
        {
            ex.printStackTrace();
        }
        return books;
    }
}
