package com.py.es.test;

import org.apache.http.HttpHost;
import org.elasticsearch.action.bulk.BulkRequest;
import org.elasticsearch.action.bulk.BulkResponse;
import org.elasticsearch.action.delete.DeleteRequest;
import org.elasticsearch.action.delete.DeleteResponse;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.common.xcontent.XContentType;

import java.io.IOException;

public class _009_EsTest_Doc_Insert_Batch {
    public static void main(String[] args) throws IOException {
        RestHighLevelClient client = new RestHighLevelClient(
                RestClient.builder(new HttpHost("localhost", 9200, "http")));
        //创建批量新增请求对象
        BulkRequest request = new BulkRequest();
        request.add(new
                IndexRequest().index("user").id("1001").source(XContentType.JSON, "name",
                "zhangsan","age", 30));
        request.add(new
                IndexRequest().index("user").id("1002").source(XContentType.JSON, "name",
                "lisi","age",30));
        request.add(new
                IndexRequest().index("user").id("1003").source(XContentType.JSON, "name",
                "wangwu","age",31));
        request.add(new
                IndexRequest().index("user").id("1004").source(XContentType.JSON, "name",
                "zhaoliu","age",36));
        request.add(new
                IndexRequest().index("user").id("1005").source(XContentType.JSON, "name",
                "tianqi","age",66));
        //客户端发送请求，获取响应对象
        BulkResponse responses = client.bulk(request, RequestOptions.DEFAULT);
        //打印结果信息
        System.out.println("took:" + responses.getTook());
        System.out.println("items:" + responses.getItems());
        client.close();
    }
}
