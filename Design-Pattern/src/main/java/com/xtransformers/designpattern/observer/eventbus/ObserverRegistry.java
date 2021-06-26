package com.xtransformers.designpattern.observer.eventbus;

import com.google.common.base.Preconditions;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.CopyOnWriteArraySet;

/**
 * @author daniel
 * @date 2021-06-26
 */
public class ObserverRegistry {

    private final ConcurrentMap<Class<?>, CopyOnWriteArraySet<ObserverAction>> registry
            = Maps.newConcurrentMap();

    public void register(Object observer) {
        Map<Class<?>, Collection<ObserverAction>> observerActions = findAllObserverAction(observer);
        observerActions.forEach((eventType, eventActions) -> {
            CopyOnWriteArraySet<ObserverAction> registerdEventActions = registry.get(eventType);
            if (registerdEventActions == null) {
                registry.putIfAbsent(eventType, new CopyOnWriteArraySet<>());
                registerdEventActions = registry.get(eventType);
            }
            registerdEventActions.addAll(eventActions);
        });
    }

    public List<ObserverAction> getMatchedObserverAction(Object event) {
        List<ObserverAction> matchedObserverList = Lists.newArrayList();
        Class<?> postEventType = event.getClass();
        registry.forEach((eventType, eventActions) -> {
            if (postEventType.isAssignableFrom(eventType)) {
                matchedObserverList.addAll(eventActions);
            }
        });
        return matchedObserverList;
    }

    private Map<Class<?>, Collection<ObserverAction>> findAllObserverAction(Object observer) {
        Map<Class<?>, Collection<ObserverAction>> observerActions = Maps.newHashMap();
        Class<?> clazz = observer.getClass();
        for (Method method : getAnnotatedMethod(clazz)) {
            Class<?>[] parameterTypes = method.getParameterTypes();
            Class<?> eventType = parameterTypes[0];
            if (!observerActions.containsKey(eventType)) {
                observerActions.put(eventType, new ArrayList<>());
            }
            observerActions.get(eventType).add(new ObserverAction(observer, method));
        }
        return observerActions;
    }

    private List<Method> getAnnotatedMethod(Class<?> clazz) {
        List<Method> annotatedMethodList = Lists.newArrayList();
        for (Method method : clazz.getDeclaredMethods()) {
            if (method.isAnnotationPresent(Subscribe.class)) {
                Class<?>[] parameterTypes = method.getParameterTypes();
                Preconditions.checkArgument(parameterTypes.length == 1,
                        "Method %s has @Subscribe annotation but has %s parameters."
                                + "Subscriber methods must have exactly 1 parameter.",
                        method, parameterTypes.length);
                annotatedMethodList.add(method);
            }
        }
        return annotatedMethodList;
    }
}
