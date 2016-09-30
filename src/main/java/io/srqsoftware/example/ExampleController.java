package io.srqsoftware.example;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/test")
public class ExampleController {

	@Autowired
	ExampleItemService itemService;

	@RequestMapping("/{person}")
	public ResponseEntity<String> getTestResponse(@PathVariable String person,
												  @RequestParam(name="isExcited", required=false) String isExcited) {
		String name = person + (((isExcited != null) && (isExcited.equals("y"))) ? "!" : "");
		return new ResponseEntity<String>("Hello, " + name, HttpStatus.OK);
	}
}
