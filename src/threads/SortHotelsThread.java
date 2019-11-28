package threads;

import model.Principal;

public class SortHotelsThread extends Thread{
	
	private Principal principal;
	private String indicator;
	public SortHotelsThread(Principal principal, String indicator) {
		this.principal = principal;
		this.indicator = indicator;
	}

	public void run() {
		
		if(indicator.equalsIgnoreCase("Name")) {
			principal.alphabeticalSortHotels();
		}else if(indicator.equalsIgnoreCase("PriceRange")) {
			principal.sortHotelsByPriceRange();
		}else if(indicator.equalsIgnoreCase("Stars")) {
			principal.sortHotelsByStars();
		}else if(indicator.equalsIgnoreCase("Score")) {
			principal.sortHotelsByScore();
		}else if(indicator.equalsIgnoreCase("City")) {
			principal.sortHotelsByCity();
		}
	}
}
