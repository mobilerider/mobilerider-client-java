package com.mobilerider;

import okhttp3.Interceptor;
import okhttp3.Request;

import java.io.IOException;

class AddHeaderInterceptor implements Interceptor
{
    private String _headerName;

    public String getHeaderName()
    {
        return _headerName;
    }

    private void setHeaderName(String headerName) throws IllegalArgumentException
    {
        if (headerName == null || headerName.length() == 0)
        {
            throw new IllegalArgumentException("headerName");
        }

        _headerName = headerName;
    }

    private String _headerValue;

    public String getHeaderValue()
    {
        return _headerValue;
    }

    private void setHeaderValue(String headerValue) throws IllegalArgumentException
    {
        if (headerValue == null)
        {
            throw new IllegalArgumentException("headerName");
        }

        _headerValue = headerValue;
    }

    public AddHeaderInterceptor(String headerName, String headerValue) throws IllegalArgumentException
    {
        if (headerName == null || headerName.length() == 0)
        {
            throw new IllegalArgumentException("headerName");
        }

        if (headerValue == null)
        {
            throw new IllegalArgumentException("headerValue");
        }

        setHeaderName(headerName);
        setHeaderValue(headerValue);
    }

    @Override
    public okhttp3.Response intercept(Chain chain) throws IOException
    {
        Request request = chain.request();

        request = request.newBuilder()
            .addHeader(getHeaderName(), getHeaderValue())
            .build();

        return chain.proceed(request);
    }
}