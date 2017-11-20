package com.example.hp.SneakAlert;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;

import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageLoader;
import com.example.shweta.sneakalert.CustomVolleyRequest;
import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.auth.api.signin.GoogleSignInResult;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.Scopes;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.Scope;

import java.io.IOException;

import static com.example.hp.SneakAlert.Navigation_Bar.profile_image;
import static com.example.hp.SneakAlert.Navigation_Bar.profile_name;

public class SignIn extends AppCompatActivity implements View.OnClickListener, GoogleApiClient.OnConnectionFailedListener {

    private SignInButton signInButton;

    static GoogleSignInAccount acct;
    private GoogleSignInOptions gso;

    private GoogleApiClient mGoogleApiClient;

    private int RC_SIGN_IN = 100;

    static ImageLoader imageLoader;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);

        gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .requestScopes(new Scope(Scopes.FITNESS_ACTIVITY_READ_WRITE), new Scope(Scopes.FITNESS_LOCATION_READ))
                .build();

        signInButton = (SignInButton) findViewById(R.id.sign_in_button);
        signInButton.setSize(SignInButton.SIZE_WIDE);
        signInButton.setScopes(gso.getScopeArray());


        //Initializing google api client
        mGoogleApiClient = new GoogleApiClient.Builder(this)
                .enableAutoManage(this /* FragmentActivity */,0, this /* OnConnectionFailedListener */)
                .addApi(Auth.GOOGLE_SIGN_IN_API, gso)
                .build();


        //Setting onclick listener to signing button
        signInButton.setOnClickListener(this);


    }


    //This function will option signing intent
    private void signIn() {
        //Creating an intent
        Intent signInIntent = Auth.GoogleSignInApi.getSignInIntent(mGoogleApiClient);

        //Starting intent for result
        startActivityForResult(signInIntent, RC_SIGN_IN);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(resultCode != RESULT_CANCELED && data != null) {
            //If signin
            if (requestCode == RC_SIGN_IN) {

                GoogleSignInResult result = Auth.GoogleSignInApi.getSignInResultFromIntent(data);
                int statusCode = result.getStatus().getStatusCode();
                //Calling a new function to handle signin
                try {
                    handleSignInResult(result);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }


    //After the signing we are calling this function
    private void handleSignInResult(GoogleSignInResult result) throws IOException {
        //If the login succeed
        if (result.isSuccess()) {
            //Getting google account
            acct = result.getSignInAccount();

            imageLoader = CustomVolleyRequest.getInstance(this.getApplicationContext()).getImageLoader();
            imageLoader.get(acct.getPhotoUrl().toString(),
                    new ImageLoader.ImageListener() {

                        @Override
                        public void onErrorResponse(VolleyError error) {

                        }

                        @Override
                        public void onResponse(ImageLoader.ImageContainer response, boolean arg1) {
                            if (response.getBitmap() != null) {
                                // load image into imageview
                                profile_image.setImageBitmap(response.getBitmap());
                                profile_name.setText(acct.getDisplayName());
                            }
                        }
                    });
            Intent i = new Intent(getApplicationContext(),HomeActivity.class);
            startActivity(i);

        } else {
            //If login fails
            Toast.makeText(this, "Login Failed", Toast.LENGTH_LONG).show();
        }
    }

    @Override
    public void onClick(View v) {
        if (v == signInButton) {
            //Calling signin
            signIn();
        }
    }

    @Override
    public void onConnectionFailed(ConnectionResult connectionResult) {

    }
}
