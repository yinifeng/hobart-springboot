package com.hobart.mybatis.model;

import java.io.Serializable;
import java.util.Date;

public class CustCode implements Serializable {

	private static final long serialVersionUID = 1L;
	private Long id;
	private String codeType;
	private String typeName;
	private String code;
	private String codeVal;
	private String status;
	private Date updatetime;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getCodeType() {
		return codeType;
	}

	public void setCodeType(String codeType) {
		this.codeType = codeType;
	}

	public String getTypeName() {
		return typeName;
	}

	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getCodeVal() {
		return codeVal;
	}

	public void setCodeVal(String codeVal) {
		this.codeVal = codeVal;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Date getUpdatetime() {
		return updatetime;
	}

	public void setUpdatetime(Date updatetime) {
		this.updatetime = updatetime;
	}

	@Override
	public String toString() {
		return "CustCode [id=" + id + ", codeType=" + codeType + ", typeName=" + typeName + ", code=" + code
				+ ", codeVal=" + codeVal + ", status=" + status + ", updatetime=" + updatetime + "]";
	}

}
