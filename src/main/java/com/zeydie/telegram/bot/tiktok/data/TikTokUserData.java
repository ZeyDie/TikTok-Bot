package com.zeydie.telegram.bot.tiktok.data;

import lombok.Data;

import java.util.HashMap;
import java.util.Map;

@Data
public class TikTokUserData {
    private ItemListData ItemList;
    private Map<String, TikTokPostData.ItemData> ItemModule = new HashMap<>();
    private TikTokPostData.UserModuleData UserModule;
    private UserPageData UserPage;
    private RecommendUserData RecommendUserList;
    private AccountListData AccountList;
    private Map<String, PlayListItemData> PlaylistItemModule = new HashMap<>();

    @Data
    public static class ItemListData {
        private PostData favorites;
        private PostData userPost;
        private PostData userLiked;

        @Data
        public static class PostData {
            private String[] list;
            private String[] browserList;
            private boolean loading;
            private int statusCode;
            private boolean hasMore;
            private String cursor;
            private TikTokPostData.ItemListData.VideoData.Preload[] preloadList;
        }
    }

    @Data
    public static class UserPageData {
        private String uniqueId;
        private int statusCode;
        private String secUid;
        private String profileState;
    }

    @Data
    public static class RecommendUserData {
        private String uniqueId;
    }

    @Data
    public static class AccountListData {
        private AccountList following;
        private AccountList follower;
        private AccountList suggest;
        private AccountList friend;
        private AccountList others_following;
        private AccountList others_follower;
        private AccountList suggested_follow;

        @Data
        public static class AccountList {
            @Deprecated
            private Object[] list;
            private boolean hasMore;
            private boolean loading;
            private int maxCursor;
            private int minCursor;
            private int total;
            private int statusCode;
        }
    }

    @Data
    public static class PlayListItemData {
        private String mixId;
        private String mixName;
        private String id;
        private String name;
        private int videoCount;
        private TikTokPostData.UserModuleData.UserData creator;
        private String cover;
    }
}