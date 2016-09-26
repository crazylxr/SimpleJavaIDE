package com.lxrsuper;

import java.awt.*;
import java.awt.event.*;
import java.io.*;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSplitPane;
import javax.swing.JToolBar;

class MyMenuDemo {
	private Frame frame;
	private MenuBar bar;
	private JToolBar myJToolBar;
	private JButton jB_file;
	private JButton jB_save;
	private JButton jB_shear;
	private JButton jB_help;
	private Menu fileMenu, editMenu, wMenu, pMenu, mMenu, oMenu, vMenu, hMenu;
	private MenuItem revokeItem, cutItem, copyItem, pasteItem;
	private MenuItem closeItem, openItem, saveItem, othersaveItem;

	private FileDialog openDia, saveDia, othersaveDia;

	private TextArea textArea;
	private TextArea textAreaDis1;
	private TextArea textAreaDis2;

	private File file;

	MyMenuDemo() {
		init();
	}

	public void init() {
		frame = new Frame("Simple Java IDE");
		frame.setBounds(300, 100, 1260, 600);
		frame.setLayout(new BorderLayout(100,100));
		
		GridLayout gl2 = new GridLayout(2, 1);
		JPanel rightPanel = new JPanel();
		JPanel leftPanel = new JPanel();
		 myJToolBar = new JToolBar();
		 myJToolBar.setBounds(new Rectangle(0, 0, 1200, 50));
		
		/*创建工具栏*/
		ImageIcon iconHelp = new ImageIcon("res/帮助.png");
		ImageIcon iconSave = new ImageIcon("res/保存.png");
		ImageIcon iconOpen = new ImageIcon("res/文件_打开.png");
		ImageIcon iconJian = new ImageIcon("res/剪切.png");
		
		 jB_file = new JButton();
		 jB_save = new JButton();
		 jB_shear = new JButton();
		 jB_help = new JButton();
		
		jB_file.setIcon(iconOpen);
		jB_save.setIcon(iconSave);
		jB_shear.setIcon(iconJian);
		jB_help.setIcon(iconHelp);
		
		myJToolBar.add(jB_file);
		myJToolBar.add(jB_save);
		myJToolBar.add(jB_shear);
		myJToolBar.add(jB_help);
		
		rightPanel.setLayout(gl2);
		
		bar = new MenuBar();

		textArea = new TextArea();
		textAreaDis1 = new TextArea();
		textAreaDis2 = new TextArea();

		leftPanel.add(textArea);
		rightPanel.add(textAreaDis1);
		rightPanel.add(textAreaDis2);
		
		frame.add("North",myJToolBar);
		frame.add("West",textArea);
		frame.add("East",rightPanel);
		
		fileMenu = new Menu("文件(F)");
		editMenu = new Menu("编辑(E)");
		wMenu = new Menu("此法分析(W)");
		pMenu = new Menu("语法分析(P)");
		mMenu = new Menu("中间代码(M)");
		oMenu = new Menu("目标代码生成(O)");
		vMenu = new Menu("查看(V)");
		hMenu = new Menu("帮助(H)");

		bar.add(fileMenu);
		bar.add(editMenu);
		bar.add(wMenu);
		bar.add(pMenu);
		bar.add(mMenu);
		bar.add(oMenu);
		bar.add(vMenu);
		bar.add(hMenu);

		openItem = new MenuItem("打开");
		saveItem = new MenuItem("保存");
		othersaveItem = new MenuItem("另存为Ϊ");
		closeItem = new MenuItem("关闭");
		fileMenu.add(openItem);
		fileMenu.add(saveItem);
		fileMenu.add(othersaveItem);
		fileMenu.add(closeItem);

		revokeItem = new MenuItem("撤销");
		cutItem = new MenuItem("剪切");
		copyItem = new MenuItem("复制");
		pasteItem = new MenuItem("粘贴");
		editMenu.add(revokeItem);
		editMenu.add(cutItem);
		editMenu.add(copyItem);
		editMenu.add(pasteItem);

		frame.setMenuBar(bar);

		openDia = new FileDialog(frame, "打开", FileDialog.LOAD);
		saveDia = new FileDialog(frame, "保存", FileDialog.SAVE);
		othersaveDia = new FileDialog(frame, "另存为", FileDialog.SAVE);

		
		myEvent();

		frame.setVisible(true);

	}

	private void myEvent() {
		saveItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (file == null)
				{
					saveDia.setVisible(true);  
					String dirPath = saveDia.getDirectory();
					String fileName = saveDia.getFile();

					if (dirPath == null || fileName == null)
						return;
					file = new File(dirPath, fileName);
				}

				try {
					BufferedWriter bufw = new BufferedWriter(new FileWriter(
							file));

					String text = textArea.getText();

					bufw.write(text);

					bufw.close();
				} catch (IOException ex) {
					throw new RuntimeException("11");
				}
			}
		});

		openItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				openDia.setVisible(true);
				String dirPath = openDia.getDirectory();// 
				String fileName = openDia.getFile();//

				if (dirPath == null || fileName == null)
					return;

				textArea.setText("");//

				file = new File(dirPath, fileName);

				try {
					BufferedReader bufr = new BufferedReader(new FileReader(
							file));

					String line = null;

					while ((line = bufr.readLine()) != null) {
						textArea.append(line + "\r\n");
					}
					bufr.close();
				} catch (IOException ex) {
					throw new RuntimeException("11");
				}

			}
		});

		othersaveItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (file == null)// 
				{
					saveDia.setVisible(true);
					String dirPath = saveDia.getDirectory();
					String fileName = saveDia.getFile();

					if (dirPath == null || fileName == null)
						return;
					file = new File(dirPath, fileName);
				}

				try {
					BufferedWriter bufw = new BufferedWriter(new FileWriter(
							file));

					String text = textArea.getText();

					bufw.write(text);

					bufw.close();
				} catch (IOException ex) {
					throw new RuntimeException("11");
				}
			}
		});

		closeItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});

		frame.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				System.exit(0);
			}
		});
	}

	public static void main(String[] args) {
		new MyMenuDemo();
	}
}