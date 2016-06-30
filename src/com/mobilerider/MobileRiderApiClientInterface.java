package com.mobilerider;

import java.io.IOException;
import java.util.List;

public interface MobileRiderApiClientInterface
{
    Channel getChannelById(String channelId) throws IOException;

    Media getMediaById(String mediaId) throws IOException;

    List<Channel> getChannels() throws IOException;

    Page<Channel> getChannels(Integer page) throws IOException;

    Page<Channel> getChannels(Integer page, Integer pageSize) throws IOException;

    List<Media> getMediaByChannel(String channelId) throws IOException;

    Page<Media> getMediaByChannel(String channelId, Integer page) throws IOException;

    Page<Media> getMediaByChannel(String channelId, Integer page, Integer pageSize) throws IOException;
}