package com.ljq.mvp.util;

import android.app.Activity;
import android.view.View;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;

/**
 * @author 刘镓旗
 * @date 2017/11/16
 * @description
 */
public class ViewFind {
    public static void bind(Activity activity) {
        Class<? extends Activity> aClass = activity.getClass();

        Field[] declaredFields = aClass.getDeclaredFields();

        if (declaredFields != null && declaredFields.length > 0) {
            for (Field field : declaredFields) {
                Class<?> type = field.getType();
                if (
                    //是否是静态的
                        Modifier.isStatic(field.getModifiers())
                                //是否是final的
                                || Modifier.isFinal(field.getModifiers())
                                //是否是基本类型
                                || type.isPrimitive()
                                //是否是数组
                                || type.isArray()) {
                    continue;
                }

                //获取注解
                FieldView annotation = field.getAnnotation(FieldView.class);
                if (annotation != null) {
                    View view = activity.findViewById(annotation.value());
                    if (view != null) {
                        field.setAccessible(true);
                        try {
                            field.set(activity, view);
                        } catch (IllegalAccessException e) {
                            e.printStackTrace();
                        }
                    }
                }

            }
        }
    }
}
