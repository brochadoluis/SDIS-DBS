
import communication.Address;
import communication.CommunicatonChannel;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.MulticastSocket;
import java.net.UnknownHostException;

/**
 * Created by Luis on 27/03/2016.
 */
public class Sender {
    public static void main(String[] args) {
        //apenas para testar o envio de uma mensasgem!
        //daqui
        Address addr = new Address("224.5.5.5", 1919);
        CommunicatonChannel bc = new CommunicatonChannel(addr.getIp(),addr.getPort());
        System.out.println("Channel: " +  addr.getIp() + " : " + addr.getPort());
        System.out.println("Sender address: " + bc.getAddress());

        String send = "1923 BACKUP test1.pdf 3";
        byte[] buf = send.getBytes();
        System.out.println("MSG = " + buf);
        bc.send(buf);
        System.out.println("Sent!");
        //até aqui

    }
}