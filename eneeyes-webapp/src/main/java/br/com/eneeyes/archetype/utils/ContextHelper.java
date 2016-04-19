package br.com.eneeyes.archetype.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.Principal;
import java.util.Enumeration;
import java.util.Locale;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletInputStream;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@SuppressWarnings("rawtypes")
public class ContextHelper {
	private HttpServletRequest request;

	public ContextHelper(HttpServletRequest request) {
		this.request = request;
	}
	
	public String url(String path) {
		if(!"/".equals(path.substring(0, 1)))
			path = "/".concat(path);
		return getContextPath().concat(path);
	}
	
	public Object getAttribute(String name) {
		return request.getAttribute(name);
	}

	public String getAuthType() {
		return request.getAuthType();
	}

	public Cookie[] getCookies() {
		return request.getCookies();
	}

	public Enumeration getAttributeNames() {
		return request.getAttributeNames();
	}

	public long getDateHeader(String name) {
		return request.getDateHeader(name);
	}

	public String getCharacterEncoding() {
		return request.getCharacterEncoding();
	}

	public void setCharacterEncoding(String env)
			throws UnsupportedEncodingException {
		request.setCharacterEncoding(env);
	}

	public String getHeader(String name) {
		return request.getHeader(name);
	}

	public int getContentLength() {
		return request.getContentLength();
	}

	public String getContentType() {
		return request.getContentType();
	}

	public Enumeration getHeaders(String name) {
		return request.getHeaders(name);
	}

	public ServletInputStream getInputStream() throws IOException {
		return request.getInputStream();
	}

	public String getParameter(String name) {
		return request.getParameter(name);
	}

	public Enumeration getHeaderNames() {
		return request.getHeaderNames();
	}

	public int getIntHeader(String name) {
		return request.getIntHeader(name);
	}

	public Enumeration getParameterNames() {
		return request.getParameterNames();
	}

	public String getMethod() {
		return request.getMethod();
	}

	public String[] getParameterValues(String name) {
		return request.getParameterValues(name);
	}

	public String getPathInfo() {
		return request.getPathInfo();
	}

	public Map getParameterMap() {
		return request.getParameterMap();
	}

	public String getProtocol() {
		return request.getProtocol();
	}

	public String getPathTranslated() {
		return request.getPathTranslated();
	}

	public String getScheme() {
		return request.getScheme();
	}

	public String getContextPath() {
		return request.getContextPath();
	}

	public String getServerName() {
		return request.getServerName();
	}

	public int getServerPort() {
		return request.getServerPort();
	}

	public BufferedReader getReader() throws IOException {
		return request.getReader();
	}

	public String getQueryString() {
		return request.getQueryString();
	}

	public String getRemoteUser() {
		return request.getRemoteUser();
	}

	public String getRemoteAddr() {
		return request.getRemoteAddr();
	}

	public String getRemoteHost() {
		return request.getRemoteHost();
	}

	public boolean isUserInRole(String role) {
		return request.isUserInRole(role);
	}

	public void setAttribute(String name, Object o) {
		request.setAttribute(name, o);
	}

	public Principal getUserPrincipal() {
		return request.getUserPrincipal();
	}

	public String getRequestedSessionId() {
		return request.getRequestedSessionId();
	}

	public void removeAttribute(String name) {
		request.removeAttribute(name);
	}

	public String getRequestURI() {
		return request.getRequestURI();
	}

	public Locale getLocale() {
		return request.getLocale();
	}

	public Enumeration getLocales() {
		return request.getLocales();
	}

	public StringBuffer getRequestURL() {
		return request.getRequestURL();
	}

	public boolean isSecure() {
		return request.isSecure();
	}

	public RequestDispatcher getRequestDispatcher(String path) {
		return request.getRequestDispatcher(path);
	}

	public String getServletPath() {
		return request.getServletPath();
	}

	public HttpSession getSession(boolean create) {
		return request.getSession(create);
	}

	@SuppressWarnings("deprecation")
	public String getRealPath(String path) {
		return request.getRealPath(path);
	}

	public int getRemotePort() {
		return request.getRemotePort();
	}

	public String getLocalName() {
		return request.getLocalName();
	}

	public HttpSession getSession() {
		return request.getSession();
	}

	public String getLocalAddr() {
		return request.getLocalAddr();
	}

	public boolean isRequestedSessionIdValid() {
		return request.isRequestedSessionIdValid();
	}

	public int getLocalPort() {
		return request.getLocalPort();
	}

	public boolean isRequestedSessionIdFromCookie() {
		return request.isRequestedSessionIdFromCookie();
	}

	public boolean isRequestedSessionIdFromURL() {
		return request.isRequestedSessionIdFromURL();
	}

	@SuppressWarnings("deprecation")
	public boolean isRequestedSessionIdFromUrl() {
		return request.isRequestedSessionIdFromUrl();
	}
}
