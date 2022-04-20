package com.codestates.hello.helloController

import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.ResponseBody
import reactor.core.publisher.Mono
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.reactive.function.server.ServerResponse
import org.springframework.http.MediaType
import org.springframework.web.reactive.function.client.WebClient

@Controller
class helloController {
	
	var jobClinet:WebClient = WebClient.create("http://localhost:8081");
	
	@GetMapping("/hello")
	@ResponseBody
	fun  hello(@RequestParam("name") name:String):Mono<ServerResponse>{

		var job = jobClinet
			.get().uri("/job").retrieve().bodyToMono(String.class);
		var result={
			var to: String = name
			var message: String = "hello "+name
			var job: String = job
		}
		
		return ServerResponse.ok().contentType(MediaType.APPLICATION_JSON).bodyValue(Mono.just(result));
	}
}