package com.demo.searcher.vo;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.lang.NonNull;

@Getter
@Setter
@ToString
public class ProductInfoVo {
    private int seq;

    @NonNull
    private String category;
    @NonNull
    private String brand;
    @NonNull
    private int price;
}
