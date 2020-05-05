package com.defa.slack.util;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.http.HttpStatus;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public final class HttpHelper {

    public static String doPostSlackJson(String url, String token, Map<String, Object> data) throws IOException {
        Map<String, String> header = new HashMap<>();
        header.put("Authorization", "Bearer " + token);
        return doPostJson(url, header, data);
    }

    public static String doPostSimpleForm(String url, Map<String, Object> data) throws IOException {
        return doPostForm(url, null, data);
    }

    public static String doPostForm(String url, Map<String, String> head, Map<String, Object> data) throws IOException {
        Map<String, String> header = new HashMap<>();
        if( null!=head  && head.size()>0) header.putAll(head);
        header.put("content-type", "application/x-www-form-urlencoded; charset=utf-8");
        CloseableHttpClient httpClient = HttpClients.createDefault();
        HttpPost httpPost = new HttpPost(url);
        header.forEach(httpPost::addHeader);
        List<NameValuePair> params = new ArrayList<>();
        data.forEach((key, value)-> params.add(new BasicNameValuePair(key, String.valueOf(data))));
        httpPost.setEntity(new UrlEncodedFormEntity(params,"UTF-8"));
        CloseableHttpResponse response = httpClient.execute(httpPost);
        return handleResponse(response);
    }

    public static String doPostJson(String url, Map<String, String> head, Map<String, Object> data) throws IOException {
        Map<String, String> header = new HashMap<>();
        if(null != head && head.size() >= 1) header.putAll(head);
        header.put("content-type", "application/json; charset=utf-8");
        CloseableHttpClient httpClient = HttpClients.createDefault();
        HttpPost httpPost = new HttpPost(url);
        header.forEach(httpPost::addHeader);
        String plain = (new ObjectMapper()).writeValueAsString(data);
        httpPost.setEntity(new StringEntity(plain,"UTF-8"));
        CloseableHttpResponse response = httpClient.execute(httpPost);
        return handleResponse(response);
    }


    public static String doGet(String url, Map<String, Object> data) throws IOException {
        return doGet(url + "?" + mapToUrlParam(data));
    }

    public static String doGet(String url) throws IOException {
        CloseableHttpClient httpClient = HttpClients.createDefault();
        HttpGet httpGet = new HttpGet(url);
        httpGet.addHeader("Content-type", "application/x-www-form-urlencoded");
        CloseableHttpResponse response = httpClient.execute(httpGet);
        return handleResponse(response);
    }

    private static String handleResponse(CloseableHttpResponse response) throws IOException {
        String result = "";
        if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
            result = EntityUtils.toString(response.getEntity(), StandardCharsets.UTF_8);
        }
        response.close();
        return result;
    }

    private static String mapToUrlParam(Map<String, Object> data){
        if(null == data) return "";
        StringBuilder params = new StringBuilder();
        data.forEach((key, value)->{
            params.append(params.length() > 0 ? "&":"");
            params.append(key);
            params.append("=");
            params.append(null==value ? "":value);
        });
        return params.toString();
    }
}
