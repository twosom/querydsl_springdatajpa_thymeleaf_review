package jpabook.jpashop.controller;

import jpabook.jpashop.controller.dto.ItemListDto;
import jpabook.jpashop.controller.dto.MovieForm;
import jpabook.jpashop.domain.item.Movie;
import jpabook.jpashop.repository.ItemQueryRepository;
import jpabook.jpashop.service.ItemService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class ItemController {

    private final ItemService itemService;
    private final ItemQueryRepository itemQueryRepository;

    @GetMapping("/items/new")
    public String createForm(Model model) {
        model.addAttribute("form", new MovieForm());

        return "items/createItem";
    }

    @PostMapping("/items/new")
    public String create(@ModelAttribute MovieForm form) {
        Movie movie = new Movie(form);

        itemService.saveItem(movie);

        return "redirect:/";
    }

    @GetMapping("/items")
    public String list(Model model) {
        List<ItemListDto> items = itemQueryRepository.findItems();

        model.addAttribute("items", items);
        return "items/itemList";
    }

    @GetMapping("/items/{itemId}/edit")
    public String updateItemForm(@PathVariable Long itemId, Model model) {
        MovieForm form = itemQueryRepository.findOne(itemId);

        model.addAttribute("form", form);
        return "items/updateItemForm";
    }

    @PostMapping("/items/{itemId}/edit")
    public String updateItem(@PathVariable Long itemId, @ModelAttribute MovieForm form) {
        itemService.updateItem(itemId, form);
        return "redirect:/items";
    }
}
