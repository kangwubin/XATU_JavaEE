package xatu191224.tcp.threadpool;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Server {
    private static class Worker implements Runnable {
        private final Socket socket;

        Worker(Socket socket) {
            this.socket = socket;
        }

        @Override
        public void run() {
            String name = Thread.currentThread().getName();
            try {
                //写
                InputStream is = socket.getInputStream();
                Scanner scanner = new Scanner(is, "UTF-8");
                OutputStream os = socket.getOutputStream();
                PrintStream out = new PrintStream(os, false, "UTF-8");

                //读
                while (scanner.hasNextLine()) {
                    System.out.println(name + ":等待客户端发送消息");
                    String message = scanner.nextLine();
                    System.out.println(name + ":收到消息:" + message);
                    String echoMessage = message;
                    out.println(echoMessage);
                    out.flush();
                }
            } catch (UnsupportedEncodingException e) {
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                try {
                    socket.close();
                } catch (IOException ex) {
                }
            }
        }
    }

    public static void main(String[] args) throws IOException {
        //监听
        ServerSocket serverSocket = new ServerSocket(8888);
        //创建线程池
        ExecutorService pool = Executors.newFixedThreadPool(100);
        //int i = 0;
        while (true) {
            System.out.println("main:等待连接");
            // 通信
            Socket socket = serverSocket.accept();
            System.out.println("main:连接建立");
            pool.execute(new Worker(socket));
            /*Thread thread = new Thread(new Worker(socket), "工作人员(" + i++ + ")");
            thread.start();*/
        }
    }
}
