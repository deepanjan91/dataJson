package com.app.datajson;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class GlobalDataController extends AsyncTask<Void, Void, Void> {

    private String data = "";
    private String url;
    private GlobalDataModel model = new GlobalDataModel();
    private String appLabel = "";


    public GlobalDataController(String url) {
        this.url = url;
    }

    @Override
    protected Void doInBackground(Void... voids) {
        try {
            URL url = new URL(this.url);

            HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
            InputStream inputStream = httpURLConnection.getInputStream();
            BufferedReader bufferedReader =  new BufferedReader(new InputStreamReader(inputStream));
            String line = "";

            while(line != null) {
                line = bufferedReader.readLine();
                data += line;
            }

            JSONObject jsonObject = new JSONObject(data);
            appLabel = jsonObject.get("title").toString();
            JSONArray jsonArray = jsonObject.getJSONArray("rows");

            for (int i = 0; i < jsonArray.length() ; i++) {
                JSONObject jo = jsonArray.getJSONObject(i);

                model.setTitle(jo.get("title").toString());
                model.setDescription(jo.get("description").toString());
                model.setUrl(jo.getString("imageHref"));
            }
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }catch (JSONException e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    protected void onPostExecute(Void aVoid) {
        super.onPostExecute(aVoid);
        MainActivity.get().setAdapter();

    }

    public String getAppLabel() {
        return appLabel;
    }

    public GlobalDataModel getModel() {
        return model;
    }

    public void destroy(){
        model = null;
    }
}
