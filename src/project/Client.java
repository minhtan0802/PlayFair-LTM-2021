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
    private String key;
    private String message;
    private String messageEncode;
    private String result;
    private String keyTable;
    private String vitri;
    private String keyword;
    DataInputStream din = null;
    DataOutputStream dout = null;

    public String getVitri() {
        return vitri;
    }

    public String getKeyword() {
        return keyword;
    }

    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }

    public void setVitri(String vitri) {
        this.vitri = vitri;
    }

    public String getKeyTable() {
        return keyTable;
    }

    public void setKeyTable(String keyTable) {
        this.keyTable = keyTable;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    
    public String getMessageEncode() {
        return messageEncode;
    }

    public void setMessageEncode(String messageEncode) {
        this.messageEncode = messageEncode;
    }

    public DataInputStream getDin() {
        return din;
    }

    public void setDin(DataInputStream din) {
        this.din = din;
    }

    public DataOutputStream getDout() {
        return dout;
    }

    public void setDout(DataOutputStream dout) {
        this.dout = dout;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
    public void encode()
    {
          Encode encode =new Encode(key,message);
          messageEncode=encode.getResultEncode();
            keyTable=encode.getKeyTable();
    }
    public void gui() throws IOException
    {
        System.out.println("Key:"+ key);
        System.out.println("MessageEncode: "+messageEncode);
        System.out.println("Keyword: "+keyword);
        dout.writeUTF(key);
        dout.writeUTF(messageEncode);
        dout.writeUTF(keyword);
        
       result = din.readUTF();
        vitri=din.readUTF();
        
    }
    public void init()
    {
        
        try {
            // TODO add your handling code here:
            Socket client = new Socket("127.0.0.1", 8888);
             din = new DataInputStream(client.getInputStream());
             dout = new DataOutputStream(client.getOutputStream());
             
        } catch (IOException ex) {
           
        }
    }
   public Client() {
       
        init();
        
    }
      
    
}
