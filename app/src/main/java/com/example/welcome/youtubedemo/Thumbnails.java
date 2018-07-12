package com.example.welcome.youtubedemo;

public class Thumbnails{
	private JsonMemberDefault jsonMemberDefault;
	private High high;
	private Medium medium;

	public void setJsonMemberDefault(JsonMemberDefault jsonMemberDefault){
		this.jsonMemberDefault = jsonMemberDefault;
	}

	public JsonMemberDefault getJsonMemberDefault(){
		return jsonMemberDefault;
	}

	public void setHigh(High high){
		this.high = high;
	}

	public High getHigh(){
		return high;
	}

	public void setMedium(Medium medium){
		this.medium = medium;
	}

	public Medium getMedium(){
		return medium;
	}

	@Override
 	public String toString(){
		return 
			"Thumbnails{" + 
			"default = '" + jsonMemberDefault + '\'' + 
			",high = '" + high + '\'' + 
			",medium = '" + medium + '\'' + 
			"}";
		}
}
