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
		<form class="am-form" action="${base}/pushMessage/create"
			method="post">
			<fieldset>
				<legend>新消息</legend>
				<!-- <div class="am-form-group">
					<label >消息描述</label> 
					<input type="text"
						name="title"  placeholder="消息描述 " required
						minlength="3">
				</div> -->

				<div class="am-form-group">
					<label >标题</label> <input type="text"
						name="title"  placeholder="通知标题 " required
						minlength="3">
				</div>

				<div class="am-form-group">
					<label >内容</label>
					<textarea  name="content" rows="5"
						placeholder="通知内容 " required minlength="5" maxlength="50"></textarea>
				</div>


				<!--  <div class="am-checkbox">
		      <label>
		        <input type="checkbox"> 复选框，选我选我选我
		      </label>
		    </div>
		
		    <div class="am-radio">
		      <label>
		        <input type="radio" name="doc-radio-1" value="option1" checked>
		        单选框 - 选项1
		      </label>
		    </div>
		
		    <div class="am-radio">
		      <label>
		        <input type="radio" name="doc-radio-1" value="option2">
		        单选框 - 选项2
		      </label>
		    </div>
		
		    <div class="am-form-group">
		      <label class="am-checkbox-inline">
		        <input type="checkbox" value="option1"> 选我
		      </label>
		      <label class="am-checkbox-inline">
		        <input type="checkbox" value="option2"> 同时可以选我
		      </label>
		      <label class="am-checkbox-inline">
		        <input type="checkbox" value="option3"> 还可以选我
		      </label>
		    </div>
		
		    <div class="am-form-group">
		      <label class="am-radio-inline">
		        <input type="radio"  value="" name="docInlineRadio"> 每一分
		      </label>
		      <label class="am-radio-inline">
		        <input type="radio" name="docInlineRadio"> 每一秒
		      </label>
		      <label class="am-radio-inline">
		        <input type="radio" name="docInlineRadio"> 多好
		      </label>
		    </div>
		
		    <div class="am-form-group">
		      <label for="doc-select-1">下拉多选框</label>
		      <select id="doc-select-1">
		        <option value="option1">选项一...</option>
		        <option value="option2">选项二.....</option>
		        <option value="option3">选项三........</option>
		      </select>
		      <span class="am-form-caret"></span>
		    </div>
		
		    <div class="am-form-group">
		      <label for="doc-select-2">多选框</label>
		      <select multiple class="" id="doc-select-2">
		        <option>1</option>
		        <option>2</option>
		        <option>3</option>
		        <option>4</option>
		        <option>5</option>
		      </select>
		    </div>
		
		    <div class="am-form-group">
		      <label for="doc-ta-1">文本域</label>
		      <textarea class="" rows="5" id="doc-ta-1"></textarea>
		    </div> -->

				<p>
					<button type="submit" class="am-btn am-btn-primary">提交</button>
				</p>
			</fieldset>
		</form>

	</div>
	<div class="am-u-lg-2"></div>
</body>
</html>