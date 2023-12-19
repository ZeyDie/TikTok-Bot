package com.zeydie.telegram.bot.tiktok.parsers.tiktok;

import com.zeydie.telegram.bot.tiktok.api.data.TikTokUserData;
import com.zeydie.telegram.bot.tiktok.api.parsers.tiktok.ITikTokDataParser;
import lombok.NonNull;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public final class TikTokDataParser implements ITikTokDataParser {
    @Override
    public @NotNull String getId(@NonNull final TikTokUserData tikTokUserData) {
        return tikTokUserData.getId();
    }

    @Override
    public @NotNull String getUniqueId(@NonNull final TikTokUserData tikTokUserData) {
        return tikTokUserData.getUniqueId();
    }

    @Override
    public @NotNull String getNickname(@NonNull final TikTokUserData tikTokUserData) {
        return tikTokUserData.getNickname();
    }

    @Override
    public @NotNull String getUserBio(@NonNull final TikTokUserData tikTokUserData) {
        return tikTokUserData.getUserBio();
    }

    @Override
    public @Nullable String getUserLink(@NonNull final TikTokUserData tikTokUserData) {
        return tikTokUserData.getUserLink();
    }

    @Override
    public int getFollowers(@NonNull final TikTokUserData tikTokUserData) {
        return tikTokUserData.getFollowers();
    }

    @Override
    public int getFollowing(@NonNull final TikTokUserData tikTokUserData) {
        return tikTokUserData.getFollowing();
    }

    @Override
    public int getLikes(@NonNull final TikTokUserData tikTokUserData) {
        return tikTokUserData.getLikes();
    }

    @Override
    public int getFriends(@NonNull final TikTokUserData tikTokUserData) {
        return tikTokUserData.getFriends();
    }

    @Override
    public int getVideos(@NonNull final TikTokUserData tikTokUserData) {
        return tikTokUserData.getVideos();
    }

    @Override
    public @NotNull String getRegion(@NonNull final TikTokUserData tikTokUserData) {
        return tikTokUserData.getRegion();
    }

    @Override
    public @NotNull String getLanguage(@NonNull final TikTokUserData tikTokUserData) {
        return tikTokUserData.getLanguage();
    }

    @Override
    public boolean isPrivate(@NonNull final TikTokUserData tikTokUserData) {
        return tikTokUserData.isPrivate();
    }

    @Override
    public boolean isCommerce(@NonNull final TikTokUserData tikTokUserData) {
        return tikTokUserData.isCommerce();
    }

    @Override
    public @NonNull String getCommerceCategory(@NonNull final TikTokUserData tikTokUserData) {
        return tikTokUserData.getCommerceCategory();
    }

    @Override
    public int getCreateTime(@NonNull final TikTokUserData tikTokUserData) {
        return tikTokUserData.getCreateTime();
    }

    @Override
    public int getNicknameModifyTime(@NonNull final TikTokUserData tikTokUserData) {
        return tikTokUserData.getNicknameModifyTime();
    }
}