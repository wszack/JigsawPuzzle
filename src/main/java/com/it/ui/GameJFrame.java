package com.it.ui;

import javax.swing.*;
import javax.swing.border.BevelBorder;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Random;

public class GameJFrame extends JFrame implements KeyListener, ActionListener {

    // 创建一个二维数组
    int[][] data = new int[4][4];

    int x = 0;
    int y = 0;

    int[][] win = new int[][]{
            {1, 2, 3, 4},
            {5, 6, 7, 8},
            {9, 10, 11, 12},
            {13, 14, 15, 0}
    };
    int[][] failed = new int[][]{
            {2, 1, 3, 4},
            {5, 6, 7, 8},
            {9, 10, 11, 12},
            {13, 14, 15, 0}
    };

    int step = 0;

    String path = "JigsawPuzzle/image/animal/animal3/";

    // 创建选项下面的条目对象
    JMenuItem replayItem = new JMenuItem("重新游戏");
    JMenuItem reLoginItem = new JMenuItem("重新登陆");
    JMenuItem closeItem = new JMenuItem("关闭游戏");

    JMenuItem accountItem = new JMenuItem("公众号");

    JMenuItem girlItem = new JMenuItem("美女");
    JMenuItem animalItem = new JMenuItem("动物");
    JMenuItem sportItem = new JMenuItem("运动");

    public GameJFrame() {
        // 初始化界面
        initJFrame();
        // 初始化菜单
        initMenuBar();
        // 打乱图片
        initData();
        // 初始化图片
        initImage();


        // 设置页面显示
        this.setVisible(true);
    }

    private void initJFrame() {
        // 设置窗口大小
        this.setSize(603, 680);
        // 设置标题
        this.setTitle("拼图单机版 v1.0");
        // 设置页面置顶
        this.setAlwaysOnTop(true);
        // 设置页面居中
        this.setLocationRelativeTo(null);
        // 设置默认关闭方式
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        // 取消默认居中放置
        this.setLayout(null);
        this.addKeyListener(this);
    }

    private void initData() {
        int[] arr = {0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15};
        // 打乱arr
        Random r = new Random();
        for (int i = 0; i < arr.length; i++) {
            int index = r.nextInt(arr.length);
            int temp = arr[i];
            arr[i] = arr[index];
            arr[index] = temp;
        }
        //System.out.println(Arrays.toString(arr));
        // 给二维数组添加数据
        // 1.遍历一维数组,把每一个元素添加到二维数组
        for (int i = 0; i < arr.length; i++) {
            // 0/4=0 ... 3/4=0  ... 14/4=3 15/4=3
            // 0-3    0%4=0 1%4=1 3%4=3      14%4=2 15%4=3
            if (arr[i] == 0) {
                x = i / 4;
                y = i % 4;
            }
            data[i / 4][i % 4] = arr[i];
        }
    }

    private void initImage() {

        // 清除原本已经出现的所有图片
        this.getContentPane().removeAll();

        if (victory()) {
            JLabel jLabel = new JLabel(new ImageIcon("JigsawPuzzle/image/win.png"));
            // 指定图片位置
            jLabel.setBounds(203, 283, 179, 73);
            this.getContentPane().add(jLabel);
        }

        JLabel stepLabel = new JLabel("步数: " + step);
        stepLabel.setBounds(50, 30, 100, 20);
        this.getContentPane().add(stepLabel);

        // 循环添加图片4x4
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                // 创建图片ImageIcon对象
                // 创建JLabel
                int number = data[i][j];
                JLabel jLabel = new JLabel(new ImageIcon(path + number + ".jpg"));
                // 指定图片位置
                jLabel.setBounds(105 * j + 83, 105 * i + 134, 105, 105);
                // 给图片添加边框
                // 0 让图片凸起来  1 让图片凹下去
                jLabel.setBorder(new BevelBorder(BevelBorder.LOWERED));
                // 把管理容器添加到界面中
                //this.add(jLabel);
                this.getContentPane().add(jLabel);
            }
        }
        // 添加背景图片 后添加的图片内嵌(在底下)
        JLabel background = new JLabel(new ImageIcon("JigsawPuzzle/image/background.png"));
        background.setBounds(40, 40, 508, 560);
        this.getContentPane().add(background);

        // 刷新界面
        this.getContentPane().repaint();
    }


    private void initMenuBar() {
        // 初始化菜单
        // 创建整个的菜单对象
        JMenuBar jMenuBar = new JMenuBar();
        // 创建菜单下面的两个选项
        JMenu functionJMenu = new JMenu("功能");
        JMenu aboutJMenu = new JMenu("关于");

        JMenu changeImgJMenu = new JMenu("更换图片");


        // 将每个选项下面的条目添加到选项中
        functionJMenu.add(changeImgJMenu);
        functionJMenu.add(replayItem);
        functionJMenu.add(reLoginItem);
        functionJMenu.add(closeItem);


        aboutJMenu.add(accountItem);

        changeImgJMenu.add(girlItem);
        changeImgJMenu.add(animalItem);
        changeImgJMenu.add(sportItem);


        // 将选项添加到菜单上
        jMenuBar.add(functionJMenu);
        jMenuBar.add(aboutJMenu);

        // 给条目绑定事件
        replayItem.addActionListener(this);
        reLoginItem.addActionListener(this);
        closeItem.addActionListener(this);
        accountItem.addActionListener(this);

        girlItem.addActionListener(this);
        animalItem.addActionListener(this);
        sportItem.addActionListener(this);

        // 给整个界面设置菜单
        this.setJMenuBar(jMenuBar);
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {

        // 按照A显示图片
        if (e.getKeyCode() == 65) {
            this.getContentPane().removeAll();
            JLabel all = new JLabel(new ImageIcon(path + "all.jpg"));
            all.setBounds(83, 134, 420, 420);
            this.getContentPane().add(all);

            // 添加背景图片 后添加的图片内嵌(在底下)
            JLabel background = new JLabel(new ImageIcon("JigsawPuzzle/image/background.png"));
            background.setBounds(40, 40, 508, 560);
            this.getContentPane().add(background);
            // 刷新页面
            this.getContentPane().repaint();
        }

    }

    @Override
    public void keyReleased(KeyEvent e) {
        if (victory()) return;
        // 左上右下  37 38 39 40
        int keyCode = e.getKeyCode();
        switch (keyCode) {
            case 37:
                if (y == 3) return;
                data[x][y] = data[x][y + 1];
                data[x][y + 1] = 0;
                y++;
                step++;
                // 调用方法按照最新数字赋值给空白方块
                initImage();
                break;
            case 38:
                if (x == 3) return;
                // 把空白方块下面的数组往上移动
                // x y 表示空白方块索引
                data[x][y] = data[x + 1][y];
                data[x + 1][y] = 0;
                x++;
                step++;
                // 调用方法按照最新数字赋值给空白方块
                initImage();
                break;
            case 39:
                if (y == 0) return;
                data[x][y] = data[x][y - 1];
                data[x][y - 1] = 0;
                y--;
                step++;
                // 调用方法按照最新数字赋值给空白方块
                initImage();
                break;
            case 40:
                if (x == 0) return;
                data[x][y] = data[x - 1][y];
                data[x - 1][y] = 0;
                x--;
                step++;
                // 调用方法按照最新数字赋值给空白方块
                initImage();
                break;
            case 65:
                // A显示图片
                initImage();
                this.getContentPane().repaint();
                break;
            case 87:
                // W 按下W(87)通关游戏
                data = win;
                initImage();
                this.getContentPane().repaint();
                break;
        }
    }

    // 游戏胜利判断
    public boolean victory() {
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                if (data[i][j] != win[i][j]) {
                    return false;
                }
            }
        }
        return true;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object source = e.getSource();
        if (source == replayItem) {
            // 重新游戏
            // 解决游戏胜利后,开始重新游戏胜利图片不消失bug
            data = failed;
            step = 0;
            initData();
            initImage();
        } else if (source == reLoginItem) {
            // 重新登陆
            // 隐藏主界面
            this.setVisible(false);
            // 打开登陆页面
            new LoginJFrame();
        } else if (source == closeItem) {
            System.exit(0);
        } else if (source == accountItem) {
            JDialog jDialog = new JDialog();
            JLabel jLabel = new JLabel(new ImageIcon("JigsawPuzzle/image/aboutGame.png"));
            jLabel.setBounds(0, 0, 258, 258);
            jDialog.getContentPane().add(jLabel);
            jDialog.setTitle("关于游戏");
            jDialog.setSize(344, 344);
            jDialog.setAlwaysOnTop(true);
            jDialog.setLocationRelativeTo(null);
            // 弹窗不关闭,则无法操作下面页面
            jDialog.setModal(true);
            jDialog.setVisible(true);
        } else if (source == girlItem) {
            // 随机选择图片
            Random r = new Random();
            int i = (r.nextInt(13) + 1);
            // 修改path变量记录值
            path = "JigsawPuzzle/image/girl/girl" + i + "/";
            // 写一些重开一把的逻辑
            step = 0;
            initData();
            initImage();
        } else if (source == animalItem) {
            // 随机选择图片
            Random r = new Random();
            int i = (r.nextInt(8) + 1);
            // 修改path变量记录值
            path = "JigsawPuzzle/image/animal/animal" + i + "/";
            // 写一些重开一把的逻辑
            step = 0;
            initData();
            initImage();
        } else if (source == sportItem) {
            // 随机选择图片
            Random r = new Random();
            int i = (r.nextInt(10) + 1);
            // 修改path变量记录值
            path = "JigsawPuzzle/image/sport/sport" + i + "/";
            // 写一些重开一把的逻辑
            step = 0;
            initData();
            initImage();
        }
    }
}
