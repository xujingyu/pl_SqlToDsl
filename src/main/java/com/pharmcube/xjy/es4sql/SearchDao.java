package com.pharmcube.xjy.es4sql;

import java.sql.SQLFeatureNotSupportedException;
import java.util.HashSet;
import java.util.Set;

import com.pharmcube.xjy.es4sql.exception.SqlParseException;
import com.pharmcube.xjy.es4sql.query.ESActionFactory;
import com.pharmcube.xjy.es4sql.query.QueryAction;
import org.elasticsearch.client.Client;


public class SearchDao {

    private static final Set<String> END_TABLE_MAP = new HashSet<>();

    static {
        END_TABLE_MAP.add("limit");
        END_TABLE_MAP.add("order");
        END_TABLE_MAP.add("where");
        END_TABLE_MAP.add("group");

    }

    private Client client = null;

    public SearchDao() {
    }
    public SearchDao(Client client) {
        this.client = client;
    }

    public SearchDao(String s, String myIndex) {
    }

    public Client getClient() {
        return client;
    }

    /**
     * Prepare action And transform sql
     * into ES ActionRequest
     * @param sql SQL query to execute.
     * @return ES request
     * @throws SqlParseException
     */
    public QueryAction explain(String sql) throws SqlParseException, SQLFeatureNotSupportedException {
        return ESActionFactory.create(client, sql);
    }



}
