package com.zeydie.telegram.bot.tiktok.api.data;

import com.zeydie.telegram.bot.tiktok.data.v2.itemlist.MusicData;
import com.zeydie.telegram.bot.tiktok.data.v2.itemlist.video.VideoStatsData;
import org.jetbrains.annotations.NotNull;

public abstract class TikTokVideoData {
    public abstract @NotNull String getId();

    public abstract @NotNull String getDesc();

    public abstract long getCreateTime();

    public abstract MusicData getMusic();

    public abstract VideoStatsData getStats();

    public abstract boolean isIndexEnabled();

    public abstract @NotNull String getLocationCreated();
}