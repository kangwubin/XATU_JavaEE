package xatu191226;

import java.util.HashMap;
import java.util.Map;

/**
 * Description:解析http地址
 *
 * @author: KangWuBin
 * @Date: 2019/12/26
 * @Time: 9:32
 */
public class AnalysisHtpp {
    public static void main(String[] args) {
        Map<String, Integer> allKnownPorts = new HashMap<>();
        allKnownPorts.put("http", 80);
        allKnownPorts.put("https", 443);

        String url = "https://www.baidu.com/s?ie=utf-8&f=8&rsv_bp=1&rsv_idx=2&tn=baiduhome_pg&wd=c%2B%2B%E5%AE%98%E6%96%B9%E6%96%87%E6%A1%A3&rsv_spt=1&oq=c%252B%252B&rsv_pq=b2fd88730028c27a&rsv_t=fe8fBCopkrDZWaaUTZg%2Fd2c%2B0bIWUYscUnnrQvZ%2FmyLsdy1KOIKimk%2FtmShxgQM4ScHE&rqlang=cn&rsv_enter=1&rsv_dl=tb&rsv_sug3=9&rsv_sug1=6&rsv_sug7=100&sug=c%252B%252B%25E5%25AE%2598%25E6%2596%25B9%25E6%2596%2587%25E6%25A1%25A3&rsv_n=1&bs=c%2B%2B";
        int i;

        //获取schema
        i = url.indexOf("://");
        String schema = url.substring(0, i);
        System.out.println(schema);

        //获取host+port
        url = url.substring(i + 3);
        i = url.indexOf("/");
        String hostAndPort = url.substring(0, i);
        String host;
        int port;
        if (hostAndPort.contains(":")) {
            String[] group = hostAndPort.split(":");
            host = group[0];
            port = Integer.parseInt(group[1]);
        } else {
            host = hostAndPort;
            port = allKnownPorts.get(schema);
        }
        System.out.println(host);
        System.out.println(port);
//        System.out.println(hostAndPort);

        //找path
        url = url.substring(i);
        i = url.indexOf("?");
        String path;
        if (i != -1) {
            path = url.substring(0, i);
        } else {
            path = url;
        }
        System.out.println(path);

        //区分query_string和segment
        if (i != -1) {
            String queryString;
            url = url.substring(i + 1);
            String[] group = url.split("#");
            queryString = group[0];
         /*   if (group.length == 1) {
                System.out.println(group[0]);
            } else {
                System.out.println(group[0]);
                System.out.println(group[1]);
            }*/
            if (group.length == 2) {
                System.out.println(group[1]);
            }

            String[] kvGroup = queryString.split("&");
            for (String kv : kvGroup) {
                System.out.println(kv);
            }
        }
    }
}
