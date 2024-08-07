package com.pharmcube.xjy.es4sql;


import com.pharmcube.xjy.ElasticHitsExecutor;
import com.pharmcube.xjy.IntersectExecutor;
import com.pharmcube.xjy.MinusExecutor;
import com.pharmcube.xjy.UnionExecutor;
import com.pharmcube.xjy.es4sql.exception.SqlParseException;
import com.pharmcube.xjy.es4sql.query.multi.MultiQueryRequestBuilder;
import org.elasticsearch.client.Client;

/**
 * Created by Eliran on 21/8/2016.
 */
public class MultiRequestExecutorFactory {
    public static ElasticHitsExecutor createExecutor(Client client, MultiQueryRequestBuilder builder) throws SqlParseException {
        switch (builder.getRelation()){
            case UNION_ALL:
            case UNION:
                return new UnionExecutor(client,builder);
            case MINUS:
                return new MinusExecutor(client,builder);
            case INTERSECT:
                return new IntersectExecutor(builder);
            default:
                throw new SqlParseException("only supports union, minus and intersect operations");
        }
    }
}
