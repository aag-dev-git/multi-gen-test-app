package com.demo.testApp.entities;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Test {

	
	public static void main(String[] args) {
		
		
		List<String> list= new ArrayList<String>();
		
		list.add("john");
		list.add("smith");
		list.add("mark");
		
		Map<Integer, List<String>> collect = list.stream().collect(Collectors.groupingBy(String::length));
		
		System.out.println(collect );
		
		
	}
}
