package za.co.digitalplatoon.invoiceservice.invoice;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/invoices")
public class InvoiceController {

	@Autowired
	private InvoiceRepository invoiceRepository;
	
	@PostMapping("/add-invoice")
	public Invoice addInvoice(@RequestBody Invoice invoice){
		return invoiceRepository.save(invoice);
	}
	

	@GetMapping("/all-invoices")
	public List<Invoice> viewAllInvoices(){

		return invoiceRepository.findAll();
	}
	
	@RequestMapping("/get-one/{invoiceId}")
	public Invoice viewInvoice(@PathVariable Long invoiceId){
		return invoiceRepository.getOne(invoiceId);
	}
	
}
