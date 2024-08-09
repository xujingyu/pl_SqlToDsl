package com.pharmcube.xjy.ui;

import com.intellij.openapi.project.Project;
import com.intellij.openapi.wm.ToolWindow;
import com.pharmcube.xjy.es4sql.MainTestSuite;
import com.pharmcube.xjy.es4sql.SearchDao;
import com.pharmcube.xjy.es4sql.query.QueryAction;
import com.pharmcube.xjy.es4sql.query.SqlElasticRequestBuilder;
import org.apache.commons.lang.StringUtils;

import javax.swing.*;
import java.awt.*;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class ConvertForm01 {


    public JPanel getSqlToDslPanel() {
        return sqlToDsl;
    }

    private JPanel sqlToDsl;
    private JButton convertButtion;
    private JTextArea sqlArea;
    private JTextArea dslArea;
    private JButton clearButton;
    private JButton copyButton;
    private JScrollPane sqlPane;
    private JScrollPane dslPane;

    public ConvertForm01(Project project, ToolWindow toolWindow) {
        super();

        // Convert按钮监听
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
                    } catch (Exception ex) {
                        dslArea.setText("Unsupported query: " + sql);
                        throw new RuntimeException(ex);
                    }
                } else {
                    dslArea.setText("Please enter your SQL");
                }
            }
        });

        // Clear按钮监听
        clearButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                        sqlArea.setText("");
            }
        });

        // Copy按钮监听
        copyButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String dsl = dslArea.getText();
                if(StringUtils.isNotBlank(dsl)) {
                    StringSelection stringSelection = new StringSelection(dsl);
                    Clipboard systemClipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
                    systemClipboard.setContents(stringSelection, null);
                }
            }
        });
    }

    private void createUIComponents() {
        // TODO: place custom component creation code here
    }
}
