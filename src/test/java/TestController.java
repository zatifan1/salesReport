import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.mystock.salesreport.Application;
import com.mystock.salesreport.models.Demand;
import com.mystock.salesreport.models.Product;
import com.mystock.salesreport.models.Purchase;
import com.mystock.salesreport.service.DemandService;
import com.mystock.salesreport.service.ProductService;
import com.mystock.salesreport.service.PurchaseService;
import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;
import java.util.List;

public class TestController {

    @Test
    public void testAddProduct() throws IOException {
        ProductService productService = new ProductService();
        List<Product> products = productService.findAllProducts();

        int beforeAmount = products.size();
        Application.newProductPOST("Pen");

        products = productService.findAllProducts();
        int afterAmount = products.size();

        Assert.assertEquals( beforeAmount + 1, afterAmount);
    }

    @Test
    public void testAddCopyProduct() throws IOException {
        ProductService productService = new ProductService();
        List<Product> products = productService.findAllProducts();

        int beforeAmount = products.size();
        Application.newProductPOST("Car");

        products = productService.findAllProducts();
        int afterAmount = products.size();

        Assert.assertEquals( beforeAmount, afterAmount);
    }

    @Test
    public void testAddPurchase() throws IOException {
        ProductService productService = new ProductService();
        PurchaseService purchaseService = new PurchaseService();
        List<Purchase> purchases = purchaseService.findAllPurchases();

        int beforeAmount = purchases.size();
        Product product = productService.findByName("Spoon");
        int beforeProductAmount = product.getAmount();
        Application.purchasePOST("Spoon", 5, 200, "2019-01-10");


        purchases = purchaseService.findAllPurchases();
        int afterAmount = purchases.size();
        product = productService.findByName("Spoon");
        int afterProductAmount = product.getAmount();
        Assert.assertEquals( beforeAmount + 1, afterAmount);
        Assert.assertEquals( beforeProductAmount + 5, afterProductAmount);
    }

    @Test
    public void testAddPurchaseNoProduct() throws IOException {
        PurchaseService purchaseService = new PurchaseService();
        List<Purchase> purchases = purchaseService.findAllPurchases();

        int beforeAmount = purchases.size();
        Application.purchasePOST("Chair", 5, 200, "2019-01-10");

        purchases = purchaseService.findAllPurchases();
        int afterAmount = purchases.size();
        Assert.assertEquals( beforeAmount, afterAmount);
    }

    @Test
    public void testAddPurchaseBadPrice() throws IOException {
        ProductService productService = new ProductService();
        PurchaseService purchaseService = new PurchaseService();
        List<Purchase> purchases = purchaseService.findAllPurchases();

        int beforeProductAmount = productService.findByName("Car").getAmount();
        int beforeAmount = purchases.size();
        Application.purchasePOST("Car", 5, 0, "2019-01-10");

        purchases = purchaseService.findAllPurchases();
        int afterAmount = purchases.size();
        int afterProductAmount = productService.findByName("Car").getAmount();
        Assert.assertEquals( beforeAmount, afterAmount);
        Assert.assertEquals( beforeProductAmount, afterProductAmount);
    }

    @Test
    public void testAddPurchaseBadDate() throws IOException {
        ProductService productService = new ProductService();
        PurchaseService purchaseService = new PurchaseService();
        List<Purchase> purchases = purchaseService.findAllPurchases();

        int beforeProductAmount = productService.findByName("Car").getAmount();
        int beforeAmount = purchases.size();
        Application.purchasePOST("Car", 5, 200, "01/10/2019");

        purchases = purchaseService.findAllPurchases();
        int afterAmount = purchases.size();
        int afterProductAmount = productService.findByName("Car").getAmount();
        Assert.assertEquals( beforeAmount, afterAmount);
        Assert.assertEquals( beforeProductAmount, afterProductAmount);
    }

    @Test
    public void testAddDemand() throws IOException{
        ProductService productService = new ProductService();
        DemandService demandService = new DemandService();

        List<Demand> demands = demandService.findAllDemands();
        int beforeAmount = demands.size();
        int beforeProductAmount = productService.findByName("Spoon").getAmount();
        Application.demandPOST("Spoon", 10, 500, "2019-12-30");

        demands = demandService.findAllDemands();
        int afterAmount = demands.size();
        int afterProductAmount = productService.findByName("Spoon").getAmount();
        Assert.assertEquals( beforeAmount + 1, afterAmount);
        Assert.assertEquals( beforeProductAmount - 10, afterProductAmount);
    }

    @Test
    public void testAddDemandNoProduct() throws IOException{
        DemandService demandService = new DemandService();

        List<Demand> demands = demandService.findAllDemands();
        int beforeAmount = demands.size();
        Application.demandPOST("Chair", 10, 500, "2019-12-30");

        demands = demandService.findAllDemands();
        int afterAmount = demands.size();
        Assert.assertEquals( beforeAmount, afterAmount);
    }

    @Test
    public void testAddDemandBadPrice() throws IOException{
        ProductService productService = new ProductService();
        DemandService demandService = new DemandService();

        List<Demand> demands = demandService.findAllDemands();
        int beforeAmount = demands.size();
        int beforeProductAmount = productService.findByName("Car").getAmount();
        Application.demandPOST("Car", 3, 0, "2019-12-30");

        demands = demandService.findAllDemands();
        int afterAmount = demands.size();
        int afterProductAmount = productService.findByName("Car").getAmount();
        Assert.assertEquals( beforeAmount, afterAmount);
        Assert.assertEquals( beforeProductAmount, afterProductAmount);
    }

    @Test
    public void testAddDemandBadDate() throws IOException{
        ProductService productService = new ProductService();
        DemandService demandService = new DemandService();

        List<Demand> demands = demandService.findAllDemands();
        int beforeAmount = demands.size();
        int beforeProductAmount = productService.findByName("Car").getAmount();
        Application.demandPOST("Car", 3, 500, "12/12/2019");

        demands = demandService.findAllDemands();
        int afterAmount = demands.size();
        int afterProductAmount = productService.findByName("Car").getAmount();
        Assert.assertEquals( beforeAmount, afterAmount);
        Assert.assertEquals( beforeProductAmount, afterProductAmount);
    }

    @Test
    public void testSalesReport() throws  IOException{
        String report = Application.salesreportGET("Spoon", "2019-12-31");
        JsonObject jsonObject = new JsonParser().parse(report).getAsJsonObject();
        int revenue = jsonObject.get("message").getAsInt();
        Assert.assertEquals( 3000, revenue);
    }

    @Test
    public void testSalesReportBadDate() throws  IOException{
        String report = Application.salesreportGET("Spoon", "12/12/2019");
        JsonObject jsonObject = new JsonParser().parse(report).getAsJsonObject();
        boolean result = jsonObject.get("result").getAsBoolean();
        Assert.assertEquals( false, result);
    }

    @Test
    public void testSalesReportBadProduct() throws  IOException{
        String report = Application.salesreportGET("Chair", "2019-12-31");
        JsonObject jsonObject = new JsonParser().parse(report).getAsJsonObject();
        boolean result = jsonObject.get("result").getAsBoolean();
        Assert.assertEquals( false, result);
    }

}
