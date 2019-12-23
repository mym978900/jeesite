package com.jeesite.modules.clue.web;


import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.util.Streams;
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
import org.springframework.web.multipart.MultipartFile;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.jeesite.common.config.Global;
import com.jeesite.common.shiro.realm.LoginInfo;
import com.jeesite.common.web.BaseController;
import com.jeesite.common.web.http.ServletUtils;
import com.jeesite.modules.clue.entity.UpAiinfo;
import com.jeesite.modules.clue.entity.UpClue;
import com.jeesite.modules.clue.entity.UpCluefile;
import com.jeesite.modules.clue.service.UpAiInfoService;
import com.jeesite.modules.clue.service.UpClueService;
import com.jeesite.modules.clue.service.UpCluefileService;
import com.jeesite.modules.clue.utils.ClueUtils;
import com.jeesite.modules.clue.utils.ExcelReader;
import com.jeesite.modules.clue.utils.PropertiesListenerConfig;
import com.jeesite.modules.clue.vo.AiInfoVo;
import com.jeesite.modules.clue.vo.ClueExcelVo;
import com.jeesite.modules.clue.vo.ClueVo;
import com.jeesite.modules.sys.entity.User;
import com.jeesite.modules.sys.utils.UserUtils;
import com.jeesite.modules.test.entity.JsSysMember;
import com.jeesite.modules.test.service.MemberService;

@Controller
@RequestMapping(value = "${adminPath}/clue")
public class UpClueController extends BaseController{
	
	@Autowired
	private UpClueService iUpClueService;
	
	@Autowired
	private UpCluefileService iUpCluefileService;
	
	@Autowired
	private MemberService iMeberService;
	
	@Autowired
	private UpAiInfoService iUpAiInfoService;
	
	
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
		
		// 获取用户机构品类
//		String deptType = iMeberService.getDeptType(user.getLoginCode());
		JsSysMember jsm = iMeberService.getMemberByAccountCode(user.getLoginCode());
		
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
		uc.setUpClueDepttype(jsm.getOrganClass());
		
		//更新会员机构线索数量及首次上传时间
		if(jsm.getMatchUpdate()==null) {
			jsm.setMatchUpdate(new Date());
			jsm.setClueCount(jsm.getClueCount()+1);
			iMeberService.updateByPrimaryKey(jsm);
		}
		
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
		LoginInfo loginInfo = UserUtils.getLoginInfo();
		User user = UserUtils.get(loginInfo.getId());
		List<UpClue> list = new ArrayList<UpClue>();
		clv.setUserCode(user.getLoginCode());
		if(clv == null ) {
			clv = new ClueVo();
		}
		clv.setUserCode(user.getLoginCode());
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
		cv.setResult(true);
		return cv;
	}
	
	
	//线索模板下载
    @RequestMapping(value = "downLoadClueExcel", produces = "text/html;charset=UTF-8")
    @ResponseBody
    public void downLoadClueExcel(HttpServletResponse response, HttpServletRequest request, Model model) {
        //线索新建excel下载模板保存地址从配置文件中读取
//        String folderPath = ResourceBundle.getBundle("config/sysconfig").getString("clueExcelDownLoadPath") + File.separator + "clueTemplateExcel.xlsx";
    	String folderPath = "";
    	String formFileName = "奥利格线索模板.xlsx";
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
        }
        
        String userAgent = request.getHeader("User-Agent");
        // 针对IE或者以IE为内核的浏览器：
        try {
	        if (userAgent.contains("MSIE") || userAgent.contains("Trident")) {
	            formFileName = java.net.URLEncoder.encode(formFileName, "UTF-8");
	        } else {
	            // 非IE浏览器的处理：
	            formFileName = new String(formFileName.getBytes("UTF-8"), "ISO-8859-1");
	        }
        }catch(Exception e) {
        }
        //设置contentType为vnd.ms-excel
        response.setContentType("application/vnd.ms-excel;charset=utf-8");
        response.setCharacterEncoding("utf-8");

        // 对文件名进行处理。防止文件名乱码，这里前台直接定义了模板文件名，所以就不再次定义了
        //String fileName = CharEncodingEdit.processFileName(request, "clueTemplateExcel.xlsx");
        // Content-disposition属性设置成以附件方式进行下载
        response.setHeader("Content-disposition",String.format("attachment; filename=\"%s\"", formFileName));
        //调取response对象中的OutputStream对象
        OutputStream os = null;
        try {
            os = response.getOutputStream();
            wb.write(os);
            os.flush();
            os.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    //线索模板上传
    @RequestMapping(value = "uploadClueExcel")
    @ResponseBody
    public String uploadClueExcel(@RequestParam("file") MultipartFile multipartFiles,HttpServletResponse response, HttpServletRequest request, Model model) {
    	String filePath = PropertiesListenerConfig.getProperty("clue.excelFilePath");
    	Date date = new Date();  
    	String dataForm = new SimpleDateFormat("yyyyMMdd").format(date);
    	String rootPath = filePath + "/" + dataForm;
    	int effectCount = 0;//有效数
    	int ineffectCount = 0;//无效数
    	String ineffectInfo ="";//无效信息
    	
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
		
		// 获取用户机构品类
//		String deptType = iMeberService.getDeptType(user.getLoginCode());
		JsSysMember jsm = iMeberService.getMemberByAccountCode(user.getLoginCode());
		
    	File fileDir = new File(rootPath);
        if (!fileDir.exists() && !fileDir.isDirectory()) {
            fileDir.mkdirs();
        }
        try {
            if (multipartFiles != null ) {
                    try {
                    	// 解析Excel
                        List<ClueExcelVo> parsedResult = ExcelReader.readExcel(multipartFiles);
                        // 数据校验相同的手机号线索资源 - 姓名验证 - 可以上传不删除上传自动去重
                        if(parsedResult.size()>0) {
	                        UpClue uc = new UpClue();
	                        for(int i=0;i<parsedResult.size();i++) {
	                        	uc.setUpClueName(parsedResult.get(i).getUsername());
	                    		uc.setUpClueTel(parsedResult.get(i).getPhoneNumber());
	                    		if(!ClueUtils.effectiveClue(uc)) {
	                    			ineffectInfo += parsedResult.get(i).getPhoneNumber()+",";
	                    			ineffectCount++;
	                    			continue;
	                    		}else {
	                    			effectCount++;
	                    			String upClueCode = UUID.randomUUID().toString().replace("-", "");
	                    			uc.setUpClueCode(upClueCode);
	                    			uc.setUpClueName(parsedResult.get(i).getUsername());
	                    			uc.setUpClueTel(parsedResult.get(i).getPhoneNumber());
	                    			uc.setUpClueSex(parsedResult.get(i).getSex());
	                    			uc.setUpClueAge(parsedResult.get(i).getAge());
	                    			uc.setUpClueTime(date);
	                    			uc.setUpClueAddre(parsedResult.get(i).getAddress());
	                    			uc.setUpClueAppraise("0");
	                    			uc.setUpClueStatus("2");
	                    			uc.setUpUserCode(user.getUserCode());
	                    			uc.setUpClueDepttype(jsm.getOrganClass());
	                    			iUpClueService.addUpClue(uc);
	                    		}
	                        }
	                        
	                      //更新会员机构线索数量及首次上传时间
	                		if(jsm.getMatchUpdate()==null) {
	                			jsm.setMatchUpdate(new Date());
	                			jsm.setClueCount(jsm.getClueCount()+parsedResult.size());
	                			iMeberService.updateByPrimaryKey(jsm);
	                		}
	                		
	                        //保存批量线索文件记录
	                        UpCluefile ucf = new UpCluefile();
	                        ucf.setUpUsercode(user.getUserCode());
	                        ucf.setUpUsername(user.getUserName());
	                        ucf.setUpDate(date);
	                        ucf.setUpFilename(multipartFiles.getOriginalFilename());
	                        ucf.setUpFilepath(rootPath);
	                        // 获取Excel后缀名
	                        String fileType = multipartFiles.getOriginalFilename().substring(
	                        		multipartFiles.getOriginalFilename().lastIndexOf(".") + 1, multipartFiles.getOriginalFilename().length());
	                        ucf.setUpFiletype(fileType);
	                        ucf.setUpDeptcode("");
	                        ucf.setUpType("0");
	                        ucf.setUpEffectivecount(effectCount);
	                        ucf.setUpIneffectivecount(ineffectCount);
	                        if(ineffectInfo!="" && ineffectInfo.length()>0 ) {
	                        	ineffectInfo = ineffectInfo.substring(0, ineffectInfo.length()-1);
	                        }
	                        ucf.setUpIneffectiveinfo(ineffectInfo);
	                        iUpCluefileService.addUpClueFile(ucf);
	                        
	                        //以原来的名称命名,覆盖掉旧的
	                        String storagePath = rootPath+"/"+multipartFiles.getOriginalFilename();
	                        logger.info("上传的文件：" + multipartFiles.getName() + "," + multipartFiles.getContentType() + "," + multipartFiles.getOriginalFilename()
	                                +"，保存的路径为：" + storagePath);
	                         Streams.copy(multipartFiles.getInputStream(), new FileOutputStream(storagePath), true);
	                        //或者下面的
	                         // Path path = Paths.get(storagePath);
	                        //Files.write(path,multipartFiles[i].getBytes());
                        }
                    } catch (IOException e) {
                    	model.addAttribute("result", false);
                    	model.addAttribute("message", e.getMessage());
                        return ServletUtils.renderObject(response, model);
                    }
            }

        } catch (Exception e) {
        	model.addAttribute("result", false);
        	model.addAttribute("message", e.getMessage());
            return ServletUtils.renderObject(response, model);
        }
        model.addAttribute("result", true);
        model.addAttribute("message", "上传成功");
        return ServletUtils.renderObject(response, model);
    }
    
    //线索数据共享 by xf 2019.12.16
    @RequestMapping(value = "shareListData", method = RequestMethod.POST)
	@ResponseBody
    public AiInfoVo shareListData(
			@RequestParam(required = false, defaultValue = "1", value="pageNum") Integer pageNum,
			@RequestBody(required = false) AiInfoVo aiv) {
    	//默认第一页	
		pageNum = pageNum == null ? 1 : pageNum;
		PageHelper.startPage(pageNum, 8);
		//获取用户最新匹配线索批次
		LoginInfo loginInfo = UserUtils.getLoginInfo();
		User user = UserUtils.get(loginInfo.getId());
		JsSysMember jsm = iMeberService.getMemberByAccountCode(user.getLoginCode());
		List<UpAiinfo> list = new ArrayList<UpAiinfo>();
		if(aiv == null ) {
			aiv = new AiInfoVo();
		}
		aiv.setTimes(jsm.getAiTimes());
		list = iUpAiInfoService.getUpAiInfoList(aiv);
		PageInfo<UpAiinfo> page = new PageInfo<UpAiinfo>(list);
		AiInfoVo av = new AiInfoVo();
		//向前台传值
		if(aiv!= null) {
			av.setBeginTime(aiv.getBeginTime());
			av.setEndTime(aiv.getEndTime());
			av.setStatus(aiv.getStatus());
		}
		av.setPageNum(pageNum);
		av.setPageInfo(page);
		av.setResult(true);
		return av;
    	
    }
	
}
