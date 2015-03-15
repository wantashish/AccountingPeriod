package uk.co.agilelogics;

import org.apache.commons.codec.binary.Hex;
import org.apache.commons.io.IOUtils;
import sun.jvm.hotspot.runtime.Bytes;

import java.io.*;
import java.net.Socket;
import java.nio.ByteBuffer;

/**
 * Created with IntelliJ IDEA.
 * User: ashishsri
 * Date: 15/03/15
 * Time: 12:54
 * To change this template use File | Settings | File Templates.
 */
public class TestSocket {

    public static void main(String[] args) throws IOException, InterruptedException {

        Socket socket = new Socket("isuraksha.co.in", 5023);
        OutputStream out = socket.getOutputStream();





//        String hex= "787811010862304021268464212000010001664a0d0a";
        String hex= "787811010862304021268454212000010001664a0d0a";

        String position = "787822220f030f0f1c33c60587b510000c29f4001cad00ea0a33af00554d0000000090dab60d0a";

       //Login packet
        out.write(hexStringToByteArray(hex));



       InputStream in = socket.getInputStream();



        for(int i=0;i<=10;i++) {
            if(in.available() > 5){
                byte[] bytes = new byte[in.available()];

                int count = in.read(bytes);

                System.out.println(Hex.encodeHexString(bytes));
                break;
            } else {
                System.out.println("Waiting .5 second");
                Thread.sleep(500);
            }

        }


        //position

        out.write(hexStringToByteArray(position));


        for(int i=0;i<=10;i++) {
            if(in.available() > 5){
                byte[] bytes = new byte[in.available()];

                int count = in.read(bytes);

                System.out.println(Hex.encodeHexString(bytes));
                break;
            } else {
                System.out.println("Waiting .5 second");
                Thread.sleep(500);
            }

        }


        out.flush();
        out.close();
        in.close();
        socket.close();

    }

    public static byte[] hexStringToByteArray(String s) {
        byte[] b = new byte[s.length() / 2];
        for (int i = 0; i < b.length; i++) {
            int index = i * 2;
            int v = Integer.parseInt(s.substring(index, index + 2), 16);
            b[i] = (byte) v;
        }
        return b;
    }

}
