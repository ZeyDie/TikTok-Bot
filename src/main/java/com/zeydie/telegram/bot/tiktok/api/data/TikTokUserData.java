package com.zeydie.telegram.bot.tiktok.api.data;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public abstract class TikTokUserData {
    public abstract @NotNull String getId();

    public abstract @NotNull String getUniqueId();

    public abstract @NotNull String getSecUid();

    public abstract @NotNull String getNickname();

    public abstract @NotNull String getUserBio();

    public abstract @Nullable String getUserLink();

    public abstract int getFollowers();

    public abstract int getFollowing();

    public abstract int getLikes();

    public abstract int getFriends();

    public abstract int getVideos();

    public abstract @NotNull String getRegion();

    public abstract @NotNull String getLanguage();

    public abstract boolean isPrivate();

    public abstract boolean isCommerce();

    public abstract @NotNull String getCommerceCategory();

    public abstract int getCreateTime();

    public abstract int getNicknameModifyTime();
}