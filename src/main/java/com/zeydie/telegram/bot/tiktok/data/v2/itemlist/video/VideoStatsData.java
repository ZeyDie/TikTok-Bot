package com.zeydie.telegram.bot.tiktok.data.v2.itemlist.video;

import lombok.Data;

@Data
public class VideoStatsData {
    private int collectCount;
    private int commentCount;
    private int diggCount;
    private int playCount;
    private int shareCount;
}