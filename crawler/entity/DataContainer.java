package TextMining.crawler.entity;

/**
 * ��������װ��
 * 
 * @author lyq
 * 
 */
public class DataContainer {
	// �����Ӧ��
	private int errCode;
	// ����������
	private Data data;

	public Data getData() {
		return data;
	}

	public void setData(Data data) {
		this.data = data;
	}

	public int getErrCode() {
		return errCode;
	}

	public void setErrCode(int errCode) {
		this.errCode = errCode;
	}
}
