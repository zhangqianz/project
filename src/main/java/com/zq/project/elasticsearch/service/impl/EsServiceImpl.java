package com.zq.project.elasticsearch.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.zq.project.elasticsearch.service.EsService;
import org.elasticsearch.action.admin.indices.alias.Alias;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.client.indices.CreateIndexRequest;
import org.elasticsearch.common.xcontent.XContentType;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import springfox.documentation.spring.web.json.Json;

import java.io.IOException;
import java.util.HashMap;

/**
 * @author zhangqian
 * @date 2023/2/27 14:03
 * @description:
 */
@Service
public class EsServiceImpl implements EsService {

    @Autowired
    private RestHighLevelClient restHighLevelClient;

    @Override
    public Boolean insert(String json) {
        JSONObject jsonObject = JSON.parseObject(json);
        //注意es7.x版本之后去除了type概念，所以加不加type看es版本而定
        IndexRequest indexRequest=new IndexRequest("basic_operation_log_2023.2_w").source(jsonObject, XContentType.JSON).type("_doc");
        try {
            restHighLevelClient.index(indexRequest, RequestOptions.DEFAULT);
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    @Override
    public Boolean create(String name) {
        CreateIndexRequest createIndexRequest=new CreateIndexRequest(name);
        //字段集合
        createIndexRequest.mapping(new HashMap<>(16));
        //索引别名
        createIndexRequest.alias(new Alias(""));
        try {
            restHighLevelClient.indices().create(createIndexRequest,RequestOptions.DEFAULT);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public JSON query(String name) {
        SearchRequest searchRequest = new SearchRequest();
        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
        BoolQueryBuilder boolQueryBuilder=new BoolQueryBuilder();
        boolQueryBuilder.must(QueryBuilders.termQuery("name",name));
        searchSourceBuilder.query(boolQueryBuilder);
        searchRequest.source(searchSourceBuilder);
        try {
            SearchResponse search = restHighLevelClient.search(searchRequest, RequestOptions.DEFAULT);
            return JSON.parseObject(search.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
