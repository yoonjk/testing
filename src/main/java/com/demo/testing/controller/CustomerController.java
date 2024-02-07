package com.demo.testing.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.demo.testing.dto.CustomerDto;
import com.demo.testing.dto.PageRequestDto;
import com.demo.testing.service.CustomerService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
public class CustomerController {
	@Autowired
	private CustomerService customerService;
	
    @GetMapping("/customer/register")
    public void register(){

    }
    
    @GetMapping({"/customer/read","/customer/modify"})
    public void read(Long id,@ModelAttribute("requestDto") PageRequestDto requestDto,Model model){

        log.info("id..." + id);
        
        CustomerDto dto = customerService.read(id);

        model.addAttribute("dto",dto);
    }
    
    @PostMapping("/customer/register")
    // RedirectAttributes 사용해 한 번만 화면에서 msg라를 변수를 사용할 수 있도록 처리
    public String registerPost(CustomerDto dto, RedirectAttributes redirectAttributes){
        
        log.info("dto..." + dto);
        
        // 새로 추가된 엔티티의 번호
        
        Long gno = customerService.register(dto);
        
        // addFlashAttribute() : 단 한번 만 데이터를 전달하는 용도로 사용
        redirectAttributes.addFlashAttribute("msg", gno);
        
        return "redirect:/customer/list";
    }	
	
    @PostMapping("/customer/modify")
    // GuestbookDTO : 수정해야 하는 글의 정보를 가짐
    // PageRequestDTO : 기존의 페이지 정보를 유지하기위한것
    // RedirectAttributes : 리다이렉트로 이동
    public String modify(CustomerDto dto, @ModelAttribute("requestDto") PageRequestDto requestDTO, RedirectAttributes redirectAttributes){
        log.info("post modify............................................");
        log.info("dto : " + dto );
        
        customerService.modify(dto);
        
        redirectAttributes.addAttribute("page",requestDTO.getPage());
        redirectAttributes.addAttribute("id", dto.getId());
        
        // 수정후 조회페이지로 리다이렉트 처리될 대 검색 조건을 유지하도록 추가했다
        redirectAttributes.addAttribute("type",requestDTO.getType());
        redirectAttributes.addAttribute("keyword", requestDTO.getKeyword());
        
        return "redirect:/customer/read";
    }
    
    @PostMapping("/customer/remove")
    public String remove(long gno,RedirectAttributes redirectAttributes){
        
        log.info("gno : " + gno);
        
        customerService.remove(gno);

        // addFlashAttribute 라는 메소드를 통해 String 데이터를 전달
        // addFlashAttribute 의 경우 데이타가 post 형식으로 전달
        // addFlashAttribute 의 경우 데이타가 한번만 사용
        redirectAttributes.addFlashAttribute("msg",gno);
        
        return "redirect:/customer/list";
    }
    

    
	@GetMapping("/customer/list")
    public String list(PageRequestDto pageRequestDTO, Model model){

        log.info("list............." + pageRequestDTO);

        model.addAttribute("result", customerService.getList(pageRequestDTO));

        return "/customer/list";
    }
}
