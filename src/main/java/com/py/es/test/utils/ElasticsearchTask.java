package com.py.es.test.utils;

import org.elasticsearch.client.RestHighLevelClient;

public interface ElasticsearchTask {
    void doSomething(RestHighLevelClient client) throws Exception;
}
