package cc.leevi.toutiao.util;

import com.github.kevinsawicki.http.HttpRequest;

import java.io.IOException;
import java.math.BigDecimal;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.Iterator;
import java.util.Map;
import java.util.Random;

/**
 * Created by jiang on 2017-05-03.
 */
public class StringUtils extends org.apache.commons.lang3.StringUtils {

    public static String buildUrl(String url, Map<String,Object> params,boolean encode){
        url = append(url, params);
        return encode ? encode(url) : url;
    }

    public static Double randomLon(){
        return randomLonLat(97.574061,117.222374,23.53827,41.596479,"Lon");
    }

    public static Double randomLat(){
        return randomLonLat(97.574061,117.222374,23.53827,41.596479,"Lat");
    }

    /**
     * @Title: randomLonLat
     * @Description: 在矩形内随机生成经纬度
     * @param MinLon：最新经度  MaxLon： 最大经度   MinLat：最新纬度   MaxLat：最大纬度    type：设置返回经度还是纬度
     * @return
     * @throws
     */
    public static Double randomLonLat(double MinLon, double MaxLon, double MinLat, double MaxLat, String type) {
        Random random = new Random();
        BigDecimal db = new BigDecimal(Math.random() * (MaxLon - MinLon) + MinLon);
        Double lon = db.setScale(6, BigDecimal.ROUND_HALF_UP).doubleValue();// 小数后6位
        db = new BigDecimal(Math.random() * (MaxLat - MinLat) + MinLat);
        Double lat = db.setScale(6, BigDecimal.ROUND_HALF_UP).doubleValue();
        if (type.equals("Lon")) {
            return lon;
        } else {
            return lat;
        }
    }

    public static String append(String url, Map<String,Object> params){
        String baseUrl = url.toString();
        if(params != null && !params.isEmpty()) {
            StringBuilder result = new StringBuilder(baseUrl);
            addPathSeparator(baseUrl, result);
            addParamPrefix(baseUrl, result);
            Iterator<?> iterator = params.entrySet().iterator();
            Map.Entry<?, ?> entry = (Map.Entry)iterator.next();
            result.append(entry.getKey().toString());
            result.append('=');
            Object value = entry.getValue();
            if(value != null) {
                result.append(value);
            }

            while(iterator.hasNext()) {
                result.append('&');
                entry = (Map.Entry)iterator.next();
                result.append(entry.getKey().toString());
                result.append('=');
                value = entry.getValue();
                if(value != null) {
                    result.append(value);
                }
            }

            return result.toString();
        } else {
            return baseUrl;
        }
    }

    private static StringBuilder addPathSeparator(final String baseUrl,
                                                  final StringBuilder result) {
        // Add trailing slash if the base URL doesn't have any path segments.
        //
        // The following test is checking for the last slash not being part of
        // the protocol to host separator: '://'.
        if (baseUrl.indexOf(':') + 2 == baseUrl.lastIndexOf('/'))
            result.append('/');
        return result;
    }

    private static StringBuilder addParamPrefix(final String baseUrl,
                                                final StringBuilder result) {
        // Add '?' if missing and add '&' if params already exist in base url
        final int queryStart = baseUrl.indexOf('?');
        final int lastChar = result.length() - 1;
        if (queryStart == -1)
            result.append('?');
        else if (queryStart < lastChar && baseUrl.charAt(lastChar) != '&')
            result.append('&');
        return result;
    }
    public static String encode(final CharSequence url)
            throws HttpRequest.HttpRequestException {
        URL parsed;
        try {
            parsed = new URL(url.toString());
        } catch (IOException e) {
            throw new HttpRequest.HttpRequestException(e);
        }

        String host = parsed.getHost();
        int port = parsed.getPort();
        if (port != -1)
            host = host + ':' + Integer.toString(port);

        try {
            String encoded = new URI(parsed.getProtocol(), host, parsed.getPath(),
                    parsed.getQuery(), null).toASCIIString();
            int paramsStart = encoded.indexOf('?');
            if (paramsStart > 0 && paramsStart + 1 < encoded.length())
                encoded = encoded.substring(0, paramsStart + 1)
                        + encoded.substring(paramsStart + 1).replace("+", "%2B");
            return encoded;
        } catch (URISyntaxException e) {
            IOException io = new IOException("Parsing URI failed");
            io.initCause(e);
            throw new HttpRequest.HttpRequestException(io);
        }
    }



}
