package com.zeydie.telegram.bot.tiktok.api.parsers.tiktok;

import com.zeydie.telegram.bot.tiktok.api.data.TikTokUserData;
import lombok.NonNull;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public interface ITikTokDataParser {
    @NotNull String getId(@NonNull final TikTokUserData tikTokUserData);

    @NotNull String getUniqueId(@NonNull final TikTokUserData tikTokUserData);

    @NotNull String getNickname(@NonNull final TikTokUserData tikTokUserData);

    @NotNull String getUserBio(@NonNull final TikTokUserData tikTokUserData);

    @Nullable String getUserLink(@NonNull final TikTokUserData tikTokUserData);

    int getFollowers(@NonNull final TikTokUserData tikTokUserData);

    int getFollowing(@NonNull final TikTokUserData tikTokUserData);

    int getLikes(@NonNull final TikTokUserData tikTokUserData);

    int getFriends(@NonNull final TikTokUserData tikTokUserData);

    int getVideos(@NonNull final TikTokUserData tikTokUserData);

    @NotNull String getRegion(@NonNull final TikTokUserData tikTokUserData);

    @NotNull String getLanguage(@NonNull final TikTokUserData tikTokUserData);

    boolean isPrivate(@NonNull final TikTokUserData tikTokUserData);

    boolean isCommerce(@NonNull final TikTokUserData tikTokUserData);

    @NonNull String getCommerceCategory(@NonNull final TikTokUserData tikTokUserData);

    int getCreateTime(@NonNull final TikTokUserData tikTokUserData);

    int getNicknameModifyTime(@NonNull final TikTokUserData tikTokUserData);
}