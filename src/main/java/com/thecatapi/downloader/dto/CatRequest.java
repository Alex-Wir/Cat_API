package com.thecatapi.downloader.dto;

import com.thecatapi.downloader.dto.constraint.CategoryIdsConstraint;
import com.thecatapi.downloader.dto.constraint.MimeTypeConstraint;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.PositiveOrZero;
import java.util.Set;

@Getter
@Setter
@ToString
public class CatRequest {

    @Pattern(regexp = "^(full|med|small|thumb)$", message = "{request.size.allowedValues}")
    private String size;

    @MimeTypeConstraint
    private Set<String> mimeTypes;

    @Pattern(regexp = "^(rand|asc|desc)$", message = "{request.order.allowedValues}")
    private String order;

    @PositiveOrZero(message = "{request.limit.1to100}")
    @Max(value = 100, message = "{request.limit.1to100}")
    private byte limit;

    @Min(value = 0, message = "{request.page.positiveOrZero}")
    private long page;

    @CategoryIdsConstraint
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

    private void validateMimeTypes() {

    }
}
