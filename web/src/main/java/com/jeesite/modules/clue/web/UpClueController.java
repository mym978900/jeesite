package com.jeesite.modules.clue.web;


import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;
import java.util.UUID;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.shiro.web.util.WebUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.ResourceUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.jeesite.common.config.Global;
import com.jeesite.common.lang.StringUtils;
import com.jeesite.common.shiro.realm.LoginInfo;
import com.jeesite.common.web.BaseController;
import com.jeesite.common.web.http.ServletUtils;
import com.jeesite.modules.clue.entity.UpClue;
import com.jeesite.modules.clue.service.UpClueService;
import com.jeesite.modules.clue.utils.ClueUtils;
import com.jeesite.modules.clue.vo.ClueVo;
import com.jeesite.modules.sys.entity.User;
import com.jeesite.modules.sys.utils.UserUtils;

@Controller
@RequestMapping(value = "${adminPath}/clue")
public class UpClueController extends BaseController{
	
	@Autowired
	private UpClueService iUpClueService;
	
	
	//用户新增单条线索 by xf 2019.12.04
	@RequestMapping(value="addUpClue", method = RequestMethod.POST)
	@ResponseBody
	public String addUpClue(HttpServletRequest request, HttpServletResponse response, Model model) {
		
		System.out.println("收到了前端请求");
		
		String name = WebUtils.getCleanParam(request,"name");
		String tel = WebUtils.getCleanParam(request,"tel");
		String sex = WebUtils.getCleanParam(request,"sex");
		String age = WebUtils.getCleanParam(request,"age");
		String site = WebUtils.getCleanParam(request,"site");
		
		if(StringUtils.isBlank(name)) {
			model.addAttribute("result", Global.FALSE);
			model.addAttribute("resdata","用户姓名为空，保存失败！请填写后保存");
			return ServletUtils.renderObject(response, model);
			
		}
		if(StringUtils.isBlank(tel)) {
			model.addAttribute("result", Global.FALSE);
			return "用户电话为空，保存失败！请填写后保存";
		}
		if(StringUtils.isBlank(sex)) {
			model.addAttribute("result", Global.FALSE);
			return "用户性别为空，保存失败！请填写后保存";
		}
		if(StringUtils.isBlank(age)) {
			model.addAttribute("result", Global.FALSE);
			return "用户年龄为空，保存失败！请填写后保存";
		}
		if(StringUtils.isBlank(site)) {
			model.addAttribute("result", Global.FALSE);
			return "用户地址为空，保存失败！请填写后保存";
		}
		
		//获取登录用户信息
		LoginInfo loginInfo = UserUtils.getLoginInfo();
		if(loginInfo == null){
			UserUtils.getSubject().logout();
			model.addAttribute("result", "login");
			model.addAttribute("data","会话超时请重新登录..");
			return ServletUtils.renderObject(response, model);
		}
		
		// 当前用户对象信息
		User user = UserUtils.get(loginInfo.getId());
		if (user == null){
			UserUtils.getSubject().logout();
			model.addAttribute("result", "login");
			model.addAttribute("data","会话超时请重新登录..");
			return ServletUtils.renderObject(response, model);
		}
		
		UpClue uc = new UpClue();
		String upClueCode = UUID.randomUUID().toString().replace("-", "");
		uc.setUpClueCode(upClueCode);
		uc.setUpClueName(name);
		uc.setUpClueTel(tel);
		uc.setUpClueSex(sex);
		uc.setUpClueAge(Integer.parseInt(age));
		uc.setUpClueTime(new Date());
		uc.setUpClueAddre(site);
		uc.setUpClueAppraise("0");
		uc.setUpClueStatus("2");
		uc.setUpUserCode(user.getUserCode());
		
		//相同的手机号线索资源 - 姓名性别验证 - 可以上传不删除上传自动去重
		if(!ClueUtils.effectiveClue(uc)) {
			model.addAttribute("result", Global.TRUE);
			return ServletUtils.renderObject(response, model);
		}
		
		iUpClueService.addUpClue(uc);
		
		model.addAttribute("result", Global.TRUE);
		
		return ServletUtils.renderObject(response, model);
	}
	
	//用户上传线索列表
	@RequestMapping(value = "listData", method = RequestMethod.POST)
	@ResponseBody
	public ClueVo listData(
			@RequestParam(required = false, defaultValue = "1", value="pageNum") Integer pageNum,
			@RequestBody(required = false) ClueVo clv) {
		//默认第一页	
		pageNum = pageNum == null ? 1 : pageNum;
		PageHelper.startPage(pageNum, 8);
		
		List<UpClue> list = new ArrayList<UpClue>();
		list = iUpClueService.getUpClueList(clv);
		PageInfo<UpClue> page = new PageInfo<UpClue>(list);
		ClueVo cv = new ClueVo();
		//向前台传值
		if(clv!= null) {
			cv.setBeginTime(clv.getBeginTime());
			cv.setEndTime(clv.getEndTime());
			cv.setStatus(clv.getStatus());
		}
		cv.setPageNum(pageNum);
		cv.setPageInfo(page);
		return cv;
	}
	
	
	//线索模板下载
    @RequestMapping(value = "downLoadClueExcel", produces = "text/html;charset=UTF-8")
    @ResponseBody
    public String downLoadStuInfoExcel(HttpServletResponse response, HttpServletRequest request, Model model) {
        //线索新建excel下载模板保存地址从配置文件中读取
//        String folderPath = ResourceBundle.getBundle("config/sysconfig").getString("clueExcelDownLoadPath") + File.separator + "clueTemplateExcel.xlsx";
    	String folderPath = "";
		try {
			folderPath = ResourceUtils.getFile("classpath:clueTemplateExcel.xlsx").getPath();
		} catch (FileNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
        File excelFile = new File(folderPath);
        //判断模板文件是否存在
        if (!excelFile.exists() || !excelFile.isFile()) {
//            rt.put("status", "0");
//            rt.put("message", "模板文件不存在");
        	model.addAttribute("result", Global.TRUE);
        	
        }
        //文件输入流
        FileInputStream fis = null;
        XSSFWorkbook wb = null;
        //使用XSSFWorkbook对象读取excel文件
        try {
            fis = new FileInputStream(excelFile);
            wb = new XSSFWorkbook(fis);
            fis.close();
        } catch (Exception e) {
            e.printStackTrace();
//            rt.put("status", "0");
//            rt.put("message", "模板文件读取失败");
            // return rt.toJSONString();
        }
        //设置contentType为vnd.ms-excel
        response.setContentType("application/vnd.ms-excel;charset=utf-8");
        response.setCharacterEncoding("utf-8");

        // 对文件名进行处理。防止文件名乱码，这里前台直接定义了模板文件名，所以就不再次定义了
        //String fileName = CharEncodingEdit.processFileName(request, "clueTemplateExcel.xlsx");
        // Content-disposition属性设置成以附件方式进行下载
        response.setHeader("Content-disposition", "attachment;filename=clueTemplateExcel.xlsx");
        //调取response对象中的OutputStream对象
        OutputStream os = null;
        try {
            os = response.getOutputStream();
            wb.write(os);
            os.flush();
            os.close();
        } catch (IOException e) {
            e.printStackTrace();
            model.addAttribute("","");
//            rt.put("status", "0");
//            rt.put("message", "模板文件下载失败");
        }
        //return rt.toJSONString();
        return "";
    }
	
}
