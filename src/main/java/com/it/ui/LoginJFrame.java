package com.it.ui;

import com.it.domain.User;
import com.it.util.CodeUtil;

import javax.swing.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

public class LoginJFrame extends JFrame{
    // 成员变量声明
    private JTextField usernameField;
    private JTextField passwordField;
    private JTextField codeField;
    private JLabel rightCodeLabel;

    //创建一个集合存储正确的用户名和密码
    static ArrayList<User> list = new ArrayList<>();

    static {
        list.add(new User("zhangsan", "123"));
        list.add(new User("lisi", "1234"));
        list.add(new User("zzc", "123"));
    }

    public LoginJFrame() {


        //初始化界面
        initJFrame();

        //在这个界面中添加内容
        initView();

        // 设置页面显示
        this.setVisible(true);
    }

    public void initJFrame() {
        this.setSize(488, 430);//设置宽高
        this.setTitle("拼图游戏 V1.0登录");//设置标题
        this.setDefaultCloseOperation(3);//设置关闭模式
        this.setLocationRelativeTo(null);//居中
        this.setAlwaysOnTop(true);//置顶
        this.setLayout(null);//取消内部默认布局
    }

    public void initView() {
        //1. 添加用户名文字
        JLabel usernameText = new JLabel(new ImageIcon("JigsawPuzzle/image/login/用户名.png"));
        usernameText.setBounds(116, 135, 47, 17);
        this.getContentPane().add(usernameText);

        //2.添加用户名输入框
        JTextField username = new JTextField();
        username.setBounds(195, 134, 200, 30);
        this.getContentPane().add(username);

        //3.添加密码文字
        JLabel passwordText = new JLabel(new ImageIcon("JigsawPuzzle/image/login/密码.png"));
        passwordText.setBounds(130, 195, 32, 16);
        this.getContentPane().add(passwordText);

        //4.密码输入框
        JTextField password = new JTextField();
        password.setBounds(195, 195, 200, 30);
        this.getContentPane().add(password);

        //验证码提示
        JLabel codeText = new JLabel(new ImageIcon("JigsawPuzzle/image/login/验证码.png"));
        codeText.setBounds(133, 256, 50, 30);
        this.getContentPane().add(codeText);

        //验证码的输入框
        JTextField code = new JTextField();
        code.setBounds(195, 256, 100, 30);
        this.getContentPane().add(code);

        String codeStr = CodeUtil.getCode();
        JLabel rightCode = new JLabel();
        //设置内容
        rightCode.setText(codeStr);
        //位置和宽高
        rightCode.setBounds(300, 256, 50, 30);
        //添加到界面
        this.getContentPane().add(rightCode);

        //5.添加登录按钮
        JButton login = new JButton();
        login.setBounds(123, 310, 128, 47);
        login.setIcon(new ImageIcon("JigsawPuzzle/image/login/登录按钮.png"));
        //去除按钮的默认边框
        login.setBorderPainted(false);
        //去除按钮的默认背景
        login.setContentAreaFilled(false);
        this.getContentPane().add(login);


        // 为登录按钮绑定鼠标事件
        login.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                // 当按下不松的时候：修改登录按钮的背景色为蓝色
                login.setIcon(new ImageIcon("JigsawPuzzle/image/login/登录按下.png"));
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                // 当松开的时候：将按钮的背景色修改为红色
                login.setIcon(new ImageIcon("JigsawPuzzle/image/login/登录按钮.png"));
            }

            @Override
            public void mouseClicked(MouseEvent e) {
                // 当点击的时候：校验用户输入的用户名和密码是否正确
                if (e.getSource() == login) {
                    // 获取输入框中的用户名和密码
                    String inputUsername = username.getText().trim();
                    String inputPassword = password.getText().trim();
                    String inputCode = code.getText().trim();

                    // 判断1：是否为空
                    if (inputUsername.isEmpty() || inputPassword.isEmpty() || inputCode.isEmpty()) {
                        showJDialog("用户名、密码或验证码为空");
                        return;
                    }

                    // 判断验证码是否正确
                    if (!inputCode.equalsIgnoreCase(rightCode.getText())) {
                        showJDialog("验证码错误");
                        return;
                    }

                    // 判断2：判断用户名和密码是否正确
                    boolean isValidUser = false;
                    for (User user : list) {
                        if (user.getUsername().equals(inputUsername) && user.getPassword().equals(inputPassword)) {
                            isValidUser = true;
                            break;
                        }
                    }

                    if (isValidUser) {
                        // 如果正确隐藏登录界面，进入游戏界面
                        setVisible(false);
                        // 这里可以添加进入游戏界面的代码
                        //JOptionPane.showMessageDialog(null, "登录成功，进入游戏！");
                        new GameJFrame();
                    } else {
                        // 判断3：如果错误，展示弹框提示
                        showJDialog("用户名或密码错误");
                    }
                }
            }
        });

        //6.添加注册按钮
        JButton register = new JButton();
        register.setBounds(256, 310, 128, 47);
        register.setIcon(new ImageIcon("JigsawPuzzle/image/login/注册按钮.png"));
        //去除按钮的默认边框
        register.setBorderPainted(false);
        //去除按钮的默认背景
        register.setContentAreaFilled(false);
        this.getContentPane().add(register);

        //7.添加背景图片
        JLabel background = new JLabel(new ImageIcon("JigsawPuzzle/image/login/background.png"));
        background.setBounds(0, 0, 470, 390);
        this.getContentPane().add(background);
    }

    //要展示用户名或密码错误
    public void showJDialog(String content) {
        //创建一个弹框对象
        JDialog jDialog = new JDialog();
        //给弹框设置大小
        jDialog.setSize(200, 150);
        //让弹框置顶
        jDialog.setAlwaysOnTop(true);
        //让弹框居中
        jDialog.setLocationRelativeTo(null);
        //弹框不关闭永远无法操作下面的界面
        jDialog.setModal(true);

        //创建Jlabel对象管理文字并添加到弹框当中
        JLabel warning = new JLabel(content);
        warning.setBounds(0, 0, 200, 150);
        jDialog.getContentPane().add(warning);

        //让弹框展示出来
        jDialog.setVisible(true);
    }

}
