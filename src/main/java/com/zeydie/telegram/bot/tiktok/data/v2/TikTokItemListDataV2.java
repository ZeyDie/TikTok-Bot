package com.zeydie.telegram.bot.tiktok.data.v2;

import com.zeydie.telegram.bot.tiktok.api.data.TikTokItemListData;
import com.zeydie.telegram.bot.tiktok.data.v2.itemlist.video.VideoData;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Data
@EqualsAndHashCode(callSuper = false)
public class TikTokItemListDataV2 extends TikTokItemListData {
    private @Nullable VideoData[] itemList;

    @Override
    public int getCollectCounts() {
        return this.getVideosData().stream()
                .mapToInt(value -> value.getStats().getCollectCount())
                .sum();
    }

    @Override
    public int getCommentCounts() {
        return this.getVideosData().stream()
                .mapToInt(value -> value.getStats().getCommentCount())
                .sum();
    }

    @Override
    public int getDiggCounts() {
        return this.getVideosData().stream()
                .mapToInt(value -> value.getStats().getDiggCount())
                .sum();
    }

    @Override
    public int getPlayCounts() {
        return this.getVideosData().stream()
                .mapToInt(value -> value.getStats().getPlayCount())
                .sum();
    }

    @Override
    public int getShareCounts() {
        return this.getVideosData().stream()
                .mapToInt(value -> value.getStats().getShareCount())
                .sum();
    }

    @Override
    public @NotNull List<VideoData> getVideosData() {
        return this.itemList == null ? new ArrayList<>() : Arrays.asList(this.itemList);
    }
}