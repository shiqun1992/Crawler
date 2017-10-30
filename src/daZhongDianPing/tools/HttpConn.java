package daZhongDianPing.tools;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.InetSocketAddress;
import java.net.Proxy;
import java.net.URL;

/**
 * Created by JQ-Cao on 15/4/28.
 */
public class HttpConn {
    public String httpConn(String strURL) throws Exception {


        int n=8;

        String ip[] = new String[n];
        int port[] = new int[n];

        ip[0] = "121.69.28.86" ;
        port[0] = 8118;

        ip[1] = "220.189.97.123";
        port[1] = 3218;

        ip[2] = "58.221.85.182";
        port[2] = 3218;

        ip[3] = "125.84.143.68";
        port[3] = 9999;

        ip[4] = "220.185.102.142";
        port[4] =3128;

        ip[5] = "115.28.85.240";
        port[5] = 8088;

        ip[6] = "180.76.135.145";
        port[6] = 8088;

        ip[7] = "182.92.155.193";
        port[7] = 8080;

        String buf = "";

        int ResponseCode=0;
        int i=0;

        do {
            try {

                URL url = new URL(strURL);
                //System.out.println("HttpConn:strURL"+strURL);
                CookieManager cookieManager = CookieManager.getInstance();

                Proxy proxy = new Proxy(Proxy.Type.HTTP, new InetSocketAddress(ip[i], port[i]));
                //HttpURLConnection httpConn = (HttpURLConnection) url.openConnection(proxy);

                HttpURLConnection httpConn = (HttpURLConnection) url.openConnection();
                //httpConn.setConnectTimeout(2000);

                int j = (int) (Math.random() * 2);
                if (j % 2 == 1) {
                    httpConn.setRequestProperty("User-Agent",
                            "Mozilla/5.0 (Windows NT 6.1; WOW64)" +
                                    " AppleWebKit/537.36 (KHTML, like Gecko) " +
                                    "Chrome/27.0.1453.94 Safari/537.36");
                } else {
                    httpConn.setRequestProperty("User-Agent",
                            "Mozilla/5.0 (Windows NT 5.1; rv:5.0) " +
                                    "Gecko/20100101 Firefox/5.0");
                }

                httpConn.setRequestProperty("Accept", "text/html," +
                        "application/xhtml+xml,application/xml;q=0.9,*/*;q=0.8");
                httpConn.setRequestProperty("Accept-Language",
                        "zh-cn,zh;q=0.5");
                httpConn.setRequestProperty("Accept-Encoding",
                        "GB2312,utf-8;q=0.7,*;q=0.7");
                httpConn.setRequestProperty("Referer",
                        "http://http://www.dianping.com/");
                httpConn.setRequestProperty("Cache-Control",
                        "max-age=0");
                httpConn.setRequestProperty("Cookie",
                        cookieManager.getCookies(url.getHost()));

                //httpConn.getResponseCode()
                System.out.println(httpConn.getResponseCode()+":"+strURL);
                if (httpConn.getResponseCode() == 403) {
                    ResponseCode=403;
                    System.out.println("Err:403");
                    i++;
                }

                if (httpConn.getInputStream() != null) {

                    InputStreamReader input = new InputStreamReader(httpConn.getInputStream());
                    BufferedReader bufReader = new BufferedReader(input);
                    String line = "";
                    StringBuilder contentBuf = new StringBuilder();

                    while ((line = bufReader.readLine()) != null)
                        contentBuf.append(line);
                    buf = contentBuf.toString();

                    httpConn.disconnect();
                    input.close();
                    bufReader.close();
                }
            } catch (Exception e) {
                System.out.println(e.toString());
                i++;
            }
        }while(ResponseCode==403 && i<n);

        return buf;
    }
}
