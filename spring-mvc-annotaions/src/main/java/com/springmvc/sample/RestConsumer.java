package com.springmvc.sample;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("/consumer")
public class RestConsumer {
	
	@Autowired
	private RestTemplate restTemplate;

	@RequestMapping(method=RequestMethod.GET)
	public LoginForm getJson() {
		LoginForm form = restTemplate.getForObject("http://localhost:8081/mvcsample/main/login/form", LoginForm.class);
		System.out.println(form.getUserName());
		return form;
	}

}
