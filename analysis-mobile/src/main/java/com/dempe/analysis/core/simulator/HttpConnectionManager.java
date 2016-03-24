package com.dempe.analysis.core.simulator;

import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.conn.PoolingClientConnectionManager;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;

/**
 * HttpClient 连接池，提供从连接池获取HttpClient的方法，
 *
 * @version 1.0 date: 2014-3-7
 * @author: Zheng Dongping
 */
public class HttpConnectionManager {

    /**
     * 最大连接数
     */
    public final static int MAX_TOTAL_CONNECTIONS = 1000;
    /**
     * 获取连接的最大等待时间
     */
    public final static int WAIT_TIMEOUT = 5000;
    /**
     * 每个路由最大连接数
     */
    public final static int MAX_ROUTE_CONNECTIONS = 400;
    /**
     * 连接超时时间
     */
    public final static int CONNECT_TIMEOUT = 2000;
    /**
     * 请求超时时间
     */
    public final static int READ_TIMEOUT = 5000;
    /**
     * PoolClientConnection持有最大HttpClient实例
     */
    public final static int MAXTOTAL = 4000;

    private static HttpParams httpParams;
    private static PoolingClientConnectionManager connectionManager;

    static {
        httpParams = new BasicHttpParams();
        // 设置连接超时时间
        HttpConnectionParams.setConnectionTimeout(httpParams, CONNECT_TIMEOUT);
        // 设置请求超时时间
        HttpConnectionParams.setSoTimeout(httpParams, READ_TIMEOUT);

        connectionManager = new PoolingClientConnectionManager();

        // Increase max total connection to maxTotal
        connectionManager.setMaxTotal(MAXTOTAL);
        // Increase default max connection per route to defaultMaxPerRoute
        connectionManager.setDefaultMaxPerRoute(MAX_ROUTE_CONNECTIONS);

    }


    public static DefaultHttpClient getHttpClient() {
        return new DefaultHttpClient(connectionManager, httpParams);
    }

}