package com.zeydie.telegram.bot.tiktok.api.parsers.tiktok;

import com.zeydie.telegram.bot.tiktok.data.v2.TikTokUserDataV2;
import lombok.NonNull;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public interface ITikTokDataParser {
    @NotNull String getUserTitle(@NonNull final TikTokUserDataV2 tikTokUserData);

    @NotNull String getUserId(@NonNull final TikTokUserDataV2 tikTokUserData);

    @NotNull String getUserBio(@NonNull final TikTokUserDataV2 tikTokUserData);

    @Nullable String getUserLink(@NonNull final TikTokUserDataV2 tikTokUserData);

    int getFollowers(@NonNull final TikTokUserDataV2 tikTokUserData);

    int getFollowing(@NonNull final TikTokUserDataV2 tikTokUserData);

    int getLikes(@NonNull final TikTokUserDataV2 tikTokUserData);

    int getFriends(@NonNull final TikTokUserDataV2 tikTokUserData);

    int getVideos(@NonNull final TikTokUserDataV2 tikTokUserData);

    @NotNull String getRegion(@NonNull final TikTokUserDataV2 tikTokUserData);

    @NotNull String getLanguage(@NonNull final TikTokUserDataV2 tikTokUserData);

    boolean isPrivate(@NonNull final TikTokUserDataV2 tikTokUserData);

    boolean isCommerce(@NonNull final TikTokUserDataV2 tikTokUserData);

    int getCreateTime(@NonNull final TikTokUserDataV2 tikTokUserData);

    int getNicknameModifyTime(@NonNull final TikTokUserDataV2 tikTokUserData);
}