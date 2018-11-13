package zk_manage.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * 登录过滤器
 * 过滤非法访问，返回到登录页面
 */

public class FrameFilter implements Filter {
	public void destroy() {}

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		HttpServletRequest servletRequest = (HttpServletRequest)request;
		HttpServletResponse servletResponse = (HttpServletResponse)response;
		HttpSession session = servletRequest.getSession();
		//获取用户请求的URI
		String path = servletRequest.getRequestURI();
		//得到用户学号
		String sid = (String)session.getAttribute("sid");
		
		if(path.contains("/css") || path.contains("/js") || path.contains("/img")){
            //如果发现是css或者js文件，直接放行
            chain.doFilter(request, response);
            return;
        }
		if(sid == null || "".equals(sid)){
			//拦截，跳转到登录页面
			servletResponse.sendRedirect("/ZK_Manage/login.html");
		}else{
			//放行
			chain.doFilter(request, response);
		}
	}


	public void init(FilterConfig fConfig) throws ServletException {}

}
