package com.capgemini.rest.filter;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.lang.reflect.Field;
import java.sql.Timestamp;
import java.util.Arrays;
import java.util.Enumeration;
import java.util.Locale;
import java.util.concurrent.atomic.AtomicInteger;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

import org.apache.catalina.connector.CoyoteInputStream;
import org.apache.catalina.connector.InputBuffer;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.tomcat.util.buf.ByteChunk;

public class RequestDumperFilter implements Filter {

	public RequestDumperFilter() {
		filterConfig = null;
	}

	public void destroy() {
		filterConfig = null;
	}

	private static final AtomicInteger counter = new AtomicInteger(0);

	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
		if (filterConfig == null)
			return;

		FileOutputStream out = new FileOutputStream(new File(
				counter.incrementAndGet() + ".txt"));

		StringWriter sw = new StringWriter();
		PrintWriter writer = writeCommonHeaders(request, sw);
		writeParameters(request, writer);
		writeServerInformation(request, writer);
		writeHttpInformation(request, writer);
		writer.println("=============================================");
		writer.flush();
		// filterConfig.getServletContext().log(sw.getBuffer().toString());
		out.write(sw.getBuffer().toString().getBytes());
		writeRequestBody(request, out);
		out.close();
		chain.doFilter(request, response);
	}

	private void writeRequestBody(ServletRequest request, FileOutputStream out)
			throws IOException {
		try {
			out.write("********REQUEST BODY*********\n".getBytes());
			if (request.getContentLength() > 0) {
			CoyoteInputStream stream = (CoyoteInputStream) request
					.getInputStream();

			Field bufferField = stream.getClass().getDeclaredField("ib");
			bufferField.setAccessible(true);
			InputBuffer input = (InputBuffer) bufferField.get(stream);
			Field byteChunkfield = input.getClass().getDeclaredField("bb");
			byteChunkfield.setAccessible(true);
			ByteChunk chunk = (ByteChunk) byteChunkfield.get(input);

			byte[] readCachedBytes = new byte[256];
			int read = -1;
			byte[] wholeBodyContent = new byte[0];

			while ((read = stream.read(readCachedBytes)) != -1) {
				wholeBodyContent = ArrayUtils.addAll(wholeBodyContent,
						Arrays.copyOf(readCachedBytes, read));
			}

			out.write(("body length :" + request.getContentLength() + "\n").getBytes());
			out.write(wholeBodyContent);
			chunk.setBytes(wholeBodyContent, 0, wholeBodyContent.length);
			} else {
				out.write("--EMPTY--".getBytes());
			}
			out.write("\n*****************\n".getBytes());
		} catch (NoSuchFieldException exc) {
			exc.printStackTrace();
		} catch (IllegalAccessException exc) {
			exc.printStackTrace();
		}
	}

	private void writeHttpInformation(ServletRequest request, PrintWriter writer) {
		if (request instanceof HttpServletRequest) {
			writer.println("---------------------------------------------");
			HttpServletRequest hrequest = (HttpServletRequest) request;
			writer.println((new StringBuilder()).append("       contextPath=")
					.append(hrequest.getContextPath()).toString());
			writeCookies(writer, hrequest);
			writeHeaders(writer, hrequest);

			writeAdditionalInformation(writer, hrequest);
		}
	}

	private void writeAdditionalInformation(PrintWriter writer,
			HttpServletRequest hrequest) {
		writer.println((new StringBuilder()).append("            method=")
				.append(hrequest.getMethod()).toString());
		writer.println((new StringBuilder()).append("          pathInfo=")
				.append(hrequest.getPathInfo()).toString());
		writer.println((new StringBuilder()).append("       queryString=")
				.append(hrequest.getQueryString()).toString());
		writer.println((new StringBuilder()).append("        remoteUser=")
				.append(hrequest.getRemoteUser()).toString());
		writer.println((new StringBuilder()).append("requestedSessionId=")
				.append(hrequest.getRequestedSessionId()).toString());
		writer.println((new StringBuilder()).append("        requestURI=")
				.append(hrequest.getRequestURI()).toString());
		writer.println((new StringBuilder()).append("       servletPath=")
				.append(hrequest.getServletPath()).toString());
	}

	private void writeHeaders(PrintWriter writer, HttpServletRequest hrequest) {
		String name;
		String value;
		for (Enumeration names = hrequest.getHeaderNames(); names
				.hasMoreElements(); writer.println((new StringBuilder())
				.append("            header=").append(name).append("=")
				.append(value).toString())) {
			name = (String) names.nextElement();
			value = hrequest.getHeader(name);
		}
	}

	private void writeCookies(PrintWriter writer, HttpServletRequest hrequest) {
		Cookie cookies[] = hrequest.getCookies();
		if (cookies == null)
			cookies = new Cookie[0];
		for (int i = 0; i < cookies.length; i++) {
			writer.println((new StringBuilder()).append("            cookie=")
					.append(cookies[i].getName()).append("=")
					.append(cookies[i].getValue()).toString());
		}
	}

	private void writeServerInformation(ServletRequest request,
			PrintWriter writer) {
		writer.println((new StringBuilder()).append("          protocol=")
				.append(request.getProtocol()).toString());
		writer.println((new StringBuilder()).append("        remoteAddr=")
				.append(request.getRemoteAddr()).toString());
		writer.println((new StringBuilder()).append("        remoteHost=")
				.append(request.getRemoteHost()).toString());
		writer.println((new StringBuilder()).append("            scheme=")
				.append(request.getScheme()).toString());
		writer.println((new StringBuilder()).append("        serverName=")
				.append(request.getServerName()).toString());
		writer.println((new StringBuilder()).append("        serverPort=")
				.append(request.getServerPort()).toString());
		writer.println((new StringBuilder()).append("          isSecure=")
				.append(request.isSecure()).toString());
	}

	private void writeParameters(ServletRequest request, PrintWriter writer) {
		for (Enumeration names = request.getParameterNames(); names
				.hasMoreElements(); writer.println()) {
			String name = (String) names.nextElement();
			writer.print((new StringBuilder()).append("         parameter=")
					.append(name).append("=").toString());
			String values[] = request.getParameterValues(name);
			for (int i = 0; i < values.length; i++) {
				if (i > 0)
					writer.print(", ");
				writer.print(values[i]);
			}

		}
	}

	private PrintWriter writeCommonHeaders(ServletRequest request,
			StringWriter sw) {
		PrintWriter writer = new PrintWriter(sw);
		writer.println((new StringBuilder()).append("Request Received at ")
				.append(new Timestamp(System.currentTimeMillis())).toString());
		writer.println((new StringBuilder()).append(" characterEncoding=")
				.append(request.getCharacterEncoding()).toString());
		writer.println((new StringBuilder()).append("     contentLength=")
				.append(request.getContentLength()).toString());
		writer.println((new StringBuilder()).append("       contentType=")
				.append(request.getContentType()).toString());
		writer.println((new StringBuilder()).append("            locale=")
				.append(request.getLocale()).toString());
		writer.print("           locales=");
		Enumeration locales = request.getLocales();
		boolean first = true;
		Locale locale;
		for (; locales.hasMoreElements(); writer.print(locale.toString())) {
			locale = (Locale) locales.nextElement();
			if (first)
				first = false;
			else
				writer.print(", ");
		}

		writer.println();
		return writer;
	}

	public void init(FilterConfig filterConfig) throws ServletException {
		this.filterConfig = filterConfig;
	}

	public String toString() {
		if (filterConfig == null) {
			return "RequestDumperFilter()";
		} else {
			StringBuffer sb = new StringBuffer("RequestDumperFilter(");
			sb.append(filterConfig);
			sb.append(")");
			return sb.toString();
		}
	}

	private FilterConfig filterConfig;
}
