package com.zeydie.telegram.bot.tiktok.api;

import com.zeydie.sgson.SGsonBase;
import com.zeydie.telegram.bot.tiktok.data.v2.AccessTokenData;
import com.zeydie.telegram.bot.tiktok.data.v2.UserData;
import lombok.NonNull;
import lombok.val;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import java.io.IOException;

public final class TikTokRequestAPI {
    /*
curl --location --request POST 'https://open.tiktokapis.com/v2/research/user/info/?fields=display_name,bio_description,avatar_url,is_verified,follower_count,following_count,likes_count,video_count' \
--header 'Authorization: bearer clt.IUOfkKZXbeizwP1Sq3PRv7flYLcmJsVwVBDi96QewURZUhprvAViHu0FpC1a' \
--header 'Content-Type: text/plain' \
--data-raw '{
    "username": "tiktok"
}'

curl --location --request POST 'https://open.tiktokapis.com/v2/oauth/token/' \
--header 'Content-Type: application/x-www-form-urlencoded' \
--header 'Cache-Control: no-cache' \
--data-urlencode 'client_key=awgrlwhkwaxtzwu2' \
--data-urlencode 'client_secret=J9LHM9xWhy3edkCUx5adLnMjhKnzbPrf' \
--data-urlencode 'grant_type=client_credentials'
    * */
    private static final OkHttpClient okHttpClient = new OkHttpClient();

    public static @NonNull String getAccessToken() throws IOException {
        @NonNull val requestBody = new FormBody.Builder()
                .addEncoded("client_key", "awgrlwhkwaxtzwu2")
                .addEncoded("client_secret", "J9LHM9xWhy3edkCUx5adLnMjhKnzbPrf")
                .addEncoded("grant_type", "client_credentials")
                .build();
        @NonNull val request = new Request.Builder()
                .url("https://open.tiktokapis.com/v2/oauth/token/")
                .addHeader("Content-Type", "application/x-www-form-urlencoded")
                .addHeader("Cache-Control", "no-cache")
                .post(requestBody)
                .build();

        return new SGsonBase().fromJsonToObject(getResponseAnswer(execute(request)), new AccessTokenData()).getAccess_token();
    }

    public static @NonNull UserData getUserData() {
        return null;
    }

    public static @NonNull Response execute(@NonNull final Request request) throws IOException {
        return okHttpClient.newCall(request).execute();
    }

    public static @NonNull String getResponseAnswer(@NonNull final Response response) throws IOException {
        return response.body().string();
    }
}