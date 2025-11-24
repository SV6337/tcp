import java.net.*;
import java.io.*;

public class ContentsServer {
    public static void main(String args[]) throws Exception {
        // Create server socket at port 4000
        ServerSocket sersock = new ServerSocket(4000);
        System.out.println("Server ready for connection...");

        // Accept client connection
        Socket sock = sersock.accept();
        System.out.println("Connection successful, waiting for client request...");

        // Read file name from client
        InputStream istream = sock.getInputStream();
        BufferedReader fileRead = new BufferedReader(new InputStreamReader(istream));
        String fname = fileRead.readLine();

        // Read file contents
        BufferedReader contentRead = new BufferedReader(new FileReader(fname));

        // Send file contents to client
        OutputStream ostream = sock.getOutputStream();
        PrintWriter pwrite = new PrintWriter(ostream, true);
        String str;
        while ((str = contentRead.readLine()) != null) {
            pwrite.println(str);   // send each line to client
        }

        // Close resources
        sock.close();
        sersock.close();
        pwrite.close();
        fileRead.close();
        contentRead.close();
    }
}
