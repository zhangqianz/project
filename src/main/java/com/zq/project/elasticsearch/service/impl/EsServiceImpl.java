package com.zq.project.elasticsearch.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.zq.project.elasticsearch.service.EsService;
import org.apache.http.HttpHost;
import org.elasticsearch.action.admin.indices.alias.Alias;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.client.indices.CreateIndexRequest;
import org.elasticsearch.common.xcontent.XContentType;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.index.query.RangeQueryBuilder;
import org.elasticsearch.index.query.TermQueryBuilder;
import org.elasticsearch.search.aggregations.AggregationBuilders;
import org.elasticsearch.search.aggregations.bucket.terms.Terms;
import org.elasticsearch.search.aggregations.bucket.terms.TermsAggregationBuilder;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import springfox.documentation.spring.web.json.Json;

import java.io.IOException;
import java.util.*;

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

    @Override
    public void search() throws IOException {


        // 设置查询的时间范围
        String startTime = "2023-07-25T04:25:00.000000Z";
        String endTime = "2023-07-25T04:30:00.000000Z";

        // 构造聚合查询语句
        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();

        TermsAggregationBuilder aggregationBuilder = AggregationBuilders.terms("duplicate_count")
                .field("latitude")
                .minDocCount(2); // 至少出现两次的语句被认为是重复的

        searchSourceBuilder.aggregation(aggregationBuilder);

        RangeQueryBuilder rangeQueryBuilder = QueryBuilders.rangeQuery("@timestamp@")
                .gte(startTime).lte(endTime);

        BoolQueryBuilder boolQueryBuilder = QueryBuilders.boolQuery().filter(rangeQueryBuilder);
        TermQueryBuilder termQueryBuilder = QueryBuilders.termQuery("intersectionId.keyword", "300047");
        boolQueryBuilder.must(termQueryBuilder);
        searchSourceBuilder.query(boolQueryBuilder);

        SearchRequest searchRequest = new SearchRequest("microwave_2023.07.25_d");
        searchRequest.source(searchSourceBuilder);

        // 执行查询
        SearchResponse searchResponse = restHighLevelClient.search(searchRequest, RequestOptions.DEFAULT);

        // 处理查询结果
        List<Map.Entry<String, Long>> duplicateStatements = new ArrayList<>();
        Terms duplicateCountAgg = searchResponse.getAggregations().get("duplicate_count");

        for (Terms.Bucket bucket : duplicateCountAgg.getBuckets()) {
            String statement = bucket.getKeyAsString();
            long docCount = bucket.getDocCount();
            duplicateStatements.add(new AbstractMap.SimpleEntry<>(statement, docCount));
        }

        // 输出结果
        if (duplicateStatements.size() > 0) {
            System.out.println("重复语句统计结果：");
            for (Map.Entry<String, Long> entry : duplicateStatements) {
                String statement = entry.getKey();
                long docCount = entry.getValue();
                System.out.println("语句：" + statement + "，重复次数：" + docCount);
            }
        } else {
            System.out.println("在指定时间段内没有重复语句");
        }

    }
}
