package egov.main.web;

import java.util.HashMap;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import egov.main.service.MainService;

@Controller
public class MainController {
	@Resource(name="MainService")
	MainService mainService;
	
	@RequestMapping(value="/main.do") // main.do 라는 url 을 요청하면 main.jsp 파일을 보여줌
	public String main(HttpServletRequest request, ModelMap model) 
	{	//ModelMap : url을 요청한 사용자에게 데이터를 전달
		
		return "main/main";
	}
	
	
	@RequestMapping(value="/main2.do") 
	public String main2(HttpServletRequest request, ModelMap model) 
	{	
		
		return "main/main2";
	}
	@RequestMapping(value="/main3.do") 
	public String main3(@RequestParam("pw")String pw, HttpServletRequest request, ModelMap model) 
	{	
		
		String id = request.getParameter("id");
		int userNo = Integer.parseInt(request.getParameter("userNo"));
		
		if(id.equals("asdqwe"))
		{
			model.addAttribute("userid",pw);
		}
		else {
			model.addAttribute("userid",pw);
		}
		userNo = userNo+5;
		model.addAttribute("userNo",userNo);
		
		return "main/main3";
	}
	
	@RequestMapping(value="/main4/{userNo}.do") 
	public String main4(@PathVariable String userNo, HttpServletRequest request, ModelMap model) 
	{	
		
		model.addAttribute("userNo", userNo);
		return "main/main3";
	}
	
	@RequestMapping(value="/main5.do") // main.do 라는 url 을 요청하면 main.jsp 파일을 보여줌
	public String main5(HttpServletRequest request, ModelMap model) 
	{	
		HashMap<String,Object> resultMap = new HashMap<String,Object>();
		try {
			resultMap = mainService.selectMain(request);
			model.addAttribute("serverId",resultMap.get("userid".toString()));
		} catch (Exception e) {
			// 로그 기록, 상태코드 반환 또는 에러페이지 전달
			return "error/error";
		}
		return "main/main";
	}
	
}
