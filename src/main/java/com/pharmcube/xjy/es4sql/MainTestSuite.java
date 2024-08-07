package com.pharmcube.xjy.es4sql;

public class MainTestSuite {
//    private static SearchDao searchDao = new SearchDao("http://localhost:9200", "my_index");
    private static SearchDao searchDao = new SearchDao();

    public static SearchDao getSearchDao() {
        return searchDao;
    }

}
