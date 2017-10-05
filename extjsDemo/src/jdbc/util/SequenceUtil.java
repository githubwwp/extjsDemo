package jdbc.util;

import java.util.UUID;

public class SequenceUtil {

	/**
	 * 不允许实例化
	 */
	private SequenceUtil(){}
	
	/**
	 * 生成uuid，格式 ID_c32139caf7ee49b0a5a67ad681d91517
	 * @param prefix
	 * @return
	 */
	public static String geneBaseSequence(String prefix){
		String seq = UUID.randomUUID().toString().toUpperCase();
		seq = seq.replace("-", ""); // 移除下划线
		
		// 添加前缀
		if(StringUtil.isNotBlank(prefix)){
			seq = prefix + "_" + seq;
		}
		
		return seq;
	}
	
	/**
	 * test function
	 * @param args
	 */
	public static void main(String[] args) {
		System.out.println(geneBaseSequence("ID"));
	}
	
}
