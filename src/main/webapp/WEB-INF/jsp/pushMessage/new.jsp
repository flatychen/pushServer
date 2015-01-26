<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fun"%>
<!DOCTYPE html>
<html>
<head>
</head>
<body>
	<div class="am-cf am-padding">
		<div class="am-fl am-cf">
			<strong class="am-text-primary am-text-lg">消息推送</strong> / <small>新消息</small>
		</div>
	</div>
	<div class="am-u-lg-6">
		<form class="am-form" id="pushMessage_new" url="${base}/pushMessage/create"
			method="post">
			<fieldset>
				<h2>新消息</h2>
				<hr  class=" am-divider-default" />
				<!-- <div class="am-form-group">
					<label >消息描述</label> 
					<input type="text"
						name="title"  placeholder="消息描述 " required
						minlength="3">
				</div> -->

				<div class="am-form-group">
					<label >标题</label> <input type="text"
						name="title"  placeholder="通知标题 " required
						minlength="1">
				</div>

				<div class="am-form-group">
					<label >内容</label>
					<textarea  name="content" rows="5"
						placeholder="通知内容 " required minlength="2" maxlength="50"></textarea>
				</div>
				
				<div class="am-form-group">
					<label >过期时间</label>
				<input type="text"  placeholder="过期时间 " 
						minlength="3">
				</div>

				<hr>
				
				<div class="am-form-group">
					<h2>后续操作</h2>
					<hr  class=" am-divider-default" />
					 <label class="am-radio-inline">
				        <input type="radio"  value="1" name="pushAction"  checked="checked" >  打开应用 
				      </label>
				      <label class="am-radio-inline">
				        <input type="radio" value="2" name="pushAction"> 打开URL
				      </label>
				      <label class="am-radio-inline">
				        <input type="radio" value="3" name="pushAction"> 打开ACTIVITY
				      </label>
				</div>
				<div class="am-form-group">
						<input type="text"
							name="openUrl"  placeholder="url地址 " 
							>
					</div>
					<div class="am-form-group">
						<input type="text"
							name="openActivity"  placeholder="activity地址 " 
							>
					</div>
				
				
				
				<div class="am-form-group">
					<h2>提醒方式</h2>
					<hr  class=" am-divider-default" />
					 <label class="am-checkbox-inline">
				        <input type="checkbox"  name="flags" value="1" checked="checked" > 振动提醒
				      </label>
				      <label class="am-checkbox-inline">
				        <input type="checkbox" name="flags" value="2" checked="checked"> 声音提醒
				      </label>
				      <label class="am-checkbox-inline">
				        <input type="checkbox" name="flags" value="3" checked="checked"> 呼吸灯  
				      </label>
				</div>
				
				<p>
					<button type="submit" class="am-btn am-btn-primary submit">提交</button>
				</p>
			</fieldset>
		</form>

	</div>
	<div class="am-u-lg-2"></div>
	<script type="text/javascript"  src="${base}/res/module/pushMessage/new.js"></script>
</body>
</html>