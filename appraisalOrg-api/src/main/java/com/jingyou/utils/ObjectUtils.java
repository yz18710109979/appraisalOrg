package com.jingyou.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.*;

public class ObjectUtils {


    private static final Logger logger = LoggerFactory.getLogger(ObjectUtils.class);

    /**
     * 根据属性名获取属性值
     * @param o 反射需要的实体类
     * @param fieldName 字段属性
     * @return
     */
    public static Object getFieldValueByName(Object o,String fieldName) {
        return getFieldValueByName(o,fieldName,null);
    }

    /**
     * 根据属性名获取属性值
     * @param o 反射需要的实体类
     * @param fieldName 字段属性
     * @param fieldValue 字段属性返回默认值
     * @return
     */
    public static Object getFieldValueByName(Object o,String fieldName,String fieldValue) {
        try {
            if(Objects.isNull(o)) return fieldValue;
            String firstLetter = fieldName.substring(0, 1).toUpperCase();
            String getter = "get" + firstLetter + fieldName.substring(1);
            Method method = o.getClass().getMethod(getter, new Class[]{});
            Object value = method.invoke(o, new Object[]{});
            return value;
        } catch (Exception e) {
            logger.error("根据属性名获取属性值异常" + e.getMessage());
            return fieldValue;
        }
    }

    /**
     * 将实体Stirng类型为null的值替换
     * @param t 需要修改对象
     * @param beans 通过 Object.class.getDeclaredFields()获取需要替换的属性数组 例如： Field[]
     *            beans = ApplicationVo.class.getDeclaredFields();
     * @param toString 期望替换值
     */
    public static <T> void nullConventsEmpty(T t, Field[] beans, String toString){
        for (Field field : beans){
            if (("String").equals(field.getType().getSimpleName()) && (ObjectUtils.getFieldValueByName(t,field.getName()) == null)){
                String firstLetter = field.getName().substring(0, 1).toUpperCase();
                String setter = "set" + firstLetter + field.getName().substring(1);
                try{
                    Class[] parameterTypes = new Class[1];// 这里你要调用的方法只有一个参数

                    parameterTypes[0] = String.class;// 这个参数的类型是String型的

                    Method method = t.getClass().getMethod(setter, parameterTypes);
                    try{
                        method.invoke(t, new Object[] { toString });
                    }catch (IllegalAccessException e){
                        e.printStackTrace();
                        logger.error("将实体Stirng类型为null的值替换异常:" + e.getMessage(), e);
                    }
                    catch (IllegalArgumentException e){
                        e.printStackTrace();
                        logger.error("将实体Stirng类型为null的值替换异常:" + e.getMessage(), e);
                    }
                    catch (InvocationTargetException e){
                        e.printStackTrace();
                        logger.error("将实体Stirng类型为null的值替换异常:" + e.getMessage(), e);
                    }

                }catch (NoSuchMethodException e){
                    logger.error("将实体Stirng类型为null的值替换异常:" + e.getMessage(), e);
                    e.printStackTrace();
                }catch (SecurityException e){
                    e.printStackTrace();
                    logger.error("将实体Stirng类型为null的值替换异常:" + e.getMessage(), e);
                }

            }
        }
    }

    /**
     * 将实体Stirng类型为null的值替换
     * @param t 需要修改对象
     * @param beans 通过 Object.class.getDeclaredFields()获取需要替换的属性数组 例如： Field[]
     *            beans = ApplicationVo.class.getDeclaredFields();
     */
    public static <T> void emptyConventsNull(T t, Field[] beans){
        for (Field field : beans){
            if (("String").equals(field.getType().getSimpleName()) && ("").equals(ObjectUtils.getFieldValueByName(t,field.getName()))){
                String firstLetter = field.getName().substring(0, 1).toUpperCase();
                String setter = "set" + firstLetter + field.getName().substring(1);
                try{
                    Class[] parameterTypes = new Class[1];// 这里你要调用的方法只有一个参数

                    parameterTypes[0] = String.class;// 这个参数的类型是String型的

                    Method method = t.getClass().getMethod(setter, parameterTypes);
                    try{
                        method.invoke(t, new Object[] { null });
                    }catch (IllegalAccessException e){
                        e.printStackTrace();
                        logger.error("将实体Stirng类型为null的值替换异常:" + e.getMessage(), e);
                    }
                    catch (IllegalArgumentException e){
                        e.printStackTrace();
                        logger.error("将实体Stirng类型为null的值替换异常:" + e.getMessage(), e);
                    }
                    catch (InvocationTargetException e){
                        e.printStackTrace();
                        logger.error("将实体Stirng类型为null的值替换异常:" + e.getMessage(), e);
                    }

                } catch (NoSuchMethodException e){
                    logger.error("将实体Stirng类型为null的值替换异常:" + e.getMessage(), e);
                    e.printStackTrace();
                }catch (SecurityException e){
                    e.printStackTrace();
                    logger.error("将实体Stirng类型为null的值替换异常:" + e.getMessage(), e);
                }

            }
        }
    }

    /**
     * 去掉bean中所有属性包含&nbsp;<br>
     * @param bean
     * @throws Exception
     */
    public static void beanAttributeValueNbsp(Object bean) throws Exception {
        if (bean != null) {
            //获取所有的字段包括public,private,protected,private
            // 获取实体类的所有属性，返回Field数组
            Field[] field = bean.getClass().getDeclaredFields();
            try {

                for (int j = 0; j < field.length; j++) { // 遍历所有属性
                    String name = field[j].getName(); // 获取属性的名字
                    // 将属性的首字符大写，方便构造get，set方法
                    name = name.substring(0, 1).toUpperCase() + name.substring(1);
                    // 获取属性的类型
                    String type = field[j].getGenericType().toString();
                    // 如果type是类类型，则前面包含"class "，后面跟类名
                    if (type.equals("class java.lang.String")) {
                        Method m = bean.getClass().getMethod("get" + name);
                        // 调用getter方法获取属性值
                        String value = (String) m.invoke(bean);

                        if (value == null) {
                            m = bean.getClass().getMethod("set" + name, String.class);
                            m.invoke(bean, "");

                        }else if(value.contains("<br>")||value.contains("&nbsp;")){
                            //保存委托书业务中使用,前台传递包含空格和换行符的情况,需要替换一下
                            value = value.replaceAll("<[.[^>]]*>","");
                            value = value.replaceAll(" ", "");
                            value = value.replace("&nbsp;","");
                            value = value.replace("<br>","");
                            m = bean.getClass().getMethod("set" + name, String.class);
                            m.invoke(bean, value);
                        }else{
                            value = value.replaceAll("<[.[^>]]*>","");
                            value = value.replace("<br>","");
                            value = value.replace("&nbsp;","");
                            value = value.replaceAll(" ", "");
                            m = bean.getClass().getMethod("set" + name, String.class);
                            m.invoke(bean, value);
                        }
                    }
                    /*if (type.equals("class java.lang.Integer")) {
                        Method m = bean.getClass().getMethod("get" + name);
                        Integer value = (Integer) m.invoke(bean);
                        if (value == null) {
                            m = bean.getClass().getMethod("set" + name, Integer.class);
                            m.invoke(bean, 0);
                        }
                    }
                    if (type.equals("class java.lang.Boolean")) {
                        Method m = bean.getClass().getMethod("get" + name);
                        Boolean value = (Boolean) m.invoke(bean);
                        if (value == null) {
                            m = bean.getClass().getMethod("set" + name, Boolean.class);
                            m.invoke(bean, false);
                        }
                    }
                    if (type.equals("class java.util.Date")) {
                        Method m = bean.getClass().getMethod("get" + name);
                        Date value = (Date) m.invoke(bean);
                        if (value == null) {
                            m = bean.getClass().getMethod("set" + name, Date.class);
                            m.invoke(bean, new Date());
                        }
                    }*/
                }
            } catch (NoSuchMethodException e){
                e.printStackTrace();
            } catch (SecurityException e){
                e.printStackTrace();
            } catch (IllegalAccessException e){
                e.printStackTrace();
            } catch (IllegalArgumentException e){
                e.printStackTrace();
            } catch (InvocationTargetException e){
                e.printStackTrace();
            }
        }
    }

    /**
     * 去掉bean中所有属性包含空字符串的,替换为相应的字符串
     * @param bean
     * @throws Exception
     */
    public static void beanAttributeValueReplace(Object bean,String replaceString) throws Exception {
        if (bean != null) {
            //获取所有的字段包括public,private,protected,private
            // 获取实体类的所有属性，返回Field数组
            Field[] field = bean.getClass().getDeclaredFields();
            try {

                for (int j = 0; j < field.length; j++) { // 遍历所有属性
                    String name = field[j].getName(); // 获取属性的名字
                    // 将属性的首字符大写，方便构造get，set方法
                    name = name.substring(0, 1).toUpperCase() + name.substring(1);
                    // 获取属性的类型
                    String type = field[j].getGenericType().toString();
                    // 如果type是类类型，则前面包含"class "，后面跟类名
                    if (type.equals("class java.lang.String")) {
                        Method m = bean.getClass().getMethod("get" + name);
                        // 调用getter方法获取属性值
                        String value = (String) m.invoke(bean);
                        if (value == null) {
                            m = bean.getClass().getMethod("set" + name, String.class);
                            m.invoke(bean, "");
                        }else if("".equals(value)){
                            //保存委托书业务中使用""需要替换一下"___"
                            m = bean.getClass().getMethod("set" + name, String.class);
                            m.invoke(bean, replaceString);
                        }
                    }
                }
            } catch (NoSuchMethodException e){
                e.printStackTrace();
            } catch (SecurityException e){
                e.printStackTrace();
            } catch (IllegalAccessException e){
                e.printStackTrace();
            } catch (IllegalArgumentException e){
                e.printStackTrace();
            } catch (InvocationTargetException e){
                e.printStackTrace();
            }
        }
    }

    /**
     * 去掉bean中所有属性为字符串的前后空格
     * @param t
     * @throws Exception
     */
    public static void beanAttributeValueTrim(Object t) throws Exception {
        if(t!=null){
            //获取所有的字段包括public,private,protected,private
            Field[] fields = t.getClass().getDeclaredFields();
            for (int i = 0; i < fields.length; i++) {
                Field f = fields[i];
                if (f.getType().getName().equals("java.lang.String")) {
                    String key = f.getName();//获取字段名
                    Object value = getFieldValue(t, key);

                    if (value == null)
                        continue;

                    setFieldValue(t, key, value.toString().trim());
                }
            }
        }
    }

    /**
     * 利用反射通过get方法获取bean中字段fieldName的值
     * @param t
     * @param fieldName
     * @return
     * @throws Exception
     */
    private static Object getFieldValue(Object t, String fieldName)
            throws Exception {
        StringBuffer result = new StringBuffer();
        String methodName = result.append("get")
                .append(fieldName.substring(0, 1).toUpperCase())
                .append(fieldName.substring(1)).toString();

        Object rObject = null;
        Method method = null;

        @SuppressWarnings("rawtypes")
        Class[] classArr = new Class[0];
        method = t.getClass().getMethod(methodName, classArr);
        rObject = method.invoke(t, new Object[0]);

        return rObject;
    }

    /**
     * 利用反射调用bean.set方法将value设置到字段
     * @param bean
     * @param fieldName
     * @param value
     * @throws Exception
     */
    private static void setFieldValue(Object bean, String fieldName, Object value)
            throws Exception {
        StringBuffer result = new StringBuffer();
        String methodName = result.append("set")
                .append(fieldName.substring(0, 1).toUpperCase())
                .append(fieldName.substring(1)).toString();

        /**
         * 利用反射调用bean.set方法将value设置到字段
         */
        Class[] classArr = new Class[1];
        classArr[0]="java.lang.String".getClass();
        Method method=bean.getClass().getMethod(methodName,classArr);
        method.invoke(bean,value);
    }

    /**
     * 将对象中的空字符串转为"0"
     * @param bean
     * @throws Exception
     */
    public static void changeStringForEvaluate(Object bean) throws Exception {
        if (bean != null) {
            //获取所有的字段包括public,private,protected,private
            // 获取实体类的所有属性，返回Field数组
            Field[] field = bean.getClass().getDeclaredFields();
            try {

                for (int j = 0; j < field.length; j++) { // 遍历所有属性
                    String name = field[j].getName(); // 获取属性的名字
                    // 将属性的首字符大写，方便构造get，set方法
                    name = name.substring(0, 1).toUpperCase() + name.substring(1);
                    // 获取属性的类型
                    String type = field[j].getGenericType().toString();
                    // 如果type是类类型，则前面包含"class "，后面跟类名
                    if (type.equals("class java.lang.String")) {
                        Method m = bean.getClass().getMethod("get" + name);
                        // 调用getter方法获取属性值
                        String value = (String) m.invoke(bean);
                        if (value == null) {
                            m = bean.getClass().getMethod("set" + name, String.class);
                            m.invoke(bean, "0");
                        }
                    }
                }
            } catch (NoSuchMethodException e){
                e.printStackTrace();
            } catch (SecurityException e){
                e.printStackTrace();
            } catch (IllegalAccessException e){
                e.printStackTrace();
            } catch (IllegalArgumentException e){
                e.printStackTrace();
            } catch (InvocationTargetException e){
                e.printStackTrace();
            }
        }
    }

    /**
     * 把对象转成字符串
     * @param obj
     * @return
     */
    public static String convertObjectToString(Object obj) {
        return convertObjectToString(obj,"");
    }

    /**
     * 把对象转成字符串
     * @param obj
     * @param defalutValue 设置默认值
     * @return
     */
    public static String convertObjectToString(Object obj, String defalutValue) {
        try {
            if (Objects.isNull(obj)) return defalutValue;
            if (Objects.equals("", obj)) return defalutValue;
            return String.valueOf(obj);
        } catch (Exception e) {
        }
        return defalutValue;
    }

    /**
     * 把对象转成double
     * @param obj
     * @return
     */
    public static Double convertObjectToDouble(Object obj) {
        return convertObjectToDouble(obj,0.00);
    }

    /**
     * 把对象转成double
     * @param obj 对象
     * @param defalutValue 设置默认值
     * @return
     */
    public static Double convertObjectToDouble(Object obj, Double defalutValue) {
        try {
            if (Objects.isNull(obj)) return defalutValue;
            return Double.parseDouble(convertObjectToString(obj));
        } catch (Exception e) {
        }
        return defalutValue;
    }

    /**
     * 把对象转成Integer
     * @param obj
     * @return
     */
    public static Integer convertObjectToInteger(Object obj) {
        return convertObjectToInteger(obj,0);
    }

    /**
     * 把对象转成Integer
     * @param obj 对象
     * @param defalutValue 设置默认值
     * @return
     */
    public static Integer convertObjectToInteger(Object obj, Integer defalutValue) {
        try {
            if (Objects.isNull(obj)) return defalutValue;
            return Integer.parseInt(convertObjectToString(obj));
        } catch (Exception e) {
        }
        return defalutValue;
    }

    /**
     * 把对象转成Long
     * @param obj
     * @return
     */
    public static Long convertObjectToLong(Object obj) {
        return convertObjectToLong(obj,0L);
    }

    /**
     * 把对象转成Long
     * @param obj 对象
     * @param defalutValue 设置默认值
     * @return
     */
    public static Long convertObjectToLong(Object obj, Long defalutValue) {
        try {
            if (Objects.isNull(obj)) return defalutValue;
            return Long.parseLong(convertObjectToString(obj));
        } catch (Exception e) {
        }
        return defalutValue;
    }

    /**
     * 把对象转成Float
     * @param obj
     * @return
     */
    public static Float convertObjectToFloat(Object obj) {
        return convertObjectToFloat(obj,0F);
    }

    /**
     * 把对象转成Float
     * @param obj 对象
     * @param defalutValue 设置默认值
     * @return
     */
    public static Float convertObjectToFloat(Object obj, Float defalutValue) {
        try {
            if (Objects.isNull(obj)) return defalutValue;
            return Float.parseFloat(convertObjectToString(obj));
        } catch (Exception e) {
        }
        return defalutValue;
    }

    /**
     * 判断对象是否为true
     * @param obj
     * @return 对象obj 是 "true" 或者 true (是不区分大小写)  或者 大于 0 都返回true
     */
    public static boolean isTrue(Object obj) {
        try {
            if (Objects.isNull(obj)) return false;
            if (Objects.equals(convertObjectToString(obj).toLowerCase(), "true")) return true;
            return convertObjectToInteger(obj) > 0;
        } catch (Exception e) {
        }
        return false;
    }

    /**
     * 反射调用类方法
     * @param objService 实例化类
     * @param methodName 实例化类的方法
     * @param paramargs 参数:按照方法参数顺序
     * @return
     * @throws Exception
     */
    public static Object callMethod(Object objService,String methodName,Object... paramargs) throws Exception {
        Object returnObj = null;
        try {
            List<Class> classList = new ArrayList<>();
            if(Objects.nonNull(paramargs)){
                for(Object paramarg : paramargs)
                    classList.add(paramarg.getClass());
            }
            Class[] classArr = new Class[classList.size()];
            logger.info("反射调用类方法-获取反射方法method:[{}]", methodName);
            Method method = objService.getClass().getMethod(methodName,classList.toArray(classArr));
            returnObj = method.invoke(objService, paramargs);
        } catch (Exception e) {
            throw e;
        }
        return returnObj;
    }

    /**
     * 根据属性名设置属性值
     * @param o 反射需要的实体类
     * @param fieldName 字段属性
     * @param fieldValue 字段属性设置默认值
     * @return
     */
    public static void setFieldValueByName(Object o, String fieldName, Object fieldValue) {
        try {
            String firstLetter = fieldName.substring(0, 1).toUpperCase();
            String setter = "set" + firstLetter + fieldName.substring(1);
            Method method = o.getClass().getMethod(setter, String.class);
            method.invoke(o, fieldValue);
        } catch (Exception e) {
            logger.error("根据属性名获取属性值异常" + e.getMessage());
        }
    }
}
