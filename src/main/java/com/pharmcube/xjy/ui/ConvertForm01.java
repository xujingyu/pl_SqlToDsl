package com.pharmcube.xjy.ui;

import com.intellij.openapi.project.Project;
import com.intellij.openapi.wm.ToolWindow;
import com.pharmcube.xjy.es4sql.MainTestSuite;
import com.pharmcube.xjy.es4sql.SearchDao;
import com.pharmcube.xjy.es4sql.exception.SqlParseException;
import com.pharmcube.xjy.es4sql.query.QueryAction;
import com.pharmcube.xjy.es4sql.query.SqlElasticRequestBuilder;
import org.apache.commons.lang.StringUtils;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLFeatureNotSupportedException;


public class ConvertForm01 {


    public JPanel getSqlToDslPanel() {
        return sqlToDsl;
    }

    private JPanel sqlToDsl;
    private JButton convertButtion;
    private JTextArea sqlArea;
    private JTextArea dslArea;
    private JButton button2;
    private JButton button3;
    private JScrollPane sqlPane;
    private JScrollPane dslPane;

    public ConvertForm01(Project project, ToolWindow toolWindow) {
        super();
        convertButtion.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String sql = sqlArea.getText();
                if(StringUtils.isNotBlank(sql)) {
                    SearchDao searchDao = MainTestSuite.getSearchDao();
                    try {
                        QueryAction queryAction = searchDao.explain(sql);
                        SqlElasticRequestBuilder requestBuilder = queryAction.explain();
                        dslArea.setText(requestBuilder.explain());
                    } catch (SqlParseException ex) {
                        throw new RuntimeException(ex);
                    } catch (SQLFeatureNotSupportedException ex) {
                        throw new RuntimeException(ex);
                    }
                } else {
                    dslArea.setText("Please input sql");
                }
            }
        });
    }

    private void createUIComponents() {
        // TODO: place custom component creation code here
    }
}
