package com.hubo.resource.classloader;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public class PathClassLoader extends ClassLoader{
	private String classPath;
	
	public PathClassLoader(String classPath) {
		this.classPath=classPath;
	}
	
	@Override
	protected Class<?> findClass(String name) throws ClassNotFoundException {
		String classFileName = getFileName(name);
		File file = new File(classPath,classFileName);
		try {
			FileInputStream is = new FileInputStream(file);
			ByteArrayOutputStream bos=new ByteArrayOutputStream();
			int len=0;
			while ((len = is.read()) != -1) {
				bos.write(len);
			}
			byte[] data = bos.toByteArray();
			is.close();
			bos.close();
			if(data == null){
				throw new ClassNotFoundException();
			}
			return defineClass(name, data, 0, data.length);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return super.findClass(name);
	}

	private String getFileName(String name) {
		int index = name.indexOf('.');
		if(index == -1){
			return name+".class";
		}else{
			return name.substring(index+1)+".class";
		}
	}
}
