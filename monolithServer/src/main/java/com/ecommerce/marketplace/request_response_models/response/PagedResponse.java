package com.ecommerce.marketplace.request_response_models.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.domain.Page;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class PagedResponse<T> {
    private List<T> content;
    private int page;
    private int size;
    private Long totalElements;
    private int totalPages;
    private boolean last;

    public PagedResponse(Page<T> originalPage) {
        this.content = originalPage.getContent();
        this.page = originalPage.getNumber();
        this.size = originalPage.getSize();
        this.totalElements = originalPage.getTotalElements();
        this.totalPages = originalPage.getTotalPages();
        this.last = originalPage.isLast();

    }
}
