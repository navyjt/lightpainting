package com.lightpainting.app;

public class FavItem {

	
	private String text;
	private String color;
	private String fontsize;
	private String time;
	private String delay;
	private String id;
	/**
	 * @return the mText
	 * 
	 * 
	 */
	public FavItem(String text,String color,String fontSize,String time,String delay, String id){
		
		this.text = text;
		this.color = color;
		this.fontsize = fontSize;
		this.time = time;
		this.delay = delay;
		this.id = id;
		
		
		
	}
	
	
	public String getText() {
		return text;
	}
	/**
	 * @param mText the mText to set
	 */
	public void setText(String text) {
		this.text =text;
	}
	/**
	 * @return the mColor
	 */
	public String getColor() {
		return color;
	}
	/**
	 * @param mColor the mColor to set
	 */
	public void setColor(String color) {
		this.color = color;
	}
	/**
	 * @return the mFontsize
	 */
	public String getFontsize() {
		return fontsize;
	}
	/**
	 * @param mFontsize the mFontsize to set
	 */
	public void setFontsize(String fontsize) {
		this.fontsize = fontsize;
	}
	/**
	 * @return the mTime
	 */
	public String getTime() {
		return time;
	}
	/**
	 * @param mTime the mTime to set
	 */
	public void setTime(String time) {
		this.time = time;
	}
	/**
	 * @return the mDelay
	 */
	public String getDelay() {
		return delay;
	}
	/**
	 * @param mDelay the mDelay to set
	 */
	public void setDelay(String delay) {
		this.delay = delay;
	}
	/**
	 * @return the mId
	 */
	public String getId() {
		return id;
	}
	/**
	 * @param mId the mId to set
	 */
	public void setId(String id) {
		this.id = id;
	}
	
}
