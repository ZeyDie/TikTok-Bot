package com.zeydie.telegram.bot.tiktok.api.data;

import com.zeydie.telegram.bot.tiktok.data.v2.itemlist.video.VideoData;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public abstract class TikTokItemListData {
    public abstract int getCollectCounts();

    public abstract int getCommentCounts();

    public abstract int getDiggCounts();

    public abstract int getPlayCounts();

    public abstract int getShareCounts();

    public abstract @NotNull List<VideoData> getVideosData();
}