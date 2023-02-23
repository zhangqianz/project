package com.zq.project.http.domian.bo;

import lombok.Data;

import java.util.Map;

/**
 * @author zhangqian
 * @date 2023/2/23 19:56
 * @description:
 */
@Data
public class HttpBo {

    private String url;

    private Map<String,Object> params;
}
