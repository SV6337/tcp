import java.net.*;
import java.io.*;

public class ContentsClient {
    public static void main(String args[]) throws Exception {
        // Connect to server at localhost (127.0.0.1) on port 4000
        Socket sock = new Socket("127.0.0.1", 4000);

        // Read file name from keyboard
        System.out.print("Enter the file name: ");
        BufferedReader keyRead = new BufferedReader(new InputStreamReader(System.in));
        String fname = keyRead.readLine();

        // Send file name to server
        OutputStream ostream = sock.getOutputStream();
        PrintWriter pwrite = new PrintWriter(ostream, true);
        pwrite.println(fname);

        // Receive file contents from server
        InputStream istream = sock.getInputStream();
        BufferedReader socketRead = new BufferedReader(new InputStreamReader(istream));
        String str;
        while ((str = socketRead.readLine()) != null) {
            System.out.println(str);   // print each line received
        }

        // Close resources
        pwrite.close();
        socketRead.close();
        keyRead.close();
        sock.close();
    }
}
