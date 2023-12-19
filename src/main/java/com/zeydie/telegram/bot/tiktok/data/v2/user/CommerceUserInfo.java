package com.zeydie.telegram.bot.tiktok.data.v2.user;

import lombok.Data;
import org.jetbrains.annotations.Nullable;

@Data
public class CommerceUserInfo {
    private boolean commerceUser;
    private @Nullable String category;
}