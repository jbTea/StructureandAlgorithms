public class Node {
    private Product productData;
    private Node nextNode;

    public Node(Product productData) {
        this.productData = productData;
    }

    public Product getProductData() {
        return productData;
    }

    public void setProductData(Product productData) {
        this.productData = productData;
    }

    public Node getNextNode() {
        return nextNode;
    }

    public void setNextNode(Node nextNode) {
        this.nextNode = nextNode;
    }
    public Product backData (){
        return this.productData;
    }
}
