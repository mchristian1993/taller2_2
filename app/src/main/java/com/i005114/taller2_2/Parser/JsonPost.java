

package com.i005114.taller2_2.Parser;

import com.i005114.taller2_2.Models.ModelPosts;
import com.i005114.taller2_2.Models.Modeluser;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by CHRISTIAN on 14/10/2017.
 */

public class JsonPost {

    public static List<ModelPosts> getData(String content) throws JSONException {
        JSONArray jsonArray = new JSONArray(content);
        List<ModelPosts> lista = new ArrayList<>();
        for (int i = 0; i < jsonArray.length(); i++) {
            ModelPosts modelPosts = new ModelPosts();
            JSONObject item = jsonArray.getJSONObject(i);

            modelPosts.setId(item.getString("id"));
            modelPosts.setIdusuario(item.getString("userId"));
            modelPosts.setTitulo(item.getString("title"));
            modelPosts.setContenido(item.getString("body"));


            lista.add(modelPosts);
        }
        return lista;
    }
}
