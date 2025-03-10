package com.yaksha.assignment.controller;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class DateTimeController {

	// Display page to show current date and time using JSP scripting elements
	@GetMapping("/datetime")
	public String showDateTime(Model model) {
		// Prepare the current date and time
		String currentDateTime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());

		model.addAttribute("currentDateTime", currentDateTime);
		return "datetime";
	}
}
