package purchaser;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.alibaba.fastjson.JSON;
import com.purchaser.util.CommentUtils;

/**
 * 专属测试类
 * @author administrator
 *
 */
public class Test {

	
	public static void main(String[] args) {
		try {
			// code area: coding something at here
			
			
			
			List<Map<String, Object>> listx = new ArrayList<Map<String,Object>>();
			for (int i = 0; i < 2; i++) {
				Map<String, Object> mapx = new HashMap<String, Object>();
				mapx.put("today", new Date());
				listx.add(mapx);
			}
			
			listx = CommentUtils.listDateAndFormat(listx, "yyyy-MM");
			String listStr = JSON.toJSONString(listx);
			System.err.println(listStr);
		
			
			
			
			
			
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
		
	}
	
	
}
