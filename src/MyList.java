import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MyList {
    private Node headNode;
    /**
     * LƯU DỮ LIỆU VÀO LINKLIST TẠI ĐIỂM CUỐI
     * @param arr
     */
    public void saveDataByInsertAtTail (List<Product> arr){
          Node  newNode = null;
        for (int i = 0; i <arr.size() ; i++) {
            newNode =  new Node(arr.get(i));
            if(this.headNode==null){
                this.headNode= newNode;
            }else {
                Node lastNode = this.headNode;
                while (lastNode.getNextNode()!=null){
                    lastNode=lastNode.getNextNode();
                }
                lastNode.setNextNode(newNode);
            }
        }
    }

    /**
     * THÊM PRODUCT VÀO LINK LIST TẠI VỊ TRÍ CUỐI LINK LIST
     * @param product
     */
    public void addToTail(Product product){
        Node newNode = new Node(product);
        if(this.headNode==null){
            this.headNode=newNode;
        } else {
            Node lastNode = this.headNode;
            while (lastNode.getNextNode()!=null){
                lastNode=lastNode.getNextNode();
            }
            lastNode.setNextNode(newNode);
        }
    }

    /**
     * XÓA CÁC PHẦN TỬ TRONG LINK LIST
     */
    public void deleteList (){
       this.headNode=null;
    }

    /**
     * HIỂN THỊ DỮ LIỆU TRONG LINK LIST
     */
    public void showData (){
        System.out.println("--------------------------");
        System.out.println("ID| NAME | AMOUNT |PRICE |");
        System.out.println("--------------------------");
        Node cur =this.headNode;
        while (cur!=null){
            String id = cur.backData().getID();
            String name = cur.backData().getName();
            int amount =cur.backData().getAmount();
            float price =cur.backData().getPrice();
            System.out.println(id+" |"+name+" |"+amount+" |"+price);
            cur = cur.getNextNode();
        }
    }

    /**
     * TRẢ VỀ MỘT LIST CÁC PRODUCT T LINK LIST
     * @return
     */
    public List<Product> backArr (){
        Node cur = this.headNode;
        List<Product> productList = new ArrayList<>();
        while (cur!=null){
            productList.add(cur.backData());
            cur =cur.getNextNode();
        }
        return productList;
    }

    /**
     * GHI LINK LIST VÀO FILE
     * @param fileName
     * @param list
     */
    public  void writeFile(String fileName,List<Product> list){
        FileWriter fw = null;
        BufferedWriter bw =null;
        try {
            fw = new FileWriter(fileName);
            bw = new BufferedWriter(fw);
            StringBuilder s = new StringBuilder();
            for (int i = 0; i < list.size(); i++) {
                s.append(list.get(i).getID()+","+list.get(i).getName()+","
                        +list.get(i).getAmount()+","+list.get(i).getPrice());
                if(i<=list.size()-2){
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

    /**
     * TÌM KIẾM THEO ID SẢN PHẨM
     * @param searchInput
     * @param searchList
     */
    public void searchById (String searchInput,List<Product> searchList){
        MyList foundItem = new MyList();
        List<Product> foundItemToSaveFile = new ArrayList<>();
        String regex = ".*"+searchInput+".*";
        Pattern pattern = Pattern.compile(regex.toLowerCase());
        Matcher matcher;
        for (int i = 0; i <searchList.size() ; i++) {
            matcher = pattern.matcher(searchList.get(i).getID().toLowerCase());
            if(matcher.find()){
              foundItem.addToTail(searchList.get(i));
              foundItemToSaveFile.add(searchList.get(i));
            }
        }
        if(foundItemToSaveFile.size()==0){
            System.out.println(" ID not found!");
        } else {
            writeFile("OUTPUT.TXT",foundItemToSaveFile);
            foundItem.showData();
        }
    }

    /**
     *  XÓA SẢN PHẨM THEO ID YÊU CẦU NHẬP CHÍNH XÁC ID
     * @param inputDeleteId
     */
    public void deleteById(String inputDeleteId){
        boolean foundAtHead =false;

        if(this.headNode.backData().getID().toLowerCase().equals(inputDeleteId)){
            foundAtHead=true;
        }
          if(foundAtHead){
              this.headNode=this.headNode.getNextNode();
              System.out.println("Deleteed!");
          } else {
              Node cur = this.headNode;
              Node pre = null;
              boolean found = false;
              while (cur!=null){
                  if(cur.backData().getID().toLowerCase().equals(inputDeleteId)){
                      found=true;
                      break;
                  }
                  pre=cur;
                  cur=cur.getNextNode();
              }
              if(found){
                  pre.setNextNode(cur.getNextNode());
                  System.out.println("Deleteed!");
              } else {
                  System.out.println("delete failed not found object");
              }
          }
    }

    /**
     *  CHUYỂN ĐÔI SỐ THẬP PHÂN SANG NHỊ PHÂN -DÙNG ĐỆ QUI
     * @param number
     * @return
     */
    public String convertToBinary(int number){
        String binary="";
        if(number==0) return binary="";
        return binary = convertToBinary(number/2)+number%2;
    }

    /**
     * SẮP XẾP ID THEO TỪ ĐIỂN TĂNG DẦN ANONYMOUST INNER CLASS
     * @param listProduct
     * @return
     */
    public List<Product> sortById(List<Product> listProduct){

       Collections.sort(listProduct, new Comparator<Product>() {
           @Override
           public int compare(Product o1, Product o2) {
               return o1.getID().compareTo(o2.getID());
           }
       });
        return listProduct;
    }
}
