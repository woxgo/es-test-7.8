package com.py.es.test;

import org.apache.http.HttpHost;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.common.unit.Fuzziness;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.index.query.RangeQueryBuilder;
import org.elasticsearch.index.query.TermsQueryBuilder;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.SearchHits;
import org.elasticsearch.search.aggregations.AggregationBuilders;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.elasticsearch.search.fetch.subphase.highlight.HighlightBuilder;
import org.elasticsearch.search.fetch.subphase.highlight.HighlightField;
import org.elasticsearch.search.sort.SortOrder;

import java.io.IOException;
import java.util.Map;

public class _011_EsTest_Doc_Query {
    public static void main(String[] args) throws IOException {
        RestHighLevelClient client = new RestHighLevelClient(
                RestClient.builder(new HttpHost("localhost", 9200, "http")));

        // 1.查询索引中全部的数据
        /*
        SearchRequest request = new SearchRequest();
        request.indices("user");
        SearchSourceBuilder sourceBuilder = new SearchSourceBuilder();
        // 查询所有数据
        sourceBuilder.query(QueryBuilders.matchAllQuery());
        request.source(sourceBuilder);
        SearchResponse response = client.search(request, RequestOptions.DEFAULT);
        SearchHits hits = response.getHits();
        System.out.println("took:" + response.getTook());
        System.out.println("timeout:" + response.isTimedOut());
        System.out.println("total:" + hits.getTotalHits());
        System.out.println("MaxScore:" + hits.getMaxScore());
        System.out.println("hits========>>");
        for (SearchHit hit : hits) {
            System.out.println(hit.getSourceAsString());
        }
        System.out.println("<<========");*/


        // 2.条件查询 termQuery
/*        SearchRequest request = new SearchRequest();
        request.indices("user");
        SearchSourceBuilder sourceBuilder = new SearchSourceBuilder();
        sourceBuilder.query(QueryBuilders.termQuery("age", "30"));
        request.source(sourceBuilder);
        SearchResponse response = client.search(request, RequestOptions.DEFAULT);
        SearchHits hits = response.getHits();
        System.out.println("took:" + response.getTook());
        System.out.println("timeout:" + response.isTimedOut());
        System.out.println("total:" + hits.getTotalHits());
        System.out.println("MaxScore:" + hits.getMaxScore());
        System.out.println("hits========>>");
        for (SearchHit hit : hits) {
            System.out.println(hit.getSourceAsString());
        }
        System.out.println("<<========");*/


        // 3.分页查询 from to
       /* SearchRequest request = new SearchRequest();
        request.indices("user");
        SearchSourceBuilder sourceBuilder = new SearchSourceBuilder();
        sourceBuilder.query(QueryBuilders.matchAllQuery());
        // 分页查询
        // 当前页其实索引(第一条数据的顺序号)， from
        sourceBuilder.from(0);

        // 每页显示多少条 size
        sourceBuilder.size(2);
        request.source(sourceBuilder);
        SearchResponse response = client.search(request, RequestOptions.DEFAULT);
        SearchHits hits = response.getHits();
        System.out.println("took:" + response.getTook());
        System.out.println("timeout:" + response.isTimedOut());
        System.out.println("total:" + hits.getTotalHits());
        System.out.println("MaxScore:" + hits.getMaxScore());
        System.out.println("hits========>>");
        for (SearchHit hit : hits) {
            //输出每条查询的结果信息
            System.out.println(hit.getSourceAsString());
        }
        System.out.println("<<========");

        client.close();
    }*/


        /*//4. 查询排序
        SearchRequest request = new SearchRequest();
        request.indices("user");

        SearchSourceBuilder sourceBuilder = new SearchSourceBuilder();
        sourceBuilder.query(QueryBuilders.matchAllQuery());
        // 排序
        sourceBuilder.sort("age", SortOrder.DESC);
        request.source(sourceBuilder);
        SearchResponse response = client.search(request, RequestOptions.DEFAULT);
        SearchHits hits = response.getHits();
        System.out.println("took:" + response.getTook());
        System.out.println("timeout:" + response.isTimedOut());
        System.out.println("total:" + hits.getTotalHits());
        System.out.println("MaxScore:" + hits.getMaxScore());
        System.out.println("hits========>>");
        for (SearchHit hit : hits) {
            System.out.println(hit.getSourceAsString());
        }
        System.out.println("<<========");*/


        //5.过滤字段
        /*SearchRequest request = new SearchRequest();
        request.indices("user");
        // 构建查询的请求体
        SearchSourceBuilder sourceBuilder = new SearchSourceBuilder().query(QueryBuilders.matchAllQuery());
        //
        String[] excludes = {"age"};
        String[] includes = {};
        sourceBuilder.fetchSource(includes, excludes);
        request.source(sourceBuilder);
        SearchResponse response = client.search(request, RequestOptions.DEFAULT);
        // 查询匹配
        SearchHits hits = response.getHits();
        System.out.println("took:" + response.getTook());
        System.out.println("timeout:" + response.isTimedOut());
        System.out.println("total:" + hits.getTotalHits());
        System.out.println("MaxScore:" + hits.getMaxScore());
        System.out.println("hits========>>");
        for (SearchHit hit : hits) {
            System.out.println(hit.getSourceAsString());
        }
        System.out.println("<<========");
*/


        //6.组合查询 boolQuery
        /*// 创建搜索请求对象
        SearchRequest request = new SearchRequest();
        request.indices("user");
        // 构建查询的请求体

        SearchSourceBuilder sourceBuilder = new SearchSourceBuilder();
        BoolQueryBuilder boolQueryBuilder = QueryBuilders.boolQuery();
        // 必须包含
        boolQueryBuilder.must(QueryBuilders.matchQuery("age", "30"));
        // 一定不含
        boolQueryBuilder.mustNot(QueryBuilders.matchQuery("name", "zhangsan"));
        // 可能包含
        boolQueryBuilder.should(QueryBuilders.matchQuery("sex", "男"));
        sourceBuilder.query(boolQueryBuilder);
        request.source(sourceBuilder);
        SearchResponse response = client.search(request, RequestOptions.DEFAULT);
        SearchHits hits = response.getHits();
        System.out.println("took:" + response.getTook());
        System.out.println("timeout:" + response.isTimedOut());
        System.out.println("total:" + hits.getTotalHits());
        System.out.println("MaxScore:" + hits.getMaxScore());
        System.out.println("hits========>>");
        for (SearchHit hit : hits) {
            System.out.println(hit.getSourceAsString());
        }
        System.out.println("<<========");*/


        //7.范围查询
        /*SearchRequest request = new SearchRequest();
        request.indices("user");
        SearchSourceBuilder sourceBuilder = new SearchSourceBuilder();
        RangeQueryBuilder rangeQuery = QueryBuilders.rangeQuery("age");
        // 大于等于
        rangeQuery.gte("30");
        // 小于等于
        rangeQuery.lte("40");
        sourceBuilder.query(rangeQuery);
        request.source(sourceBuilder);
        SearchResponse response = client.search(request, RequestOptions.DEFAULT);
        SearchHits hits = response.getHits();
        System.out.println("took:" + response.getTook());
        System.out.println("timeout:" + response.isTimedOut());
        System.out.println("total:" + hits.getTotalHits());
        System.out.println("MaxScore:" + hits.getMaxScore());
        System.out.println("hits========>>");
        for (SearchHit hit : hits) {
            System.out.println(hit.getSourceAsString());
        }
        System.out.println("<<========");*/


        //8.模糊查询
        // SearchRequest request = new SearchRequest();
        // request.indices("user");
        // // 构建查询的请求体
        // SearchSourceBuilder sourceBuilder = new SearchSourceBuilder();
        // sourceBuilder.query(QueryBuilders.fuzzyQuery("name","wangw").fuzziness(Fuzziness.ONE));
        // request.source(sourceBuilder);
        // SearchResponse response = client.search(request, RequestOptions.DEFAULT);
        // // 查询匹配
        // SearchHits hits = response.getHits();
        // System.out.println("took:" + response.getTook());
        // System.out.println("timeout:" + response.isTimedOut());
        // System.out.println("total:" + hits.getTotalHits());
        // System.out.println("MaxScore:" + hits.getMaxScore());
        // System.out.println("hits========>>");
        // for (SearchHit hit : hits) {
        //     //输出每条查询的结果信息
        //     System.out.println(hit.getSourceAsString());
        // }
        // System.out.println("<<========");


        // 9.高亮查询
        // SearchRequest request = new SearchRequest().indices("user");
        // //2.创建查询请求体构建器
        // SearchSourceBuilder sourceBuilder = new SearchSourceBuilder();
        // //构建查询方式：高亮查询
        // TermsQueryBuilder termsQueryBuilder =
        //         QueryBuilders.termsQuery("name","zhangsan");
        // //设置查询方式
        // sourceBuilder.query(termsQueryBuilder);
        // //构建高亮字段
        // HighlightBuilder highlightBuilder = new HighlightBuilder();
        // highlightBuilder.preTags("<font color='red'>");//设置标签前缀
        // highlightBuilder.postTags("</font>");//设置标签后缀
        // highlightBuilder.field("name");//设置高亮字段
        // //设置高亮构建对象
        // sourceBuilder.highlighter(highlightBuilder);
        // //设置请求体
        // request.source(sourceBuilder);
        // //3.客户端发送请求，获取响应对象
        // SearchResponse response = client.search(request, RequestOptions.DEFAULT);
        // //4.打印响应结果
        // SearchHits hits = response.getHits();
        // System.out.println("took::"+response.getTook());
        // System.out.println("time_out::"+response.isTimedOut());
        // System.out.println("total::"+hits.getTotalHits());
        // System.out.println("max_score::"+hits.getMaxScore());
        // System.out.println("hits::::>>");
        // for (SearchHit hit : hits) {
        //     String sourceAsString = hit.getSourceAsString();
        //     System.out.println(sourceAsString);
        //     //打印高亮结果
        //     Map<String, HighlightField> highlightFields = hit.getHighlightFields();
        //     System.out.println(highlightFields);
        // }
        // System.out.println("<<::::");


        //10.最大值查询
        SearchRequest request = new SearchRequest().indices("user");
        SearchSourceBuilder sourceBuilder = new SearchSourceBuilder();
        sourceBuilder.aggregation(AggregationBuilders.max("maxAge").field("age"));
        //设置请求体
        request.source(sourceBuilder);
        //3.客户端发送请求，获取响应对象
        SearchResponse response = client.search(request, RequestOptions.DEFAULT);
        //4.打印响应结果
        SearchHits hits = response.getHits();
        System.out.println(response);


        //11.分组查询
        // SearchRequest request = new SearchRequest().indices("user");
        // SearchSourceBuilder sourceBuilder = new SearchSourceBuilder();
        // sourceBuilder.aggregation(AggregationBuilders.terms("age_groupby").field("age"));
        // //设置请求体
        // request.source(sourceBuilder);
        // //3.客户端发送请求，获取响应对象
        // SearchResponse response = client.search(request, RequestOptions.DEFAULT);
        // //4.打印响应结果
        // SearchHits hits = response.getHits();
        // System.out.println(response);
    }





}
