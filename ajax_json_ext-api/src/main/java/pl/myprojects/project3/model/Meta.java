package pl.myprojects.project3.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Meta {

	@JsonProperty("keywords")
	String keywords;
	@JsonProperty("mood")
	String mood;
	@JsonProperty("intensity")
	String intensity;
	
	public Meta () {
		
	}
	
	public Meta(String keywords, String mood, String intensity) {
		this.keywords = keywords;
		this.mood = mood;
		this.intensity = intensity;
	}
	
	public String getKeywords() {
		return keywords;
	}
	public void setKeywords(String keywords) {
		this.keywords = keywords;
	}
	public String getMood() {
		return mood;
	}
	public void setMood(String mood) {
		this.mood = mood;
	}
	public String getIntensity() {
		return intensity;
	}
	public void setIntensity(String intensity) {
		this.intensity = intensity;
	}
	
}
