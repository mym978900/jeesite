package com.jeesite.modules.clue.web;


import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.CopyOnWriteArrayList;

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

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.byai.client.auth.Token;
import com.byai.client.core.BYClient;
import com.byai.client.core.DefaultBYClient;
import com.byai.gen.v1_0_0.api.ByaiOpenapiCalljobCreate;
import com.byai.gen.v1_0_0.api.ByaiOpenapiCalljobCustomerImport;
import com.byai.gen.v1_0_0.api.ByaiOpenapiCalljobExecute;
import com.byai.gen.v1_0_0.api.ByaiOpenapiPhoneList;
import com.byai.gen.v1_0_0.api.ByaiOpenapiRobotList;
import com.byai.gen.v1_0_0.model.ByaiOpenapiCalljobCreateParams;
import com.byai.gen.v1_0_0.model.ByaiOpenapiCalljobCreateResult;
import com.byai.gen.v1_0_0.model.ByaiOpenapiCalljobCustomerImportParams;
import com.byai.gen.v1_0_0.model.ByaiOpenapiCalljobCustomerImportResult;
import com.byai.gen.v1_0_0.model.ByaiOpenapiCalljobExecuteParams;
import com.byai.gen.v1_0_0.model.ByaiOpenapiCalljobExecuteResult;
import com.byai.gen.v1_0_0.model.ByaiOpenapiPhoneListParams;
import com.byai.gen.v1_0_0.model.ByaiOpenapiPhoneListResult;
import com.byai.gen.v1_0_0.model.ByaiOpenapiPhoneListResult.UserPhoneNumberVO;
import com.byai.gen.v1_0_0.model.ByaiOpenapiRobotListParams;
import com.byai.gen.v1_0_0.model.ByaiOpenapiRobotListResult;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.jeesite.common.config.Global;
import com.jeesite.common.shiro.realm.LoginInfo;
import com.jeesite.common.web.BaseController;
import com.jeesite.common.web.http.ServletUtils;
import com.jeesite.modules.clue.entity.UpAiinfo;
import com.jeesite.modules.clue.entity.UpAitask;
import com.jeesite.modules.clue.entity.UpClue;
import com.jeesite.modules.clue.entity.UpCluefile;
import com.jeesite.modules.clue.service.UpAiInfoService;
import com.jeesite.modules.clue.service.UpAitaskService;
import com.jeesite.modules.clue.service.UpClueService;
import com.jeesite.modules.clue.service.UpCluefileService;
import com.jeesite.modules.clue.utils.AiUtil;
import com.jeesite.modules.clue.utils.ClueUtils;
import com.jeesite.modules.clue.utils.ExcelReader;
import com.jeesite.modules.clue.utils.PropertiesListenerConfig;
import com.jeesite.modules.clue.vo.AiInfoVo;
import com.jeesite.modules.clue.vo.AiTaskVo;
import com.jeesite.modules.clue.vo.CallBackOutBoundVo;
import com.jeesite.modules.clue.vo.CallBackOutBoundVo.CallInstanceVO;
import com.jeesite.modules.clue.vo.CallBackOutBoundVo.TaskResultVO;
import com.jeesite.modules.clue.vo.ClueExcelVo;
import com.jeesite.modules.clue.vo.ClueVo;
import com.jeesite.modules.clue.vo.CreateAiVo;
import com.jeesite.modules.clue.vo.CustomerInfoExtVO;
import com.jeesite.modules.clue.vo.IntentionVo;
import com.jeesite.modules.clue.vo.RuleDetailVO;
import com.jeesite.modules.pay.service.CostService;
import com.jeesite.modules.sys.entity.User;
import com.jeesite.modules.sys.utils.UserUtils;
import com.jeesite.modules.test.entity.JsSysMember;
import com.jeesite.modules.test.service.MemberService;

import jline.internal.Log;

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
	
	@Autowired
	private UpAitaskService iUpAitaskService;
	
	@Autowired
	private CostService iCostService;
	
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
//		String deptType = iMeberService.getDeptType(user.getUserCode());
		JsSysMember jsm = iMeberService.getMemberByAccountCode(user.getUserCode());
		
		UpClue uc = new UpClue();
		String upClueCode = UUID.randomUUID().toString().replace("-", "");
		uc.setUpClueCode(upClueCode);
		uc.setUpClueName(name);
		uc.setUpClueTel(tel);
		uc.setUpClueSex(sex);
		uc.setUpClueAge(Integer.parseInt(age));
		uc.setUpClueTime(new Date());
		if(site.contains("北京市")) {
			uc.setUpClueAddre(site);
		}else {
			uc.setUpClueAddre("北京市"+site);
		}
		uc.setUpClueAppraise("0");
		uc.setUpClueStatus("2");
		uc.setUpUserCode(user.getUserCode());
		uc.setUpClueDepttype(jsm.getOrganClass());
		
		//更新会员机构线索数量及首次上传时间
		if(jsm.getMatchUpdate()==null) {
			jsm.setMatchUpdate(new Date());
		}
		
		jsm.setClueCount(jsm.getClueCount()+1);
		iMeberService.updateByPrimaryKey(jsm);
		
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
		ClueVo cv = new ClueVo();
		//获取登录用户信息
		LoginInfo loginInfo = UserUtils.getLoginInfo();
		if(loginInfo == null){
			UserUtils.getSubject().logout();
			cv.setResult(false);
			return cv;
		}
				
		// 当前用户对象信息
		User user = UserUtils.get(loginInfo.getId());
		if (user == null){
			UserUtils.getSubject().logout();
			cv.setResult(false);
			return cv;
		}
		List<UpClue> list = new ArrayList<UpClue>();
		if(clv == null ) {
			clv = new ClueVo();
		}
		//前台传的日期无时分秒，自动加上获取当天24小时创建的线索
		if(clv!=null) {
			if(clv.getBeginTime()!=  null &&clv.getEndTime() != null) {
				clv.setBeginTime(clv.getBeginTime()+" 0:0:0");
				clv.setEndTime(clv.getEndTime()+" 24:0:0");
			}
		}
		//查看会员级别
		JsSysMember member = iMeberService.getMemberByAccountCode(user.getUserCode());
		
		clv.setUserCode(user.getUserCode());
		list = iUpClueService.getUpClueList(clv);
		
		//数据脱敏处理
		UpClue uc;
		if(list != null && !list.isEmpty()) {
			for(int i=0;i<list.size();i++) {
				uc = list.get(i);
				uc.setUpClueName(ClueUtils.chineseName(uc.getUpClueName()));
				uc.setUpClueTel(ClueUtils.mobilePhone(uc.getUpClueTel()));
			}
		}
		PageInfo<UpClue> page = new PageInfo<UpClue>(list);
		//向前台传值
		if(clv!= null) {
			cv.setBeginTime(clv.getBeginTime());
			cv.setEndTime(clv.getEndTime());
			cv.setStatus(clv.getStatus());
		}
		cv.setMemberGrade(member.getMemberGrade());
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
    	String phone = "";
    	String address = "";
    	boolean p = true;
    	
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
//		String deptType = iMeberService.getDeptType(user.getUserCode());
		JsSysMember jsm = iMeberService.getMemberByAccountCode(user.getUserCode());
		
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
	                        for(int i=0;i<parsedResult.size();i++) {
	                        	UpClue uc = new UpClue();
	                        	uc.setUpClueName(parsedResult.get(i).getUsername());
	                    		uc.setUpClueTel(parsedResult.get(i).getPhoneNumber());
	                    		if(!ClueUtils.effectiveClue(uc)) {
	                    			ineffectInfo += parsedResult.get(i).getPhoneNumber()+",";
	                    			ineffectCount++;
	                    			continue;
	                    		}else {
	                    			effectCount++;
	                    			String upClueCode = UUID.randomUUID().toString().replace("-", "");
	                    			phone = parsedResult.get(i).getPhoneNumber();
	                    			uc.setUpClueCode(upClueCode);
	                    			if("".equals(parsedResult.get(i).getUsername())) {
	                    				uc.setUpIseffective("0");
	                    			}else {
	                    				uc.setUpClueName(parsedResult.get(i).getUsername());
	                    			}
	                    			//手机格式是否正确
	                    			if(!"".equals(phone)) {
	                    				p = ClueUtils.isPhoneLegal(phone);
	                    				if(p) {
	                    					uc.setUpClueTel(phone);
	                    				}else {
	                    					uc.setUpClueTel(phone);
	                    					uc.setUpIseffective("0");
	                    				}
	                    			}else {
	                    				uc.setUpIseffective("0");
	                    			}
	                    			uc.setUpClueSex(parsedResult.get(i).getSex());
	                    			uc.setUpClueAge(parsedResult.get(i).getAge());
	                    			uc.setUpClueTime(date);
	                    			address = parsedResult.get(i).getAddress();
	                    			//地址是否为空
	                    			if("".equals(address)) {
	                    				uc.setUpClueAddre(address);
	                    				uc.setUpIseffective("0");
	                    			}else {
	                    				if(!address.contains("北京市")) {
	                    					uc.setUpClueAddre(address);
	                    				}else {
	                    					uc.setUpClueAddre("北京市"+address);
	                    				}
	                    			}
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
	                		}
	                		if(jsm.getClueCount() != null) {
	                			jsm.setClueCount(jsm.getClueCount()+parsedResult.size());
	                		}else {
	                			jsm.setClueCount(parsedResult.size());
	                		}
                			iMeberService.updateByPrimaryKey(jsm);
	                		
	                        //保存批量线索文件记录
	                        UpCluefile ucf = new UpCluefile();
	                        String ucfId = UUID.randomUUID().toString().replace("-", "");
	                        ucf.setUpId(ucfId);
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
		AiInfoVo av = new AiInfoVo();
		//获取登录用户信息
		LoginInfo loginInfo = UserUtils.getLoginInfo();
		if(loginInfo == null){
			UserUtils.getSubject().logout();
			aiv.setResult(false);
			return aiv;
		}
				
		// 当前用户对象信息
		User user = UserUtils.get(loginInfo.getId());
		if (user == null){
			UserUtils.getSubject().logout();
			aiv.setResult(false);
			return aiv;
		}
		JsSysMember jsm = iMeberService.getMemberByAccountCode(user.getUserCode());
		List<UpAiinfo> list = new ArrayList<UpAiinfo>();
		if(aiv == null ) {
			aiv = new AiInfoVo();
		}
		aiv.setTimes(jsm.getAiTimes());
		list = iUpAiInfoService.getUpAiInfoList(aiv);
		
		//手机号脱敏
		UpAiinfo ua;
		if(list != null && !list.isEmpty()) {
			for(int i=0;i<list.size();i++) {
				ua = list.get(i);
				ua.setUpAiphone(ClueUtils.mobilePhone(ua.getUpAiphone()));
			}
		}
		PageInfo<UpAiinfo> page = new PageInfo<UpAiinfo>(list);
		//向前台传值
		if(aiv!= null) {
			av.setBeginTime(aiv.getBeginTime());
			av.setEndTime(aiv.getEndTime());
			av.setStatus(aiv.getStatus());
		}
		av.setMemberGrade(jsm.getMemberGrade());
		av.setPageNum(pageNum);
		av.setPageInfo(page);
		av.setResult(true);
		return av;
    	
    }
    
    //查询ai外呼和上传机构已拨打数据
    @RequestMapping(value = "getAiTask", method = RequestMethod.POST)
	@ResponseBody
    public AiTaskVo getAiTask(
    		@RequestParam(required = false, defaultValue = "1", value="pageNum") Integer pageNum,
			@RequestBody(required = false) AiTaskVo atv) {
    	AiTaskVo av = new AiTaskVo();
    	//获取登录用户信息
    	LoginInfo loginInfo = UserUtils.getLoginInfo();
    	if(loginInfo == null){
    		UserUtils.getSubject().logout();
    		av.setResult(false);
    		return av;
    	}
    	
    	// 当前用户对象信息
    	User user = UserUtils.get(loginInfo.getId());
    	if (user == null){
    		UserUtils.getSubject().logout();
    		av.setResult(false);
    		return av;
    	}
    	//查看会员级别
    	JsSysMember member = iMeberService.getMemberByAccountCode(user.getUserCode());
		//默认第一页	
		pageNum = pageNum == null ? 1 : pageNum;
		PageHelper.startPage(pageNum, 8);
		List<UpAitask> list = new ArrayList<UpAitask>();
		if(atv == null ) {
			atv = new AiTaskVo();
		}
		//前台传的日期无时分秒，自动加上获取当天24小时创建的线索
		if(atv!=null) {
			if(atv.getBeginTime()!=  null &&atv.getEndTime() != null) {
				atv.setBeginTime(atv.getBeginTime()+" 0:0:0");
				atv.setEndTime(atv.getEndTime()+" 24:0:0");
			}
		}
		
		atv.setUserCode(user.getUserCode());
		list = iUpAitaskService.getAiTask(atv);
		
		//数据脱敏处理
		UpAitask uat;
		if(list != null && !list.isEmpty()) {
			for(int i=0;i<list.size();i++) {
				uat = list.get(i);
				uat.setUpCluename(ClueUtils.chineseName(uat.getUpCluename()));
				uat.setUpCluetel(ClueUtils.mobilePhone(uat.getUpCluetel()));
			}
		}
		PageInfo<UpAitask> page = new PageInfo<UpAitask>(list);
		//向前台传值
		if(atv!= null) {
			av.setBeginTime(atv.getBeginTime());
			av.setEndTime(atv.getEndTime());
		}
		av.setMemberGrade(member.getMemberGrade());
		av.setPageNum(pageNum);
		av.setPageInfo(page);
		av.setResult(true);
		return av;
    	
    }
    
    //创建ai外呼任务
    @RequestMapping(value = "createCallJob", method = RequestMethod.POST)
	@ResponseBody
    public CreateAiVo createCallJob(
    		@RequestParam(required = false, value="source") Integer source,
    		@RequestBody(required = false) CreateAiVo aiv) {
    	//返回前端信息
    	CreateAiVo cav = new CreateAiVo();
    	//获取登录用户信息
		LoginInfo loginInfo = UserUtils.getLoginInfo();
		if(loginInfo == null){
			UserUtils.getSubject().logout();
			cav.setResult(false);
			return cav;
		}
				
		// 当前用户对象信息
		User user = UserUtils.get(loginInfo.getId());
		if (user == null){
			UserUtils.getSubject().logout();
			cav.setResult(false);
			return cav;
		}
    	
    	JsSysMember member = iMeberService.getMemberByAccountCode(user.getUserCode());
    	if(Double.parseDouble(member.getReserveField1()) < 100) {
    		cav.setResult(false);
    		cav.setMessage("您的余额当前不足100元，请充值后使用AI外呼");
    		return cav;
    	}
    	
    	//外呼记录id
    	String id="";
    	//线索集合
		List<Object> list = aiv.getList();
		//机构线索
		UpClue uc = null;
		//智能线索
		UpAiinfo ua = null;
		//外呼任务
		UpAitask ut = new UpAitask();
		//前台传值
		LinkedHashMap linkHashMap = null;
		//线索Id
		String upClueCode = "";
		//创建外呼任务,获取公司
		String accesstoken = AiUtil.getAccessToken();
		//创建客户端请求
		BYClient client = new DefaultBYClient(new Token(accesstoken));
		//获得公司的机器人话术列表
		ByaiOpenapiRobotListParams byaiOpenapiRobotListParams = new ByaiOpenapiRobotListParams();
		byaiOpenapiRobotListParams.setCompanyId(30008L);
		ByaiOpenapiRobotList byaiOpenapiRobotList = new ByaiOpenapiRobotList();
		byaiOpenapiRobotList.setAPIParams(byaiOpenapiRobotListParams);
		ByaiOpenapiRobotListResult robotResult = client.invoke(byaiOpenapiRobotList);
		
		//获得指定公司的所有线路的列表
		ByaiOpenapiPhoneListParams byaiOpenapiPhoneListParams = new ByaiOpenapiPhoneListParams();
		byaiOpenapiPhoneListParams.setCompanyId(30008L);
		ByaiOpenapiPhoneList byaiOpenapiPhoneList = new ByaiOpenapiPhoneList();
		byaiOpenapiPhoneList.setAPIParams(byaiOpenapiPhoneListParams);
		ByaiOpenapiPhoneListResult phoneResult = new ByaiOpenapiPhoneListResult();
		try {
			phoneResult = client.invoke(byaiOpenapiPhoneList);
		}catch(Exception e){
			if("当前任务绑定的线路已欠费,不能启动任务".equals(e.getMessage())) {
				Log.info("您在AI外呼预留的话费余额不足，请前往充值");
			}
		}
		
		//组合手机号码
		String phonesHead = "[";
		String phones = "";
		String phonesFoot = "]";
		if(phoneResult.getList()!=null && phoneResult.getList().length>0) {
			UserPhoneNumberVO[] upv = phoneResult.getList();
			for(int i=0;i<upv.length;i++) {
				if(i == upv.length - 1) {
					phones = Integer.parseInt(upv[i].getUserPhoneId().toString()) + "";
				}else {
					phones = Integer.parseInt(upv[i].getUserPhoneId().toString()) + ",";
				}
			}
		}
		if(phones == "") {
			cav.setResult(false);
			cav.setMessage("当前电话线路较忙，请稍后再试！");
			return cav;
		}
		
		//创建任务
		ByaiOpenapiCalljobCreateParams byaiOpenapiCalljobCreateParams = new ByaiOpenapiCalljobCreateParams();
		byaiOpenapiCalljobCreateParams.setCallJobName("培训机构推广");
		byaiOpenapiCalljobCreateParams.setCallJobType(2L);
		byaiOpenapiCalljobCreateParams.setCompanyId(30008L);
		byaiOpenapiCalljobCreateParams.setRepeatCall(true);
		JSONObject json = new JSONObject();
		JSONArray ja = new JSONArray();
		json.put("phoneStatus", 3);
		json.put("times", 3);
		json.put("interval", 5);
		ja.add(json);
		byaiOpenapiCalljobCreateParams.setRepeatCallRule(ja.toString());
		byaiOpenapiCalljobCreateParams.setRobotDefId(robotResult.getList()[0].getRobotDefId());
		byaiOpenapiCalljobCreateParams.setUserPhoneIds(phonesHead+phones+phonesFoot);
		ByaiOpenapiCalljobCreate byaiOpenapiCalljobCreate = new ByaiOpenapiCalljobCreate();
		byaiOpenapiCalljobCreate.setAPIParams(byaiOpenapiCalljobCreateParams);
		ByaiOpenapiCalljobCreateResult createResult = client.invoke(byaiOpenapiCalljobCreate);
		Long upClueTaskid = createResult.getCallJobId();
		
		//向任务中导入客户
		ByaiOpenapiCalljobCustomerImportParams byaiOpenapiCalljobCustomerImportParams = new ByaiOpenapiCalljobCustomerImportParams();
		byaiOpenapiCalljobCustomerImportParams.setCallJobId(upClueTaskid);
		byaiOpenapiCalljobCustomerImportParams.setCompanyId(30008L);
		JSONArray jarr = new JSONArray();
		//外呼任务创建时间
		Date date = new Date();
		if(list != null && !list.isEmpty()) {
			//机构线索
    		if(source == 1) {
    			for(int i=0;i<list.size();i++) {
    				JSONObject jsonCustomer = new JSONObject();
    				JSONObject properties = new JSONObject();
    				linkHashMap = (LinkedHashMap) list.get(i);
	    			upClueCode = (String) linkHashMap.get("upClueCode");
//	    			upClueCode = "73bb1c33bbb44ead8add254c21b777d3";
	    			uc = iUpClueService.selectByPrimaryKey(upClueCode);
	    			if("中".equals(uc.getUpClueSex())) {
	    				continue;
	    			}
	    			JsSysMember jsm = iMeberService.getMemberByAccountCode(user.getUserCode());
//	    			JsSysMember jsm = iMeberService.getMemberByAccountCode("system");
	    			properties.put("机构名称", jsm.getOrganName());//机构名称
	    			properties.put("课程名称", jsm.getOrganClass());//课程名称
	    			properties.put("clueSource", "1");//线索来源
	    			properties.put("clueCode", upClueCode);//线索标识
	    			properties.put("userCode", user.getUserCode());//用户编码
	    			jsonCustomer.put("name", uc.getUpClueName());
	    			jsonCustomer.put("phone", uc.getUpClueTel());
	    			jsonCustomer.put("properties", properties);
	    			jarr.add(jsonCustomer);
	    			uc.setUpClueTaskid(upClueTaskid.toString());
	    			iUpClueService.updateByPrimaryKey(uc);
	    			//创建外呼任务记录
	    			id = UUID.randomUUID().toString().replace("-", "");
	    			ut.setUpId(id);
	    			ut.setUpTaskid(upClueTaskid.toString());
	    			ut.setUpSource("1");
	    			ut.setUpCreatetime(date);
	    			ut.setUpCreateusercode(user.getUserCode());
	    			ut.setUpCluecode(upClueCode);
	    			ut.setUpCluename(uc.getUpClueName());
	    			ut.setUpDeductionmark("0");
	    			ut.setUpCluetel(uc.getUpClueTel());
	    			iUpAitaskService.addUpAitask(ut);
	    		}
			}
    		//匹配线索
    		else {
    			for(int i=0;i<list.size();i++) {
    				JSONObject jsonCustomer = new JSONObject();
    				JSONObject properties = new JSONObject();
    				linkHashMap = (LinkedHashMap)  list.get(i);
    				upClueCode = (String) linkHashMap.get("upAicode");
	    			uc = iUpClueService.selectByPrimaryKey(upClueCode);
	    			if("中".equals(uc.getUpClueSex())) {
	    				continue;
	    			}
	    			JsSysMember jsm = iMeberService.getMemberByAccountCode(user.getUserCode());
	    			properties.put("机构名称", jsm.getOrganName());//机构名称
	    			properties.put("课程名称", jsm.getOrganClass());//课程名称
	    			properties.put("clueSource", "2");//线索来源
	    			properties.put("clueCode", upClueCode);//线索标识
//	    			properties.put("userCode", user.getUserCode());//用户编码
	    			properties.put("userCode", user.getUserCode());//用户编码
	    			jsonCustomer.put("name", uc.getUpClueName());
	    			jsonCustomer.put("phone", uc.getUpClueTel());
	    			jsonCustomer.put("properties", properties);
	    			jarr.add(jsonCustomer);
	    			iUpAiInfoService.updateAiInfoByUserCodeUpClueCode(upClueTaskid.toString(),user.getUserCode(),upClueCode);
	    			//创建外呼任务记录
	    			id = UUID.randomUUID().toString().replace("-", "");
	    			ut.setUpId(id);
	    			ut.setUpTaskid(upClueTaskid.toString());
	    			ut.setUpSource("2");
	    			ut.setUpCreatetime(date);
	    			ut.setUpCreateusercode(user.getUserCode());
	    			ut.setUpCluecode(upClueCode);
	    			ut.setUpCluename(uc.getUpClueName());
	    			ut.setUpCluetel(uc.getUpClueTel());
	    			ut.setUpDeductionmark("0");
	    			iUpAitaskService.addUpAitask(ut);
	    		}
    		}
		}
		
		
		byaiOpenapiCalljobCustomerImportParams.setCustomerInfoVOList(jarr.toString());
		ByaiOpenapiCalljobCustomerImport byaiOpenapiCalljobCustomerImport = new ByaiOpenapiCalljobCustomerImport();
		byaiOpenapiCalljobCustomerImport.setAPIParams(byaiOpenapiCalljobCustomerImportParams);
		ByaiOpenapiCalljobCustomerImportResult customerResult = client.invoke(byaiOpenapiCalljobCustomerImport);
		
		//启动任务
		ByaiOpenapiCalljobExecuteParams byaiOpenapiCalljobExecuteParams = new ByaiOpenapiCalljobExecuteParams();
		byaiOpenapiCalljobExecuteParams.setCallJobId(upClueTaskid);
		byaiOpenapiCalljobExecuteParams.setCommand(1L);
		byaiOpenapiCalljobExecuteParams.setCompanyId(30008L);
		ByaiOpenapiCalljobExecute byaiOpenapiCalljobExecute = new ByaiOpenapiCalljobExecute();
		byaiOpenapiCalljobExecute.setAPIParams(byaiOpenapiCalljobExecuteParams);
		ByaiOpenapiCalljobExecuteResult executeResult = client.invoke(byaiOpenapiCalljobExecute);
		
		cav.setResult(true);
		cav.setMessage("AI外呼开启执行！");
    	return cav;
    }
	
    //外呼回调
    @RequestMapping(value = "callBackOutBound", method = RequestMethod.POST)
	@ResponseBody
    public void callBackOutBound(@RequestBody(required = false) CallBackOutBoundVo cbob,HttpServletResponse response) {
    	//外呼回调
    	System.out.println("接收到回调请求，开始处理");
    	//意向状态
    	TaskResultVO tv;
    	String upClueCode;
    	String source;
    	String appraise = null;
    	String taskId = "";
    	String userCode;
    	int callJobStatus;
    	UpClue upClue = null;
    	UpAiinfo upAiInfo = null;
    	UpAitask upAitask;
    	String startTime;
    	String endTime;
    	int duration;//通话时长
    	int callInstanceStatus;//通话状态
    	int upFinishstatus;//最终状态
    	String costPrice;//扣费金额
    	//创建外呼任务,获取公司
    	String accesstoken = AiUtil.getAccessToken();
    	try {
	    	if("CALL_INSTANCE_RESULT".equals(cbob.getData().getCallbackType())) {
	    		System.out.println("--------1--------");
	    		CallInstanceVO cv = cbob.getData().getData().getCallInstance();
	    		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	        	taskId = cv.getCallJobId();
	        	startTime = cv.getStartTime();
	        	endTime = cv.getEndTime();
	        	duration = cv.getDuration();
				Map m = cv.getProperties();
				source = (String) m.get("clueSource");
				upClueCode = (String) m.get("clueCode");
				userCode = (String) m.get("userCode");
				callInstanceStatus = cv.getCallInstanceStatus();
				upFinishstatus = cv.getFinishStatus();
				TaskResultVO[] str = cbob.getData().getData().getTaskResult();
				if(str.length > 0) {
					if("1".equals(source)) {
						upClue = iUpClueService.selectByPrimaryKey(upClueCode);
						for(int i=0;i<str.length;i++) {
							tv = str[i];
							appraise = tv.getResultValue();
							if("A".equals(appraise) || "B".equals(appraise)) {
								upClue.setUpClueStatus("1");//已拨打
								upClue.setUpClueAppraise("1");//有意向
							}else if("C".equals(appraise)){
								upClue.setUpClueStatus("1");//已拨打
								upClue.setUpClueAppraise("2");//无意向
							}else if("D".equals(appraise)) {
								upClue.setUpClueStatus("3");//未接通
							}else if("E".equals(appraise)) {
								upClue.setUpClueStatus("4");//拨打失败
							}else if("F".equals(appraise)) {
								upClue.setUpClueStatus("5");//无效客户
							}
							upClue.setUpAiexecutetime(sdf.parse(startTime));
							upClue.setUpAiendtime(sdf.parse(startTime));
							upClue.setUpTalktime(duration);
							iUpClueService.updateByPrimaryKey(upClue);
						}
					}else {
						upAiInfo = iUpAiInfoService.getMatchClueByUserCodeAndClueCode(upClueCode,userCode);
						for(int i=0;i<str.length;i++) {
							tv = str[i];
							appraise = tv.getResultValue();
							if("A".equals(appraise) || "B".equals(appraise)) {
								upAiInfo.setUpAistatus("1");//已拨打
								upAiInfo.setUpAiappraise("1");//有意向
							}else if("C".equals(appraise)){
								upAiInfo.setUpAistatus("1");//已拨打
								upAiInfo.setUpAiappraise("2");//无意向
							}else if("D".equals(appraise)) {
								upAiInfo.setUpAistatus("3");//未接通
							}else if("E".equals(appraise)) {
								upAiInfo.setUpAistatus("4");//拨打失败
							}else if("F".equals(appraise)) {
								upAiInfo.setUpAistatus("5");//无效客户
							}
							upAiInfo.setUpAiexecutetime(sdf.parse(startTime));
							upAiInfo.setUpAiendtime(sdf.parse(endTime));
							upAiInfo.setUpTalktime(duration);
							iUpAiInfoService.updateByPrimaryKey(upAiInfo);
						}
					}
					
					//更新外呼任务信息
					upAitask = iUpAitaskService.getUpAitaskByUpCodeTaskId(taskId,upClueCode);
					if(upAitask != null) {
						if(startTime != null) {
							upAitask.setUpStartaitime(sdf.parse(startTime));
						}
						if(endTime != null) {
							upAitask.setUpEndtime(sdf.parse(startTime));
						}
						upAitask.setUpCallinstancestatus(callInstanceStatus+"");
						upAitask.setUpFinishstatus(upFinishstatus+"");
						if("1".equals(source)) {
							if(upClue != null) {
								if("A".equals(appraise) || "B".equals(appraise) ) {
									upAitask.setUpAiappraise("1");
								}else if("C".equals(appraise)) {
									upAitask.setUpAiappraise("2");
								}else {
									upAitask.setUpAiappraise("0");
								}
							}else {
								upAitask.setUpAiappraise("0");
							}
						}else if("2".equals(source)){
							if(upAiInfo!=null) {
								if("A".equals(appraise) || "B".equals(appraise) ) {
									upAitask.setUpAiappraise("1");
								}else if("C".equals(appraise)) {
									upAitask.setUpAiappraise("2");
								}else {
									upAitask.setUpAiappraise("0");
								}
							}else {
								upAitask.setUpAiappraise("0");
							}
						}
						if(callInstanceStatus == 2) {
							if("0".equals(upAitask.getUpDeductionmark())) {
								//到账户扣费
								costPrice = iCostService.updateBalanceByCallTime(userCode,duration);
								if(!"".equals(costPrice) && !"0".equals(costPrice)) {
									upAitask.setUpDeductionprice(Double.parseDouble(costPrice));
									upAitask.setUpDeductionmark("1");
								}
							}
							iUpAitaskService.updateByPrimaryKey(upAitask);
						}
						
						//查询账户余额如果不够负十元停掉当前任务
						JsSysMember member = iMeberService.getMemberByAccountCode(userCode);
						if(Double.parseDouble(member.getReserveField1())<=-10) {
							BYClient client = new DefaultBYClient(new Token(accesstoken));
							ByaiOpenapiCalljobExecuteParams byaiOpenapiCalljobExecuteParams = new ByaiOpenapiCalljobExecuteParams();

							byaiOpenapiCalljobExecuteParams.setCallJobId(Long.parseLong(taskId));
							byaiOpenapiCalljobExecuteParams.setCommand(3L);
							byaiOpenapiCalljobExecuteParams.setCompanyId(30008L);

							ByaiOpenapiCalljobExecute byaiOpenapiCalljobExecute = new ByaiOpenapiCalljobExecute();
							byaiOpenapiCalljobExecute.setAPIParams(byaiOpenapiCalljobExecuteParams);
							ByaiOpenapiCalljobExecuteResult result = client.invoke(byaiOpenapiCalljobExecute);
						}
						
					}else {
						BYClient client = new DefaultBYClient(new Token(accesstoken));
						ByaiOpenapiCalljobExecuteParams byaiOpenapiCalljobExecuteParams = new ByaiOpenapiCalljobExecuteParams();

						byaiOpenapiCalljobExecuteParams.setCallJobId(Long.parseLong(taskId));
						byaiOpenapiCalljobExecuteParams.setCommand(3L);
						byaiOpenapiCalljobExecuteParams.setCompanyId(30008L);

						ByaiOpenapiCalljobExecute byaiOpenapiCalljobExecute = new ByaiOpenapiCalljobExecute();
						byaiOpenapiCalljobExecute.setAPIParams(byaiOpenapiCalljobExecuteParams);
						ByaiOpenapiCalljobExecuteResult result = client.invoke(byaiOpenapiCalljobExecute);
						response.setCharacterEncoding("utf-8");
						response.getWriter().write("fail");
						return;
					}
					
				}
				response.setCharacterEncoding("utf-8");
				response.getWriter().write("success");
				return;
	    	}else if("JOB_INFO_RESULT".equals(cbob.getData().getCallbackType())) {
	    		System.out.println("--------2--------");
	    		callJobStatus = cbob.getData().getData().getCallJobStatus();
	    		taskId = cbob.getData().getData().getCallJobId()+"";
	    		iUpAitaskService.updateByTaskId(callJobStatus+"",taskId);
	    		if("2".equals(callJobStatus)) {
	    			String usercode = iUpAitaskService.getAiTaskBytaskId(taskId);
//	    			String number = iUpAitaskService.getAiTaskBytastCount(taskId);
//	    			JsSysMember member = iMeberService.getMemberByAccountCode(userCode);
//	    			member.get
//		    		AiUtil au = new AiUtil();
//		    		au.sentMessage(phone, number);
	    		}
	    		response.setCharacterEncoding("utf-8");
	    		response.getWriter().write("success");
				return;
	    	}
    	}catch(Exception e) {
    		e.printStackTrace();
    		try {
    			if(!"".equals(taskId)) {
	    			BYClient client = new DefaultBYClient(new Token(accesstoken));
					ByaiOpenapiCalljobExecuteParams byaiOpenapiCalljobExecuteParams = new ByaiOpenapiCalljobExecuteParams();
	
					byaiOpenapiCalljobExecuteParams.setCallJobId(Long.parseLong(taskId));
					byaiOpenapiCalljobExecuteParams.setCommand(3L);
					byaiOpenapiCalljobExecuteParams.setCompanyId(30008L);
	
					ByaiOpenapiCalljobExecute byaiOpenapiCalljobExecute = new ByaiOpenapiCalljobExecute();
					byaiOpenapiCalljobExecute.setAPIParams(byaiOpenapiCalljobExecuteParams);
					ByaiOpenapiCalljobExecuteResult result = client.invoke(byaiOpenapiCalljobExecute);
    			}
    			response.setCharacterEncoding("utf-8");
				response.getWriter().write("fail");
			} catch (IOException e1) {
				e1.printStackTrace();
			}
			return;
    	}
    }
    
    //意向用户
    @RequestMapping(value = "intentionClue", method = RequestMethod.POST)
	@ResponseBody
	public IntentionVo intentionClue(@RequestParam(required = false, defaultValue = "1", value="pageNum") Integer pageNum,
			@RequestBody(required = false) IntentionVo itv) {
    	//默认第一页	
		pageNum = pageNum == null ? 1 : pageNum;
		PageHelper.startPage(pageNum, 8);
		IntentionVo iv = new IntentionVo();
		//获取登录用户信息
		LoginInfo loginInfo = UserUtils.getLoginInfo();
		if(loginInfo == null){
			UserUtils.getSubject().logout();
			iv.setResult(false);
			return iv;
		}
				
		// 当前用户对象信息
		User user = UserUtils.get(loginInfo.getId());
		if (user == null){
			UserUtils.getSubject().logout();
			iv.setResult(false);
			return iv;
		}
		List list = new ArrayList();
		if(itv == null ) {
			itv = new IntentionVo();
		}
		//前台传的日期无时分秒，自动加上获取当天24小时创建的线索
		if(itv!=null) {
			if(itv.getBeginTime()!=  null &&itv.getEndTime() != null) {
				itv.setBeginTime(itv.getBeginTime()+" 0:0:0");
				itv.setEndTime(itv.getEndTime()+" 24:0:0");
			}
		}
		itv.setUserCode(user.getUserCode());
		list = iUpClueService.getIntentionClue(itv);
    	
		//数据脱敏处理
		HashMap m;
		if(list != null && !list.isEmpty()) {
			for(int i=0;i<list.size();i++) {
				m = (HashMap) list.get(i);
				SimpleDateFormat sdf=  new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				if(m.get("upAiendtime")!= null ) {
					m.put("upAiendtime", sdf.format(m.get("upAiendtime")));
				}
			}
		}
		PageInfo<UpAiinfo> page = new PageInfo<UpAiinfo>(list);
		//向前台传值
		if(itv!= null) {
			iv.setBeginTime(itv.getBeginTime());
			iv.setEndTime(itv.getEndTime());
		}
		iv.setPageNum(pageNum);
		iv.setPageInfo(page);
		iv.setResult(true);
		
    	return iv;
    }
    
}
