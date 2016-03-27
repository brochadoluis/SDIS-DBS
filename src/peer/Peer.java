package peer;

import chunk.Chunk;
import communication.CommunicatonChannel;
import communication.Handler;
import communication.Address;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

/**
 * Created by Luis on 29/02/2016.
 */

public class Peer extends Thread {

    private static Integer peerID;
    private Address mc,mcb,mcr,ic;
    public static Handler handlerBackup;
    private static ArrayList<String> arguments;
    private static String CRLF = "\r\n";
    private static String version = "1.0";


    public static int getPeerID() {
        return peerID;
    }

    public static void main(String[] args){
        if(args.length < 9)
        System.out.println("Usage java Peer  <Peer Id> <Multicast Address> <Multicast Port> <Backup Address> <Backup Port> <Restore Address> <Restore Port>");

        establishNetwork(args);
    }

    private static void establishNetwork(String[] args) {
    peerID = Integer.parseInt(args[0]);
        System.out.println("Peer ID: " + peerID);
        Address amc = new Address(args[1], Integer.parseInt(args[2]));
        System.out.println("Multicast Channel: " + amc.getIp() + " : " + amc.getPort());
        Address amcb = new Address(args[3], Integer.parseInt(args[4]));
        System.out.println("Backup Channel: " +  amcb.getIp() + " : " + amcb.getPort());
        Address amcr = new Address(args[5], Integer.parseInt(args[6]));
        System.out.println("Restore Channel: " + amcr.getIp() + " : " + amcr.getPort());
        Address aic = new Address(args[7], Integer.parseInt(args[8]));
        System.out.println("Interface Channel: " + aic.getIp() + " : " + aic.getPort());
        /**
         * DECODE MSG AND CHOOSE CHANNEL
         */
        CommunicatonChannel mc,mcb,mcr,ic;
        mc = new CommunicatonChannel(amc.getIp(),amc.getPort());
        mcb = new CommunicatonChannel(amcb.getIp(),amcb.getPort());
        mcr = new CommunicatonChannel(amcr.getIp(),amcr.getPort());
        ic = new CommunicatonChannel(aic.getIp(),aic.getPort());


        byte[] msg = null;
        arguments = new ArrayList<>();
        String tmp;
        while(true){
            System.out.println("Listening in IP: " + ic.getAddress() + " and in Port: " + ic.getPort());
            System.out.println("Address =  "+ ic.getAddress());
            msg = ic.receive();
            System.out.println("msg = " + msg);
            if (msg != null){
                System.out.println("received something!!!");
                //Checks if is the right peer, if not just ignores the message
                tmp = "";
                BufferedReader in = new BufferedReader(new InputStreamReader(new ByteArrayInputStream(msg)));
                try {
                    tmp = in.readLine();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                String[] splited = tmp.split("\\s+");
                for (String aSplited : splited) {
                    arguments.add(aSplited.trim());
                }
                if (arguments.get(0).equals((peerID).toString())) {
                    System.out.println("That's up to me!");
                    if(arguments.get(1).equals("BACKUP")) {
                        System.out.println("BACKUP");
                    /*
                    new Backup(msg).start();
                    * Create PUTCHUNK message and send it to backup
                    */
                    }
                    else if(arguments.get(1).equals("RESTORE")){
                        System.out.println("RESTORE");
                        /*
                        Deal with it
                         */
                    }
                    else if(arguments.get(1).equals("DELETE")){
                        System.out.println("DELETE");
                        /*
                        It's the end of the world
                         */
                    }
                    else if(arguments.get(1).equals("RECLAIM")){
                        System.out.println("SPACE RECLAIM");
                        /*
                        MY PRECIOUS
                         */
                    }
                    else
                        System.out.println("Invalid Subprotocol");

                } else {
                    System.out.println("Don't even care");
                    /*
                    keeps listening
                     */
                }
            }
            else
                System.out.println("Error somewhere");
        }
        //handlerBackup = new Handler( mcb.getIp(), mcb.getPort());
        //handlerBackup.start();

    }
}
