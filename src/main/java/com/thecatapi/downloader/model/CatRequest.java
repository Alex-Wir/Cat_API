package com.thecatapi.downloader.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.Pattern;
import java.util.Set;

@Getter
@Setter
@ToString
public class CatRequest {

    @Pattern(regexp = "^(full|med|small|thumb)$", message = "{request.size.allowedValues}")
    private String size;

    //TODO add validation
    private Set<String> mimeTypes;

    @Pattern(regexp = "^(random|asc|desc)$", message = "{request.order.allowedValues}")
    private String order;

    @Min(value = 1, message = "{request.limit.1to100}")
    @Max(value = 100, message = "{request.limit.1to100}")
    private byte limit;

    @Min(value = 0, message = "{request.page.positiveOrZero}")
    private long page;

    //TODO add validation
    private Set<String> categoryIds;

    //TODO fix validation
    @Pattern(regexp = "^[a-z]{4}$", message = "{request.breedId.allowedValues}")
    private String breedId;

    public boolean isEmpty() {
        return (this.size == null &&
                this.mimeTypes == null &&
                this.order == null &&
                this.limit == 0 &&
                this.page == 0 &&
                this.categoryIds == null &&
                this.breedId == null);
    }
}
