package webapp.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import webapp.MyDate;
import webapp.Service.DateService;

@Controller
public class DateController {

	@Autowired
	DateService dateService;

	@RequestMapping(value = "/date", method = RequestMethod.GET)
	public String showLanding() {
		return "dateapplication";
	}

	@RequestMapping(value = "/dispatch", method = RequestMethod.GET)
	public String showOutput(@RequestParam(name = "firstDate") String firstDate,
			@RequestParam(name = "operation") String operation,
			@RequestParam(name = "secondDate", required = false, defaultValue = "") String secondDate,
			@RequestParam(name = "nUnits", required = false, defaultValue = "0") String nUnits, ModelMap model) {

		String convertedFirstDate = dateService.convertToDate(firstDate);
		MyDate date1;
		if (convertedFirstDate == null) {
			if (dateService.isValidDate(firstDate)) {
				date1 = new MyDate(firstDate);
			} else {
				model.put("output", "not a valid first date");
				return "Output";
			}
		} else {
			date1 = new MyDate(convertedFirstDate);
		}

		if (nUnits.equals("0")) {

			if (Integer.parseInt(operation) == 1) {
				String convertedSecondDate = dateService.convertToDate(secondDate);
				MyDate date2;
				if (convertedSecondDate == null) {
					if (dateService.isValidDate(secondDate)) {
						date2 = new MyDate(secondDate);
					} else {
						model.put("output", "not a valid second date");
						return "Output";
					}
				} else {
					date2 = new MyDate(convertedSecondDate);
				}
				model.put("output", dateService.addDates(date1, date2));

			} else if (Integer.parseInt(operation) == 2) {
				String convertedSecondDate = dateService.convertToDate(secondDate);
				MyDate date2;
				if (convertedSecondDate == null) {
					if (dateService.isValidDate(secondDate)) {
						date2 = new MyDate(secondDate);
					} else {
						model.put("output", "not a valid second date");
						return "Output";
					}
				} else {
					date2 = new MyDate(convertedSecondDate);
				}
				model.put("output", dateService.subtractDates(date1, date2));

			} else if (Integer.parseInt(operation) == 5) {
				model.put("output", dateService.whichDay(date1));

			} else if (Integer.parseInt(operation) == 6) {
				model.put("output", dateService.findWeekNumber(date1));
			}
		} else {
			if (Integer.parseInt(operation) == 3) {
				model.put("output", dateService.addNUnits(date1, Integer.parseInt(nUnits)));
			} else {
				model.put("output", dateService.subtractNUnits(date1, Integer.parseInt(nUnits)));
			}
		}

		return "Output";
	}

}
