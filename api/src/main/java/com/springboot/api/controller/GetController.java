package com.springboot.api.controller;

import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.springboot.api.dto.MemberDto;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

@RestController
@RequestMapping("/api/v1/get-api")
public class GetController {

	private final Logger LOGGER = LoggerFactory.getLogger(GetController.class);

	// http://localhost:8080/api/v1/get-api/hello
	@RequestMapping(value = "/hello", method = RequestMethod.GET)
	public String getHello() {
		LOGGER.info("getHello 메서드가 호출되었습니다.");
		return "Hello World";
	}

	// http://localhost:8080/api/v1/get-api/name
	@GetMapping(value = "/name")
	public String getName() {
		LOGGER.info("getName 메서드가 호출되었습니다.");
		return "Flature";
	}

	// http://localhost:8080/api/v1/get-api/variable1/{String 값}
	@GetMapping(value = "/variable1/{variable}")
	public String getVariable1(@PathVariable String variable) {
		LOGGER.info("@PathVariable을 통해 들어온 값 : {}", variable);
		return variable;
	}

	// http://localhost:8080/api/v1/get-api/variable2/{String 값}
	@GetMapping(value = "/variable2/{variable}")
	public String getVariable2(@PathVariable("variable") String var) {
		return var;
	}

	// http://localhost:8080/api/v1/get-api/request1?name=value&email=value2&organization=value3
	@ApiOperation(value = "GET 메서드 예제", notes = "@RequestParam을 활용한 GET Method")
	@GetMapping(value = "/request1")
	public String getRequestParam1(@ApiParam(value = "이름", required = true) @RequestParam String name,
			@ApiParam(value = "이메일", required = true) @RequestParam String email,
			@ApiParam(value = "회사", required = true) @RequestParam String organization) {
		return name + " " + email + " " + organization;
	}

	// 퀴리스트링에 어떤 값이 들어올지 모른다면 Map 객체를 활용하면 편리하다
	// http://localhost:8080/api/v1/get-api/request2?key1=value1&key2=value2
	@GetMapping(value = "/request2")
	public String getRequestParam2(@RequestParam Map<String, String> param) {
		StringBuilder sb = new StringBuilder();
		param.entrySet().forEach(map -> {
			sb.append(map.getKey() + " : " + map.getValue() + "\n");
		});
		return sb.toString();
	}

	// http://localhost:8080/api/v1/get-api/request3?name=value&email=value2&organization=value3
	@GetMapping(value = "/request3")
	public String getRequestParam3(MemberDto memberDto) {
		return memberDto.toString();
	}

}
