package com.udacity.sandwichclub.utils;

import com.udacity.sandwichclub.model.Sandwich;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class JsonUtils {

    public static Sandwich parseSandwichJson(String json) {
        try {
            JSONObject jsonObject=new JSONObject(json);
            //Using jsonObject we can get the jsonObject with key name
          JSONObject rootobject =  jsonObject.getJSONObject("name");
          //Inside above mentioned rootobject we will get the String value  with mainname
               String namejson = rootobject.getString("mainName");
               //using JsonObject we will get JsonArray and save that in alsoknownArray
             JSONArray alsoknownArray=  rootobject.getJSONArray("alsoKnownAs");
             //since it is an array we will save that in arraylist
            //and using for loop we will check the values of alsoKnownAs array at different positions from 0
            ArrayList<String> alsoKnownList=new ArrayList<>();
             for (int i=0;i<alsoknownArray.length();i++){
                         alsoKnownList.add(alsoknownArray.getString(i));
             }
             //using the jsonObject we will get the value of placeOfOrigin and description
           String place=  jsonObject.getString("placeOfOrigin");
           String description= jsonObject.getString("description");
           //will get the image and save that in string and then that url will be loaded using Picasso library
          String image= jsonObject.getString("image");
        JSONArray ingredients=  jsonObject.getJSONArray("ingredients");
           ArrayList<String> ingredientsList=new ArrayList<>();

        for (int i=0;i<ingredients.length();i++){
            ingredientsList.add(ingredients.getString(i));
        }
      //return Sandwich object and it will save the fields and now we use use getter methods to get the data saved in these fields
        return new Sandwich(namejson,alsoKnownList,place,description,image,ingredientsList);
        } catch (JSONException e) {
            e.printStackTrace();
            return null;
        }

    }

}
