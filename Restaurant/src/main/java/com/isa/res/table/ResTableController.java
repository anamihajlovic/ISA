package com.isa.res.table;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.isa.restaurant.Restaurant;
import com.isa.restaurant.RestaurantService;

@RestController
@RequestMapping("/resTables")
public class ResTableController {

	private final ResTableService resTableService;
	private final RestaurantService restaurantService;

	@Autowired
	public ResTableController(final ResTableService resTableService, final RestaurantService restaurantService) {
		this.resTableService = resTableService;
		this.restaurantService = restaurantService;
	}

	@GetMapping(path = "/getTables/{id}")
	public List<ResTable> getTables(@PathVariable Long id) {
		ArrayList<ResTable> outTables = new ArrayList<ResTable>();
		ArrayList<ResTable> resultTables = new ArrayList<ResTable>();
		int visina = 0;
		int sirina = 0;

		try {
			Restaurant r = restaurantService.findOne(id);
			for (int i = 0; i < r.getSegments().size(); i++) {
				outTables.addAll(r.getSegments().get(i).getTables());

			}

			if (visina == 0 || sirina == 0) {
				for (ResTable tt : outTables) {
					if (tt.getxPos() > visina) {
						visina = tt.getxPos();
					}
					if (tt.getyPos() > sirina) {
						sirina = tt.getyPos();
					}

				}
				visina = visina + 1;
				sirina = sirina + 1;

			}

			for (int i = 0; i < visina; i++) {
				for (int j = 0; j < sirina; j++) {
					for (ResTable rr : outTables) {
						if (rr.getxPos() == i && rr.getyPos() == j) {
							resultTables.add(rr);
						}

					}
				}
			}


			return resultTables;
		} catch (Exception e) {
			return resultTables;
		}
	}

}
