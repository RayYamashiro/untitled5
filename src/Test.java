import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class Test {
    public static void main(String [] args) throws InterruptedException, IOException {
        //String path = "C:/Users/RayYamashiro/IdeaProjects/untitled5/bb.txt";
        String path = "";
        System.out.println("Write task number");
        Scanner scanner = new Scanner(System.in);
        String flag = scanner.nextLine();


        boolean existFlag = false;
        while (!existFlag)
        {
            System.out.println("Write the path. If you see this text twice or more then file doesn't exist");
            path = scanner.nextLine();
            existFlag = checkFileExistence(path);
        }
        switch (flag) {
            case "1":
                ByteAnalysis temp = new ByteAnalysis(path);
                temp.work();
                break;
            case "2":
                UnicodeAnalysis temp1 = new UnicodeAnalysis(path);
                temp1.work();
                break;
            case "3":
                ThirdTask temp2 = new ThirdTask();
                temp2.work();
        }
    }

    static boolean checkFileExistence(String path)
    {
        if(new File(path).exists())
            return true;
        else {
            System.out.println("File doesn't exists");
            return false;
        }
    }
}

//class Work
//{
//    Random random = new Random();
//    Object lock1 = new Object();
//    Object lock2 = new Object();
//    Object lock3 = new Object();
//
//
//    List<Integer> list1 = new ArrayList<>();
//    List<Integer> list2 = new ArrayList<>();
//    List<Integer> list3 = new ArrayList<>();
//   public  void  addToList1()
//   {
//        synchronized (lock1) {
//            try {
//                System.out.println("in block1 " + Thread.currentThread().getName());
//                Thread.sleep(1);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//            list1.add(random.nextInt(100));
//            System.out.println("end  block1 " + Thread.currentThread().getName());
//        }
//   }
//
//    public  void addToList2()
//    {
//        synchronized (lock2) {
//            try {
//                System.out.println("in block2 " + Thread.currentThread().getName());
//                Thread.sleep(1);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//            list2.add(random.nextInt(100));
//            System.out.println("end  block2" + Thread.currentThread().getName());
//        }
//    }
//    public  void  addToList3()
//    {
//    synchronized (lock3) {
//        try {
//            System.out.println("in block3 " + Thread.currentThread().getName());
//            Thread.sleep(1);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
//        list3.add(random.nextInt(100));
//        System.out.println("end  block3 " + Thread.currentThread().getName());
//    }
//    }
//    public void work()
//    {
//        for(int i =0 ; i< 1; i++)
//        {
//            addToList1();
//            addToList2();
//            addToList3();
//        }
//    }
//
//    public void main() throws InterruptedException {
//        long before = System.currentTimeMillis();
//        Thread thread1 = new Thread(new Runnable() {
//            @Override
//            public void run() {
//                work();
//            }
//        });
//        Thread thread2 = new Thread(new Runnable() {
//            @Override
//            public void run() {
//                work();
//            }
//        });
//        thread1.start();
//        thread2.start();
//
//        thread1.join();
//        thread2.join();
//
//        long after = System.currentTimeMillis();
//        System.out.println("Time: " + (after - before) + "ms");
//        System.out.println("List1 " + list1.size());
//        System.out.println("List2 " + list2.size());
//        System.out.println("List3 " + list3.size());
//    }
//}
