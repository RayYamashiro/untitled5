import java.io.*;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.ListIterator;

public class UnicodeAnalysis {

    // размер алфавита unicode 137994
    private double sumOfInformation;
    private int size;
    private String pathOfTheFile;
    private ArrayList<Symbol> list = new ArrayList<>();

    public UnicodeAnalysis(String pathOfTheFile) {
        this.pathOfTheFile = pathOfTheFile;
        size = 0;
        sumOfInformation = 0;
    }

    public void setSumOfInformation(int sumOfInformation) {
        this.sumOfInformation = sumOfInformation;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public void setPathOfTheFile(String pathOfTheFile) {
        this.pathOfTheFile = pathOfTheFile;
    }

    public void setList(ArrayList<Symbol> list) {
        this.list = list;
    }

    public double getSumOfInformation() {
        return sumOfInformation;
    }

    public int getSize() {
        return size;
    }

    public String getPathOfTheFile() {
        return pathOfTheFile;
    }

    public ArrayList<Symbol> getList() {
        return list;
    }





    public void CalcTheProbability()
    {

        for (Symbol s : list)
            s.setProbability((double) s.getCount() / 137994);

    }

    public void CalcTheAmountOfInformation()
    {
        for(Symbol s : list)
            s.setAmountOfTheInformation(s.getCount() * 17.1);
    }

    public void CalcSumOfInformation()
    {

        int tempSum = 0;
        for (Symbol s : list)
            tempSum += s.getAmountOfInformation();
        setSumOfInformation(tempSum);

    }

    public void CalcTheFrequency() throws IOException {

        //setSize((int) new File(getPathOfTheFile()).length());
        BufferedReader reader = new BufferedReader(new FileReader(getPathOfTheFile()));
        int ch = reader.read();

        while (ch != -1) {
            //size++;
            // ListIterator<Symbol> listIt = list.listIterator();
            Symbol symbol = new Symbol(ch, 1);
            if (list.size() == 0)
                list.add(symbol);
            else
            {
                int flag = 0;
                for(int i = 0; i < list.size(); i ++) {
                    if(list.get(i).equals(symbol))
                        flag = i;
                }
                if(flag>0)
                {
                    int temp = list.get(flag).getCount();
                    temp++;
                    list.get(flag).setCount(temp);
                }
                else
                    list.add(symbol);



//            if(list.contains(symbol))
//            {
//                int temp = list.get(list.indexOf(symbol)).getCount();
//                list.get(list.indexOf(symbol)).setCount(temp++);
//            }
//            else
//                list.add(symbol);

//            else {
//
//
//
//                    Symbol tempS = listIt.next();
//                    if (symbol.equals(tempS)) {
//                        int temp = tempS.getCount();
//                        temp++;
//                        listIt.previous().setCount(temp);
//                    } else
//                        listIt.add(symbol);

//            for(Symbol s : list) {
//                if (s.equals(symbol)) {
//                    int temp = s.getCount();
//                    temp++;
//                    s.setCount(temp);
//                } else
//                    list.add(symbol);
//                }
//            }


            }
            ch = reader.read();
            //lock.notifyAll();
        }
        reader.close();
        System.out.println();

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
        System.out.println();
        printAllInformation();
    }

    public void printAllInformation()
    {
        list.sort(Comparator.comparing(Symbol::getSymbolName));     // сортировка по алфавиту
        for(Symbol s : list)
            s.printInformationTaskUni();
        System.out.println("Sum of information " + getSumOfInformation());
        System.out.println("Size of the file in our alphabet " + getSize() );
    }
}
