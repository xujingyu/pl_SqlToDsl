package com.pharmcube.xjy;


import com.pharmcube.xjy.es4sql.exception.SqlParseException;
import org.elasticsearch.search.SearchHits;

import java.io.IOException;

/**
 * Created by Eliran on 21/8/2016.
 */
public interface ElasticHitsExecutor {
    void run() throws IOException, SqlParseException;
    SearchHits getHits();
}
