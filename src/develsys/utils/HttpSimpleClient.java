/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package develsys.utils;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.SocketTimeoutException;
import java.net.UnknownHostException;

/**
 *
 * @author hmariod es
 */
public class HttpSimpleClient {
    private String host;
    private int port;
    private int connectionTimeout;
    public int resultCode;
    public String errorMessage;
    public String rawAnswer;
    public String[] headers;
    public String body;
    
    /*
     * @host
     */
    public HttpSimpleClient(String host){
        this(host, 80, 3000);
    }
    
    public HttpSimpleClient(String host, int port, int connectionTimeout){
        this.host = host;
        this.port = port;
        this.connectionTimeout = connectionTimeout;
        headers = new String[50];
    }
    
    /**
     *
     * @param url
     * String pointing to the page(/folder/page.asp?someParam=someValue)
     * @return
     * True on success, False on exception catch.
     */
    public boolean get(String url){
        try {
            Socket socket = new Socket();
            boolean b;
            socket.connect(new InetSocketAddress(host, port), connectionTimeout);

            BufferedWriter out = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream(), "UTF8"));
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream(),"ISO-8859-1"));
            //BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream(),"UTF8"));
            
            String uri = "GET " + url + "\r\n HTTP/1.1\r\n";
            out.write(uri);
            out.flush();
            
            rawAnswer = "";
            body = "";
            String line;
            boolean separatorFounded = false;
            int headerCounter = 0;
            while ((line = in.readLine()) != null) {
                rawAnswer += line + "\n";
                if(line.length() < 1){
                    separatorFounded = true;
                    headers[headerCounter] = "";
                    continue;
                }
                if(!separatorFounded){
                    headers[headerCounter] = line + "\n";
                    headerCounter++;
                }else{
                    body += line + "\n";
                }
            }
            out.close(); 
            in.close();
            socket.close();
            if(body.length() >= 1){body = body.substring(0, body.length()-1);}; //Exclude last \n
            resultCode = Integer.parseInt((headers[0].split("\\s")[1]),10);
            
        } catch (UnknownHostException | SocketTimeoutException ex) {
            errorMessage = ex.getMessage();
            return false;
        } catch (IOException ex) {
            errorMessage = ex.getMessage();
            return false;
        }
        return true;
    }
    

    

}
