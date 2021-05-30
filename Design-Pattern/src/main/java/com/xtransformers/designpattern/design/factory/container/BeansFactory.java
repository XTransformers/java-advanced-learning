package com.xtransformers.designpattern.design.factory.container;

import com.google.common.annotations.VisibleForTesting;
import com.google.common.collect.Maps;

import java.lang.reflect.InvocationTargetException;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author daniel
 * @date 2021-05-30
 */
public class BeansFactory {

    private final ConcurrentHashMap<String, Object> singletonObjects =
            (ConcurrentHashMap<String, Object>) Maps.<String, Object>newConcurrentMap();
    private final ConcurrentHashMap<String, BeanDefinition> beanDefinitions =
            (ConcurrentHashMap<String, BeanDefinition>) Maps.<String, BeanDefinition>newConcurrentMap();


    public void addBeanDefinitions(List<BeanDefinition> beanDefinitionList) {
        beanDefinitionList.forEach(beanDefinition -> {
            this.beanDefinitions.putIfAbsent(beanDefinition.getId(), beanDefinition);
            if (!beanDefinition.isLazyInit() && beanDefinition.isSingleton()) {
                createBean(beanDefinition);
            }
        });
    }

    public Object getBean(String beanId) {
        BeanDefinition beanDefinition = beanDefinitions.get(beanId);
        if (beanDefinition == null) {
            throw new NoSuchBeanDefinitionException("bean is not defined:" + beanId);
        }
        return createBean(beanDefinition);
    }

    @VisibleForTesting
    protected Object createBean(BeanDefinition beanDefinition) {
        if (beanDefinition.isSingleton() && singletonObjects.containsKey(beanDefinition.getId())) {
            return singletonObjects.get(beanDefinition.getId());
        }
        Object bean = null;
        try {
            Class<?> beanClass = Class.forName(beanDefinition.getClassName());
            List<ConstructorArg> constructorArgs = beanDefinition.getConstructorArgs();
            if (constructorArgs.isEmpty()) {
                bean = beanClass.newInstance();
            } else {
                Class<?>[] argClasses = new Class[constructorArgs.size()];
                Object[] argObjects = new Object[constructorArgs.size()];
                for (int i = 0; i < constructorArgs.size(); i++) {
                    ConstructorArg constructorArg = constructorArgs.get(i);
                    if (!constructorArg.isRef()) {
                        argClasses[i] = constructorArg.getType();
                        argObjects[i] = constructorArg.getArg();
                    } else {
                        BeanDefinition refBeanDefinition = beanDefinitions.get(constructorArg.getArg().toString());
                        if (refBeanDefinition == null) {
                            throw new NoSuchBeanDefinitionException("bean is not defined:" + constructorArg.getArg());
                        }
                        argClasses[i] = Class.forName(refBeanDefinition.getClassName());
                        argObjects[i] = createBean(refBeanDefinition);
                    }
                }
                bean = beanClass.getConstructor(argClasses).newInstance(argObjects);
            }
        } catch (ClassNotFoundException | IllegalAccessException | InstantiationException
                | NoSuchMethodException | InvocationTargetException e) {
            e.printStackTrace();
        }

        if (bean != null && beanDefinition.isSingleton()) {
            singletonObjects.putIfAbsent(beanDefinition.getId(), bean);
            return singletonObjects.get(beanDefinition.getId());
        }

        return bean;
    }
}
