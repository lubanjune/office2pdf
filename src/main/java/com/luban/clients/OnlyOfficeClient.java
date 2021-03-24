package com.luban.clients;

import com.alibaba.fastjson.JSONObject;
import com.google.gson.JsonObject;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class OnlyOfficeClient {
    private static final Logger log = LoggerFactory.getLogger(OnlyOfficeClient.class);
    private String url;

    public OnlyOfficeClient() {
    }

    public OnlyOfficeClient(String url) {
        this.url = url;
        if (!this.url.endsWith("/")) {
            this.url = this.url + "/";
        }
    }

    public String sendPOST(String fileUrl, String targetSuffix) {
        try {
            HttpPost post = new HttpPost(this.url);

            String s = String.valueOf(System.currentTimeMillis());
            String suffix = fileUrl.substring(fileUrl.lastIndexOf(".") + 1).toLowerCase();
            JsonObject json = new JsonObject();
            json.addProperty("async", false);
            json.addProperty("filetype", suffix);
            json.addProperty("key", s);
            json.addProperty("outputtype", targetSuffix);
            json.addProperty("title", s + "." + suffix);
            json.addProperty("url", fileUrl);

            post.setEntity(new StringEntity(json.toString()));
            post.setHeader("Content-Type", "application/json");
            post.setHeader("Accept", "application/json");

            CloseableHttpClient httpClient = HttpClients.createDefault();
            CloseableHttpResponse response = httpClient.execute(post);
            JSONObject jsonObject = JSONObject.parseObject(EntityUtils.toString(response.getEntity()));
            return jsonObject.getString("fileUrl");
        } catch (Exception e) {
            log.info("发送http请求发生异常<{}>", e.getMessage());
            return null;
        }

    }

}
