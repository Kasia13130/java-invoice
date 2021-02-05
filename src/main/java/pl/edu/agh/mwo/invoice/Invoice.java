package pl.edu.agh.mwo.invoice;

import java.math.BigDecimal;
import java.util.Map;
import java.util.LinkedHashMap;

import pl.edu.agh.mwo.invoice.product.Product;

public class Invoice {
    private Map<Product, Integer> products = new LinkedHashMap<>();

    public void addProduct(Product product) {
    	this.products.put(product, 1);
    }

    public void addProduct(Product product, Integer quantity) {
    	this.products.put(product, quantity);
    	if (quantity <= 0 || quantity == null)
    	{
    		throw new IllegalArgumentException("Quantity is lower than 0");
    	}
    }

    public BigDecimal nettoPrice() {
    	BigDecimal sum = BigDecimal.ZERO;
        for (Product product : this.products.keySet())
        {
        	Integer quantity = this.products.get(product);
        	sum = sum.add(product.getPrice().multiply(new BigDecimal(quantity)));
        }
        return sum;
    }
    
    public BigDecimal getTax() { 
    	
    	return bruttoPrice().subtract(nettoPrice());
    }

    public BigDecimal bruttoPrice() { 
    	BigDecimal sum = BigDecimal.ZERO;
        for (Product product : this.products.keySet())
        {
        	Integer quantity = this.products.get(product);
        	
        	sum = sum.add(product.getPriceWithTax().multiply(new BigDecimal(quantity)));
        }
        return sum;
    }
}
