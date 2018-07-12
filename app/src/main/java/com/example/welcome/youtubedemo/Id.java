package com.example.welcome.youtubedemo;

public class Id{
	private String kind;
	private String videoId;

	public void setKind(String kind){
		this.kind = kind;
	}

	public String getKind(){
		return kind;
	}

	public void setVideoId(String videoId){
		this.videoId = videoId;
	}

	public String getVideoId(){
		return videoId;
	}

	@Override
 	public String toString(){
		return 
			"Id{" + 
			"kind = '" + kind + '\'' + 
			",videoId = '" + videoId + '\'' + 
			"}";
		}
}
