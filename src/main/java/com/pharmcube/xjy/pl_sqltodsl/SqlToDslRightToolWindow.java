package com.pharmcube.xjy.pl_sqltodsl;

import com.intellij.openapi.project.Project;
import com.intellij.openapi.ui.SimpleToolWindowPanel;
import com.intellij.openapi.wm.ToolWindow;
import com.intellij.ui.JBSplitter;
import com.pharmcube.xjy.ui.ConvertForm01;

import javax.swing.*;

public class SqlToDslRightToolWindow extends SimpleToolWindowPanel {

    private final Project project;
    private final ToolWindow toolWindow;

    public SqlToDslRightToolWindow(Project project, ToolWindow toolWindow) {
        super(false, false);
        this.project = project;
        this.toolWindow = toolWindow;
        initUI();
    }

    private void initUI() {
//        //创建一个左右分割的面板
//        JBSplitter jbSplitter = new JBSplitter(false);
//        //设置它的唯一标识
//        jbSplitter.setSplitterProportionKey("main.splitter.key");
//        //创建一个左侧面板
//        HttpRunnerLeftPanel leftPanel = new HttpRunnerLeftPanel(project, toolWindow);
//        jbSplitter.setFirstComponent(leftPanel.getContainer());
//        //创建一个右侧面板
//        HttpRunnerRightPanel rightPanel = new HttpRunnerRightPanel(project, toolWindow);
//        jbSplitter.setSecondComponent(rightPanel.getContainer());
//        //设置面板的左右比例，这里是左侧占60%，右侧占40%
//        jbSplitter.setProportion(0.6f);
//        //将面板设置到自己的内容面板中
//        setContent(jbSplitter);

        ConvertForm01 convertForm01 = new ConvertForm01(project, toolWindow);
//        JPanel jPanel = new JPanel();
//        jPanel.add(convertForm01.getjPanel());
        setContent(convertForm01.getSqlToDslPanel());
    }
}

