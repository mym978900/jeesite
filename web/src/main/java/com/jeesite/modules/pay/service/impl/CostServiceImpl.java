package com.jeesite.modules.pay.service.impl;

import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import com.jeesite.modules.clue.mapper.UpAitaskMapper;
import com.jeesite.modules.pay.model.Product;
import com.jeesite.modules.pay.service.CostService;
import com.jeesite.modules.pay.service.IAliPayService;
import com.jeesite.modules.pay.utils.CommonUtils;
import com.jeesite.modules.pay.utils.IpUtils;
import com.jeesite.modules.pay.utils.TimeUtil;
import com.jeesite.modules.test.entity.JsSysMember;
import com.jeesite.modules.test.entity.JsSysSeat;
import com.jeesite.modules.test.entity.JsSysSetmeal;
import com.jeesite.modules.test.entity.VideoOrder;
import com.jeesite.modules.test.mapper.JsSysMemberMapper;
import com.jeesite.modules.test.mapper.JsSysSeatMapper;
import com.jeesite.modules.test.mapper.JsSysSetmealMapper;
import com.jeesite.modules.test.mapper.VideoOrderMapper;
import com.jeesite.modules.test.util.DailyUtil;
import com.jeesite.modules.test.util.PasswordUtil;
import com.jeesite.modules.test.vo.AitaskVo;
import com.jeesite.modules.test.vo.CostVo;
import com.jeesite.modules.test.vo.GetUserVo;

@Service
public class CostServiceImpl implements CostService {
	@Autowired
	private JsSysSetmealMapper jsSysSetmealMapper;
	@Autowired
	private JsSysMemberMapper jsSysMemberMapper;
	@Autowired
	private JsSysSeatMapper jsSysSeatMapper;
	@Autowired
	private VideoOrderMapper videoOrderMapper;
	@Autowired
	private UpAitaskMapper upAitaskMapper;

	@Override
	public List<JsSysSetmeal> findAllMeal() {
		// TODO Auto-generated method stub
		return jsSysSetmealMapper.selectAllMeal();
	}

	@Override
	public JsSysMember findBalanceByNum(String loginCode) {
		// TODO Auto-generated method stub
		return jsSysMemberMapper.selectMemberByNumber(loginCode);
	}

	@Override
	public List<VideoOrder> findOrderByLimit(CostVo vo) {
		// TODO Auto-generated method stub
		return videoOrderMapper.findOrderByLimit(vo);
	}

	@Override
	public JsSysSeat findSeat(String i) {
		// TODO Auto-generated method stub
		return jsSysSeatMapper.selectByPrimaryKey(i);
	}

	@Override
	public String insertPaymentByBalance(Product product, HttpServletRequest request, HttpServletResponse response,
			Model model) {
		// TODO Auto-generated method stub
		GetUserVo userVo = DailyUtil.getLoginUser(response, model);
		// 查询会员信息
		JsSysMember member = jsSysMemberMapper.selectMemberByNumber(userVo.getUser().getLoginCode());
		// 判断用户余额是否充足
		if (Double.doubleToLongBits(Double.valueOf(member.getReserveField1())) >= Double
				.doubleToLongBits(Double.valueOf(product.getTotalFee()))) {
			VideoOrder order = new VideoOrder();
			String openid = CommonUtils.generateUUID();
			order.setOpenid(openid);
			order.setOutTradeNo(product.getSubject());
			order.setState(0);
			order.setCreateTime(new Date());
			order.setTotalFee(product.getTotalFee());
			order.setNickname("余额支付");
			order.setHeadImg(userVo.getUser().getUserCode());
			order.setVideoTitle("消费");
			order.setIp(IpUtils.getIpAddr(request));
			order.setDel(0);
			order.setVideoImg(userVo.getUser().getLoginCode());
			int num = videoOrderMapper.insertSelective(order);
			if (num != 1) {
				return "0";
			}
			return openid;
		}

		return "1";
	}

	@Override
	public Integer toBalancePayment(Product product, HttpServletRequest request, HttpServletResponse response,
			Model model) throws ParseException {
		// TODO Auto-generated method stub
		GetUserVo userVo = DailyUtil.getLoginUser(response, model);
		System.out.println(product.getBody());
		boolean boo = PasswordUtil.checkPassword(product.getBody(), userVo.getUser().getPassword());
		System.out.println(boo);
		if (!boo) {
			return 0;// 密码输入错误
		}
		// 6、数据库查找订单,如果存在则根据订单号更新该订单
		VideoOrder VideoOrder = videoOrderMapper.findOrderByTradeNo(product.getOutTradeNo());
		System.out.println(VideoOrder);
		if (VideoOrder != null && VideoOrder.getState() == 0) { // 判断逻辑看业务场景
			/**
			 * 会员表编辑
			 */
			// 查询会员信息
			JsSysMember member = jsSysMemberMapper.selectMemberByNumber(VideoOrder.getVideoImg());
			// 标题
			String title = VideoOrder.getOutTradeNo();
			// 会员等级
			String grade = member.getMemberGrade();
			// 到期时间
			String overTime = member.getMemberOvertime();
			// 余额
			String balance = String.format("%.2f",
					(Double.valueOf(member.getReserveField1()) - Double.valueOf(VideoOrder.getTotalFee())));
			// 坐席数量
			String seat = member.getReserveDield2();

			if (title.length() > 2) {
				String lei = title.substring(0, 2);
				if (lei.equals("开通")) {
					String type = title.substring(2, 5);
					SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
					overTime = formatter.format(new Date());
					if (type.equals("基础版")) {
						grade = "1";
						String time = title.substring(5, 7);
						if (time.equals("半年"))
							overTime = TimeUtil.getBanYear(overTime);
						else
							overTime = TimeUtil.getZhenYear(overTime);
					} else if (type.equals("升级版")) {
						grade = "2";
						String time = title.substring(5, 7);
						if (time.equals("半年"))
							overTime = TimeUtil.getBanYear(overTime);
						else
							overTime = TimeUtil.getZhenYear(overTime);
					} else {
						grade = "3";
						String time = title.substring(5, 7);
						if (time.equals("半年"))
							overTime = TimeUtil.getBanYear(overTime);
						else
							overTime = TimeUtil.getZhenYear(overTime);
					}
				} else if (lei.equals("续费")) {
					String type = title.substring(5, 7);
					if (type.equals("半年"))
						overTime = TimeUtil.getBanYear(overTime);
					else
						overTime = TimeUtil.getZhenYear(overTime);
				} else if (lei.equals("升级")) {
					String type = title.substring(2, 5);
					if (type.equals("基础版")) {
						grade="1";
					}else if(type.equals("升级版")) {
						grade="2";
					}else {
						grade="3";
					}
				}
				else {
				}
			} else {
				String lei = title.substring(0, 2);
				if (lei.equals("坐席"))
					seat = (Integer.parseInt(seat) + 1) + "";
				else {
				}
			}
			JsSysMember mem = new JsSysMember();
			mem.setSerialNumber(member.getSerialNumber());
			mem.setMemberGrade(grade);
			mem.setMemberOvertime(overTime);
			mem.setReserveField1(balance);
			mem.setReserveDield2(seat);
			// 需要修改订单的信息
			VideoOrder videoOrder = new VideoOrder(VideoOrder.getOpenid(), 1, new Date());

			// 根据商户订单号更新订单
			int num1 = videoOrderMapper.updateByPrimaryKeySelective(videoOrder);
			int num2 = jsSysMemberMapper.updateByPrimaryKeySelective(mem);
			if (num1 == 1 && num2 == 1) {
				return 2;// 余额支付成功
			} else {
				return 1;// 支付失败（网络原因等不可控因素）
			}
		}
		return 3;// 订单为空，或者该订单已支付

	}

	@Override
	public Product getUpGradeMoney(Product product, HttpServletResponse response, Model model) throws ParseException {
		// TODO Auto-generated method stub
		GetUserVo userVo = DailyUtil.getLoginUser(response, model);
		// 升级的套餐
		JsSysSetmeal meal1 = jsSysSetmealMapper.findMealByTitle(product.getBody());
		// 当前的套餐
		JsSysMember member = jsSysMemberMapper.selectMemberByNumber(userVo.getUser().getLoginCode());
		JsSysSetmeal meal2 = jsSysSetmealMapper.selectByPrimaryKey(member.getMemberGrade());
		// 升级价格
		int numa;
		int numb;
		int ints;
		// 用户到期天数
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date date = sdf.parse(member.getMemberOvertime());
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(new Date());
		long timeInMillis1 = calendar.getTimeInMillis();
		calendar.setTime(date);
		long timeInMillis2 = calendar.getTimeInMillis();
		long betweenDays = (timeInMillis2 - timeInMillis1) / (1000L * 3600L * 24L);
		
		// 应付金额
		if (betweenDays > 182) {
			numa = (int) Math.floor(Double.valueOf(meal1.getWholePrice()));
			numb = (int) Math.floor(Double.valueOf(meal2.getWholePrice()));
			ints=365;
		} else {
			numa = (int) Math.floor(Double.valueOf(meal1.getHalfPrice()));
			numb = (int) Math.floor(Double.valueOf(meal2.getHalfPrice()));
			ints=182;
		}
		float num = (float) (numa - numb) / ints;
		DecimalFormat df = new DecimalFormat("0.00");// 格式化小数
		String s = df.format(num);// 返回的是String类型
		System.out.println(s);
		Long l=Math.round(Double.valueOf(s) * betweenDays);
		System.out.println(l);
		String str=String.format("%.2f",(Double.valueOf(l)));
		Product prod=new Product();
		prod.setTotalFee(str);
		return prod;
	}

	@Override
	public List<AitaskVo> getDateStatistics() {
		// TODO Auto-generated method stub
		List<AitaskVo> aitaskVos=upAitaskMapper.getDateStatisticsByDay();
		return aitaskVos;
	}

	@Override
	public List<AitaskVo> getMonthStatistics() {
		// TODO Auto-generated method stub
		List<AitaskVo> aitaskVos=upAitaskMapper.getDateStatisticsByMonth();
		return aitaskVos;
	}

	@Override
	public Integer findMemberNum() {
		// TODO Auto-generated method stub
		return jsSysMemberMapper.findMemberNum();
	}

	@Override
	public Integer findMemberNumByMonth() {
		// TODO Auto-generated method stub
		return jsSysMemberMapper.findMemberNumByMonth();
	}

	@Override
	public Integer findClueNum() {
		// TODO Auto-generated method stub
		return jsSysMemberMapper.findClueNum();
	}

	@Override
	public Integer findClueNumByMonth() {
		// TODO Auto-generated method stub
		return jsSysMemberMapper.findClueNumByMonth();
	}

	@Override
	public String updateBalanceByCallTime(String userCode, Integer duration) {
		// TODO Auto-generated method stub
		//获取用户会员信息
		JsSysMember member=jsSysMemberMapper.getMemberByAccountCode(userCode);
		//分钟数
		int temp=duration/60;
		if (duration % 60!=0) {
			temp=temp+1;
		}
		//应扣金额
		Double reduceMondy=0.15 * temp;
		//比较
		if (reduceMondy <= Double.valueOf(member.getReserveField1())+10) {
			//修改余额
			String balanceMoney=String.format("%.2f",(Double.valueOf(member.getReserveField1())-reduceMondy));
			JsSysMember mem=new JsSysMember();
			mem.setSerialNumber(member.getSerialNumber());
			mem.setReserveField1(balanceMoney);
			int num=jsSysMemberMapper.updateByPrimaryKeySelective(mem);
			if (num==1) {
				return reduceMondy+"";
			}
		}
		return "0";
	}
	
}