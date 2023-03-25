import java.io.*;
import java.util.*;

public class AS2_Main {
    private static MyList list = new MyList();

    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        menu();
        selectFunction();
    }

    /**
     * HÀM HIỂN THỊ MENU
     */

    public static void menu(){
        System.out.println("+-----------------------------------------+");
        System.out.println("|            WAREHOUSE MANAGEMENT         |");
        System.out.println("+-----------------------------------------+");
        System.out.println("+-------------------Menu------------------+");
        System.out.println("|      1. Load data from file and display |");
        System.out.println("|      2. Input & add to the end.         |");
        System.out.println("|      3.Display data                     |");
        System.out.println("|      4.Save product list to file.       |");
        System.out.println("|      5. Search by ID                    |");
        System.out.println("|      6.Delete by ID                     |");
        System.out.println("|      7.Sort by ID.                      |");
        System.out.println("|      8. Convert to Binary               |");
        System.out.println("|      9. Load to stack and display       |");
        System.out.println("|      10. Load to queue and display.     |");
        System.out.println("|      0.Exit                             |");
        System.out.println("+-----------------------------------------.+");
        System.out.print("CHOSE FUNCTION : ");
    }
    /**
     * HÀM PHỤ- LẤY SỐ
     * @return : trả về một số dạng int
     */
    public static int inputNumber (){
        Scanner scanner = new Scanner(System.in);
        int numBer =scanner.nextInt();
        return  numBer;
    }

    /**
     * HÀM CHÍNH - HÀM ĐIỀU KHIỂN CHỨC NĂNG CỦA CHƯƠNG TRÌNH NGƯỜI DÙNG
     */
    public static void selectFunction (){
        // khai báo  báo biến đầu vào và biến báo lỗi
        int inputFun = 0;
        boolean error;
        do {
            try {
                error =false;
                inputFun=inputNumber();
                switch (inputFun){
                    case 0:
                        System.out.println("GOOD BYE");
                        break;
                    case 1:// ĐỌC DỮ LIỆU TỪ FILE -IN RA MÀN HÌNH
                        list.deleteList();
                        List<Product> arr = readFile("DATA.TXT");
                        list.saveDataByInsertAtTail(arr);
                        list.showData();
                        System.out.println("Successfully!");
                        menu();
                        selectFunction();
                        break;
                    case 2: // THÊM SẢN PHẨM VÀO CUỐI LINK LIST
                        Product newProduct = makeProductByInputFromUser();
                        list.addToTail(newProduct);
                        menu();
                        selectFunction();
                        break;
                    case 3: // HIỂN THỊ SẢN PHẨM THEO LINK LIST
                        list.showData();
                        list.writeFile("OUTPUT.TXT",list.backArr());
                        menu();
                        selectFunction();
                        break;
                    case 4: // LƯU LINK LIST VÀO FILE
                        list.writeFile("DATA.TXT",list.backArr());
                        System.out.println("Successfully!");
                        menu();
                        selectFunction();
                        break;
                    case 5: // TÌM KIẾM THEO ID
                        List<Product> searchList = list.backArr();
                        System.out.print("Enter ID to search: ");
                        String searchInput = scanner.nextLine();
                        list.searchById(searchInput,searchList);
                        menu();
                        selectFunction();
                        break;
                    case 6: // XÓA THEO ID
                        System.out.print("Enter ID to Delete: ");
                        String deleteInput = scanner.nextLine();
                        list.deleteById(deleteInput.toLowerCase());
                        list.writeFile("OUTPUT.TXT",list.backArr());
                        list.showData();
                        menu();
                        selectFunction();
                        break;
                    case 7: // SẮP XẾP THEO ID
                        List<Product> ABC = list.sortById(list.backArr());
                        list.deleteList();
                        list.saveDataByInsertAtTail(ABC);
                        list.showData();
                        list.writeFile("OUTPUT.TXT",ABC);
                        list.writeFile("DATA.TXT",ABC);
                        menu();
                        selectFunction();
                        break;
                    case 8: // CHUYỂN ĐỔI SANG NHỊ PHÂN
                        int conversionNumber = list.backArr().get(0).getAmount();
                        String binary =list.convertToBinary(conversionNumber);
                        System.out.println("Amount = "+conversionNumber+" => "+binary);
                        writeFileVer2("OUTPUT.TXT","Amount = "+conversionNumber+" => "+binary);
                        menu();
                        selectFunction();
                        break;
                    case 9: // ĐỌC FILE => LƯU VÀO STACK=> HIỂN THỊ THEO STACK
                        List<Product> listToStack = readFile("DATA.TXT");
                        MyStack myStack = new MyStack(listToStack.size());
                        myStack.pushFromList(listToStack);
                        myStack.showDataFromStack();
                        menu();
                        selectFunction();
                        break;
                    case 10: // ĐỌC FILE =>LƯU VÀO QUEUE => HIỂN THỊ THEO QUEUE
                        List<Product> listToQueue = readFile("DATA.TXT");
                        MyQueue myQueue = new MyQueue(listToQueue.size());
                        myQueue.enQueueFromList(listToQueue);
                        myQueue.showDataFromQueue();
                        menu();
                        selectFunction();
                        break;
                    default:
                        System.out.print("Enter the wrong function, please choose again 0-10 :");
                }
            }catch (InputMismatchException e){
                System.out.println("please enter a number:");
                error =true;
            }
        } while (inputFun!=0&&inputFun!=1&&inputFun!=2&&inputFun!=3&&
                inputFun!=4&&inputFun!=5&&inputFun!=6&&inputFun!=7
                &&inputFun!=8&&inputFun!=9&&inputFun!=10||error);
    }

    /**
     * ĐỌC FILE BAN ĐẦU KHI KHỞI ĐỘNG CHƯƠNG TRÌNH LẤY INPUT CHO CHƯƠNG TRÌNH HOẠT ĐỘNG
     * @param fileName
     * @return
     */
    public static List<Product> readFile (String fileName){
        FileReader fr =null;
        BufferedReader br =null;
        try {
           fr = new FileReader(fileName);
           br = new BufferedReader(fr);
           String line = br.readLine();
           List<Product> resultProduct = new ArrayList<>();
           while (line!=null){
               try {
                   String txt[] =line.split(",");
                   String id = txt[0];
                   String name = txt[1];
                   int amount = Integer.parseInt(txt[2]);
                   float price = Float.parseFloat(txt[3]);
                   Product product = new Product(id,name,amount,price);
                   resultProduct.add(product);
               } catch (Exception e){
                   System.out.println("Exception while parsing line to Product");
               }
               line= br.readLine();
           }
            return resultProduct;

        } catch (IOException e){
            return null;
        } finally {
            if(br!= null){
                try {
                    br.close();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
            if(fr != null) {
                try {
                    fr.close();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        }

    }

    /**
     *  GHI FILE : RIÊNG CHỨC NĂNG SỐ 8- CHUYỂN DỔI SỐ LƯỢNG SANG NHỊ PHÂN
     * @param fileName
     * @param input
     */
    public static void writeFileVer2(String fileName,String input){
        FileWriter fw = null;
        BufferedWriter bw =null;
        try {
            fw = new FileWriter(fileName);
            bw = new BufferedWriter(fw);
            bw.write(String.valueOf(input));
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
     * HÀM TẠO ĐỐI TƯỢNG PRODUCT TỪ INPUT NGƯỜI DÙNG NHẬP
     * @return
     */

    public static Product makeProductByInputFromUser(){
        System.out.println("Enter the parameters for the product:");
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter Id :");
        String id =sc.nextLine();
        System.out.print("Enter name :");
        String name = sc.nextLine();
        int amount =intNumber();
        float price =aFloatNumber();
        Product newProduct = new Product(id,name,amount,price);
        return newProduct;
    }
    /**
     *  PHỤ TRỢ -SỬ LÝ NGOẠI LỆ KIỂU FLOAT
     * @return số float đúng định dạng
     */
    public static  float aFloatNumber (){
        Scanner scanner = new Scanner(System.in);
        float number =0;
        boolean err;
        do {
            try {
                err = false;
                System.out.print("enter price :");
                number =scanner.nextFloat();
            }catch (InputMismatchException e){
                scanner.nextLine();
                err =true;
                System.out.println("Wrong format re-enter :");
            }
        }while (err);
        return number;
    }

    /**
     *  PHỤ TRỢ - SỬ LÝ NGOẠI LỆ KIỂU INT
     * @return : sô int đúng định dạng
     */
    public static  int intNumber (){
        Scanner scanner = new Scanner(System.in);
        int number =0;
        boolean err;
        do {
            try {
                err = false;
                System.out.print("Enter Amount :");
                number =scanner.nextInt();
            }catch (InputMismatchException e){
                scanner.nextLine();
                err =true;
                System.out.println("Wrong format re-enter: ");
            }
        }while (err);
        return number;
    }
}
