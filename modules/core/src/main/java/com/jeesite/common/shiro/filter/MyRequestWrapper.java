package com.jeesite.common.shiro.filter;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.StringWriter;
import java.io.Writer;

import javax.servlet.ReadListener;
import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;


//xf 2019.11.29
//包装shiro请求，在控制层可重复获取request值
public class MyRequestWrapper extends HttpServletRequestWrapper {
	 
    private final String body;

    private static final int BUFFER_SIZE = 1024 * 8;

 
 
    public MyRequestWrapper(HttpServletRequest request) throws IOException {
        super(request);
        this.body = MyRequestWrapper.read(request);
    }
 
    public String getBody() {
        return body;
    }
 
 
 
    @Override
    public ServletInputStream getInputStream()  {
        final ByteArrayInputStream bais = new ByteArrayInputStream(body.getBytes());
        return new ServletInputStream() {
 
            @Override
            public boolean isFinished() {
                return false;
            }
 
            @Override
            public boolean isReady() {
                return false;
            }
 
            @Override
            public void setReadListener(ReadListener readListener) {
 
            }
 
            @Override
            public int read(){
                return bais.read();
            }
        };
    }
 
    @Override
    public BufferedReader getReader(){
        return new BufferedReader(new InputStreamReader(this.getInputStream()));
    }
 
    public static String read(HttpServletRequest request) throws IOException {
        BufferedReader bufferedReader = request.getReader();
        StringWriter writer = new StringWriter();
        write(bufferedReader,writer);
        return writer.getBuffer().toString();
    }

    public static long write(Reader reader,Writer writer) throws IOException {
        return write(reader, writer, BUFFER_SIZE);
    }
 
    public static long write(Reader reader, Writer writer, int bufferSize) throws IOException
    {
        int read;
        long total = 0;
        char[] buf = new char[bufferSize];
        while( ( read = reader.read(buf) ) != -1 ) {
            writer.write(buf, 0, read);
            total += read;
        }
        return total;
    }
}