package com.pharmcube.xjy.pl_sqltodsl;

import com.intellij.openapi.project.Project;
import com.intellij.openapi.wm.ToolWindow;
import com.intellij.openapi.wm.ToolWindowFactory;
import com.intellij.ui.content.Content;
import com.intellij.ui.content.ContentFactory;
import org.jetbrains.annotations.NotNull;

import javax.swing.*;
import java.awt.*;

public class SqlToDslRightToolWindow implements ToolWindowFactory {

    @Override
    public void createToolWindowContent(@NotNull Project project, @NotNull ToolWindow toolWindow) {
        final ContentFactory factory = ContentFactory.SERVICE.getInstance();
        {
            JPanel panel = new JPanel();
            JLabel label = new JLabel("SQL convert to Elasticsearch DSL");
            label.setFont(new Font("微软雅黑", Font.BOLD, 20));
            panel.add(label);

            JTextArea inputJta = new JTextArea(15,30);
            inputJta.setToolTipText("Please enter your sql.");
//            inputJta.setText("Please enter your sql.");
            inputJta.setFont(new Font("微软雅黑", Font.PLAIN, 15));
            panel.add(inputJta);

            JButton convertBtn = new JButton("Convert");
            panel.add(convertBtn);

            JTextArea outputJta = new JTextArea(15,30);
//            inputJta.setText("Please enter your sql.");
            outputJta.setFont(new Font("微软雅黑", Font.PLAIN, 15));
            panel.add(outputJta);

            final Content content1 = factory.createContent(panel, null, false);
            toolWindow.getContentManager().addContent(content1);

            convertBtn.addActionListener(e -> {
                String actionCommand = e.getActionCommand();
                outputJta.setText("Your sql is" + inputJta.getText());
            });
        }
//        {
//            JPanel panel = new JPanel();
//            JLabel label = new JLabel("Hello world");
//            label.setFont(new Font("宋体" , Font.BOLD , 32));
//            label.setForeground(JBColor.RED);
//            panel.add(label);
//            final Content content1 = factory.createContent(panel, "world", true);
//            toolWindow.getContentManager().addContent(content1);
//        }
    }
}
