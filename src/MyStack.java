import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class MyStack {
    private int maxSize;
    private Product[] product ;
    private int top;

    public MyStack(int maxSize) {
        this.maxSize = maxSize;
        this.product = new Product[maxSize];
        this.top =-1;
    }

    public Product[] getProduct() {
        return product;
    }

    public void setProduct(Product[] product) {
        this.product = product;
    }

    /**
     * THÊM CÁC ĐỐI TƯỢNG DẠNG PRODUCT VÀO STACK
     * @param pro
     */
    public void pushFromList(List<Product> pro){
        if(this.top>=maxSize-1){
            System.out.println("Stack OverFlow");
            return;
        }
        if(pro.size()==0){
            System.out.println("no oj to push");
            return;
        }
        for (int i = 0; i <pro.size() ; i++) {
            product[++top]=pro.get(i);
        }
    }
    public Product pop(){
        if(isEmpty()){
            System.out.println("stack is empty,no oj to pop");
            return null;
        } else return product[top--];
    }
    public Product peek(){
        return product[top];
    }
    public boolean isEmpty() {
        return (top == -1);
    }

    /**
     * HIỂN THỊ THÔNG TIN CHỨA TRONG STACK
     */
    public void showDataFromStack(){
        System.out.println("--------------------------");
        System.out.println("ID| NAME | AMOUNT |PRICE |");
        System.out.println("--------------------------");
        Product[] cur = new Product[this.maxSize];
        for (int i = 0; i < cur.length ; i++) {
            if(top<0) break;
            cur[i]=product[top];
            System.out.println(cur[i].getID()+" |"+cur[i].getName()+" |"+cur[i].getAmount()+" |"+cur[i].getPrice());
            top =top-1;
        }
        writeFileOfStack("OUTPUT.TXT",cur);
    }

    /**
     * HÀM GHI FILE CỦA STACK
     * @param fileName
     * @param arr
     */
    public  void writeFileOfStack(String fileName, Product[] arr){
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
