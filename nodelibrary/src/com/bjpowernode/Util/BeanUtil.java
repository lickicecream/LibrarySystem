package com.bjpowernode.Util;

import java.io.ObjectInputStream;
import java.lang.reflect.Field;

public class BeanUtil {

    public static void populate(Object origin, Object dest) {
        try {
            if (origin.getClass() != dest.getClass()) {
                throw new RuntimeException("�������������ͬһ����");
            }
            Field[] fields = origin.getClass().getDeclaredFields();
            for (Field field : fields) {
                //���Ʒ�װ
                field.setAccessible(true);
                //��ֵ
                field.set(dest, field.get(origin));
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}

