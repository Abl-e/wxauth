package util;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import java.io.IOException;

/**
 * Created by 唐国翔 on 2017/7/20 0020.
 */
public class AuthUtil {

    public static final String APPID = "wx9e6052614f95005b";
    public static final String APPSECRET = "32c2dadebea8f5dd3eab7b0ee90fb50e";

    public static JSONObject doGetJson(String url) throws IOException {
        JSONObject json = null;
        CloseableHttpClient client = HttpClients.createDefault();//创建连接对象
        HttpGet httpGet = new HttpGet(url);//get请求
        HttpResponse response = client.execute(httpGet);//发送get请求 获取返回值
        HttpEntity entity = response.getEntity();
        if(entity!=null){
            String result = EntityUtils.toString(entity,"UTF-8");
            json = JSONObject.parseObject(result);
        }
        httpGet.releaseConnection();//释放连接
        return json;
    }
}
