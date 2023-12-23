package com.zeydie.telegram.bot.tiktok.data.v2.itemlist.video;

import com.zeydie.telegram.bot.tiktok.data.v2.itemlist.MusicData;
import com.zeydie.telegram.bot.tiktok.data.v2.user.UserData;
import lombok.Data;

@Data
public class VideoData {
    private UserData author;
    private long createTime;
    private String desc;
    private String id;
    private MusicData music;
    private VideoStatsData stats;
}