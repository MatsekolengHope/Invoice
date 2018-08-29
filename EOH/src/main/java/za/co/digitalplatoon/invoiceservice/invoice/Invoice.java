package za.co.digitalplatoon.invoiceservice.invoice;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import za.co.digitalplatoon.invoiceservice.lineitem.LineItem;

@Entity
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Invoice {

	@GeneratedValue(strategy = GenerationType.AUTO)
	@Id
	private Long id;
	private String client;
	private Long vatRate;
	private Date invoiceDate;


	@OneToMany(fetch = FetchType.LAZY,cascade = CascadeType.REFRESH)
	private List<LineItem> lineItems;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getClient() {
		return client;
	}

	public void setClient(String client) {
		this.client = client;
	}

	public Long getVatRate() {
		return vatRate;
	}

	public void setVatRate(Long vatRate) {
		this.vatRate = vatRate;
	}

	public Date getInvoiceDate() {
		return invoiceDate;
	}

	public void setInvoiceDate(Date invoiceDate) {
		this.invoiceDate = invoiceDate;
	}

    public List<LineItem> getLineItems() {
        return lineItems;
    }

    public void setLineItems(List<LineItem> lineItems) {
        this.lineItems = lineItems;
    }


    public BigDecimal getVat() {

	    BigDecimal itemsTotal = new BigDecimal(0);

       for (int x = 0; x < this.getLineItems().size(); x++){

           itemsTotal = itemsTotal.add(this.getLineItems().get(x).getLineItemTotal().multiply(BigDecimal.valueOf(this.getVatRate())));
       }

       return itemsTotal.divide(BigDecimal.valueOf(100), 2, BigDecimal.ROUND_HALF_UP);
    }

    public BigDecimal getSubTotal() {

        return this.getTotal().subtract(this.getVat());
    }

    public BigDecimal getTotal() {

        return this.getSubTotal().add(this.getVat());
    }

}
