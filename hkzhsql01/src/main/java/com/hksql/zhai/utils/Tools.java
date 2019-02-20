package com.hksql.zhai.utils;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.regex.Pattern;

public class Tools {
	public static boolean isInteger(String str) {  
        Pattern pattern = Pattern.compile("^[-\\+]?[\\d]*$");  
        return pattern.matcher(str).matches();  
  }
	
	
	/**
	 * list去重
	 * @param list
	 */
	public static void removeDuplicate(List<String> list) {
	    LinkedHashSet<String> set = new LinkedHashSet<String>(list.size());
	    set.addAll(list);
	    list.clear();
	    list.addAll(set);
	}
	
	public static void writeFileContext(List<String>  strings, String path) {
		try{
			File file = new File(path);
	        //如果没有文件就创建
	        if (!file.isFile()) {
	            file.createNewFile();
	        }
	        BufferedWriter writer = new BufferedWriter(new FileWriter(path));
	        for (String l:strings){
	            writer.write(l + "\r\n");
	        }
	        writer.close();
		}catch(Exception e){
			e.printStackTrace();
		}
	}
    
	public static void main(String[] args) {
		List<String> a=new ArrayList<String>();
		List<String> b=new ArrayList<String>();
		a.add("1");
		b.addAll(a);
		System.out.println(b);
	}

}
