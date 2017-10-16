package com.i005114.taller2_2.Parser;

import com.i005114.taller2_2.Models.ModelComment;
import com.i005114.taller2_2.Models.ModelPosts;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by CHRISTIAN on 14/10/2017.
 */

public class Jsoncomment {

    public static List<ModelComment> getData(String content) throws JSONException {
        JSONArray jsonArray = new JSONArray(content);
        List<ModelComment> lista = new ArrayList<>();
        for (int i = 0; i < jsonArray.length(); i++) {
            ModelComment modelComment = new ModelComment();
            JSONObject item = jsonArray.getJSONObject(i);

            modelComment.setId(item.getString("id"));
            modelComment.setIdpost(item.getString("postId"));
            modelComment.setEmail(item.getString("email"));
            modelComment.setContenido(item.getString("body"));


            lista.add(modelComment);
        }
        return lista;
    }
}