package pl.myprojects.project3.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.validation.Errors;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import pl.myprojects.project3.model.AjaxResponseBody;
import pl.myprojects.project3.model.SearchCriteria;
import pl.myprojects.project3.model.Horoscope;

@RestController
public class SearchController {

	@PostMapping(value="/api/search")
	public ResponseEntity<?> getSearchResultViaAjax(@RequestBody SearchCriteria search, Errors errors) {
		
		System.out.println(search);
		AjaxResponseBody result = new AjaxResponseBody();
		
		//If error return a bad request with the error message
		if(errors.hasErrors()) {
			result.setMsg(errors.getAllErrors()
								.stream().map(x -> x.getDefaultMessage())
								.collect(Collectors.joining(",")));
			return ResponseEntity.badRequest().body(result);
		}
				
		final String url = "http://sandipbgt.com/theastrologer/api/horoscope/"+search.getSunsign()+"/"+search.getDay();
        
        RestTemplate restTemplate = new RestTemplate();
        
        /* ***************converter ******************************************* */
        List<HttpMessageConverter<?>> messageConverters = new ArrayList<HttpMessageConverter<?>>();        
        
        //Add the Jackson Message converter
		MappingJackson2HttpMessageConverter converter = new MappingJackson2HttpMessageConverter();
		
		// here we are making this converter to process any kind of response, 
		// not only application/*json, which is the default behaviour
		converter.setSupportedMediaTypes(Arrays.asList(MediaType.ALL));         
		messageConverters.add(converter);  
		restTemplate.setMessageConverters(messageConverters);  
		/* ******************************************************************* */
		
		Horoscope description = restTemplate.getForObject(url, Horoscope.class);
      
        System.out.println("description: "+description);
				
		if(description.equals(null)) {
			result.setMsg("no horoscope found!");
		} else {
			result.setMsg("success");
		}
		
		result.setResult(description);
		
		return ResponseEntity.ok(result);
	}

}
