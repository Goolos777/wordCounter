package gl.ed.od.ua.controller;


import org.junit.Test;

public class TcpClientTest  {

    @Test
    public void simpleRequest(){
        TcpClient tcpClient = new TcpClient((val)-> System.out.println(val));
        tcpClient.send("https://habr.com/ru/flows/develop/page1/");
    }

}