package sg.nus.iss.search.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import sg.nus.iss.search.component.SearchComponent;
import sg.nus.iss.search.entity.Flight;

@CrossOrigin
@RestController
@RequestMapping("/search")
class SearchRestController {
	
	private SearchComponent searchComponent;
	
	@Autowired
	public SearchRestController(SearchComponent searchComponent){
		this.searchComponent = searchComponent;
	}
	
	@RequestMapping(value="/get", method = RequestMethod.POST)
	List<Flight> search(@RequestBody SearchQuery query){
		System.out.println("Input : "+ query);
		return searchComponent.search(query);
	}
 
}
