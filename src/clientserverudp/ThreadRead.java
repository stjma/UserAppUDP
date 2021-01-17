/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clientserverudp;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JTextArea;

/**
 *
 * @author info1
 */
public class ThreadRead implements Runnable {

    private final DatagramSocket datagramSocket;
    private JTextArea jTextArea;

    private ArrayList<User> ArrayListUser = new ArrayList<>();

    public ThreadRead(DatagramSocket datagramSocket, JTextArea jTextArea) {
        this.datagramSocket = datagramSocket;
        this.jTextArea = jTextArea;
    }

    @Override
    public void run() {

        while (true) {
            byte[] buf = new byte[1024];
            DatagramPacket datagramPacket = new DatagramPacket(buf, 1024);
            try {
                datagramSocket.receive(datagramPacket);
                String message = new String(datagramPacket.getData());

                jTextArea.append(message + "\n");

            } catch (IOException ex) {
                Logger.getLogger(ThreadRead.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

}

//                String[] parts = message.split("!9/1\\/2\\/3\\9!");
//
//                for (int cpt = 0; cpt < parts.length; cpt++) {
//                    System.out.println(parts[cpt]);
//                }
//
//                String messageReceive = "";
//                if (parts[0].equals("addUser")) {
//
//                    for (User user : ArrayListUser) {
//                        if (parts[1] == user.getNickName()) {
//                            messageReceive = "NickName Existe deja Veillez change de nickname";
//                            break;
//                        }
//                    }
//                    User user = new User(parts[1], parts[2]);
//                    ArrayListUser.add(user);
//                    messageReceive = "User ajouter";
//                } else if (parts[0].equals("delUser")) {
//                    int validation = 0;
//                    for (User user : ArrayListUser) {
//                        if (parts[1] == user.getNickName()) {
//                            ArrayListUser.remove(user);
//                            messageReceive = "NickName supprimer";
//                            validation++;
//                        }
//                    }
//                    if (validation == 0){
//                        messageReceive = "nickName existe pas suppresion annuler";
//                    }
//                }
//                else{
//                    System.out.println(message);
//                     messageReceive = "Erreur aucun demande (ajour/suppresion)";
//                }
//
//                //send message
//                byte[] bufSend = new byte[1024];
//                DatagramPacket dataGramPacketSend = new DatagramPacket(bufSend, 1024);
//
//                dataGramPacketSend.setData(messageReceive.getBytes());
//                //client ip
//                String ip = datagramPacket.getAddress().toString();
//                dataGramPacketSend.setAddress(InetAddress.getByName(ip.substring(1)));
//
//                dataGramPacketSend.setPort(datagramPacket.getPort());
//                datagramSocket.send(dataGramPacketSend);
