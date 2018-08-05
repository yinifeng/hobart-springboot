package com.hobart.amqp;

import java.util.Arrays;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

import org.junit.Test;
import org.springframework.boot.autoconfigure.amqp.RabbitProperties;
import org.springframework.util.StringUtils;

/**
 * rabbitmq 集群addresses
 * @author hobart
 *
 */
public class RabbitPropertiesTest {
	
	@Test
	public void test1(){
		RabbitProperties rp=new RabbitProperties();
		rp.setAddresses("192.168.1.100:5673,192.168.1.101:5673,192.168.1.102:5673");
		System.out.println(rp.determineAddresses());
		System.out.println(rp.determineHost());
		System.out.println(rp.determinePort());
		
		System.out.println(shuffle(rp.determineAddresses()));
		
	}
	
    private String shuffle(String addresses) {
        String[] addrArr = StringUtils.commaDelimitedListToStringArray(addresses);
        List<String> addrList = Arrays.asList(addrArr);
        System.out.println("befor->"+addrList);
        Collections.shuffle(addrList);
        System.out.println("after->"+addrList);

        StringBuilder stringBuilder = new StringBuilder();
        Iterator<String> iter = addrList.iterator();
        while (iter.hasNext()) {
            stringBuilder.append(iter.next());
            if (iter.hasNext()) {
                stringBuilder.append(",");
            }
        }
        return stringBuilder.toString();
    }
}
