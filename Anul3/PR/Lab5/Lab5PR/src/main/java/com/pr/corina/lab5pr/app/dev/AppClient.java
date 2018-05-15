/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pr.corina.lab5pr.app.dev;

import com.pr.corina.lab5pr.utils.ChatConstants;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;

/**
 *
 * @author corina
 */
public class AppClient {
    public static void main(String[] args) {
        Socket kkSocket = null;
        PrintWriter pw = null;
        BufferedReader br = null;

        try {
            kkSocket = new Socket("127.0.0.1", AppServer.PORT_NR);
            pw = new PrintWriter(kkSocket.getOutputStream());
            br = new BufferedReader(new InputStreamReader(kkSocket.getInputStream()));
        } catch (UnknownHostException e) {
            System.err.println("Don't know about host: taranis");
        } catch (IOException e) {
            System.err.println("Couldn't get I/O for the connection to: taranis");
        }

        if (kkSocket != null && pw != null && br != null) {
            try {
                StringBuffer buf = new StringBuffer(50);
                int c;
                String fromServer;

                while ((fromServer = br.readLine()) != null) {
                    String[]msgFromServerArr=fromServer.split(ChatConstants.PROTOCOL_NEW_LINE);
                    System.out.print("[Server]:");
                    for(String msgFromServer:msgFromServerArr){
                        System.out.println(msgFromServer);
                    }
                     System.out.print(ChatConstants.SYMB_ARROWS);
                    if (fromServer.equals("Bye."))
                        break;
                    while ((c = System.in.read()) != '\n') {
                        buf.append((char)c);
                    }

                    pw.println(buf.toString());
                    pw.flush();
                    buf.setLength(0);
                }

                pw.close();
                br.close();
                kkSocket.close();
            } catch (UnknownHostException e) {
                System.err.println("Trying to connect to unknown host: " + e);
            } catch (IOException e) {
                System.err.println("IOException:  " + e);
            }
        }
    }
}