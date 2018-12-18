package com.hobart.boot.quartz.model;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.Serializable;

public class BaseModel implements Serializable{
	private static final long serialVersionUID = 1364719195974184093L;
	
	protected transient Logger logger;
	
	public BaseModel(){
		logger = LoggerFactory.getLogger(this.getClass());
		if(logger.isDebugEnabled()){
			logger.debug("create instance of class:"+getClass().getName());
		}
	}

	@Override
	public String toString(){
		return ToStringBuilder.reflectionToString(this, ToStringStyle.MULTI_LINE_STYLE);
	}
	
	@Override
	public boolean equals(Object obj){
		return EqualsBuilder.reflectionEquals(this, obj);
	}
	
	@Override
	public int hashCode(){
		return HashCodeBuilder.reflectionHashCode(this);
	}
}
