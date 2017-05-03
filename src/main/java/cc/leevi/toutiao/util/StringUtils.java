package cc.leevi.toutiao.util;

import com.github.kevinsawicki.http.HttpRequest;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.Iterator;
import java.util.Map;

/**
 * Created by jiang on 2017-05-03.
 */
public class StringUtils extends org.apache.commons.lang3.StringUtils {

    public static String buildUrl(String url, Map<String,Object> params,boolean encode){
        url = append(url, params);
        return encode ? encode(url) : url;
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
