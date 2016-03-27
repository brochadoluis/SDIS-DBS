package communication;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.MulticastSocket;
import java.net.UnknownHostException;

/**
 * Created by Luis on 07/03/2016.
 */
public class CommunicatonChannel {
    private final Integer port;
    private MulticastSocket socket;
    private InetAddress address;

    public CommunicatonChannel(String newIp, Integer port) {
        this.port = port;

        try {
            socket = new MulticastSocket(port);
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            socket.setTimeToLive(2);
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            address = InetAddress.getByName(newIp);
        } catch (UnknownHostException e1) {
            e1.printStackTrace();
        }

        try {
            socket.joinGroup(address);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public byte[] receive() {
        //64*1024 = 65536
        int packetSize = 65536;
        byte[] buf = new byte[packetSize];
        DatagramPacket receivedPacket = new DatagramPacket(buf, packetSize);

        try {
            socket.receive(receivedPacket);
        } catch (IOException e) {
            return null;
        }


      /*  try {
            if (receivedPacket.getAddress().toString().equals("/" + InetAddress.getLocalHost().getHostAddress())) {
                return null;
            }
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }*/

        byte[] trimmedMessage = new byte[receivedPacket.getLength()];
        System.arraycopy(receivedPacket.getData(), receivedPacket.getOffset(), trimmedMessage, 0, receivedPacket.getLength());

        return trimmedMessage;
    }

    public void send(byte[] message) {
        DatagramPacket packet;
        packet = new DatagramPacket(message, message.length, address, port);
        try {
            socket.send(packet);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void close() {
        socket.close();
    }

    public Integer getPort() {
        return port;
    }

    public MulticastSocket getSocket() {
        return socket;
    }

    public void setSocket(MulticastSocket socket) {
        this.socket = socket;
    }

    public InetAddress getAddress() {
        return address;
    }

    public void setAddress(InetAddress address) {
        this.address = address;
    }
}
