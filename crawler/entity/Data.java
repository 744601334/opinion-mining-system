package TextMining.crawler.entity;

import java.util.ArrayList;

/**
 * ������ʵ��
 * 
 * @author lyq
 * 
 */
public class Data {
	// ��Ӧ������ID
	private String targetid;
	// �����ŵ���������
	private int total;
	// ��ǰ��ȡ���������е�����������id
	private String first;
	// ��ǰ��ȡ���������е�ĩβ������id
	private String last;
	// �ж��ڴ����ݺ��滹��û����������
	private boolean hasnext;
	// �����������б�
	private ArrayList<Comment> commentid;

	public String getTargetid() {
		return targetid;
	}

	public void setTargetid(String targetid) {
		this.targetid = targetid;
	}

	public int getTotal() {
		return total;
	}

	public void setTotal(int total) {
		this.total = total;
	}

	public String getFirst() {
		return first;
	}

	public void setFirst(String first) {
		this.first = first;
	}

	public String getLast() {
		return last;
	}

	public void setLast(String last) {
		this.last = last;
	}

	public boolean isHasnext() {
		return hasnext;
	}

	public void setHasnext(boolean hasnext) {
		this.hasnext = hasnext;
	}

	public ArrayList<Comment> getCommentid() {
		return commentid;
	}

	public void setCommentid(ArrayList<Comment> commentid) {
		this.commentid = commentid;
	}
}
