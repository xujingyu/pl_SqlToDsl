package com.pharmcube.xjy.es4sql.query.multi;

import com.alibaba.druid.sql.ast.statement.SQLUnionOperator;
import com.pharmcube.xjy.es4sql.domain.Select;


public class MultiQuerySelect {
    private SQLUnionOperator operation;
    private Select firstSelect;
    private Select secondSelect;

    public MultiQuerySelect(SQLUnionOperator operation, Select firstSelect, Select secondSelect) {
        this.operation = operation;
        this.firstSelect = firstSelect;
        this.secondSelect = secondSelect;
    }

    public SQLUnionOperator getOperation() {
        return operation;
    }

    public Select getFirstSelect() {
        return firstSelect;
    }

    public Select getSecondSelect() {
        return secondSelect;
    }
}
