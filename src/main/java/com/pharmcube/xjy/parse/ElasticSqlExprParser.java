package com.pharmcube.xjy.parse;

import com.alibaba.druid.sql.parser.Lexer;
import com.alibaba.druid.sql.parser.SQLExprParser;
import com.alibaba.druid.util.FnvHash;
import com.alibaba.druid.util.JdbcConstants;

import java.util.Arrays;

public class ElasticSqlExprParser extends SQLExprParser {

    public final static String[] AGGREGATE_FUNCTIONS;

    public final static long[] AGGREGATE_FUNCTIONS_CODES;

    static {
        String[] strings = { "AVG", "COUNT", "GROUP_CONCAT", "MAX", "MIN", "STDDEV", "SUM" };
        AGGREGATE_FUNCTIONS_CODES = FnvHash.fnv1a_64_lower(strings, true);
        AGGREGATE_FUNCTIONS = new String[AGGREGATE_FUNCTIONS_CODES.length];
        for (String str : strings) {
            long hash = FnvHash.fnv1a_64_lower(str);
            int index = Arrays.binarySearch(AGGREGATE_FUNCTIONS_CODES, hash);
            AGGREGATE_FUNCTIONS[index] = str;
        }
    }

    public ElasticSqlExprParser(Lexer lexer){
        super(lexer, JdbcConstants.MYSQL);
        this.aggregateFunctions = AGGREGATE_FUNCTIONS;
        this.aggregateFunctionHashCodes = AGGREGATE_FUNCTIONS_CODES;
    }

    public ElasticSqlExprParser(String sql){
        this(new ElasticLexer(sql));
        this.lexer.nextToken();
    }
}
