package com.zeydie.telegram.bot.tiktok.data.v2;

import com.zeydie.telegram.bot.tiktok.api.data.TikTokUserData;
import com.zeydie.telegram.bot.tiktok.data.v2.user.StatsData;
import com.zeydie.telegram.bot.tiktok.data.v2.user.UserData;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Data
@EqualsAndHashCode(callSuper = false)
public class TikTokUserDataV2 extends TikTokUserData {
    private UserData user;
    private StatsData stats;
    @Deprecated
    private Object[] itemList;

    @Override
    public @NotNull String getId() {
        return this.user.getId();
    }

    @Override
    public @NotNull String getUniqueId() {
        return this.user.getUniqueId();
    }

    @Override
    public @NotNull String getNickname() {
        return this.user.getNickname();
    }

    @Override
    public @NotNull String getUserBio() {
        return this.user.getSignature();
    }

    @Override
    public @Nullable String getUserLink() {
        return this.user.getBioLink().getLink();
    }

    @Override
    public int getFollowers() {
        return this.stats.getFollowerCount();
    }

    @Override
    public int getFollowing() {
        return this.stats.getFollowingCount();
    }

    @Override
    public int getLikes() {
        return this.stats.getHeartCount();
    }

    @Override
    public int getFriends() {
        return this.stats.getFriendCount();
    }

    @Override
    public int getVideos() {
        return this.stats.getVideoCount();
    }

    @Override
    public @NotNull String getRegion() {
        return this.user.getRegion();
    }

    @Override
    public @NotNull String getLanguage() {
        return this.user.getLanguage();
    }

    @Override
    public boolean isPrivate() {
        return this.user.isPrivateAccount();
    }

    @Override
    public boolean isCommerce() {
        return this.user.getCommerceUserInfo().isCommerceUser();
    }

    @Override
    public @NotNull String getCommerceCategory() {
        return this.user.getCommerceUserInfo().getCategory();
    }

    @Override
    public int getCreateTime() {
        return this.user.getCreateTime();
    }

    @Override
    public int getNicknameModifyTime() {
        return this.user.getNickNameModifyTime();
    }
}