package TextMining.ui;

import static TextMining.tool.ConstantUtils.initFrameLocation;

import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import TextMining.crawler.entity.Comment;
import TextMining.tool.ConstantUtils;

public class CommentContentView {
	//��������ҳ����
	private String newsTitle;
	//���������б�
	private ArrayList<Comment> commentList;
	
	public CommentContentView(String newsTitle, ArrayList<Comment> commentList) {
		this.newsTitle = newsTitle;
		this.commentList = commentList;
	}

	/**
	 * ��ʾ������������
	 */
	public void showCommentContent() {
		StringBuilder strBuilder;
		String time;
		
		JFrame f = new JFrame(ConstantUtils.FRAME_COMMENT_CONTENT_VIEW_NAME);
		Container contentPane = f.getContentPane();
		contentPane.setLayout(new BorderLayout());

		JPanel p1 = new JPanel();
		p1.setLayout(new GridLayout(1, 1));
		p1.setBorder(BorderFactory
				.createTitledBorder(newsTitle));

		JTextArea t1 = new JTextArea(25, 50);
		t1.setTabSize(10);
		t1.setFont(new Font("�꿬��", Font.BOLD, 16));
		t1.setLineWrap(true);// �����Զ����й���
		t1.setWrapStyleWord(true);// ������в����ֹ���
		
		//���ò��ɱ༭
		t1.setEditable(false);
		
		strBuilder = new StringBuilder();
		//������ʾ������
		strBuilder.append(newsTitle);
		strBuilder.append("\n");
		
		//��ʾÿ�����ݸ�ʽΪʱ�䣺��������
		for(Comment c: commentList){
			//��ʱ��ת��
			time = ConstantUtils.timeTransFormat(c.getTime(), ConstantUtils.CHART_LINE_DIMENSION_DAY_AND_HOUR);
			
			strBuilder.append(time);
			strBuilder.append("��");
			strBuilder.append(c.getContent());
			//�Ի��з���Ϊ�ָ���
			strBuilder.append("\n");
		}
		//������չ�����ı���
		t1.setText(strBuilder.toString());
		t1.setBackground(ConstantUtils.BACK_COLOR);
		t1.setForeground(ConstantUtils.FONT_COLOR);
		// ��JTextArea����JScrollPane�У������������ù�����Ч���������볬��JTextArea�߶ȵ�
		p1.add(new JScrollPane(t1));
		// ����.
		contentPane.add(p1);
		f.pack();
		f.show();
		f.setDefaultCloseOperation(2);
		initFrameLocation(f);
	}
}