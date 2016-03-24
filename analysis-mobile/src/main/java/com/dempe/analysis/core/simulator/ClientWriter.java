package com.dempe.analysis.core.simulator;

import com.dempe.analysis.core.Constants;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * @author : Dempe
 * @version 1.0 date : 2014/11/24
 */
public class ClientWriter implements Runnable {

    static DefaultHttpClient httpclient = HttpConnectionManager.getHttpClient();

    public final static Random rand = new Random();

    public void run() {
        HttpResponse httpresponse = null;
        HttpPost httpPost = null;
        try {
            httpPost = new HttpPost(MonitorProp.QUEUE_URL);
            List<NameValuePair> params = new ArrayList<NameValuePair>();
            String m = RandomClientMsgProducer.getmsg();
            params.add(new BasicNameValuePair(Constants.M, m));
            params.add(new BasicNameValuePair(Constants.APPKEY, MonitorProp.MONITOR_APPKEYS[rand.nextInt(MonitorProp.MONITOR_APPKEYS.length)]));
            httpPost.addHeader("Connection", "close");
            httpPost.setEntity(new UrlEncodedFormEntity(params, HTTP.UTF_8));
            httpresponse = httpclient.execute(httpPost);
            EntityUtils.consume(httpresponse.getEntity());

        } catch (Throwable t) {
            t.printStackTrace();
        } finally {
            if (httpPost != null) {
                httpPost.releaseConnection();
            }
            if (httpresponse != null) {
                try {
                    httpresponse.getEntity().getContent().close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
