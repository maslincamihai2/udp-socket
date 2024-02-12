import java.io.*;
import java.net.*;

public class Main {
    public static void main(String[] args) {
        String hostname = "djxmmx.net";
        int port = 17;

        try {
            //obtinem adresa ip a hostului
            InetAddress address = InetAddress.getByName(hostname);
            //deschidem un canal pentru comunicarea cu hostul
            DatagramSocket socket = new DatagramSocket();
            DatagramPacket request = new DatagramPacket(new byte[1], 1, address, port);

            //128 bytes
            //trimitem un request
            socket.send(request);
            byte[] buffer = new byte[128];
            //setam dimensiunea raspunsului pe care trebuie sa il primim
            DatagramPacket response = new DatagramPacket(buffer, buffer.length);
            long start = System.currentTimeMillis();
            //asteptam raspunsul
            socket.receive(response);
            long finish = System.currentTimeMillis();
            //convertim bufferul de octeti primit in sir de caractere
            String quote = new String(buffer, 0, response.getLength() - 1);
            //afisam in consola raspunsul primit si rimpul de raspuns
            System.out.println(quote);
            System.out.println("Timp de raspuns 128:" + (finish - start));
            System.out.println();

            //repetam pentru pachete de 512 bytes si 1024
            //512 bytes
            socket.send(request);
            buffer = new byte[512];
            response = new DatagramPacket(buffer, buffer.length);
            start = System.currentTimeMillis();
            socket.receive(response);
            finish = System.currentTimeMillis();
            quote = new String(buffer, 0, response.getLength() - 1);
            System.out.println(quote);
            System.out.println("Timp de raspuns 512:" + (finish - start));
            System.out.println();

            //1024 bytes
            socket.send(request);
            buffer = new byte[1024];
            response = new DatagramPacket(buffer, buffer.length);
            start = System.currentTimeMillis();
            socket.receive(response);
            finish = System.currentTimeMillis();
            quote = new String(buffer, 0, response.getLength() - 1);
            System.out.println(quote);
            System.out.println("Timp de raspuns 1024:" + (finish - start));
            System.out.println();

        } catch (SocketTimeoutException ex) {
            System.out.println("Timeout error: " + ex.getMessage());
            ex.printStackTrace();
        } catch (IOException ex) {
            System.out.println("Client error: " + ex.getMessage());
            ex.printStackTrace();
        }
    }
}