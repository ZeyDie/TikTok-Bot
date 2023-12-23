package com.zeydie.telegram.bot.tiktok.data.v2.user;

import lombok.Data;

@Data
public class UserData {
    private String id;
    private String shortId;
    private String uniqueId;
    private String nickname;
    private String avatarLarger;
    private String avatarMedium;
    private String avatarThumb;
    private String signature;
    private int createTime;
    private boolean verified;
    private String secUid;
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
    private String roomId;
    private int uniqueIdModifyTime;
    private boolean ttSeller;
    private String region;
    private ProfileTab profileTab = new ProfileTab();
    private int followingVisibility;
    private String recommendReason;
    private String nowInvitationCardUrl;
    private int nickNameModifyTime;
    private boolean isEmbedBanned;
    private boolean canExpPlaylist;
    private int profileEmbedPermission;
    private String language;
    @Deprecated
    private Object[] eventList;
}