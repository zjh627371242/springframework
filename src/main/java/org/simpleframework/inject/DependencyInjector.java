package org.simpleframework.inject;

import lombok.extern.slf4j.Slf4j;
import org.simpleframework.core.BeanContainer;
import org.simpleframework.inject.annotation.Autowired;
import org.simpleframework.util.ClassUtil;
import org.simpleframework.util.ValidationUtil;

import java.lang.reflect.Field;
import java.util.Set;

/**
 * @author : ZhuangJunHui
 * @date : 2022/5/7 19:46
 */
@Slf4j
public class DependencyInjector {
    /**
     * Bean的容器
     */
    private BeanContainer beanContainer;
    public DependencyInjector(){
        beanContainer = BeanContainer.getInstance();
    }
    /**
     * 执行IOC
     */
    public void doIoc(){
        //1、遍历Bean容器中所有的Class对象
        if (ValidationUtil.isEmpty(beanContainer.getClasses())){
            log.warn("empty classset in BeanContainer");
            return;
        }
        for (Class<?> clazz:beanContainer.getClasses()){
            //2、遍历Class对象的所有成员变量
            Field[] fields = clazz.getDeclaredFields();
            if (ValidationUtil.isEmpty(fields)){
                continue;
            }
            for (Field field: fields) {
                //3、找出被Autowire标记的成员变量
                if (field.isAnnotationPresent(Autowired.class)){
                    Autowired autowired = field.getAnnotation(Autowired.class);
                    String autowiredValue = autowired.value();
                    //4、获取这些成员变量的类型
                    Class<?> fieldClass = field.getType();
                    //5、获取这些成员变量的类型在容器里对应的实例
                    Object fieldValue = getFieldInstance(fieldClass,autowiredValue);
                    if (fieldValue == null){
                        throw new RuntimeException("unable to inject relevant type,target fieldClass is:"+fieldClass.getName());
                    }else {
                        //6、通过反射将对应的成员变量实例注入到成员变量所在类的实例里
                        Object targetBean = beanContainer.getBean(clazz);
                        ClassUtil.setField(field,targetBean,fieldValue,true);
                    }

                }

            }
        }

    }

    /**
     * 根据Class在BeanContainer中获取其实例或实现类
     * @param fieldClass
     * @return
     */
    private Object getFieldInstance(Class<?> fieldClass,String autowiredValue) {
        Object fieldValue = beanContainer.getBean(fieldClass);
        if (fieldValue!=null){
            return fieldValue;
        }else {
            Class<?> implementedClass = getImplementClass(fieldClass,autowiredValue);
            if (implementedClass != null){
                return beanContainer.getBean(implementedClass);
            }else {
                return null;
            }
        }
    }

    /**
     * 获取接口的实现类
     * @param fieldClass
     * @param autowiredValue
     * @return
     */
    private Class<?> getImplementClass(Class<?> fieldClass,String autowiredValue) {
        Set<Class<?>> classSet = beanContainer.getClassesBySuper(fieldClass);
        if (!ValidationUtil.isEmpty(classSet)){
            if (ValidationUtil.isEmpty(autowiredValue)){
                if (classSet.size()==1){
                    return classSet.iterator().next();
                }else {
                    //如果多于俩个实现类且没有指定其中一个实现类 则直接抛出异常
                    throw new RuntimeException("multiple implemented classes for"+fieldClass.getName()+"please set @Autowire's value to pick one");
                }
            }else {
                for (Class<?> clazz :classSet){
                    if (autowiredValue.equals(clazz.getSimpleName())){
                        return clazz;
                    }
                }
            }
        }
        return null;
    }
}
