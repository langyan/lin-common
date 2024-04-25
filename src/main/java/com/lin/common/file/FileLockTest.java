package com.lin.common.file;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.channels.FileChannel;
import java.nio.channels.FileLock;
import java.util.Scanner;

public class FileLockTest {

    public static void main(String args[]) {
        FileLockTest test = new FileLockTest();
        new Thread(() -> {
            test.a("线程1");
        }).run();
        //
        new Thread(() -> {
            test.a("线程2");
        }).run();

    }

    public void a(String name) {
        Scanner scanner = new Scanner(System.in);
        try {
            File file = new File("D:\\FileLockTest.txt");
            RandomAccessFile input = new RandomAccessFile(file, "rw");
            // RandomAccessFile对象还需要指定一个mode参数。该参数指定RandomAccessFile的访问模式，有以下4个值：
            // “r” 以只读方式来打开指定文件夹。如果试图对该RandomAccessFile执行写入方法，都将抛出IOException异常。
            // “rw” 以读，写方式打开指定文件。如果该文件尚不存在，则试图创建该文件。
            // “rws” 以读，写方式打开指定文件。相对于”rw” 模式，还要求对文件内容或元数据的每个更新都同步写入到底层设备。
            // “rwd” 以读，写方式打开指定文件。相对于”rw” 模式，还要求对文件内容每个更新都同步写入到底层设备。

            // 文件锁在OS中很常见，如果多个程序同时访问、修改同一个文件，很容易因为文件数据不同步而出现问题。
            // 给文件加一个锁，同一时间，只能有一个程序修改此文件，或者程序都只能读此文件，这就解决了同步问题，
            // 保证了线程安全。
            System.out.println(name + "输入读取的行数");

            // 通过一个InputStream、OutputStream或者RandomAccessFile来获得一个FileChannel
            FileChannel channnel = input.getChannel();
            FileLock lock = channnel.tryLock();

            while (scanner.hasNextInt()) {
                int m = scanner.nextInt();

                if (m == 0) {
                    break;
                }
                lock.release();
                for (int i = 1; i <= m; i++) {
                    String str = input.readLine();
                    System.out.println(str);
                }
                lock = channnel.tryLock();
                System.out.println(name + "输入读取的行数");
            }
        } catch (IOException e) {
            e.printStackTrace();// System.out.println(e);

        }
    }

}
