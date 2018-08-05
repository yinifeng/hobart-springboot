package com.hobart.mybatis.filter;

import javax.servlet.annotation.WebFilter;
import javax.servlet.annotation.WebInitParam;

import com.alibaba.druid.support.http.WebStatFilter;

/**   
 * 配置监控拦截器 
 * druid监控拦截器   
 * @ClassName: DruidStatFilter    
 * @author hobart    
 */    
@WebFilter(filterName="druidWebStatFilter",    
urlPatterns="/*",    
initParams={    
    @WebInitParam(name="exclusions",value="*.js,*.gif,*.jpg,*.bmp,*.png,*.css,*.ico,/druid/*"),// 忽略资源    
})
public class DruidStatFilter extends WebStatFilter{

}
