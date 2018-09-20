package com.wm.remusic.http;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.URLEncodedUtils;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.List;

public class HttpUtils {
    // static final String URL =
    // "http://10.1.1.186:8080/MyServer_andrioid/login.do";
    public static final String URL = "http://localhost:8080/home/zqr/IdeaProjects/musiconlineshop";
    public static final String GETURL = "http://192.168.191.1:8080/MyServer_andrioid/main.do";
    public static final String ENCODING = "UTF-8";
    public static String TAG = "Server responce:";
    public static String result = "it's a test.";

    public static HttpClient httpClient;
    public static HttpPost httpPost;
    public HttpGet httpGet;

    public HttpUtils() {
        httpClient = new DefaultHttpClient();
        httpPost = new HttpPost(URL);
    }

    public static String SendPostMethod(List<BasicNameValuePair> params) {
        try {
            UrlEncodedFormEntity encodedFormEntity = new UrlEncodedFormEntity(params, ENCODING);
            httpPost.setEntity(encodedFormEntity);
            HttpResponse httpResponse = httpClient.execute(httpPost);
            HttpEntity entity = httpResponse.getEntity();

            int resCode = httpResponse.getStatusLine().getStatusCode();
            if (resCode == 200) {
                result = (String) EntityUtils.toString(entity, ENCODING);
            }
            httpClient.getConnectionManager().shutdown();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return result;
    }

    public String SendGetMethod(List<BasicNameValuePair> params) {
        String param = URLEncodedUtils.format(params, ENCODING);
        httpGet = new HttpGet(GETURL + "?" + param);
        try {
            HttpResponse httpResponse = httpClient.execute(httpGet);
            int resCode = httpResponse.getStatusLine().getStatusCode();
            if (resCode == 200) {
                result = EntityUtils.toString(httpResponse.getEntity());
            }
        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
        }

        return result;
    }
}
