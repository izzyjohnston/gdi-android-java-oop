package com.girldevelopit.android.views;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import com.girldevelopit.android.GirlDevelopIt;
import com.girldevelopit.android.R;

//this is the activity that a user will first see when they open the application
//if the user has entered in a username, the will see a welcome message and a tiny button to logout
//if the user has not entered in a username, they will see a prompt to add one
//there will be two buttons. One to take images, the other to see the gallery of images
public class WelcomeActivity extends Activity
{
    private GirlDevelopIt app;
    //there are three elements in our layout that we will want to get data from, we declare them here and initialize them below
    private EditText usernameField;
    private Button login;
    private Button logout;
    private TextView welcomeText;

    /** Called when the activity is first created. 
     The code in here is what the phone goes through first
     The @override is there because onCreate is a function in the Activity class we extended
     we override the default functionality of that method. The default functionali is just to
     create an activity that the user can see. we want to do that AND make that activity look
     and act like the one we are trying to build. Every single activity in every single android
     application has this function
     */
    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.welcome);
        this.app = (GirlDevelopIt)getApplicationContext();
        initElements();
    }
    /*
    	This function gives values to all the elements in our layout 
    	It also decides whether or not to show the log in text box ir the logout button
    	We will run it three times. 1. when the activity is first created. 2. When someone logs in. 3. when someone logs out.
    
    */
    private void initElements(){
        //set the value of our layout elementsto match the ids we gave them on the layout
        usernameField = (EditText)this.findViewById(R.id.usernameField);
        login = (Button) this.findViewById(R.id.login);
        logout = (Button) this.findViewById(R.id.logout);
        welcomeText = (TextView) this.findViewById(R.id.welcomeText);
        //there are two cases to check fpr no username
        //a null value which means no data at all
        //an empty string, so there is data but to a person it looks like nothing
        if (app.getUsername() == null || app.getUsername().equals("")){
            //if there is no username, show the edittext, the save username button, change the welcome text to something thoat will let a user know why they should log in, and hids the logout button
            usernameField.setVisibility(View.VISIBLE);
            login.setVisibility(View.VISIBLE);
            welcomeText.setText("Please login to add images to the gallery");
            logout.setVisibility(View.GONE);
        }
        else{
            //else the user is logged in. In that case hide the editext and login button, set the welcome text to be the user's username and show the logout button
            usernameField.setVisibility(View.GONE);
            login.setVisibility(View.GONE);
            welcomeText.setText("Welcome back, "+ app.getUsername()+"!");
            logout.setVisibility(View.VISIBLE);
        }
    }
    /*
         this function is what is called when the user hits the login button. it first checks if the user out anything into the esittext. if not, gives an alert, if so, it saves the user name snd reloads the page.
     */
    public void login(View view){
        //if to get whatever the user put into the text box, you will use getText()
        //however, getText() does not return a string, but instead it returns raw data. think 0s and 1s
        //to turn that data int a string, you will use .toString()
        //then, see if that string is an empty string .equals("");
        if(usernameField.getText().toString().equals("")){
            //if there is no username entered, build an alert with a message prompting the usern to enter something into the field. set the NegativeButton to have an onclick listener that closes the alert
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setMessage("Please enter your username")
                    .setCancelable(false)
                    .setNegativeButton("OK", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            dialog.cancel();
                        }
                    });
            AlertDialog alert = builder.create();
            alert.show();
        }
        else{
            //if there was data in the text box, save the username in the textbox and reload the elements on the screen by using initElements()
            app.setUsername(usernameField.getText().toString());
            usernameField.setText("");
            int i = usernameField.getText().toString().length();
            initElements();
        }
    }
    /*
    	This function is called when the user presses the logout button. it sets the username to be an empty string and reloads the elements on the page using initElements();
    */

    public void logout(View view){
        app.setUsername("");
        initElements();
    }
    /*
    	this function is called when the user presses the add a takepicture button. it creates an intent which will have WelcomeActivity.this as the "from" parameter and TakePictureActivity.class as the "to" parameter
    */

    public void openPictureActivity(View view){

    }

    /*
        this function is called when the user presses the go to gallery button. it creates an intent which will have WelcomeActivity.this as the "from" parameter and GalleryActivity.class as the "to" parameter
    */

    public void openGalleryActivity(View view){
    }

}
