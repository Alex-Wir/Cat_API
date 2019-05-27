package com.thecatapi.downloader.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Set;

@Getter
@Setter
@ToString
public class CatRequest {

    //TODO add validation
    private String size;
    private Set<String> mimeTypes;
    private String order;
    private byte limit;
    private long page;
    private Set<String> categoryIds;
    private String format;
    private String breedId;

    public boolean isEmpty() {
        return (this.size == null &&
                this.mimeTypes == null &&
                this.order == null &&
                this.limit == 0 &&
                this.page == 0 &&
                this.categoryIds == null &&
                this.format == null &&
                this.breedId == null);
    }
}
