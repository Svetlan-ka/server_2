package org.example;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class Main {
    public static void main(String[] args) throws IOException {
        System.out.println("Сервер запущен!");
        final int port = 8050;

        try (ServerSocket serverSocket = new ServerSocket(port)) {
            while (true) {
                try (Socket clientSocket = serverSocket.accept(); // ждем подключения
                     PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
                     BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
                ) {
                    System.out.printf("Принято новое соединение. Порт: %d%n", clientSocket.getPort());

                    out.println("Привет, это Сервер, как я могу к тебе обращаться?");
                    String answerName = in.readLine(); // ждем пока клиент что-нибудь нам напишет

                    out.println(String.format("Отлично, %s, давай продолжим наше знакомство! Подскажи, пожалуйста, сколько тебе лет?", answerName));
                    String answerAge = in.readLine();
                    int age = Integer.parseInt(answerAge); // ждём пока клиент что-нибудь нам напишет

                    if (age < 18) {
                        out.println(String.format("Добро пожаловать в детскую зону, %s", answerName));
                        System.out.printf("Детская зона: добавлен пользователь %s\n", answerName);
                    } else {
                        out.println(String.format("Добро пожаловать во взрослую зону, %s", answerName));
                        System.out.printf("Взрослая зона: добавлен пользователь %s\n", answerName);
                    }
                }
            }
        }
    }
}

