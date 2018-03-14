package pl.myprojects.project3.controller;

import java.util.Arrays;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.client.RestTemplate;

@Controller
public class IndexController {

    private static final String[] list = {"Spring Boot", "Thymeleaf", "Maven", "JavaScript", 
    		"jQuery", "jQuery.ajax", "HTML", "CSS", "Bootstrap", "external API: http://sandipbgt.com/theastrologer/api/"};
	
    @GetMapping("/technologies")
    public String about(Model model) {
    	
    	model.addAttribute("technologies", list);
    	
        return "technologies";
    }
    
    @GetMapping("/")
    public String index(Model model) {
    	
    	model.addAttribute("sunsigns", getSunsigns());
    	
        return "ajax";
    }
    
    private static List<String> getSunsigns()
    {
        final String url = "http://sandipbgt.com/theastrologer/api/sunsigns/";
         
        RestTemplate restTemplate = new RestTemplate();
		String result = restTemplate.getForObject(url, String.class);
		
        List<String> list = Arrays.asList(result.split(","));
        list.replaceAll(x -> x.substring(x.indexOf("\"")+1, x.lastIndexOf("\"")));
        
        System.out.println("result: "+list);
        
        return list;
    }

}