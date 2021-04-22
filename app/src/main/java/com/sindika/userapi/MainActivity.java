package com.sindika.userapi;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import java.util.List;

import model.User;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import service.ApiClient;
import service.GetService;

public class MainActivity extends AppCompatActivity {

    private TextView tvData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tvData = findViewById(R.id.tvData);

        GetService service = ApiClient.getRetrofitInstance().create(GetService.class);
        Call<List<User>> call = service.getUserlist();
        call.enqueue(new Callback<List<User>>(){
            @Override
            public void onResponse(Call<List<User>> call, Response<List<User>> response){
                if (response.isSuccessful()){

                    List<User> users = response.body();
                    for (User data : users){
                        String isidata = "\n";
                        isidata += "Id : "+ data.getId()+ "\n";
                        isidata += "Username : "+data.getUsername()+ "\n";
                        isidata += "Name : "+ data.getName()+ "\n";
                        isidata += "Email : "+ data.getEmail()+ "\n";
                        tvData.append(isidata);
                    }
                    return;
                }
            }
            @Override
            public void onFailure (Call<List<User>> call, Throwable t){
                tvData.setText(t.getMessage());
            }
        });
    }
}