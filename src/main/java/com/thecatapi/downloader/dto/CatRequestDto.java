package com.thecatapi.downloader.dto;

import com.thecatapi.downloader.constraint.BreedIdConstraint;
import com.thecatapi.downloader.constraint.CategoryIdsConstraint;
import com.thecatapi.downloader.constraint.MimeTypeConstraint;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.PositiveOrZero;
import java.util.Set;

/**
 * Request DTO class. Fields are parameters of request
 */
@Getter
@Setter
@ToString
public class CatRequestDto {

    @Pattern(regexp = "^(full|med|small|thumb)$", message = "{request.size.allowedValues}")
    private String size;

    @MimeTypeConstraint
    private Set<String> mimeTypes;

    @Pattern(regexp = "^(random|asc|desc)$", message = "{request.order.allowedValues}")
    private String order;

    @PositiveOrZero(message = "{request.limit.1to100}")
    @Max(value = 100, message = "{request.limit.1to100}")
    private byte limit;

    @Min(value = 0, message = "{request.page.positiveOrZero}")
    private long page;

    @CategoryIdsConstraint
    private Set<String> categoryIds;

    @BreedIdConstraint
    private String breedId;

    /**
     * Check instance of CatRequestDTO is empty
     *
     * @return - true, if all fields are null or 0
     */
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
