package SchedulerTask;

import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;

public class TCP {

    public String host;
    public int port; 

    public TCP(String _host, int _port){
        this.host = _host;
        this.port = _port;
    }

    public String SendToServer(String msg) {    

        try {                                    
            var socket = new Socket(this.host, this.port);

            PrintWriter outToServer = new PrintWriter(new OutputStreamWriter(socket.getOutputStream()));    

            outToServer.write(msg);
            outToServer.flush();
            System.out.print("msg sent to server");

            socket.close();            

            return msg;            
        }
        catch(Exception ex){
            return ex.getMessage();
        }        
    } 
}

