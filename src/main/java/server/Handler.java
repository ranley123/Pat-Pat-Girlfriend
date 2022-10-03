package server;

import com.sun.net.httpserver.Headers;
import com.sun.net.httpserver.HttpExchange;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.InetSocketAddress;
import java.net.URI;

public class Handler implements com.sun.net.httpserver.HttpHandler {

    @Override
    public void handle(HttpExchange exchange) throws IOException {
        //请求地址
        InetSocketAddress inetSocketAddress = exchange.getRemoteAddress();
        System.out.println("请求ip地址：" + inetSocketAddress);
        System.out.println("请求host：" + inetSocketAddress.getHostName());
        System.out.println("请求port:" + inetSocketAddress.getPort());
        //请求方式
        String requestMethod = exchange.getRequestMethod();
        System.out.println("请求方式:" + exchange.getRequestMethod());
        //url
        URI url = exchange.getRequestURI();
        System.out.println("url:" + url);
        if (requestMethod.equalsIgnoreCase("GET")) {//客户端的请求是get方法
            //设置服务端响应的编码格式，否则在客户端收到的可能是乱码
            Headers responseHeaders = exchange.getResponseHeaders();
            responseHeaders.set("Content-Type", "text/html;charset=utf-8");

            //在这里通过httpExchange获取客户端发送过来的消息
            //URI url = exchange.getRequestURI();
            //InputStream requestBody = exchange.getRequestBody();

            String response = "this is server";

            exchange.sendResponseHeaders(HttpURLConnection.HTTP_OK, response.getBytes("UTF-8").length);

            OutputStream responseBody = exchange.getResponseBody();
            OutputStreamWriter writer = new OutputStreamWriter(responseBody, "UTF-8");
            writer.write(response);
            writer.close();
            responseBody.close();
        } else {
            //请求报文
            InputStream inputStream = exchange.getRequestBody();
            ByteArrayOutputStream bas = new ByteArrayOutputStream();
            int i;
            while ((i = inputStream.read()) != -1) {
                bas.write(i);
            }
            String requestmsg = bas.toString();
            System.out.println("请求报文:" + requestmsg);

            //返回报文
            String resmsg = "恭喜你成功了!";
            exchange.sendResponseHeaders(HttpURLConnection.HTTP_OK, resmsg.getBytes("UTF-8").length);
            OutputStream outputStream = exchange.getResponseBody();
            outputStream.write(resmsg.getBytes("UTF-8"));
            outputStream.close();
            System.out.println("通讯结束!");
        }
    }
}
