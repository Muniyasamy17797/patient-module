package com.patient.module.domain.dto;

import java.util.List;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class PagedResponse<T> {

    public PagedResponse(List<T> items, long totalElements, int page, int size) {
        this.items = items;
        this.totalElements = totalElements;
        this.page = page;
        this.size = size;
    }

    private List<T> items;
    private long totalElements;
    private int page;
    private int size;
    
}
