/**
 * 
 */
package com.mindtree.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author M1023212
 *
 */

@Controller
public class WelcomeController {
	
	@RequestMapping("/welcome")
	public String welcome(){
		return "welcome";
	}
}
