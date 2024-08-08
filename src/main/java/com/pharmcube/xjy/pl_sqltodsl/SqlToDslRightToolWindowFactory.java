package com.pharmcube.xjy.pl_sqltodsl;

import com.intellij.openapi.project.Project;
import com.intellij.openapi.wm.ToolWindow;
import com.intellij.openapi.wm.ToolWindowFactory;
import com.intellij.ui.JBColor;
import com.intellij.ui.content.Content;
import com.intellij.ui.content.ContentFactory;
import com.intellij.ui.content.ContentManager;
import com.pharmcube.xjy.es4sql.MainTestSuite;
import com.pharmcube.xjy.es4sql.SearchDao;
import com.pharmcube.xjy.es4sql.exception.SqlParseException;
import com.pharmcube.xjy.es4sql.query.QueryAction;
import com.pharmcube.xjy.es4sql.query.SqlElasticRequestBuilder;
import org.apache.commons.lang.StringUtils;
import org.jetbrains.annotations.NotNull;

import javax.swing.*;
import java.awt.*;
import java.sql.SQLFeatureNotSupportedException;

public class SqlToDslRightToolWindowFactory implements ToolWindowFactory {

    @Override
    public void createToolWindowContent(@NotNull Project project, @NotNull ToolWindow toolWindow) {

//        ContentFactory factory = ContentFactory.SERVICE.getInstance();
//        ContentManager contentManager = toolWindow.getContentManager();
//        {
//            JPanel panel = new JPanel();
//            JLabel label = new JLabel("SQL convert to Elasticsearch DSL");
//            label.setFont(new Font("微软雅黑", Font.BOLD, 20));
//            panel.setEnabled(true);
//            panel.add(label);
//
//            JTextArea inputJta = new JTextArea(15, 30);
//            inputJta.setToolTipText("Please enter your sql.");
////            inputJta.setText("Please enter your sql.");
//            inputJta.setFont(new Font("微软雅黑", Font.PLAIN, 15));
//            panel.add(inputJta);
//
//            JButton convertBtn = new JButton("Convert");
//            panel.add(convertBtn);
//
//            JTextArea outputJta = new JTextArea(15, 30);
////            inputJta.setText("Please enter your sql.");
//            outputJta.setFont(new Font("微软雅黑", Font.PLAIN, 15));
//            panel.add(outputJta);
//
//            Content content1 = factory.createContent(panel, "convert", false);
//            contentManager.addContent(content1);
//
//            convertBtn.addActionListener(e -> {
//                String actionCommand = e.getActionCommand();
//                String sql = inputJta.getText();
//                if (StringUtils.isBlank(sql)) {
//                    outputJta.setText("Please enter your sql.");
//                }
//                SearchDao searchDao = MainTestSuite.getSearchDao();
//                try {
//                    QueryAction queryAction = searchDao.explain(sql);
//                    SqlElasticRequestBuilder requestBuilder = queryAction.explain();
//                    outputJta.setText(requestBuilder.explain());
//                } catch (SqlParseException ex) {
//                    throw new RuntimeException(ex);
//                } catch (SQLFeatureNotSupportedException ex) {
//                    throw new RuntimeException(ex);
//                }
//
//            });
//        }
//        {
//            JPanel panel = new JPanel();
//            JLabel label = new JLabel("Hello world");
//            label.setFont(new Font("宋体", Font.BOLD, 32));
//            label.setForeground(JBColor.RED);
//            panel.add(label);
//            Content content2 = factory.createContent(panel, "example", true);
//            contentManager.addContent(content2);
//        }


        //toolwindow 里面的内容，创建我们自己定义的面板
        SqlToDslRightToolWindow sqlToDslRightToolWindow = new SqlToDslRightToolWindow(project, toolWindow);

        //获取ContentFactory实例
        //这种方式是为了兼容老版本的IDEA，但是SERVICE是已经被标记为了@Deprecated
        //ContentFactory contentFactory = ContentFactory.SERVICE.getInstance();

        //这种方式只能在 build number 222.3345.118 之后调用，也就是如果我们使用了这种方式，那么在plugin.xml中的since-version必须要配置为：<idea-version since-build="222.3345.118"/>
        ContentFactory contentFactory = ContentFactory.SERVICE.getInstance();

        //创建一个Content，也就是toolwindow里面的一个tab页
        Content content = contentFactory.createContent(sqlToDslRightToolWindow, "convert", false);
        //将Content加入到toolwindow中
        toolWindow.getContentManager().addContent(content);

    }
}
