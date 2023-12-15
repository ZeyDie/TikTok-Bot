package com.zeydie.telegram.bot.tiktok.data.v2;

import lombok.Data;

@Data
public final class AccessTokenData {
    private String access_token;
    private int expires_in;
    private String token_type;
}