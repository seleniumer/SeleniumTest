package com.alibaba.selemiumer.utils;

import java.lang.reflect.Method;

import org.apache.commons.lang.StringUtils;
import org.testng.annotations.DataProvider;

public class ExcelPatch {
		private String excelFile;
	    private String relativePath;
	    @DataProvider(name = "excel")
	    public Object[][] excel(Method method) throws Exception {
	    DataFile dataFileAnn = method.getAnnotation(DataFile.class);
	        if (null == dataFileAnn) {
	            System.out.println("没有发现路径");
	        } else {
	            String path = dataFileAnn.path();	        
	            // 对于通过classpath的方式来指定数据池位置的写法,优先使用相对路径去查找数据池,以避免修改后需要clean的问题.
	            // changed by shan.lvs on 2012-11-13
	            if (StringUtils.startsWith(path, "classpath") && StringUtils.contains(path, ":")) {
	            	relativePath = StringUtils.split(path, ":")[1];    	               
	            }	
	        }
	        excelFile=DataFile.class.getResource("/").getPath()+relativePath;
	        Object[][] ObjArray = ExcelUtils.getTableArray(excelFile,dataFileAnn.sheetName());
			return (ObjArray);	
	    }	    
}