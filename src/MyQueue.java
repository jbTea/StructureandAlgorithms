import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class MyQueue {
    private int maxSize;
    private Product[] products;
    int head;
    int tail;

    public MyQueue(int maxSize) {
        this.maxSize = maxSize;
        this.head=-1;
        this.tail=-1;
        this.products= new Product[maxSize];
    }

    /**
     * HÀM THÊM CÁC ĐỐI TƯƠNG TỪ LIST PRODUCT VÀO QUEUE
     * @param pro
     */
    public  void enQueueFromList (List<Product> pro){
       if(tail>=maxSize-1){
           System.out.println("Stack OverFlow");
           return;
       }
       if(pro.size()==0){
           System.out.println("no oj to push");
           return;
       }

           for (int i = 0; i <pro.size() ; i++) {
               if (isEmpty()){
                   products[head+1]=pro.get(i);
               }
               products[++tail]=pro.get(i);
           }
    }

    /**
     * HÀM HIỂN THỊ DỮ LIỆU TRONG QUEUE
     */
    public void showDataFromQueue(){
        System.out.println("--------------------------");
        System.out.println("ID| NAME | AMOUNT |PRICE |");
        System.out.println("--------------------------");
        Product[] cur = new Product[this.maxSize];
        for (int i = 0; i <cur.length ; i++) {
            if(head>tail) break;
            cur[i]=products[head+1];
            System.out.println(cur[i].getID()+" |"+cur[i].getName()+" |"+cur[i].getAmount()+" |"+cur[i].getPrice());
            head=head+1;
        }
        writeFileOfQueue("OUTPUT.TXT",cur);

    }
    public boolean isEmpty(){
        return this.tail==-1&&this.head==-1;
    }

    /**
     * HÀM GHI FILE CỦA ĐỐI TƯỢNG QUEEUE
     * @param fileName
     * @param arr
     */
    public  void writeFileOfQueue(String fileName,Product[] arr){
        FileWriter fw = null;
        BufferedWriter bw =null;
        try {
            fw = new FileWriter(fileName);
            bw = new BufferedWriter(fw);
            StringBuilder s = new StringBuilder();
            for (int i = 0; i < arr.length; i++) {
                s.append(arr[i].getID()+","+arr[i].getName()+","
                        +arr[i].getAmount()+","+arr[i].getPrice());
                if(i<=arr.length-2){
                    s.append("\n");
                }
            }
            bw.write(String.valueOf(s));
            bw.flush();

        } catch (IOException e){
            System.out.println("Write data fail ");
            throw new RuntimeException(e);
        }
        finally {
            try {
                if (bw != null) {
                    bw.close();
                }
                if (fw != null) {
                    fw.close();
                }
            }catch (IOException ex){
                throw new RuntimeException(ex);
            }
        }
    }
}
