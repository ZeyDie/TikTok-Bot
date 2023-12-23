package com.zeydie.telegram.bot.tiktok.data.v2;

import com.zeydie.telegram.bot.tiktok.api.data.TikTokVideoData;
import com.zeydie.telegram.bot.tiktok.data.v2.itemlist.MusicData;
import com.zeydie.telegram.bot.tiktok.data.v2.itemlist.video.VideoStatsData;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public class TikTokVideoDataV2 extends TikTokVideoData {
    private String id;
    private String desc;
    private long createTime;
    private MusicData music;
    private VideoStatsData stats;
    private boolean indexEnabled;
    private String locationCreated;
}