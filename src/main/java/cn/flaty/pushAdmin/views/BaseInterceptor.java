package cn.flaty.pushAdmin.views;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

@Controller
public class BaseInterceptor extends HandlerInterceptorAdapter {

	@Override
	public boolean preHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler) throws Exception {
		this.addBaseContext(request); // 添加web上下文路径
		return true;
	}

	private void addBaseContext(HttpServletRequest request) {
		String base = request.getContextPath();
		String servletPath = request.getServletPath();
		request.setAttribute(WebConstants.BASE, request.getScheme() + "://"
				+ request.getServerName() + ":" + request.getServerPort()
				+ base);
		request.setAttribute(WebConstants.WEBAPP_PATH,
				request.getScheme() + "://" + request.getServerName() + ":"
						+ request.getServerPort());
		request.setAttribute(WebConstants.CURRENT_PATH,
				request.getAttribute(WebConstants.BASE) + servletPath);

	}

}
