package com.example.demo.utils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.example.demo.entities.CommonResult;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.codec.Charsets;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.Map;

/**
 * @author sebastian
 * @date 2020/5/10 21:04
 */
@Slf4j
@Service
public class HttpRequest {

    public CommonResult<JSON> httpGet(JSONObject parameters, String url) throws URISyntaxException {
        CommonResult<JSON> res = new CommonResult<>();

        HttpClient client = HttpClientBuilder.create().build();
        URIBuilder uriBuilder = new URIBuilder(url);
        if (parameters != null) {
            for (Map.Entry<String, Object> entry : parameters.entrySet()) {
                uriBuilder.addParameter(entry.getKey(), entry.getValue().toString());
            }
        }
        HttpGet httpGet = new HttpGet(uriBuilder.build());
        HttpResponse response = null;
        try {
            response = client.execute(httpGet);
        } catch (IOException e) {
            log.info("====请求异常~");
            e.printStackTrace();
            res.fail("请求异常~");
        }

        assert response != null;
        if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
            try {
                JSONObject object = (JSONObject) JSON.parse(EntityUtils.toString(response.getEntity(), Charsets.UTF_8));
                res.success(object);
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            res.fail("转换异常~");
        }
        return res;
    }
}
