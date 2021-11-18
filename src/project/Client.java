/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package project;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.lang.System.Logger;
import java.lang.System.Logger.Level;
import java.net.Socket;
import java.util.Scanner;

/**
 *
 * @author minht
 */
public class Client {
    public static void main(String[] args) {
        DataInputStream din = null;
        DataOutputStream dout = null;
        try {
            // TODO add your handling code here:
            Socket client = new Socket("127.0.0.1", 8888);
             din = new DataInputStream(client.getInputStream());
             dout = new DataOutputStream(client.getOutputStream());
        } catch (IOException ex) {
           
        }
        Scanner sc = new Scanner(System.in);
        System.out.println("Nhap key:");
        String key=sc.nextLine();
        System.out.println("Nhap message:");
     
        String message=sc.nextLine();
          Encode encode =new Encode(key,message);
          String messageEncode=encode.getResultEncode();
        try {
            dout.writeUTF(key);
            dout.writeUTF(messageEncode);
            String ketQua = din.readUTF();
            System.out.println("Ket qua: "+ketQua);
            
        } catch (IOException ex) {
           
        }
        
    }
      
    
}
