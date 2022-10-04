import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;

public class ByteAnalysis {

    private int sumOfInformation;
    private int size;
    private String pathOfTheFile;
    private ArrayList<Symbol> list = new ArrayList<>(256);  // переделать потом в хэшкарту



    public ByteAnalysis(String pathOfTheFile) {
        this.pathOfTheFile = pathOfTheFile;

        for (int i = 0; i < 256; i++) {
            list.add(new Symbol(i));
        }
        size = 0;
        sumOfInformation = 0;
    }

    public void setSumOfInformation(int sumOfInformation) {
        this.sumOfInformation = sumOfInformation;
    }

    public void setSize(int size) {
        this.size = size;
    }


    public int getSumOfInformation() {
        return sumOfInformation;
    }

    public int getSize() {
        return size;
    }

    public String getPathOfTheFile() {
        return pathOfTheFile;
    }





    public void CalcTheProbability()
    {

            for (Symbol s : list)
                s.setProbability((double) s.getCount() / getSize());

    }

    public void CalcTheAmountOfInformation()
    {
        for(Symbol s : list)
            s.setAmountOfTheInformation(s.getCount() * 8);
    }

    public void CalcSumOfInformation()
    {

            int tempSum = 0;
            for (Symbol s : list)
                tempSum += s.getAmountOfInformation();
            setSumOfInformation(tempSum);

    }

    public void CalcTheFrequency() throws FileNotFoundException {

            setSize((int) new File(getPathOfTheFile()).length());
            FileInputStream inputStream = new FileInputStream(getPathOfTheFile());
           // Symbol [] arr = (Symbol[]) list.toArray();
            for (int i = 0; i < getSize(); i++) {

                try {
                    int temp = list.get(inputStream.read()).getCount();
                    temp++;
                    list.get(inputStream.read()).setCount(temp);
                } catch (IOException e) {
                    e.getStackTrace();
                }
                //list.get(inputStream.read()).count++;
                //list.get(list.indexOf(inputStream.readNBytes(1))).count++;
            }

        //lock.notifyAll();
    }

    public void work() throws InterruptedException {
        Thread thread1 = new Thread(new Runnable() {
            @Override
            public void run() {
                CalcTheProbability();
            }
        });   // CalcTheProbability
        Thread thread2 = new Thread(new Runnable() {
            @Override
            public void run() {
                CalcSumOfInformation();
            }
        });   // CalcSumOfInformation
        Thread thread3 = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    CalcTheFrequency();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        });   // CalcTheFrequency
        Thread thread4 = new Thread(new Runnable() {
            @Override
            public void run() {
                CalcTheAmountOfInformation();
            }
        });   // CalcTheAmountOfInformation

        thread3.start();
        thread3.join();
        thread1.start();
        thread4.start();



        thread1.join();
        thread4.join();


        thread2.start();
        thread2.join();

        printAllInformation();
    }

    public void printAllInformation()
    {
        list.sort(Comparator.comparing(Symbol::getSymbolName));     // сортировка по алфавиту
        for(Symbol s : list)
            s.printInformationTaskByte();
        System.out.println("Sum of information " + getSumOfInformation());
        System.out.println("Size of the file in our alphabet " + getSize() );
    }
}