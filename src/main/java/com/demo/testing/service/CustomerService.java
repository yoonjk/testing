package com.demo.testing.service;

import java.util.Optional;
import java.util.function.Function;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.demo.testing.domain.Customer;
import com.demo.testing.domain.QCustomer;
import com.demo.testing.dto.CustomerDto;
import com.demo.testing.dto.PageRequestDto;
import com.demo.testing.dto.PageResultDto;
import com.demo.testing.repository.CustomerRepository;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.dsl.BooleanExpression;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class CustomerService {
    @Autowired
    private CustomerRepository repository;


    public CustomerDto entityToDto(Customer entity) {

        CustomerDto dto = CustomerDto.builder()
                .id(entity.getId())
                .nickname(entity.getNickname())
                .email(entity.getEmail())
                .joinDate(entity.getJoinDate())
                .build();
        return dto;
    }

    public void remove(Long id) {
        repository.deleteById(id);
    }
    
    public Customer dtoToEntity(CustomerDto dto){
        Customer entity = Customer.builder()
                .id(dto.getId())
                .nickname(dto.getNickname())
                .email(dto.getEmail())
                .joinDate(dto.getJoinDate())
                .build();
        return entity;
    }   
    
    public Long register(CustomerDto dto) {

        log.info("DTO----------------------");
        log.info(dto.toString());

        Customer entity = dtoToEntity(dto);

      

        // 저장
        repository.save(entity);

        // 엔티티가 가지는 gno값 반환
        return entity.getId();
    }

    private BooleanBuilder getSearch(PageRequestDto requestDTO){
        //Querydsl 처리
        String type = requestDTO.getType();
        
        BooleanBuilder booleanBuilder = new BooleanBuilder();

        QCustomer qCustomer = QCustomer.customer;

        String keyword = requestDTO.getKeyword();

        // gno > 0 조건만 생성
        BooleanExpression expression = qCustomer.id.gt(0L);
        
        booleanBuilder.and(expression);
        
        // 검색 조건이 없는경우
        if(type == null || type.trim().length() == 0){
            return booleanBuilder;
        }
        
        // 검색 조건 작성
        
        BooleanBuilder conditionBuilder = new BooleanBuilder();
        
        if(type.contains("t")){
            conditionBuilder.or(qCustomer.nickname.contains(keyword));
        }
        if(type.contains("c")){
            conditionBuilder.or(qCustomer.email.contains(keyword));
        }
        if(type.contains("w")){
            conditionBuilder.or(qCustomer.joinDate.contains(keyword));
        }
        
        // 모든 조건 통합
        booleanBuilder.and(conditionBuilder);
        
        return booleanBuilder;
        
    }    
    
    public PageResultDto<CustomerDto, Customer> getList(PageRequestDto requestDTO) {

        Pageable pageable = requestDTO.getPageable(Sort.by("id").descending());

        // 검색 조건 처리
        BooleanBuilder booleanBuilder = getSearch(requestDTO);

        // booleanBuilder 추가하여 Querydsl 사용
        Page<Customer> result = repository.findAll(booleanBuilder, pageable);

        // entityToDto()를 이용해 Function생성하고 이를 PageResultDTO로 구성
        Function<Customer, CustomerDto> fn = (entity -> entityToDto(entity));

        // PageResultDTO : JPA의 처리결과인 Page<Entity> 와 Function을 전달해 엔티티 객체들을 DTO의 리스트로 변환하고 화면에 페이지 처리와 필요한 값들을 생성
        return new PageResultDto<>(result, fn);
    }
    
    public void modify(CustomerDto dto) {

        // 업데이트(수정) 하는 항목은 "제목", "내용" 만 수정하고 다시 저장하는 방식

        Optional<Customer> result = repository.findById(dto.getId());

        if(result.isPresent()){
            Customer entity = result.get();

            entity.setNickname(dto.getNickname());
            entity.setEmail(dto.getEmail());

            repository.save(entity);
        }
    }   
    public CustomerDto read(Long id) {

        // findById() 를 통해 엔티티 객체 가져오기
        Optional<Customer> result = repository.findById(id);

        // entityToDto()를 이용해 엔티티 객체를 DTO로 변환 해서 반환
        return result.isPresent()? entityToDto(result.get()) : null;
    }    
}
