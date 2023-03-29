package com.zq.project.util;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.google.common.collect.Lists;
import org.springframework.beans.BeanUtils;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * @author 张骞
 * 转换工具类
 */
public class ConvertUtil {

    public static <T, R> R convert(T source, Class<R> targetType) {
        try {
            if (Objects.nonNull(source) && Objects.nonNull(targetType)) {
                R target = targetType.newInstance();
                BeanUtils.copyProperties(source, target);
                return target;
            }
        } catch (InstantiationException | IllegalAccessException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static <T, R> Page<R> convertPage(Page<T> source, Class<R> targetType) {
        Page<R> target = new Page<>();
        if (Objects.nonNull(source) && Objects.nonNull(targetType)) {
            BeanUtils.copyProperties(source, target);
            List<T> sourceRecords = source.getRecords();
            if (!CollectionUtils.isEmpty(sourceRecords)) {
                List<R> records = convertList(sourceRecords, targetType);
                target.setRecords(records);
            }
        }
        return target;
    }

    public static <T, R> List<R> convertList(List<T> source, Class<R> targetType) {
        if (!CollectionUtils.isEmpty(source)) {
            return source.stream().map(t -> convert(t, targetType))
                    .filter(Objects::nonNull)
                    .collect(Collectors.toList());
        }
        return Lists.newArrayList();
    }
}
