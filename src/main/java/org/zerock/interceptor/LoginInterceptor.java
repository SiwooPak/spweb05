package org.zerock.interceptor;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.ui.ModelMap;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

public class LoginInterceptor extends HandlerInterceptorAdapter {

  private static final String LOGIN = "login";
  private static final Logger logger = LoggerFactory.getLogger(LoginInterceptor.class);

  @Override
  public void postHandle(HttpServletRequest request, 
      HttpServletResponse response, Object handler,
      ModelAndView modelAndView) throws Exception {

    HttpSession session = request.getSession();

    ModelMap modelMap = modelAndView.getModelMap();
    Object userVO = modelMap.get("userVO");

    if (userVO != null) {

      logger.info("new login success");
      session.setAttribute(LOGIN, userVO);

      if (request.getParameter("useCookie") != null) {

        logger.info("remember me................");
        Cookie loginCookie = new Cookie("loginCookie", session.getId());
        //loginCookie.setDomain("www.zerock.com");
        
//        loginCookie.setPath("/");
//        loginCookie.setMaxAge(60 * 60 * 24 * 7);
//        loginCookie.setMaxAge(60 * 10);
//        loginCookie.setHttpOnly(true);
//        loginCookie.setSecure(false);
//        response.addCookie(loginCookie);
        
        int expiration = 7*24*60*60;
        StringBuilder cookieString = new StringBuilder("loginCookie="+session.getId()+"; ");

        DateFormat df = new SimpleDateFormat("EEE, dd-MMM-yyyy HH:mm:ss 'GMT'", Locale.US);
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.SECOND, expiration);
        cookieString.append("Expires=" + df.format(cal.getTime()) + "; ");
        //cookieString.append("Domain="+request.getServerName()+"; ");
        cookieString.append("Version=0; ");
        cookieString.append("Path=/; ");
        cookieString.append("Max-Age=" + expiration + "; ");
        cookieString.append("HttpOnly");
        response.addHeader("Set-Cookie", cookieString.toString());
        
      }
      // response.sendRedirect("/");
      Object dest = session.getAttribute("dest");

      response.sendRedirect(dest != null ? (String) dest : "/");
    }
  }

  // @Override
  // public void postHandle(HttpServletRequest request,
  // HttpServletResponse response, Object handler,
  // ModelAndView modelAndView) throws Exception {
  //
  // HttpSession session = request.getSession();
  //
  // ModelMap modelMap = modelAndView.getModelMap();
  // Object userVO = modelMap.get("userVO");
  //
  // if(userVO != null){
  //
  // logger.info("new login success");
  // session.setAttribute(LOGIN, userVO);
  // //response.sendRedirect("/");
  //
  // Object dest = session.getAttribute("dest");
  //
  // response.sendRedirect(dest != null ? (String)dest:"/");
  // }
  // }

  @Override
  public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

    HttpSession session = request.getSession();

    if (session.getAttribute(LOGIN) != null) {
      logger.info("clear login data before");
      session.removeAttribute(LOGIN);
    }

    return true;
  }
}
