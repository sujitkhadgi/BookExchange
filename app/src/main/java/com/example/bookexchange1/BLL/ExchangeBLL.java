package com.example.bookexchange1.BLL;

import android.widget.Toast;

import com.example.bookexchange1.Model.Notification;

import com.example.bookexchange1.Model.User;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import StrictMode.StrictModeClass;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.http.Field;


import static com.example.bookexchange1.URL.URL.exchangeAPI;


public class ExchangeBLL {
    boolean isSuccess;

    public boolean add(Integer bookReqId, Integer bookOfferedId, Integer userId, Integer id, String status) {
        Call<ResponseBody> call = exchangeAPI.add(bookReqId, bookOfferedId, userId, id, status);

        StrictModeClass.StrictMode();
        try {
            Response<ResponseBody> response = call.execute();
            final Response<ResponseBody> finalResponse = response;


            if (response.code() == 200) {

                isSuccess = true;
            } else {
                isSuccess = false;
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

        return isSuccess;
    }

    public boolean delete(int id) {
        Call<ResponseBody> call = exchangeAPI.delete(id);
        StrictModeClass.StrictMode();
        try {
            Response<ResponseBody> response = call.execute();
            if (response.code() == 200) {
                isSuccess = true;

            } else {
                isSuccess = false;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return isSuccess;
    }

    //for getting notification list
    public List<Notification> notification() {
        List<Notification> notifications = new ArrayList<>();
        Call<ResponseBody> call = exchangeAPI.requestedTo(User.id);
        StrictModeClass.StrictMode();
        try {
            Response<ResponseBody> response = call.execute();
            JSONObject jobj = new JSONObject((response.body().string()));


            JSONObject object1 = jobj.getJSONObject("data");
            JSONArray jsonArray = object1.getJSONArray("data");

            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject jsonObject = jsonArray.getJSONObject(i);
                int id = jsonObject.getInt("id");
                String status = jsonObject.getString("status");
                JSONObject bookWanted = jsonObject.getJSONObject("book_wanted");
                JSONObject bookOffered = jsonObject.getJSONObject("book_offered");
                JSONObject request = jsonObject.getJSONObject("requested_by");
                JSONObject bookWantedJSONObject = bookWanted.getJSONObject("data");
                JSONObject bookOfferedJSONObject = bookOffered.getJSONObject("data");
                JSONObject requestJSONObject = request.getJSONObject("data");
                String email= (requestJSONObject.getString("email"));
                String senderName = requestJSONObject.getString("name");
                String requestedBook = bookWantedJSONObject.getString("name");
                String proposedBook = bookOfferedJSONObject.getString("name");
                String bookImg = bookOfferedJSONObject.getString("image");
                String time = jsonObject.getString("created_at");
                if (status.equals("requested")) {
                    notifications.add(new Notification(id, senderName, requestedBook, proposedBook, time, bookImg, status,email));
                }
            }
        } catch (Exception ex) {
            ex.printStackTrace();

        }
        Call<ResponseBody> call2 = exchangeAPI.requestedBy(User.id);
        StrictModeClass.StrictMode();
        try {
            Response<ResponseBody> response = call2.execute();
            JSONObject jobj = new JSONObject((response.body().string()));

            //  String status = (jobj.getString("message"));

            JSONObject object1 = jobj.getJSONObject("data");
            JSONArray jsonArray = object1.getJSONArray("data");

            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject jsonObject = jsonArray.getJSONObject(i);
                int id = jsonObject.getInt("id");
                JSONObject bookWanted = jsonObject.getJSONObject("book_wanted");
                JSONObject bookOffered = jsonObject.getJSONObject("book_offered");
                JSONObject request = jsonObject.getJSONObject("requested_to");
                JSONObject bookWantedJSONObject = bookWanted.getJSONObject("data");
                JSONObject bookOfferedJSONObject = bookOffered.getJSONObject("data");
                JSONObject requestJSONObject = request.getJSONObject("data");
                String email= (requestJSONObject.getString("email"));
                String senderName = requestJSONObject.getString("name");
                String requestedBook = bookWantedJSONObject.getString("name");
                String proposedBook = bookOfferedJSONObject.getString("name");
                String bookImg = bookOfferedJSONObject.getString("image");
                String time = jsonObject.getString("created_at");
                String status = jsonObject.getString("status");
                if (status.equals("accepted")) {
                    notifications.add(new Notification(id, senderName, requestedBook, proposedBook, time, bookImg, status,email));
                }
            }

        } catch (Exception ex) {
            ex.printStackTrace();

        }
        return notifications;
    }

    //for getting my activity list
    public List<Notification> myActivity() {
        List<Notification> notifications = new ArrayList<>();
        Call<ResponseBody> call = exchangeAPI.requestedBy(User.id);
        StrictModeClass.StrictMode();
        try {
            Response<ResponseBody> response = call.execute();
            JSONObject jobj = new JSONObject((response.body().string()));

            //  String status = (jobj.getString("message"));

            JSONObject object1 = jobj.getJSONObject("data");
            JSONArray jsonArray = object1.getJSONArray("data");

            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject jsonObject = jsonArray.getJSONObject(i);
                int id = jsonObject.getInt("id");
                JSONObject bookWanted = jsonObject.getJSONObject("book_wanted");
                JSONObject bookOffered = jsonObject.getJSONObject("book_offered");
                JSONObject request = jsonObject.getJSONObject("requested_to");
                JSONObject bookWantedJSONObject = bookWanted.getJSONObject("data");
                JSONObject bookOfferedJSONObject = bookOffered.getJSONObject("data");
                JSONObject requestJSONObject = request.getJSONObject("data");
                String email= (requestJSONObject.getString("email"));
                String senderName = requestJSONObject.getString("name");
                String requestedBook = bookWantedJSONObject.getString("name");
                String proposedBook = bookOfferedJSONObject.getString("name");
                String bookImg = bookOfferedJSONObject.getString("image");
                String time = jsonObject.getString("created_at");
                String status = jsonObject.getString("status");
                notifications.add(new Notification(id, senderName, requestedBook, proposedBook, time, bookImg, status,email));
            }

        } catch (Exception ex) {
            ex.printStackTrace();

        }
        Call<ResponseBody> call2 = exchangeAPI.requestedTo(User.id);
        StrictModeClass.StrictMode();
        try {
            Response<ResponseBody> response = call2.execute();
            JSONObject jobj = new JSONObject((response.body().string()));


            JSONObject object1 = jobj.getJSONObject("data");
            JSONArray jsonArray = object1.getJSONArray("data");

            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject jsonObject = jsonArray.getJSONObject(i);
                int id = jsonObject.getInt("id");
                String status = jsonObject.getString("status");
                JSONObject bookWanted = jsonObject.getJSONObject("book_wanted");
                JSONObject bookOffered = jsonObject.getJSONObject("book_offered");
                JSONObject request = jsonObject.getJSONObject("requested_by");
                JSONObject bookWantedJSONObject = bookWanted.getJSONObject("data");
                JSONObject bookOfferedJSONObject = bookOffered.getJSONObject("data");
                JSONObject requestJSONObject = request.getJSONObject("data");
                String email= (requestJSONObject.getString("email"));
                String senderName = requestJSONObject.getString("name");
                String requestedBook = bookWantedJSONObject.getString("name");
                String proposedBook = bookOfferedJSONObject.getString("name");
                String bookImg = bookOfferedJSONObject.getString("image");
                String time = jsonObject.getString("created_at");
                if (status.equals("accepted")) {
                    notifications.add(new Notification(id, senderName, requestedBook, proposedBook, time, bookImg, status,email));
                }
            }
        } catch (Exception ex) {
            ex.printStackTrace();

        }
        return notifications;
    }
    //after accpeting exchange request
    public boolean accept(Integer id)
    {
        Call<ResponseBody> call = exchangeAPI.accept( id,"accepted");

        StrictModeClass.StrictMode();
        try {
            Response<ResponseBody> response = call.execute();
            final Response<ResponseBody> finalResponse = response;


            if (response.code() == 200) {

                isSuccess = true;
            } else {
                isSuccess = false;
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

        return isSuccess;
    }
    public boolean confirm (Integer id)
    {
        Call<ResponseBody> call = exchangeAPI.confirm( id);

        StrictModeClass.StrictMode();
        try {
            Response<ResponseBody> response = call.execute();
            final Response<ResponseBody> finalResponse = response;


            if (response.code() == 200) {

                isSuccess = true;
            } else {
                isSuccess = false;
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

        return isSuccess;
    }
}
