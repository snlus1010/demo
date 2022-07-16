package com.demo.searcher.controller;

import com.demo.searcher.service.ProductSearchService;
import com.demo.searcher.vo.ResponseVo;
import com.demo.searcher.vo.SearchHighLowByBrandVo;
import com.demo.searcher.vo.SearchLowestByBrandVo;
import com.demo.searcher.vo.SearchLowestResponseVo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping(path = "searcher")
public class ProductSearchController {

    private final ProductSearchService productSearchService;

    private static final String DEFAULT_SUCCESS_MESSAGE = "SUCCESS";

    private static final String NOTFOUND_PRODUCT = "원하시는 상품을 찾을 수 없습니다.";

    private static final String NO_CATEGORY = "카테고리 값이 존재하지 않습니다.";

    /**
     * 1) 모든 카테고리의 상품을 브랜드 별로 자유롭게 선택해서 모든 상품을 구매할 때 최저가 조회 API
     */
    @GetMapping(value = "/search/lowest-price")
    public ResponseVo searchLowestPrice() {

        SearchLowestResponseVo searchLowestResponseVo = productSearchService.searchLowestPrice();

        int status = HttpStatus.OK.value();
        String message = DEFAULT_SUCCESS_MESSAGE;

        if (searchLowestResponseVo == null || searchLowestResponseVo.getTotalPrice() == 0) {
            status = HttpStatus.NOT_FOUND.value();
            message = NOTFOUND_PRODUCT;
        }

        return ResponseVo.builder()
                .status(status)
                .timestamp(LocalDateTime.now())
                .result(searchLowestResponseVo)
                .message(message)
                .build();
    }

    /**
     * 2) 한 브랜드에서 모든 카테고리의 상품을 한꺼번에 구매할 경우 최저가 및 브랜드 조회  API
     */
    @GetMapping(value = "/search/one-brand")
    public ResponseVo searchLowestPriceByBrand() {

        SearchLowestByBrandVo searchLowestByBrandVo = productSearchService.searchLowestPriceByBrand();

        int status = HttpStatus.OK.value();
        String message = DEFAULT_SUCCESS_MESSAGE;

        if (searchLowestByBrandVo == null) {
            status = HttpStatus.NOT_FOUND.value();
            message = NOTFOUND_PRODUCT;
        }

        return ResponseVo.builder()
                .status(status)
                .timestamp(LocalDateTime.now())
                .result(searchLowestByBrandVo)
                .message(message)
                .build();
    }


    /**
     * 3) 각 카테고리 이름으로 최소, 최대 가격 조회 API
     */
    @GetMapping(value = "/search/high-low-price")
    public ResponseVo searchLowHighPriceByCategory(@RequestParam String category) {
        int status = HttpStatus.OK.value();
        String message = DEFAULT_SUCCESS_MESSAGE;

        if (StringUtils.isEmpty(category)) {
            status = HttpStatus.BAD_REQUEST.value();
            message = NO_CATEGORY;
        }

        SearchHighLowByBrandVo searchHighLowByBrandVo = productSearchService.searchHighLowByCategory(category);

        return ResponseVo.builder()
                .status(status)
                .timestamp(LocalDateTime.now())
                .result(searchHighLowByBrandVo)
                .message(message)
                .build();

    }


}
