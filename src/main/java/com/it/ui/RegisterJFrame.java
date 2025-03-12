package com.it.ui;

import javax.swing.*;

public class RegisterJFrame extends JFrame {

    public RegisterJFrame() {
        // 设置窗口大小
        this.setSize(488,500);
        // 设置标题
        this.setTitle("注册");
        // 设置页面置顶
        this.setAlwaysOnTop(true);
        // 设置页面居中
        this.setLocationRelativeTo(null);
        // 设置默认关闭方式
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        // 设置页面显示
        this.setVisible(true);
    }
}
