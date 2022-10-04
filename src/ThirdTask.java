import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Comparator;
import java.util.HashMap;
import java.util.SortedSet;
import java.util.TreeSet;

public class ThirdTask {
    private String [] fileName = {"utf8.txt", "iso.txt" ,"windows.txt",  "utf16.txt","koi8r.txt"};
    private HashMap<Integer, Integer> map = new HashMap<>();
    int size;
    public void ThirdTask()
    {
        for(int i =0; i < 256; i ++)
            map.put(i, 0);
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public String[] getFileName() {
        return fileName;
    }

    public HashMap<Integer, Integer> getMap() {
        return map;
    }

    public void setFileName(String[] fileName) {
        this.fileName = fileName;
    }

    public void setMap(HashMap<Integer, Integer> map) {
        this.map = map;
    }

    public void work() throws IOException {
        for(int i = 0; i < getFileName().length; i++)
        {
            setSize((int) new File(getFileName()[i]).length());
            FileInputStream inputStream = new FileInputStream(getFileName()[i]);
            for(int n =0; n <getSize() ; n++) {
                int temp = inputStream.read();
                if (temp < 32) {
                    int temp2 = map.get(temp);
                    temp2++;
                    map.put(temp, temp2);
                }
            }
        }
    }

    public void printInformation()
    {
        SortedSet<Integer> keys = new TreeSet<>(map.keySet());
        for (int key : keys) {
            int value = map.get(key);
            System.out.println(String.format(
                    "* Byte - %-6s || Count - %-6d  " , key , value
            ));

        }
    }
}
