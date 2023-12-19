package com.zeydie.telegram.bot.tiktok.data.v2.user;

import lombok.Data;
import org.jetbrains.annotations.Nullable;

@Data
public class UserData {
    private @Nullable String id;
    private @Nullable String shortId;
    private @Nullable String uniqueId;
    private @Nullable String nickname;
    private @Nullable String avatarLarger;
    private @Nullable String avatarMedium;
    private @Nullable String avatarThumb;
    private @Nullable String signature;
    private int createTime;
    private boolean verified;
    private @Nullable String secUid;
    private boolean ftc;
    private int relation;
    private boolean openFavorite;
    private BioLink bioLink = new BioLink();
    private int commentSetting;
    private CommerceUserInfo commerceUserInfo = new CommerceUserInfo();
    private int duetSetting;
    private int stitchSetting;
    private boolean privateAccount;
    private boolean secret;
    private boolean isADVirtual;
    private @Nullable String roomId;
    private int uniqueIdModifyTime;
    private boolean ttSeller;
    private @Nullable String region;
    private ProfileTab profileTab = new ProfileTab();
    private int followingVisibility;
    private @Nullable String recommendReason;
    private @Nullable String nowInvitationCardUrl;
    private int nickNameModifyTime;
    private boolean isEmbedBanned;
    private boolean canExpPlaylist;
    private int profileEmbedPermission;
    private @Nullable String language;
    @Deprecated
    private Object[] eventList;
}