package cn.flaty.NettyPush.window;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.border.EmptyBorder;

import org.springframework.beans.factory.annotation.Autowired;

import cn.flaty.NettyPush.services.PushService;

public class MainFrame extends JFrame {

	
	
	@Autowired
	private PushService pushService;

	private JPanel contentPane;

	private JTextArea textArea;

	/**
	 * Create the frame.
	 */
	public MainFrame() {

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		getContentPane().setLayout(new FlowLayout());
		setTitle("发消息");
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));

		JPanel panel = new JPanel();
		contentPane.add(panel, BorderLayout.CENTER);
		panel.setLayout(null);

		JLabel lblNewLabel = new JLabel("消息");
		lblNewLabel.setFont(new Font("宋体", Font.PLAIN, 12));
		lblNewLabel.setBounds(10, 40, 31, 15);
		panel.add(lblNewLabel);

		textArea = new JTextArea();
		textArea.setText("");
		textArea.setBounds(51, 10, 363, 150);
		panel.add(textArea);

		JButton btnNewButton = new JButton("发送");
		btnNewButton.setFont(new Font("宋体", Font.PLAIN, 12));
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String _s = textArea.getText().trim();
				System.out.println(_s);
				pushService.send(_s);
				textArea.setText("");
			}
		});
		btnNewButton.setBounds(302, 175, 93, 23);
		panel.add(btnNewButton);

	}


	public static void initFrame() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainFrame frame = new MainFrame();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
}
