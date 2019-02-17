package com.lianxi.o2o.dto;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonProperty;

public class WechatUser implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 5631848268475663582L;
	//openId标识该公众号下面的该用户的唯一Id
	@JsonProperty("openId")
	private String openId;
	//用户昵称
	@JsonProperty("nickname")
	private String nickName;
	//性别
	@JsonProperty("sex")
	private int sex;
	//省份
	@JsonProperty("province")
	private String province;
	//城市
	@JsonProperty("city")
	private String city;
	//区
	@JsonProperty("county")
	private String county;
	//图像图片地址
	@JsonProperty("headimgurl")
	private String headImgUrl;
	//语言
	@JsonProperty("language")
	private String language;
	//用户权限，这里没什么作用
	@JsonProperty("privilege")
	private String[]  privilege;
	public String getOpenId() {
		return openId;
	}
	public void setOpenId(String openId) {
		this.openId = openId;
	}
	public String getNickName() {
		return nickName;
	}
	public void setNickName(String nickName) {
		this.nickName = nickName;
	}
	public int getSex() {
		return sex;
	}
	public void setSex(int sex) {
		this.sex = sex;
	}
	public String getProvince() {
		return province;
	}
	public void setProvince(String province) {
		this.province = province;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getCounty() {
		return county;
	}
	public void setCounty(String county) {
		this.county = county;
	}
	public String getHeadImgUrl() {
		return headImgUrl;
	}
	public void setHeadImgUrl(String headImgUrl) {
		this.headImgUrl = headImgUrl;
	}
	public String getLanguage() {
		return language;
	}
	public void setLanguage(String language) {
		this.language = language;
	}
	public String[] getPrivilege() {
		return privilege;
	}
	public void setPrivilege(String[] privilege) {
		this.privilege = privilege;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	@Override
	public String toString() {
		return "WechatUser [openId=" + openId + ", nickName=" + nickName + "]";
	}
}