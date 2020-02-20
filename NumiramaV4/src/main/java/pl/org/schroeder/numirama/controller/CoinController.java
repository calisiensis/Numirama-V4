package pl.org.schroeder.numirama.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import pl.org.schroeder.numirama.entity.Coin;
import pl.org.schroeder.numirama.service.CoinService;

@Controller
@RequestMapping("/coin")
public class CoinController {

	// need to inject our customer service
	@Autowired
	private CoinService coinService;

	private int pageSize = 25;

	@InitBinder
	public void initBinder(WebDataBinder dataBinder) {

		StringTrimmerEditor stringTrimmerEditor = new StringTrimmerEditor(true);
		dataBinder.registerCustomEditor(String.class, stringTrimmerEditor);
	}

	@GetMapping("/list")
	public String listCoins(@RequestParam(name = "pageNumber", required = false, defaultValue = "1") int pageNumber,
			Model theModel) {

		long totalCoinsQuantity = coinService.getCoinsQuantity();
		int pages = (int) Math.floor(totalCoinsQuantity / pageSize);

		if ((totalCoinsQuantity % pageSize) > 0) {
			pages++;
		}

		System.out.println("Numer strony = " + pageNumber);

		List<Coin> theCoins = coinService.getPaginatedCoins(pageNumber);
		List<Coin> totalCoins = coinService.getCoins();

		theModel.addAttribute("coins", theCoins);
		theModel.addAttribute("totalCoinsQuantity", totalCoinsQuantity);
		theModel.addAttribute("currentPage", pageNumber);
		theModel.addAttribute("pages", pages);
		theModel.addAttribute("pageSize", pageSize);
		theModel.addAttribute("totalCoins", totalCoins);

		return "list-coins";
	}

	@GetMapping("/showFormForAdd")
	public String showFormForAdd(Model theModel) {

		// create model attribute to bind form data
		Coin theCoin = new Coin();

		theModel.addAttribute("coin", theCoin);

		return "coin-form";
	}

	@PostMapping("/saveCoin")
	public String saveCoin(@Valid @ModelAttribute("coin") Coin theCoin, BindingResult theBindingResult) {

		if (theBindingResult.hasErrors()) {
			return "coin-form";
		} else {

			coinService.saveCoin(theCoin);

			return "redirect:/coin/list";
		}
	}

	@GetMapping("/showFormForUpdate")
	public String showFormForUpdate(@RequestParam("coinId") int theId, Model theModel) {

		// get the customer from our service
		Coin theCoin = coinService.getCoin(theId);

		// set customer as a model attribute to pre-populate the form
		theModel.addAttribute("coin", theCoin);

		// send over to our form
		return "coin-form";
	}

	@GetMapping("/delete")
	public String deleteCoin(@RequestParam("coinId") int theId) {

		// delete the customer
		coinService.deleteCoin(theId);

		return "redirect:/coin/list";
	}

	@GetMapping("/search")
	public String searchForCoin(@RequestParam("searchedText") String searchedText, Model theModel) {

		List<Coin> theCoins = coinService.searchForCoin(searchedText);

		theModel.addAttribute("coins", theCoins);

		return "list-coins";
	}

}
