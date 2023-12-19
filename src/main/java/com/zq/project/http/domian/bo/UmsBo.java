package com.zq.project.http.domian.bo;

import lombok.Data;

import java.util.Map;

/**
 * @author zhangqian
 * @date 2023/12/13 14:54
 */
@Data
public class UmsBo {

    private String spCpde;

    private String requestId;

    private String appKey;

    private String sign;

    private Map<String,Object> param;

    private String url;
}
