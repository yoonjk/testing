package com.demo.testing.controller;

import java.util.Map;
import java.util.TreeMap;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
public class GoogleChartsController {

	@GetMapping("/charts")
	public String getPieChart(Model model) {
		Map<String, Integer> graphData = new TreeMap<>();
		graphData.put("2016", 147);
		graphData.put("2017", 1256);
		graphData.put("2018", 3856);
		graphData.put("2019", 19807);
		model.addAttribute("chartData", graphData);
		
		log.info("charts testing");
		
		return "/pages/google-charts";
	}
}
