package net.example.androidphpmysql;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;
import android.widget.EditText;
import android.widget.Button;

import java.util.HashMap;
import java.util.Map;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import com.android.volley.AuthFailureError;

import org.json.JSONException;
import org.json.JSONObject;


public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    private EditText username1,email1,password1;
    private Button register1;
    private ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        username1=(EditText) findViewById(R.id.username5);
        email1=(EditText) findViewById(R.id.email5);
        password1=(EditText) findViewById(R.id.password5);

        register1=(Button) findViewById(R.id.register5);

        progressDialog=new ProgressDialog(this);

        register1.setOnClickListener(this);
    }

    private void registeruser()
    {
     final String username2=username1.getText().toString().trim();
     final String email2=email1.getText().toString().trim();
     final String password2=password1.getText().toString().trim();

      progressDialog.setMessage("Registering user...");
      progressDialog.show();

        StringRequest stringRequest=new StringRequest(Request.Method.POST, constants.URL_REGISTER,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response)
                    {
                      progressDialog.dismiss();
                      try {
                          JSONObject jsonObject=new JSONObject(response);
                          Toast.makeText(getApplicationContext(),jsonObject.getString("message"),Toast.LENGTH_LONG).show();
                          }catch (JSONException e)
                      {
                          e.printStackTrace();
                      }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error)
                    {
                       progressDialog.hide();
                       Toast.makeText(getApplicationContext(),error.getMessage(),Toast.LENGTH_LONG).show();
                    }
                }){

            @Override
            protected Map<String, String> getPostParams() throws AuthFailureError {
                Map<String,String> params = new HashMap<String, String>();
                params.put("username",username2);
                params.put("password",password2);
                params.put("email",email2);
                return params;
            }
        };

        RequestQueue requestQueue= Volley.newRequestQueue(MainActivity.this);
        requestQueue.add(stringRequest);

    }

    @Override
    public void onClick(View view)
    {
       if (view==register1)
           registeruser();
    }
}
