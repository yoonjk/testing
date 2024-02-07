package com.demo.testing.dto;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class PageRequestDto {
	public static int DEFAULT_PAGE_SIZE = 3;
    private int page;
    private int size;
    // 검색을 위한 추가
    private String type;
    private String keyword;

    public PageRequestDto() {
        // 페이지 번호는 기본값을 가지는것이 좋기 때문에 기본값 설정
        this.page = 1;
        this.size = DEFAULT_PAGE_SIZE;
    }


    public Pageable getPageable(Sort sort) {
        // 페이지 번호가 0부터 시작한다는 점을 감안해 1페이지의 경우 0이 될 수 있도록 pagee-1하는 형태로 작성
        return PageRequest.of(page - 1, size, sort);
    }
}
