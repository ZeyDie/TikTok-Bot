package com.zeydie.telegram.bot.tiktok.data.v2;

import lombok.Data;

@Data
public class TikTokUserDataV2 {
    private UserData user;
    private StatsData stats;
    @Deprecated
    private Object[] itemList;

    @Data
    public static class UserData {
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
        private BioLink bioLink;
        private int commentSetting;
        private CommerceUserInfo commerceUserInfo;
        private int duetSetting;
        private int stitchSetting;
        private boolean privateAccount;
        private boolean secret;
        private boolean isADVirtual;
        private String roomId;
        private int uniqueIdModifyTime;
        private boolean ttSeller;
        private String region;
        private ProfileTab profileTab;
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

        @Data
        public static class BioLink {
            private String link;
            private int risk;
        }

        @Data
        public static class CommerceUserInfo {
            private boolean commerceUser;
            private String category;
        }

        @Data
        public static class ProfileTab {
            private boolean showMusicTab;
            private boolean showQuestionTab;
            private boolean showPlayListTab;
        }
    }

    @Data
    public static class StatsData {
        private int followerCount;
        private int followingCount;
        private int heart;
        private int heartCount;
        private int videoCount;
        private int diggCount;
        private int friendCount;
    }
}