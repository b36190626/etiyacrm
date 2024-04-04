package com.etiyacrm.customerservice.core.business.paging;

import com.etiyacrm.customerservice.services.dtos.responses.cityresponses.GetAllCityResponse;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class PageInfoResponse<T> {
    private List<T> items;
    private int page;
    private int size;
    private long totalElements;
    private int totalPages;
    private boolean hasNext;
    private boolean hasPrevious;

    public PageInfoResponse(Page<T> responsePage) {
        this.setItems(responsePage.getContent());
        this.setPage(responsePage.getNumber());
        this.setSize(responsePage.getSize());
        this.setTotalPages(responsePage.getTotalPages());
        this.setTotalElements(responsePage.getTotalElements());
        this.setHasNext(responsePage.hasNext());
        this.setHasPrevious(responsePage.hasPrevious());
    }
}
