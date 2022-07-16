package com.demo.searcher.vo;

import lombok.*;

import java.util.List;

@Getter
@Builder
@ToString
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class SearchLowestResponseVo {
    private List<ProductInfoVo> productInfoVo;
    private int totalPrice;
}
