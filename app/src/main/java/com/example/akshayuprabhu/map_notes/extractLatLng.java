package com.example.akshayuprabhu.map_notes;
import android.util.Log;

import java.util.*;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.JSONArray;

import static com.google.android.gms.wearable.DataMap.TAG;

/**
 * Created by akshayuprabhu on 6/12/16.
 */

public class extractLatLng extends Thread{

    String place;
    double latitude;
    double longitude;

    //public String getLat(){
       // return this.latitude;
    //}
    //public String getLon(){
      //  return this.longitude;
    //}


//    public static ArrayList<String> extractLatandLng(String place){
//        URL url;
//        HttpURLConnection connection = null;
//        InputStream is = null;
//        JSONParser parser = new JSONParser();
//        String placeName=place.replaceAll(" ","+");
//        ArrayList<String> arr=new ArrayList<String>();
//        try
//        {
//            url = new URL("https://maps.googleapis.com/maps/api/geocode/json?address="+placeName+"&key=AIzaSyBiISQ-hgIi1AL07xCc_zIm9Za4NJEGrHE");
//            String u="https://maps.googleapis.com/maps/api/geocode/json?address="+placeName+"&key=AIzaSyBiISQ-hgIi1AL07xCc_zIm9Za4NJEGrHE";
//            connection = (HttpURLConnection) url.openConnection();
//            connection.setRequestMethod("GET");
//            connection.connect();
//            is = connection.getInputStream();
//            BufferedReader theReader = new BufferedReader(new InputStreamReader(is, "UTF-8"));
//            String reply;
//            StringBuffer buf=new StringBuffer();
//            while ((reply = theReader.readLine()) != null)
//            {
////                           System.out.println(reply);
//                buf.append(reply);
//
//            }
//
////                    System.out.println(buf.toString());
//            String line=buf.toString();
//            Object obj = parser.parse(line);
//            JSONObject jsonObject = (JSONObject) obj;
//
//
//            if( !(jsonObject.get("status").equals("OK"))){
//                arr.add("status not ok");
//                arr.add(u);
//                //System.out.println(" location not found or api not working \n");
//            }else{
//                JSONArray res=(JSONArray)jsonObject.get("results");
//                JSONObject obj1 =(JSONObject)res.get(0);
//                //System.out.println(obj1.get("formatted_address"));
//                JSONObject geometry =(JSONObject)obj1.get("geometry");
//                JSONObject loc =(JSONObject)geometry.get("location");
//                //System.out.println("lat: " + loc.get("lat") +", lng: " + loc.get("lng"));
//                arr.add(loc.get("lat").toString());
//                arr.add(loc.get("lng").toString());
//                return (arr);
//            }
//
//        }
//        catch (Exception e) {
//            arr.add("inside catch block");
//            Log.e(TAG, "extractLatandLng: inside catch",e );
//            e.printStackTrace();
//        }
//
//        arr.add("outside try");
//        arr.add("outside catch");
//        return arr;
//    }

    @Override
    public void run() {
        URL url;
        HttpURLConnection connection = null;
        InputStream is = null;
        JSONParser parser = new JSONParser();
        //String placeName=place.replaceAll(" ","+");
        //ArrayList<String> arr=new ArrayList<String>();
        String placeName=(this.place).replaceAll(" ","+");
        try
        {
            url = new URL("https://maps.googleapis.com/maps/api/geocode/json?address="+placeName+"&key="ENTER YOUR API KEY HERE"");
            String u="https://maps.googleapis.com/maps/api/geocode/json?address="+placeName+"&key="ENTER YOUR API KEY HERE"";
            connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            connection.connect();
            is = connection.getInputStream();
            BufferedReader theReader = new BufferedReader(new InputStreamReader(is, "UTF-8"));
            String reply;
            StringBuffer buf=new StringBuffer();
            while ((reply = theReader.readLine()) != null)
            {
//                           System.out.println(reply);
                buf.append(reply);

            }

//                    System.out.println(buf.toString());
            String line=buf.toString();
            Log.v("buffer contents ", line);

            Object obj = parser.parse(line);
            JSONObject jsonObject = (JSONObject) obj;


            if( !(jsonObject.get("status").equals("OK"))){
//                this.latitude="status not ok";
//                this.longitude="status not ok";

                //arr.add("status not ok");
                //arr.add(u);
                //System.out.println(" location not found or api not working \n");
            }else{
                JSONArray res=(JSONArray)jsonObject.get("results");
                JSONObject obj1 =(JSONObject)res.get(0);
                //System.out.println(obj1.get("formatted_address"));
                JSONObject geometry =(JSONObject)obj1.get("geometry");
                JSONObject loc =(JSONObject)geometry.get("location");
                //System.out.println("lat: " + loc.get("lat") +", lng: " + loc.get("lng"));
                this.latitude = (double)loc.get("lat");
                this.longitude = (double)  loc.get("lng");

//                Log.v("lat : ", latitude);
//                Log.v("lon : ", longitude);


//                arr.add(loc.get("lng").toString());
                //return (arr);
            }

        }
        catch (Exception e) {
            //arr.add("inside catch block");
            Log.e(TAG, "extractLatandLng: inside catch",e );
            e.printStackTrace();
        }


        //arr.add("outside try");
        //arr.add("outside catch");
        //return arr;
    }
}
