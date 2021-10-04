package com.bjpowernode.Util;

import java.io.ObjectInputStream;
import java.lang.reflect.Field;

public class BeanUtil {

    public static void populate(Object origin, Object dest) {
        try {
            if (origin.getClass() != dest.getClass()) {
                throw new RuntimeException("两个对象必须是同一类型");
            }
            Field[] fields = origin.getClass().getDeclaredFields();
            for (Field field : fields) {
                //打破封装
                field.setAccessible(true);
                //赋值
                field.set(dest, field.get(origin));
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}

