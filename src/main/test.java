package main;

import java.math.BigDecimal;
import java.time.*;
import java.util.*;

public class test {

	static class Order {
		int orderId;
		LocalDateTime creationDate;
		List<OrderLine> orderLines;
	}

	static class OrderLine {
		int productId;
		String name;
		int quantity;
		BigDecimal unitPrice;
	}

	public Map<DayOfWeek, Integer> totalDailySales(int productId, List<Order> orders) {
		HashMap<DayOfWeek, Integer> dailySales = new HashMap<>();
		        
        for(Order orderList : orders){
        	for(OrderLine order : orderList.orderLines) {
	        	if(orderList.orderId == productId) {
        			DayOfWeek day = orderList.creationDate.getDayOfWeek();
	        		if(dailySales.containsKey(day))
	        			dailySales.put(day, dailySales.get(day)+order.quantity);
	        		else
	        			dailySales.put(day, order.quantity);
	        	}
        	}
        }
        
		return dailySales;
	}
}
