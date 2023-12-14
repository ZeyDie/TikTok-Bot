package com.zeydie.telegram.bot.tiktok.parsers.tiktok;

import com.zeydie.telegram.bot.tiktok.api.parsers.tiktok.ITikTokDataParser;
import com.zeydie.telegram.bot.tiktok.data.v2.TikTokUserDataV2;
import lombok.NonNull;
import lombok.val;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public final class TikTokDataParser implements ITikTokDataParser {
    @Override
    public @NotNull String getUserTitle(@NonNull final TikTokUserDataV2 tikTokUserData) {
        return tikTokUserData.getUser().getNickname();
    }

    @Override
    public @NotNull String getUserId(@NonNull TikTokUserDataV2 tikTokUserData) {
        return tikTokUserData.getUser().getId();
    }

    @Override
    public @NotNull String getUserBio(@NonNull final TikTokUserDataV2 tikTokUserData) {
        return tikTokUserData.getUser().getSignature();
    }

    @Override
    public @Nullable String getUserLink(@NonNull final TikTokUserDataV2 tikTokUserData) {
        @Nullable val bioLink = tikTokUserData.getUser().getBioLink();

        if (bioLink == null) return null;

        return bioLink.getLink();
    }

    @Override
    public int getFollowers(@NonNull final TikTokUserDataV2 tikTokUserData) {
        return tikTokUserData.getStats().getFollowerCount();
    }

    @Override
    public int getFollowing(@NonNull final TikTokUserDataV2 tikTokUserData) {
        return tikTokUserData.getStats().getFollowingCount();
    }

    @Override
    public int getLikes(@NonNull final TikTokUserDataV2 tikTokUserData) {
        return tikTokUserData.getStats().getHeartCount();
    }

    @Override
    public int getFriends(@NonNull final TikTokUserDataV2 tikTokUserData) {
        return tikTokUserData.getStats().getFriendCount();
    }

    @Override
    public int getVideos(@NonNull final TikTokUserDataV2 tikTokUserData) {
        return tikTokUserData.getStats().getVideoCount();
    }

    @Override
    public @NotNull String getRegion(@NonNull final TikTokUserDataV2 tikTokUserData) {
        return tikTokUserData.getUser().getRegion();
    }

    @Override
    public @NotNull String getLanguage(@NonNull final TikTokUserDataV2 tikTokUserData) {
        return tikTokUserData.getUser().getLanguage();
    }

    @Override
    public boolean isPrivate(@NonNull final TikTokUserDataV2 tikTokUserData) {
        return tikTokUserData.getUser().isPrivateAccount();
    }

    @Override
    public boolean isCommerce(@NonNull final TikTokUserDataV2 tikTokUserData) {
        return tikTokUserData.getUser().getCommerceUserInfo().isCommerceUser();
    }

    @Override
    public int getCreateTime(@NonNull final TikTokUserDataV2 tikTokUserData) {
        return tikTokUserData.getUser().getCreateTime();
    }

    @Override
    public int getNicknameModifyTime(@NonNull final TikTokUserDataV2 tikTokUserData) {
        return tikTokUserData.getUser().getNickNameModifyTime();
    }
}