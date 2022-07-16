package com.demo.searcher.controller;

import com.demo.searcher.service.ProductInfoService;
import com.demo.searcher.vo.ProductInfoVo;
import com.demo.searcher.vo.ResponseVo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping(path = "product")
public class ProductInfoController {

    private final ProductInfoService productInfoService;

    private static final String DEFAULT_SUCCESS_MESSAGE = "SUCCESS";

    /**
     *  상품 등록
     */
    @PostMapping(value = "/add")
    public ResponseVo addProduct(@RequestBody @Validated ProductInfoVo productInfoVo, BindingResult bindingResult) throws BindException {
        if (bindingResult.hasErrors()) {
            throw new BindException(bindingResult);
        }

        productInfoService.addProduct(productInfoVo);

        return ResponseVo.builder()
                .status(HttpStatus.OK.value())
                .timestamp(LocalDateTime.now())
                .result(DEFAULT_SUCCESS_MESSAGE)
                .build();
    }

    /**
     * 상품 수정
     */
    @PostMapping(value = "/update")
    public ResponseVo updateProduct(@RequestBody @Validated ProductInfoVo productInfoVo, BindingResult bindingResult) throws BindException {
        if (bindingResult.hasErrors()) {
            throw new BindException(bindingResult);
        }

        productInfoService.updateProduct(productInfoVo);

        return ResponseVo.builder()
                .status(HttpStatus.OK.value())
                .timestamp(LocalDateTime.now())
                .result(DEFAULT_SUCCESS_MESSAGE)
                .build();
    }

    /**
     * 상품 삭제
     */
    @PostMapping(value = "/remove")
    public ResponseVo removeProduct(@RequestParam int seq) {

        productInfoService.removeProduct(seq);

        return ResponseVo.builder()
                .status(HttpStatus.OK.value())
                .timestamp(LocalDateTime.now())
                .result(DEFAULT_SUCCESS_MESSAGE)
                .build();
    }


}
