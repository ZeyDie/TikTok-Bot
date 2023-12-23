package com.zeydie.telegram.bot.tiktok.data.v2.user;

import lombok.Data;

@Data
public class UserStatsData {
    private int followerCount;
    private int followingCount;
    private int heart;
    private int heartCount;
    private int videoCount;
    private int diggCount;
    private int friendCount;
}