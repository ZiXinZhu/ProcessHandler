package com.zzx.transactions.nio;


import org.junit.Test;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.time.LocalDateTime;
import java.util.Iterator;

public class NonBlockingIOTest {

    @Test
    public void client() throws IOException {
        //1.获取通道
        SocketChannel sChannel = SocketChannel.open(new InetSocketAddress("127.0.0.1", 9898));

        //切换非阻塞模式
        sChannel.configureBlocking(false);

        //分配指定大小的缓冲区
        ByteBuffer buf = ByteBuffer.allocate(1024);

        //发送数据给服务端
        buf.put(LocalDateTime.now().toString().getBytes());
        buf.flip();
        sChannel.write(buf);
        buf.clear();

        sChannel.close();
    }


    @Test
    public void server() throws IOException{
        //1.获取通道
        ServerSocketChannel ssChannel = ServerSocketChannel.open();
        //2.切换非阻塞模式
        ssChannel.configureBlocking(false);
        //3.绑定连接
        ssChannel.bind(new InetSocketAddress(9898));
        //4.获取选择器
        Selector selector = Selector.open();

        //5.将通道注册到选择器上，并指定监听事件
        ssChannel.register(selector, SelectionKey.OP_ACCEPT);//选择键，监控是接收状态,可以监听多个状态，用位或|运算符连接

        //6.轮询获取选择器上已经准备就绪的事件，
        while (true) {
            //这是一个阻塞方法，一直等待直到有数据可读，返回值是key的数量（可以有多个
            selector.select();

            //7.获取当前选择器中所有注册的选择键
            Iterator<SelectionKey> iterator = selector.selectedKeys().iterator();

            while (iterator.hasNext()) {
                //8.获取准备就绪的事件
                SelectionKey key = iterator.next();

                //取消选择键
                iterator.remove();

                //9.判断具体是什么事件
                if (key.isAcceptable()) {
                    //10.接收就绪，获取客户端连接
                    SocketChannel sChannel = ssChannel.accept();
                    //切换非阻塞模式
                    sChannel.configureBlocking(false);
                    //将该通道注册到选择器上
                    sChannel.register(selector, SelectionKey.OP_READ);
                }else if (key.isReadable()) {
                    //获取当前选择器上读就绪状态的通道
                    SocketChannel sChannel = (SocketChannel) key.channel();
                    //读取数据
                    ByteBuffer buf = ByteBuffer.allocate(1024);

                    int len = 0;
                    while ((len = sChannel.read(buf)) != -1) {
                        buf.flip();
                        System.out.println(new String(buf.array(),0,len));
                        buf.clear();
                    }
                }
            }

        }

    }
}