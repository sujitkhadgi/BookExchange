package com.example.bookexchange1.BLL;

import android.widget.Toast;

import com.example.bookexchange1.Model.User;

import com.example.bookexchange1.UI.LoginActivity;


import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import StrictMode.StrictModeClass;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Response;

import static com.example.bookexchange1.URL.URL.userAPI;

public class UserBLL {
    boolean isSuccess;
    User user ;
    public boolean signUp(String email,String fullName,String password,String mobile,String address, MultipartBody.Part image) {
        RequestBody reqName=RequestBody.create(MediaType.parse("text/plain"), fullName);
        RequestBody reqEmail=RequestBody.create(MediaType.parse("text/plain"), email);
        RequestBody  reqPassword=RequestBody.create(MediaType.parse("text/plain"), password);
        RequestBody  reqMobile=RequestBody.create(MediaType.parse("text/plain"), mobile);
        RequestBody  reqAddress=RequestBody.create(MediaType.parse("text/plain"), address);

        Call<ResponseBody> call = userAPI.signUp(reqEmail,reqName,reqPassword,reqMobile,reqAddress,image);
        StrictModeClass.StrictMode();
        try {
            Response<ResponseBody> response=call.execute();

            if(response.code()==200)
            {
                isSuccess= true;
            }
            else
            {
                isSuccess= false;
            }

        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return isSuccess;
    }

    public boolean login (String email, String password)
    {

        final Call<ResponseBody>call=userAPI.login(email,password);

      StrictModeClass.StrictMode();
                try {
                    Response<ResponseBody>   response = call.execute();

                  if(response.code()==200)
                  {
                      JSONObject jobj = new JSONObject((response.body().string()));
                      User.id = (jobj.getInt("id"));
                      isSuccess=true;
                  }
                  else {isSuccess=false;}

                } catch (IOException | JSONException e) {
                    e.printStackTrace();
                }



       return isSuccess;
    }

    public User profile(String email)
    {
        Call<ResponseBody>call=userAPI.getProfile(email);
        StrictModeClass.StrictMode();
        try{
            Response<ResponseBody> response =call.execute();
            JSONObject jobj = new JSONObject((response.body().string()));
            JSONObject object = jobj.getJSONObject("data");
            JSONArray jsonArray =object.getJSONArray("data");
            JSONObject jsonObject=jsonArray.getJSONObject(0);
          String fullName=jsonObject.getString("name");
             String address=jsonObject.getString("address");
            String Email=jsonObject.getString("email");
            String phone=jsonObject.getString("phone");
            String image =jsonObject.getString("avatar");
            user=new User(Email,fullName,phone,address,image);

    } catch (JSONException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return user;
    }

    //update without image
    public boolean update(int id, String name, String email, String address, String phone)
    {
        RequestBody reqName=RequestBody.create(MediaType.parse("text/plain"), name);
        RequestBody reqEmail=RequestBody.create(MediaType.parse("text/plain"), email);

        RequestBody  reqMobile=RequestBody.create(MediaType.parse("text/plain"), phone);
        RequestBody  reqAddress=RequestBody.create(MediaType.parse("text/plain"), address);

        Call<ResponseBody> call=userAPI.update(id,name,email,address,phone);
        StrictModeClass.StrictMode();
        try {
            Response<ResponseBody> response = call.execute();
            if (response.code() == 200) {
                isSuccess=true;
            }
            else {isSuccess=false;}
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        return isSuccess;
    }
}
