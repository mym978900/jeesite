package com.jeesite.modules.clue.vo;

import java.util.Date;
import java.util.Map;

public class CallBackOutBoundVo {

	private Long code;
	
	private ResultDataVO data;
	
	private String resultMsg;
	
	public Long getCode() {
		return code;
	}

	public void setCode(Long code) {
		this.code = code;
	}

	public ResultDataVO getData() {
		return data;
	}

	public void setData(ResultDataVO data) {
		this.data = data;
	}

	public String getResultMsg() {
		return resultMsg;
	}

	public void setResultMsg(String resultMsg) {
		this.resultMsg = resultMsg;
	}

	public static class ResultDataVO {
		
		private String callbackType;
		
		private CallbackPushDetailVO data;
		
		public String getCallbackType() {
			return callbackType;
		}

		public void setCallbackType(String callbackType) {
			this.callbackType = callbackType;
		}

		public CallbackPushDetailVO getData() {
			return data;
		}

		public void setData(CallbackPushDetailVO data) {
			this.data = data;
		}
		
	}
	
	public static class CallbackPushDetailVO {
		
		private CallInstanceVO callInstance;
		
		private PhoneLogVO[] phoneLogs;
		
		private TaskResultVO[] taskResult;
		
		//任务状态回调
		private String jobName;
		
		private int companyId;
		
		private int callJobStatus;
		
		private int callJobId;
		
		public String getJobName() {
			return jobName;
		}

		public void setJobName(String jobName) {
			this.jobName = jobName;
		}

		public int getCompanyId() {
			return companyId;
		}

		public void setCompanyId(int companyId) {
			this.companyId = companyId;
		}

		public int getCallJobStatus() {
			return callJobStatus;
		}

		public void setCallJobStatus(int callJobStatus) {
			this.callJobStatus = callJobStatus;
		}

		public int getCallJobId() {
			return callJobId;
		}

		public void setCallJobId(int callJobId) {
			this.callJobId = callJobId;
		}

		public CallInstanceVO getCallInstance() {
			return callInstance;
		}

		public void setCallInstance(CallInstanceVO callInstance) {
			this.callInstance = callInstance;
		}

		public PhoneLogVO[] getPhoneLogs() {
			return phoneLogs;
		}

		public void setPhoneLogs(PhoneLogVO[] phoneLogs) {
			this.phoneLogs = phoneLogs;
		}

		public TaskResultVO[] getTaskResult() {
			return taskResult;
		}

		public void setTaskResult(TaskResultVO[] taskResult) {
			this.taskResult = taskResult;
		}
		
	}
	
	public static class CallInstanceVO {
		
		private int calledTimes;
		
		private long callInstanceId;
		
		private int callInstanceStatus;
		
		private String callJobId;
		
		private int chatRound;
		
		private int companyId;
		
		private String customerName;
		
		private String customerTelephone;
		
		private int duration;
		
		private String endTime;
		
		private int finishStatus;
		
		private int hangUp;
		
		private String jobName;
		
		private String luyinOssUrl;
		
		private Map properties;
		
		private int robotDefId;
		
		private String startTime;
		
		private String userLuyinOssUrl;

		public int getCalledTimes() {
			return calledTimes;
		}

		public void setCalledTimes(int calledTimes) {
			this.calledTimes = calledTimes;
		}

		public long getCallInstanceId() {
			return callInstanceId;
		}

		public void setCallInstanceId(long callInstanceId) {
			this.callInstanceId = callInstanceId;
		}

		public int getCallInstanceStatus() {
			return callInstanceStatus;
		}

		public void setCallInstanceStatus(int callInstanceStatus) {
			this.callInstanceStatus = callInstanceStatus;
		}

		public String getCallJobId() {
			return callJobId;
		}

		public void setCallJobId(String callJobId) {
			this.callJobId = callJobId;
		}

		public int getChatRound() {
			return chatRound;
		}

		public void setChatRound(int chatRound) {
			this.chatRound = chatRound;
		}

		public int getCompanyId() {
			return companyId;
		}

		public void setCompanyId(int companyId) {
			this.companyId = companyId;
		}

		public String getCustomerName() {
			return customerName;
		}

		public void setCustomerName(String customerName) {
			this.customerName = customerName;
		}

		public String getCustomerTelephone() {
			return customerTelephone;
		}

		public void setCustomerTelephone(String customerTelephone) {
			this.customerTelephone = customerTelephone;
		}

		public int getDuration() {
			return duration;
		}

		public void setDuration(int duration) {
			this.duration = duration;
		}

		public String getEndTime() {
			return endTime;
		}

		public void setEndTime(String endTime) {
			this.endTime = endTime;
		}

		public int getFinishStatus() {
			return finishStatus;
		}

		public void setFinishStatus(int finishStatus) {
			this.finishStatus = finishStatus;
		}

		public int getHangUp() {
			return hangUp;
		}

		public void setHangUp(int hangUp) {
			this.hangUp = hangUp;
		}

		public String getJobName() {
			return jobName;
		}

		public void setJobName(String jobName) {
			this.jobName = jobName;
		}

		public String getLuyinOssUrl() {
			return luyinOssUrl;
		}

		public void setLuyinOssUrl(String luyinOssUrl) {
			this.luyinOssUrl = luyinOssUrl;
		}

		public Map getProperties() {
			return properties;
		}

		public void setProperties(Map properties) {
			this.properties = properties;
		}

		public int getRobotDefId() {
			return robotDefId;
		}

		public void setRobotDefId(int robotDefId) {
			this.robotDefId = robotDefId;
		}

		public String getStartTime() {
			return startTime;
		}

		public void setStartTime(String startTime) {
			this.startTime = startTime;
		}

		public String getUserLuyinOssUrl() {
			return userLuyinOssUrl;
		}

		public void setUserLuyinOssUrl(String userLuyinOssUrl) {
			this.userLuyinOssUrl = userLuyinOssUrl;
		}
		
	}
	
	public static class PhoneLogVO{
		
		private int aiUnknown;
		
		private long callInstanceId;
		
		private String content;
		
		private Date endTime;
		
		private String speaker;
		
		private Date startTime;
		
		private String userMean;
		
		private UserMeanDetailVO[] userMeanDetail;

		public int getAiUnknown() {
			return aiUnknown;
		}

		public void setAiUnknown(int aiUnknown) {
			this.aiUnknown = aiUnknown;
		}

		public long getCallInstanceId() {
			return callInstanceId;
		}

		public void setCallInstanceId(long callInstanceId) {
			this.callInstanceId = callInstanceId;
		}

		public String getContent() {
			return content;
		}

		public void setContent(String content) {
			this.content = content;
		}

		public Date getEndTime() {
			return endTime;
		}

		public void setEndTime(Date endTime) {
			this.endTime = endTime;
		}

		public String getSpeaker() {
			return speaker;
		}

		public void setSpeaker(String speaker) {
			this.speaker = speaker;
		}

		public Date getStartTime() {
			return startTime;
		}

		public void setStartTime(Date startTime) {
			this.startTime = startTime;
		}

		public String getUserMean() {
			return userMean;
		}

		public void setUserMean(String userMean) {
			this.userMean = userMean;
		}

		public UserMeanDetailVO[] getUserMeanDetail() {
			return userMeanDetail;
		}

		public void setUserMeanDetail(UserMeanDetailVO[] userMeanDetail) {
			this.userMeanDetail = userMeanDetail;
		}
		
	}
	
	public static class UserMeanDetailVO {
		
		private String answer;
		
		private int score;

		public String getAnswer() {
			return answer;
		}

		public void setAnswer(String answer) {
			this.answer = answer;
		}

		public int getScore() {
			return score;
		}

		public void setScore(int score) {
			this.score = score;
		}
		
	}
	
	public static class TaskResultVO {
		
		private String resultDesc;
		
		private ResultLabelVO[] resultLabels;
		
		private String resultName;
		
		private String resultValue;

		public String getResultDesc() {
			return resultDesc;
		}

		public void setResultDesc(String resultDesc) {
			this.resultDesc = resultDesc;
		}

		public ResultLabelVO[] getResultLabels() {
			return resultLabels;
		}

		public void setResultLabels(ResultLabelVO[] resultLabels) {
			this.resultLabels = resultLabels;
		}

		public String getResultName() {
			return resultName;
		}

		public void setResultName(String resultName) {
			this.resultName = resultName;
		}

		public String getResultValue() {
			return resultValue;
		}

		public void setResultValue(String resultValue) {
			this.resultValue = resultValue;
		}
		
	}
	
	public static class ResultLabelVO {
		
		private int key;
		
		private String value;

		public int getKey() {
			return key;
		}

		public void setKey(int key) {
			this.key = key;
		}

		public String getValue() {
			return value;
		}

		public void setValue(String value) {
			this.value = value;
		}
		
	}
	
}
