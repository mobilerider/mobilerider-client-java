package com.mobilerider;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class MobileRiderApiClient implements MobileRiderApiClientInterface
{
    private static int _defaultPageSize = 20;

    public static int getDefaultPageSize()
    {
        return _defaultPageSize;
    }

    public static void setDefaultPageSize(int defaultPageSize)
    {
        if (defaultPageSize <= 0)
        {
            throw new IllegalArgumentException("defaultPageSize");
        }

        _defaultPageSize = defaultPageSize;
    }

    private final String _appId;

    public String getAppId()
    {
        return _appId;
    }

    private final String _secret;

    public String getSecret()
    {
        return _secret;
    }

    private MobileRiderApiRetrofitClientInterface _innerClient;

    private MobileRiderApiRetrofitClientInterface getInnerClient()
    {
        return _innerClient;
    }

    private void setInnerClient(MobileRiderApiRetrofitClientInterface innerClient)
    {
        if (innerClient == null)
        {
            throw new IllegalArgumentException("innerClient");
        }

        _innerClient = innerClient;
    }

    public MobileRiderApiClient(String appId, String secret)
    {
        if (appId == null || appId.isEmpty())
        {
            throw new IllegalArgumentException("appId");
        }

        _appId = appId;
        _secret = secret;

        setInnerClient(MobileRiderApiRetrofitClient.create(getAppId(), getSecret()));
    }

    @Override
    public Channel getChannelById(String channelId) throws IOException
    {
        // Returns null if item is not found.
        return _innerClient.channel(channelId).execute().body();
    }

    @Override
    public Media getMediaById(String mediaId) throws IOException
    {
        // Returns null if item is not found.
        return _innerClient.media(mediaId).execute().body();
    }

    @Override
    public List<Channel> getChannels() throws IOException
    {
        List<Channel> channels = new ArrayList<Channel>();

        Page<Channel> page = getChannels(0);

        assert(page != null);

        for (Channel c : page)
        {
            channels.add(c);
        }

        while (!page.isLast())
        {
            page = getChannels(page.getIndex() + 1);

            assert(page != null);

            for (Channel c : page)
            {
                channels.add(c);
            }
        }

        return channels;
    }

    @Override
    public Page<Channel> getChannels(Integer page) throws IOException
    {
        if (page < 0)
        {
            throw new IllegalArgumentException("page");
        }

        return getChannels(page, getDefaultPageSize());
    }

    @Override
    public Page<Channel> getChannels(Integer page, Integer pageSize) throws IOException
    {
        if (page < 0)
        {
            throw new IllegalArgumentException("page");
        }

        if (pageSize <= 0)
        {
            throw new IllegalArgumentException("pageSize");
        }

        return _innerClient.channelList(page + 1, pageSize).execute().body();
    }

    @Override
    public List<Media> getMediaByChannel(String channelId) throws IOException
    {
        if (channelId == null || channelId.isEmpty())
        {
            throw new IllegalArgumentException("channelId");
        }

        List<Media> media = new ArrayList<Media>();

        Page<Media> page = getMediaByChannel(channelId, 0);

        assert(page != null);

        for (Media m : page)
        {
            media.add(m);
        }

        while (!page.isLast())
        {
            page = getMediaByChannel(channelId, page.getIndex() + 1);

            assert(page != null);

            for (Media m : page)
            {
                media.add(m);
            }
        }

        return media;
    }

    @Override
    public Page<Media> getMediaByChannel(String channelId, Integer page) throws IOException
    {
        if (channelId == null || channelId.isEmpty())
        {
            throw new IllegalArgumentException("channelId");
        }

        if (page < 0)
        {
            throw new IllegalArgumentException("page");
        }

        return getMediaByChannel(channelId, page, getDefaultPageSize());
    }

    @Override
    public Page<Media> getMediaByChannel(String channelId, Integer page, Integer pageSize) throws IOException
    {
        if (channelId == null || channelId.isEmpty())
        {
            throw new IllegalArgumentException("channelId");
        }

        if (page < 0)
        {
            throw new IllegalArgumentException("page");
        }

        if (pageSize <= 0)
        {
            throw new IllegalArgumentException("pageSize");
        }

        return _innerClient.mediaList(channelId, page + 1, pageSize).execute().body();
    }
}