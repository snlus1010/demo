package com.demo.searcher.vo;

import lombok.*;

@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@ToString
public class SearchLowestByBrandVo {
    private String brand;
    private long totalPrice;
}
