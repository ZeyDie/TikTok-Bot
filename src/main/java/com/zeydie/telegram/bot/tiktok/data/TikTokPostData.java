package com.zeydie.telegram.bot.tiktok.data;

import lombok.Data;

import java.util.HashMap;
import java.util.Map;

@Data
public class TikTokPostData {
    private ItemListData ItemList;
    private Map<String, ItemData> ItemModule = new HashMap<>();
    private UserModuleData UserModule;

    @Data
    public static class ItemListData {
        private VideoData video;

        @Data
        public static class VideoData {
            private String[] list;
            private String[] browserList;
            private boolean loading;
            private int statusCode;
            private boolean hasMore;
            private String cursor;
            private Preload[] preloadList;
            private String keyword;

            @Data
            public static class Preload {
                private String url;
                private String id;
            }
        }
    }

    @Data
    public static class ItemData {
        private String id;
        private String desc;
        private String createTime;
        private long scheduleTime;
        private VideoData video;
        private String author;
        private MusicData music;
        private ChallengeData[] challenges;
        private StatsData stats;
        @Deprecated
        private Object[] warnInfo;
        private boolean originalItem;
        private boolean officalItem;
        private TextExtraData[] textExtra;
        private boolean secret;
        private boolean forFriend;
        private boolean digged;
        private int itemCommentStatus;
        private int takeDown;
        @Deprecated
        private Object[] effectStickers;
        private boolean privateItem;
        private boolean duetEnabled;
        private boolean stitchEnabled;
        @Deprecated
        private Object[] stickersOnItem;
        private boolean shareEnabled;
        @Deprecated
        private Object[] comments;
        private int duetDisplay;
        private int stitchDisplay;
        private boolean indexEnabled;
        private String locationCreated;
        @Deprecated
        private Object[] suggestedWords;
        private ContentData[] contents;
        private boolean collected;
        @Deprecated
        private Object[] channelTags;
        private boolean isPinnedItem;
        private String nickname;
        private String authorId;
        private String authorSecId;
        private String avatarThumb;
        private int downloadSetting;
        private boolean authorPrivate;
        @Deprecated
        private Object[] capcutAnchorsOriginal;
        @Deprecated
        private Object[] capcutAnchors;

        @Data
        public static class VideoData {
            private String id;
            private int height;
            private int width;
            private int duration;
            private String ratio;
            private String cover;
            private String originalCover;
            private String dynamicCover;
            private String playAddr;
            private String downloadAddr;
            private String[] shareCover;
            private String reflowCover;
            private long bitrate;
            private String encodedType;
            private String format;
            private String videoQuality;
            private String encodeUserTag;
            private String codecType;
            private String definition;
            private SubtitleInfo[] subtitleInfos;
            private Map<Integer, String> zoomCover = new HashMap<>();
            private Map<String, Double> volumeInfo = new HashMap<>();
            private BitrateInfo[] bitrateInfo;

            @Data
            public static class SubtitleInfo {
                private String UrlExpire;
                private String Size;
                private String LanguageID;
                private String LanguageCodeName;
                private String Url;
                private String Format;
                private String Version;
                private String Source;
            }

            @Data
            public static class BitrateInfo {
                private long Bitrate;
                private int QualityType;
                private String GearName;
                private PlayAddr PlayAddr;
                private String CodecType;

                @Data
                public static class PlayAddr {
                    private String DataSize;
                    private String Uri;
                    private String[] UrlList;
                    private String UrlKey;
                    private String FileHash;
                    private String FileCs;
                }
            }
        }

        @Data
        public static class MusicData {
            private String id;
            private String title;
            private String playUrl;
            private String coverLarge;
            private String coverMedium;
            private String coverThumb;
            private String authorName;
            private boolean original;
            private int duration;
            private int scheduleSearchTime;
            private boolean collected;
            private PreciseDuration preciseDuration;

            @Data
            public static class PreciseDuration {
                private double preciseDuration;
                private double preciseShootDuration;
                private double preciseAuditionDuration;
                private double preciseVideoDuration;
            }
        }

        @Data
        public static class ChallengeData {
            private String id;
            private String title;
            private String desc;
            private String profileLarger;
            private String profileMedium;
            private String profileThumb;
            private String coverLarger;
            private String coverMedium;
            private String coverThumb;
        }

        @Data
        public static class StatsData {
            private long diggCount;
            private long shareCount;
            private long commentCount;
            private long playCount;
            private String collectCount;
        }

        @Data
        public static class TextExtraData {
            private String awemeId;
            private int start;
            private int end;
            private String hashtagId;
            private String hashtagName;
            private int type;
            private int subType;
            private boolean isCommerce;
        }

        @Data
        public static class ContentData {
            private String desc;
            private TextExtraData[] textExtra;
        }
    }

    @Data
    public static class UserModuleData {
        private Map<String, UserData> users = new HashMap<>();
        private Map<String, StatsData> stats = new HashMap<>();

        @Data
        public static class UserData {
            private String id;
            private String shortId;
            private String uniqueId;
            private String nickname;
            private String signature;
            private long createTime;
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
            private long uniqueIdModifyTime;
            private boolean ttSeller;
            private String region;
            private int downloadSetting;
            private ProfileTab profileTab;
            private int followingVisibility;
            private String recommendReason;
            private String nowInvitationCardUrl;
            private long nickNameModifyTime;
            private boolean isEmbedBanned;
            private boolean canExpPlaylist;
            private int profileEmbedPermission;
            private String language;
            @Deprecated
            private Object[] eventList;
            private ExtraInfo extraInfo;

            @Data
            public static class BioLink {
                private String link;
                private int risk;
            }

            @Data
            public static class CommerceUserInfo {
                private boolean commerceUser;
            }

            @Data
            public static class ProfileTab {
                private boolean showMusicTab;
            }

            @Data
            public static class ExtraInfo {
                private int statusCode;
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
            private boolean needFix;
        }
    }
}