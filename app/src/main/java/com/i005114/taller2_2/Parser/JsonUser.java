package com.i005114.taller2_2.Parser;

import com.i005114.taller2_2.Models.Modeluser;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by CHRISTIAN on 14/10/2017.
 */

public class JsonUser {

    public static List<Modeluser> getData(String content) throws JSONException {
        JSONArray jsonArray = new JSONArray(content);
        List<Modeluser> lista = new ArrayList<>();
        for (int i = 0; i < jsonArray.length(); i++){
            Modeluser modeluser = new Modeluser();
            JSONObject item = jsonArray.getJSONObject(i);
            JSONObject item2 =item.getJSONObject("address");
            JSONObject item3 =item.getJSONObject("company");


            modeluser.setId(item.getString("id"));
            modeluser.setNombre(item.getString("name"));
            modeluser.setNombreusuario(item.getString("username"));
            modeluser.setDireccion(item2.getString("street"));
            modeluser.setCompania(item3.getString("name"));


          lista.add(modeluser);
        }
        return lista;
    }
}
