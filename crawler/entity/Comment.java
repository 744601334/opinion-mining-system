package TextMining.crawler.entity;

/**
 * ����ĵ���������
 * 
 * @author lyq
 * 
 */
public class Comment {
	// ������Ǵ����۵�ID
	private String id;
	// ���۶�Ӧ������ID
	private String targetid;
	// ���۵�ʱ��
	private long time;
	// ���۵ľ�������
	private String content;
	// ���۱����Ĵ���
	private String up;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getTargetid() {
		return targetid;
	}

	public void setTargetid(String targetid) {
		this.targetid = targetid;
	}

	public long getTime() {
		return time;
	}

	public void setTime(long time) {
		this.time = time;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getUp() {
		return up;
	}

	public void setUp(String up) {
		this.up = up;
	}

}
