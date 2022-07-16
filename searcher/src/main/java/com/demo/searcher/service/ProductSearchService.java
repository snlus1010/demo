package com.demo.searcher.service;

import com.demo.searcher.domain.ProductInfo;
import com.demo.searcher.repository.ProductInfoRepository;
import com.demo.searcher.vo.ProductInfoVo;
import com.demo.searcher.vo.SearchHighLowByBrandVo;
import com.demo.searcher.vo.SearchLowestByBrandVo;
import com.demo.searcher.vo.SearchLowestResponseVo;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class ProductSearchService {

    private final ProductInfoRepository productInfoRepository;

    private final ObjectMapper objectMapper;

    public SearchLowestResponseVo searchLowestPrice() {

        //1. 카테고리 목록 가져와서
        List<String> categoryList = productInfoRepository.getCategoryList();
        log.debug("### categoryList >>> {} ", categoryList);

        if (categoryList != null && categoryList.size() > 0) {

            //2. 카테고리별 최저가 브랜드, 가격 가져와서
            int totalPrice = 0;
            List<ProductInfoVo> productInfoVoList = new ArrayList<>();

            for (String category : categoryList) {
                ProductInfo productInfo = productInfoRepository.findTopByCategoryOrderByPriceAsc(category);
                ProductInfoVo productInfoVo = objectMapper.convertValue(productInfo, ProductInfoVo.class);

                if (productInfoVo != null) {
                    totalPrice += productInfoVo.getPrice();
                    productInfoVoList.add(productInfoVo);
                }

            }

            log.debug("### Product Info List >>> {} ", productInfoVoList);
            log.debug("### Total Price >>> {} ", totalPrice);

            //3. total 포함 객체 리턴
            return SearchLowestResponseVo.builder()
                    .productInfoVo(productInfoVoList)
                    .totalPrice(totalPrice)
                    .build();
        }

        return null;

    }

    public SearchLowestByBrandVo searchLowestPriceByBrand() {
        //TODO: 개선사항 TOP1 을 가져와야하는데, 모든 브랜드를 구하고 있음
        List<SearchLowestByBrandVo> list = productInfoRepository.searchLowestPriceByBrand();
        SearchLowestByBrandVo searchLowestByBrandVo = null;

        if (list != null && list.size() > 0) {
            searchLowestByBrandVo = list.get(0);
            log.debug("### Lowest Brand, Price >> {}, {} ", searchLowestByBrandVo.getBrand(), searchLowestByBrandVo.getTotalPrice());
        }

        return searchLowestByBrandVo;
    }

    public SearchHighLowByBrandVo searchHighLowByCategory(String category) {

        ProductInfo hightProduct = productInfoRepository.findTopByCategoryOrderByPriceDesc(category);
        log.debug("#### Highest Product >> {} ", hightProduct);

        ProductInfo lowProduct = productInfoRepository.findTopByCategoryOrderByPriceAsc(category);
        log.debug("#### Lowest Product >> {} ", lowProduct);

        return SearchHighLowByBrandVo.builder()
                .highestProduct(hightProduct)
                .lowestProduct(lowProduct)
                .build();
    }


}
