package com.hobart.httpserver.utils;

import com.hobart.httpserver.annotation.MyAutowired;
import com.hobart.httpserver.annotation.MyController;
import com.hobart.httpserver.annotation.MyRepository;
import com.hobart.httpserver.annotation.MyService;

import java.io.File;
import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.net.URL;
import java.util.*;

/**
 * 扫描工具类
 * 1、扫描包下的注解
 * 2、扫描包下的类名
 */
public class ScanUtil {
    private static List<String> listClassName=new ArrayList<>();

    private static List<String> compomentList=new ArrayList<>();

    private static Map<String,String> interfaceAndImplMap=new HashMap<>();

    /**
     * 扫描包下的所有类名
     */
    public static List<String> getClassName(String packageName){
        ClassLoader contextClassLoader = Thread.currentThread().getContextClassLoader();
        String newPackageName = packageName.replace(".", "/");
        try {
            Enumeration<URL> urls = contextClassLoader.getResources(newPackageName);
            while (urls.hasMoreElements()){
                URL url = urls.nextElement();
                File packageFile = new File(url.getPath());
                File[] files = packageFile.listFiles();
                if(files == null){
                    break;
                }
                for (File file:files){
                    //如果是class，则添加到list中返回
                    if(file.getName().endsWith(".class")){
                        String templeName = packageName.replace("/", ".") + "." + file.getName();
                        String newTempleName = templeName.substring(0, templeName.lastIndexOf("."));
                        listClassName.add(newTempleName);
                    }else{
                        String nextPackageName = newPackageName + "." + file.getName();
                        getClassName(nextPackageName);
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return listClassName;
    }

    /**
     * 返回 有注解的实例化顺序的链表
     * @param packageName
     * @return
     */
    public static List<String> getComponentList(String packageName){
        List<String> classNameList = getClassName(packageName);
        makeInterfaceAndImplMap(classNameList);
        for(String className : classNameList){
            try {
                resolveComponent(className);
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
        return compomentList;
    }

    private static void resolveComponent(String className) throws ClassNotFoundException {
        Class<?> aClass = Class.forName(className);
        //在此处添加要识别的注解，也是每次扫描的顺序，最好遵循习惯
        addNewAnnotation(MyController.class,aClass);
        addNewAnnotation(MyService.class,aClass);
        addNewAnnotation(MyRepository.class,aClass);

    }

    private static <A extends Annotation> void addNewAnnotation(Class<A> annotatationClass, Class<?> aClass) throws ClassNotFoundException {
        //如果类上有注解，判断属性有没有注解
        if (aClass.getAnnotation(annotatationClass) !=null){
            Field[] fields = aClass.getDeclaredFields();
            if(fields == null || fields.length == 0){
                //ListAddUtils.add(componentList,aClass.getName());
            }else{
                //跳出递归语句，也就是最底层的类，如果没有属性没有@MyAutowired注解，则加入集合
                if(isEmptyAutowired(fields)){
                    //ListAddUtils.add(componentList,aClass.getName());
                }else{
                    for(Field field : fields){
                        if(field.getAnnotation(MyAutowired.class) !=null){
                            //如果有则根据类名查找类，然后去对应的类中递归此过程
                            String newFieldName = field.getType().getName();
                            //如果是接口，则用其实现类注入
                            if(Class.forName(newFieldName).isInterface()){
                                String nextName = convertInterfaceToImpl(newFieldName);
                                if(!compomentList.contains(nextName)){
                                    resolveComponent(nextName);
                                }
                            }else{
                                resolveComponent(newFieldName);
                            }
                        }
                    }

                }
                //ListAddUtils.add(componentList,aClass.getName());
            }
        }else if(aClass.isInterface() && "proxy".equals(interfaceAndImplMap.get(aClass.getName()))){
            //ListAddUtils.add(componentList,aClass.getName());
        }

    }

    private static String convertInterfaceToImpl(String newFieldName) {
        for(Map.Entry<String,String> entry:interfaceAndImplMap.entrySet()){
            if(newFieldName.equals(entry.getKey()) && !"proxy".equals(entry.getValue())){
                return entry.getValue();
            }else if(newFieldName.equals(entry.getKey()) && "proxy".equals(entry.getValue())){
                return entry.getKey();
            }
        }
        return null;
    }

    private static boolean isEmptyAutowired(Field[] fields) {
        for(Field field : fields){
            if(field.getAnnotation(MyAutowired.class) !=null){
                return false;
            }
        }
        return true;
    }

    private static Map<String,String> makeInterfaceAndImplMap(List<String> classNameList) {
        Class<?> aClass=null;
        //所有接口类名的链表
        List<String> interfaceNameList=new ArrayList<>();
        //这个集合保存的是实现类的接口链表名，默认没有实现类的接口即为需要动态注入的链表
        List<String> interfaceExist=new ArrayList<>();

        for(String className : classNameList){
            try {
                aClass = Class.forName(className);
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
            if(aClass.isInterface()){
                interfaceNameList.add(aClass.getName());
            }
        }
        for(String className : classNameList){
            Class<?> bClass = null;
            try {
                bClass = Class.forName(className);
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
            Class<?>[] interfaces = bClass.getInterfaces();
            if(interfaces != null && interfaces.length > 0){
                for(String interfaceName:interfaceNameList){
                    for(Class<?> interfaceClass :interfaces){
                        //如果既有接口，又有实现类
                        if(interfaceName.equals(interfaceClass.getName())){
                            interfaceAndImplMap.put(interfaceName,className);
                            interfaceExist.add(interfaceName);
                            
                        }
                    }
                }
            }
        }

        //需要动态代理注入的接口，在map中用value = proxy来识别
        if(interfaceNameList !=null && interfaceNameList.size() > 0){
            interfaceNameList.removeAll(interfaceExist);
            for(String spareInterfaceName : interfaceNameList){
                interfaceAndImplMap.put(spareInterfaceName,"proxy");
            }
            System.out.println("已经存在的" + interfaceNameList);
        }
        return interfaceAndImplMap;
    }

    public static void main(String[] args) {
        List<String> className = getClassName("com.hubo.idea");
        className.forEach(System.out::println);
    }
}
