package com.mobilerider;

import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.Request;

import java.io.IOException;

class AddQueryStringParameterInterceptor implements Interceptor
{
    private String _parameterName;

    public String getParameterName()
    {
        return _parameterName;
    }

    private void setParameterName(String parameterName) throws IllegalArgumentException
    {
        if (parameterName == null || parameterName.length() == 0)
        {
            throw new IllegalArgumentException("parameterName");
        }

        _parameterName = parameterName;
    }

    private String _parameterValue;

    public String getParameterValue()
    {
        return _parameterValue;
    }

    private void setParameterValue(String parameterValue) throws IllegalArgumentException
    {
        if (parameterValue == null)
        {
            throw new IllegalArgumentException("parameterValue");
        }

        _parameterValue = parameterValue;
    }

    public AddQueryStringParameterInterceptor(String parameterName, String parameterValue) throws IllegalArgumentException
    {
        if (parameterName == null || parameterName.length() == 0)
        {
            throw new IllegalArgumentException("parameterName");
        }

        if (parameterValue == null)
        {
            throw new IllegalArgumentException("parameterValue");
        }

        setParameterName(parameterName);
        setParameterValue(parameterValue);
    }

    @Override
    public okhttp3.Response intercept(Chain chain) throws IOException
    {
        Request request = chain.request();

        HttpUrl url = request.url().newBuilder()
            .addQueryParameter(getParameterName(), getParameterValue())
            .build();

        request = request.newBuilder()
            .url(url)
            .build();

        return chain.proceed(request);
    }
}