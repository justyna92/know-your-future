package pl.myprojects.project3.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Horoscope {
	
	@JsonProperty("credit")
	String credit;
	@JsonProperty("meta")
	Meta meta;
	@JsonProperty("horoscope")
	String description;
	@JsonProperty("date")
	String date;
	@JsonProperty("sunsign")
	String sunsign;
	
	public Horoscope(){
		
	}
	public Horoscope(String credit, Meta meta, String description, String date, String sunsign) {
		super();
		this.credit = credit;
		this.meta = meta;
		this.description = description;
		this.date = date;
		this.sunsign = sunsign;
	}

	public String getCredit() {
		return credit;
	}

	public void setCredit(String credit) {
		this.credit = credit;
	}

	public Meta getMeta() {
		return meta;
	}

	public void setMeta(Meta meta) {
		this.meta = meta;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getSunsign() {
		return sunsign;
	}

	public void setSunsign(String sunsign) {
		this.sunsign = sunsign;
	}
	
	
	
	
}
