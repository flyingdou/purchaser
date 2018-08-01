package com.purchaser.pojo;

/**
 * 系统参数表(tb_parameter)
 * @author Administrator
 *
 */
public class Parameter {
    private Long id;

    // 标签标识
    private String code;

    // 具体项目名称
    private String name;

    // 本表id外键
    private Long parent;
    
    // value
    private String value;
    
    /**
     * 参数有效性
     */
    private String valid;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code == null ? null : code.trim();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public Long getParent() {
        return parent;
    }

    public void setParent(Long parent) {
        this.parent = parent;
    }

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}
	
	public String getValid() {
		return valid;
	}
	
	public void setValid(String valid) {
		this.valid = valid;
	}
}