package communication;

import chunk.Chunk;
import communication.CommunicatonChannel;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Inet4Address;
import java.util.ArrayList;

/**
 * Created by Luis on 27/03/2016.
 */
public class Handler extends Thread {
    private ArrayList<String> arguments;
    private static String CRLF = "\r\n";
    private static String version = "1.0";


  /*  public Handler(String addr, Integer port, Integer id){
        this.cc = new CommunicatonChannel();
    }
*/
    public void run(){
        /**
         * BELONGS TO PEER.
         * PEER MUST "DECODE" THE MESSAGE SO IT KNOWS WHICH CHANNEL TO CHOOSE
         */

    }

    private void backupMessage(Chunk ch) {
      //  byte[] backupMsg = ("PUTCHUNK " + version + " " + peerID.toString() + " " + ch.getFileId().toString() + " " + ch.getChunkNo().toString() + " " + ch.getRepDegree().toString() + CRLF + CRLF).getBytes();
      //  System.out.println(backupMsg);
    }
}
