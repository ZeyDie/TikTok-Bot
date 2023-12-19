package com.zeydie.telegram.bot.tiktok.data.v2.user;

import lombok.Data;
import org.jetbrains.annotations.Nullable;

@Data
public class BioLink {
    private @Nullable String link;
    private int risk;
}