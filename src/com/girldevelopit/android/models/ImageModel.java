package com.girldevelopit.android.models;

import java.io.Serializable;
//our model class which defines what an "image" is in the context of our application
//it extends serializable so we can save it using the method in DataStore
public class ImageModel implements Serializable{

    //setting up the private variables that we will use in the method below
    private String title;
    private String username;
    private String description;
    private long dateCreated;
    private String pathToImage;

    //our "constructor" method that builds an image object without any initial data
    public ImageModel(){}

    //our "constructor" method that builds an image object with initial data
    public ImageModel(String title, String username, String description, String pathToImage, long dateCreated){

        //set the values of our image using the setter methods below
        setTitle(title);
        setUsername(username);
        setDescription(description);
        setPathToImage(pathToImage);
        setDateCreated(dateCreated);
    }
    //all the following are "setter" and "getter" functions for the values we want to know about
    //our image.In Java, "setter" methods usually begin with the word set and end with the name of
    //whatever data you are saving. They are void methods, which means they perform an action but don't return data
    //"Getter" methods usually begin with the word"get" and end with the name of the data you want returned.
    // They are return functions and return whatever kind of data you saved originally, a String, int, double, long, any object really!
    //for all getter and setter methods, you "set" data you want to save and "get" data that you saved previously
    public void setTitle(String t){
        title = t;
    }
    public String getTitle(){
        return title;
    }
    public void setUsername(String un){
        username = un;
    }
    public String getUsername(){
        return username;
    }
    public void setDescription(String d){
        description =  d;
    }
    public String getDescription(){
        return description;
    }
    public void setPathToImage(String path){
        pathToImage = path;
    }
    public String getPathToImage(){
        return pathToImage;
    }
    public void setDateCreated(long date){
        dateCreated = date;
    }
    public long getDateCreated(){
        return dateCreated;
    }
}
