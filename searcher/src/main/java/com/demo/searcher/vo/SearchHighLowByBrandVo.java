package com.demo.searcher.vo;

import com.demo.searcher.domain.ProductInfo;
import lombok.*;

@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@ToString
public class SearchHighLowByBrandVo {
    private ProductInfo lowestProduct;
    private ProductInfo highestProduct;
}
