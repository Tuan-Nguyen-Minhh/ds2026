import java.net.*;
import java.io.*;
import java.lang.*;
public class client {
    private Socket socket;
    private DataInputStream input;
    private DataOutputStream output;
    public client(String addr,int port){
        try{
            socket = new Socket(addr,port);
            System.out.println("CONNECTED");
            input = new DataInputStream(System.in);
            output = new DataOutputStream(socket.getOutputStream());
        }catch (SocketException s){
            System.out.println(s);
            return;
        }catch (UnknownHostException u){
            System.out.printf("u");
            return;

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        String message = "";
        while(!message.equals("Over")){
            try{
                message = input.readLine();
                output.writeUTF(message);
            }catch (IOException i){
                System.out.printf("i");
            }
        }
        try{
            input.close();
            output.close();
            socket.close();
        }catch (IOException i){
            System.out.println(i);
        }
    }
    public static void main(String[] args) {
        client client = new client("127.0.0.1",5000);
    }
}
