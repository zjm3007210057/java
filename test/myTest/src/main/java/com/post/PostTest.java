package com.post;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;
import java.util.Map;

/**
 * Created by zhjm on 2017/1/7.
 */
public class PostTest {

    private String requestURL = "url";// 请求的URL
    private String param1 = "param1";// 参数1
    private String param2 = "param2";// 参数2
    private String param3 = "param3";// 参数3

    // 更多参数...

    public String sendPostTest() throws UnsupportedEncodingException {
        String result = null;// 返回结果

        InputStream inStream = null;
        ByteArrayOutputStream outStream = null;
        byte[] data = null;

        try {
            URL url = new URL(requestURL);// 请求的URL
            // 打开连接
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("POST");// 提交模式为post
            conn.setRequestProperty("Content-Type", "application/json");
            conn.setDoOutput(true);// 是否输入参数
            // 下面进行拼接参数
            StringBuffer params = new StringBuffer();
            // 表单参数与get形式一样
            params.append("param1").append("=").append(param1.trim()).append("&").
                    append("param2").append("=").append(param2.trim()).append("&").
                    append("param3").append("=").append(param3.trim());
            // 可以添加更的参数,每个参数之间使用“&”分开

            byte[] bypes = params.toString().getBytes();// 把参数转换成二进制

            conn.getOutputStream().write(bypes);// 输入参数

            inStream = conn.getInputStream();
            outStream = new ByteArrayOutputStream();

            byte[] buffer = new byte[1024];
            int len = 0;
            while ((len = inStream.read(buffer)) != -1) {
                outStream.write(buffer, 0, len);
            }
            data = outStream.toByteArray();// 网页的二进制数据

            result = new String(data, "utf-8");

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                outStream.close();
                inStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return result;
    }

    /**
     * 向指定URL发送GET方法的请求
     *
     * @param url
     *            发送请求的URL
     * @param param
     *            请求参数，请求参数应该是 name1=value1&name2=value2 的形式。
     * @return URL 所代表远程资源的响应结果
     */
    public static String sendGet(String url, String param) {
        String result = "";
        BufferedReader in = null;
        try {
            String urlNameString = url + "?" + param;
            URL realUrl = new URL(urlNameString);
            // 打开和URL之间的连接
            HttpURLConnection connection = (HttpURLConnection)realUrl.openConnection();
            // 设置通用的请求属性
            connection.setRequestMethod("GET");
            connection.setRequestProperty("accept", "*/*");
            connection.setRequestProperty("connection", "Keep-Alive");
            connection.setRequestProperty("Content-Type", "application/json");
//            connection.setRequestProperty("user-agent", "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
            // 建立实际的连接
            connection.connect();
            // 获取所有响应头字段
//            Map<String, List<String>> map = connection.getHeaderFields();
            // 遍历所有的响应头字段
           /* for (String key : map.keySet()) {
                System.out.println(key + "--->" + map.get(key));
            }*/
            // 定义 BufferedReader输入流来读取URL的响应
            in = new BufferedReader(new InputStreamReader(
                    connection.getInputStream()));
            String line;
            while ((line = in.readLine()) != null) {
                System.out.println(line);
                result += line;
            }
        } catch (Exception e) {
            System.out.println("发送GET请求出现异常！" + e);
            e.printStackTrace();
        }
        // 使用finally块来关闭输入流
        finally {
            try {
                if (in != null) {
                    in.close();
                }
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
        return result;
    }

    /**
     * 向指定 URL 发送POST方法的请求
     *
     * @param url
     *            发送请求的 URL
     * @param param
     *            请求参数，请求参数应该是 name1=value1&name2=value2 的形式。
     * @return 所代表远程资源的响应结果
     */
    public static String sendPost(String url, String param) {
        PrintWriter out = null;
        BufferedReader in = null;
        String result = "";
        try {
            URL realUrl = new URL(url);
            // 打开和URL之间的连接
            HttpURLConnection conn = (HttpURLConnection) realUrl.openConnection();
            // 设置通用的请求属性
            conn.setRequestMethod("POST");
            conn.setRequestProperty("accept", "*/*");
            conn.setRequestProperty("connection", "Keep-Alive");
            conn.setRequestProperty("Content-Type", "application/json");
//            conn.setRequestProperty("user-agent", "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
            // 发送POST请求必须设置如下两行
            conn.setDoOutput(true);
            conn.setDoInput(true);
            // 获取URLConnection对象对应的输出流
            out = new PrintWriter(conn.getOutputStream());
            // 发送请求参数
            out.print(param);
            // flush输出流的缓冲
            out.flush();
            // 定义BufferedReader输入流来读取URL的响应
            in = new BufferedReader(
                    new InputStreamReader(conn.getInputStream()));
            String line;
            while ((line = in.readLine()) != null) {
                System.out.println(line);
                result += line;
            }
        } catch (Exception e) {
            System.out.println("发送 POST 请求出现异常！"+e);
            e.printStackTrace();
        }
        //使用finally块来关闭输出流、输入流
        finally{
            try{
                if(out!=null){
                    out.close();
                }
                if(in!=null){
                    in.close();
                }
            }
            catch(IOException ex){
                ex.printStackTrace();
            }
        }
        return result;
    }

    public static void main(String[] args) {
        /*new Thread(new Runnable() {
            public void run() {
                String str;
                for(int i=0; i<5; i++){
                    str = "http://127.0.0.1:8080/map/";
                    sendPost(str + "keys/test" + i, "");
                    sendGet(str + "test" + i, "");
                }
            }
        }).start();*/

        new Thread(new Runnable() {
            public void run() {
                for(int i=0; i<300; i++){
                    System.out.println("这是post的1线程：" + i);
                    try {
                        Thread.sleep(10);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    sendPost("http://127.0.0.1:8080/redis/keys", "");
                }
            }
        }).start();

        new Thread(new Runnable() {
            public void run() {
                for(int i=0; i<300; i++){
                    System.out.println("这是get的1线程：" + i);
                    sendGet("http://127.0.0.1:8080/redis/keys", "");
                }
            }
        }).start();

        /*new Thread(new Runnable() {
            public void run() {
                for(int i=300; i<600; i++){
                    System.out.println("这是post的2线程：" + i);
                    sendPost("http://127.0.0.1:8080/redis/keys", "");
                }
            }
        }).start();

        new Thread(new Runnable() {
            public void run() {
                for(int i=300; i<600; i++){
                    System.out.println("这是get的2线程：" + i);
                    sendGet("http://127.0.0.1:8080/redis/keys", "");
                }
            }
        }).start();*/
    }
}
