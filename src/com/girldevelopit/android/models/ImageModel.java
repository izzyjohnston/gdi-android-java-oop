package com.girldevelopit.android.models;

import java.io.Serializable;

/**
 * Created with IntelliJ IDEA.
 * User: izzyjohnston
 * Date: 7/6/12
 * Time: 6:29 PM
 * To change this template use File | Settings | File Templates.
 */
public class ImageModel implements Serializable{
    private String title;
    private String username;
    private String description;
    private long dateCreated;
    private String pathToImage;

    public ImageModel(){}

    public ImageModel(String title, String username, String description, String pathToImage, long dateCreated){
        setTitle(title);
        setUsername(username);
        setDescription(description);
        setPathToImage(pathToImage);
        setDateCreated(dateCreated);
    }

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
