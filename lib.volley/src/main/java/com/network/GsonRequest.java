package com.network;

import com.android.volley.NetworkResponse;
import com.android.volley.ParseError;
import com.android.volley.Response;
import com.android.volley.Response.ErrorListener;
import com.android.volley.Response.Listener;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.HttpHeaderParser;
import com.android.volley.toolbox.JsonRequest;
import com.baseproject.util.BaseProjectLog;

import java.io.UnsupportedEncodingException;

public class GsonRequest extends JsonRequest<String> {

    private final static String TAG = GsonRequest.class.getSimpleName();
    protected final static ResponseErrorListener ERROR_LISTENER = new ResponseErrorListener();

    private final Listener<String> listener;

    /**
     * Make a GET request and return a parsed object from JSON.
     *
     * @param url URL of the request to make
     */
    public GsonRequest(String url, String requestBody, Listener<String> listener,
                       ErrorListener errorListener) {
        super(Method.POST, url, requestBody, listener, errorListener);
        this.listener = listener;
    }

    @Override
    protected void deliverResponse(String response) {
        if (listener != null) {
            listener.onResponse(response);
        }
    }

    @Override
    protected Response<String> parseNetworkResponse(NetworkResponse response) {
        try {
            String json = new String(response.data,
                    HttpHeaderParser.parseCharset(response.headers));
            BaseProjectLog.printlnNetworkResponse(response);
            BaseProjectLog.d(TAG, "Request Response json:" + json);
            Response<String> res = Response.success(json,
                    HttpHeaderParser.parseCacheHeaders(response));
            return res;
        } catch (UnsupportedEncodingException e) {
            return Response.error(new ParseError(e));
        }
    }


    private static class ResponseErrorListener implements ErrorListener {

        private String mUrl = "";

        @Override
        public void onErrorResponse(VolleyError error) {
            BaseProjectLog.printlnNetworkResponseError(error);
        }

        public ResponseErrorListener fillUrl(String url) {
            mUrl = url;
            return this;
        }

    }
}