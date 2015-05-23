package TextMining.res;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class Resource {
	public ArrayList<String> getResource() {
		String s;
		InputStream is;
		BufferedReader br;
		ArrayList<String> lines;

		s = "";
		lines = new ArrayList<>();
		// ��ȡ����İ��µ���Դ�ļ�
		is = this.getClass().getResourceAsStream("dict.txt");

		br = new BufferedReader(new InputStreamReader(is));

		try {
			// ���ж���
			while ((s = br.readLine()) != null) {
				lines.add(s);
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return lines;
	}
}
