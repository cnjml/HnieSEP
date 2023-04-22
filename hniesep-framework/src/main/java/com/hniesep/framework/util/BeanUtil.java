package com.hniesep.framework.util;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

import java.lang.reflect.InvocationTargetException;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author 吉铭炼
 */
@Component
public class BeanUtil {
    private BeanUtil(){}
    public static <V>V copyBean(Object source, Class<V> target) {
        try {
            V result = target.getDeclaredConstructor().newInstance();
            BeanUtils.copyProperties(source,result);
            return result;
        } catch (InstantiationException | IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
            throw new RuntimeException(e);
        }
    }

    public static <S,T> List<T> copyBeanList(List<S> sources,Class<T> target){
        return sources.stream()
                .map(o->copyBean(o,target))
                .collect(Collectors.toList());
    }
}
