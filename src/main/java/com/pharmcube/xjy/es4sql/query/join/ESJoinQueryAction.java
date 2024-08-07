package com.pharmcube.xjy.es4sql.query.join;


import com.pharmcube.xjy.es4sql.domain.Field;
import com.pharmcube.xjy.es4sql.domain.JoinSelect;
import com.pharmcube.xjy.es4sql.domain.Select;
import com.pharmcube.xjy.es4sql.domain.TableOnJoinSelect;
import com.pharmcube.xjy.es4sql.domain.hints.Hint;
import com.pharmcube.xjy.es4sql.domain.hints.HintType;
import com.pharmcube.xjy.es4sql.exception.SqlParseException;
import com.pharmcube.xjy.es4sql.query.DefaultQueryAction;
import com.pharmcube.xjy.es4sql.query.QueryAction;
import com.pharmcube.xjy.es4sql.query.SqlElasticRequestBuilder;
import org.elasticsearch.client.Client;

import java.util.List;

/**
 * Created by Eliran on 15/9/2015.
 */
public abstract class ESJoinQueryAction extends QueryAction {

    protected JoinSelect joinSelect;

    public ESJoinQueryAction(Client client, JoinSelect joinSelect) {
        super(client, null);
        this.joinSelect = joinSelect;
    }

    @Override
    public SqlElasticRequestBuilder explain() throws SqlParseException {
        JoinRequestBuilder requestBuilder = createSpecificBuilder();
        fillBasicJoinRequestBuilder(requestBuilder);
        fillSpecificRequestBuilder(requestBuilder);
        return requestBuilder;
    }

    protected abstract void fillSpecificRequestBuilder(JoinRequestBuilder requestBuilder) throws SqlParseException;

    protected abstract JoinRequestBuilder createSpecificBuilder();


    private void fillBasicJoinRequestBuilder(JoinRequestBuilder requestBuilder) throws SqlParseException {

        fillTableInJoinRequestBuilder(requestBuilder.getFirstTable(), joinSelect.getFirstTable());
        fillTableInJoinRequestBuilder(requestBuilder.getSecondTable(), joinSelect.getSecondTable());

        requestBuilder.setJoinType(joinSelect.getJoinType());

        requestBuilder.setTotalLimit(joinSelect.getTotalLimit());

        updateRequestWithHints(requestBuilder);


    }

    protected void updateRequestWithHints(JoinRequestBuilder requestBuilder){
        for(Hint hint : joinSelect.getHints()) {
            if (hint.getType() == HintType.JOIN_LIMIT) {
                Object[] params = hint.getParams();
                requestBuilder.getFirstTable().setHintLimit((Integer) params[0]);
                requestBuilder.getSecondTable().setHintLimit((Integer) params[1]);
            }
        }
    }

    private void fillTableInJoinRequestBuilder(TableInJoinRequestBuilder requestBuilder, TableOnJoinSelect tableOnJoinSelect) throws SqlParseException {
        List<Field> connectedFields = tableOnJoinSelect.getConnectedFields();
        addFieldsToSelectIfMissing(tableOnJoinSelect,connectedFields);
        requestBuilder.setOriginalSelect(tableOnJoinSelect);
        DefaultQueryAction queryAction = new DefaultQueryAction(client,tableOnJoinSelect);
        queryAction.explain();
        requestBuilder.setRequestBuilder(queryAction.getRequestBuilder());
        requestBuilder.setReturnedFields(tableOnJoinSelect.getSelectedFields());
        requestBuilder.setAlias(tableOnJoinSelect.getAlias());
    }

    private void addFieldsToSelectIfMissing(Select select, List<Field> fields) {
        //this means all fields
        if(select.getFields() == null || select.getFields().size() == 0) return;

        List<Field> selectedFields = select.getFields();
        for(Field field : fields){
            if(!selectedFields.contains(field)){
                selectedFields.add(field);
            }
        }

    }

}

