package com.defu.meeting;

import org.springframework.beans.factory.annotation.Value;

import com.ctrip.framework.apollo.Config;
import com.ctrip.framework.apollo.model.ConfigChange;
import com.ctrip.framework.apollo.model.ConfigChangeEvent;
import com.ctrip.framework.apollo.spring.annotation.ApolloConfig;
import com.ctrip.framework.apollo.spring.annotation.ApolloConfigChangeListener;

public class TestJavaConfigBean {
	@ApolloConfig("application")
	private Config config;
	@Value("${mytest:hhhhhhhhhh}")
	private String name;
	
    //config change listener for namespace application  
    @ApolloConfigChangeListener("application")  
    private void anotherOnChange(ConfigChangeEvent changeEvent) {  
          
              ConfigChange change = changeEvent.getChange("mytest"); 
              if(change ==null){
            	  return;
              }
              System.out.println(String.format("Found change - key: %s, oldValue: %s,"  
                      + " newValue: %s, changeType: %s", change.getPropertyName(), change.getOldValue(), change.getNewValue(), change.getChangeType()));  
     } 
}
