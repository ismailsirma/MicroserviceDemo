package com.sirmam.security.controller;

import java.util.List;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.Collectors;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/numbers")
public class SecureRestController {

    @GetMapping
	public List<Integer> getNumbers(){
		return ThreadLocalRandom.current().ints(1,60).distinct().limit(6).sorted().boxed().collect(Collectors.toList());
	}
}
