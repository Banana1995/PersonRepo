package designpattern.factorypattern.abstractfactorypattern;

public abstract class ProductC {

    private String procuctName;

    protected String Attributes;

    public abstract String getProductName();

    public abstract String getAttributes();

    /*所有产品的公共方法*/
    public void setPubMethod(){
        this.Attributes = "attributes";
    }
}
