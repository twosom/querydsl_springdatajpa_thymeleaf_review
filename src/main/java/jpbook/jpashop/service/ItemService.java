package jpbook.jpashop.service;

import jpbook.jpashop.controller.dto.MovieForm;
import jpbook.jpashop.domain.item.Item;
import jpbook.jpashop.domain.item.Movie;
import jpbook.jpashop.repository.ItemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class ItemService {

    private final ItemRepository itemRepository;

    @Transactional
    public void saveItem(Item item) {
        itemRepository.save(item);
    }

    public List<Item> findItems() {
        return itemRepository.findAll();
    }

    public Item findOne(Long itemId) {
        return itemRepository.findOne(itemId);
    }


    @Transactional
    public void updateItem(Long itemId, MovieForm form) {
        Movie findMovie = (Movie) itemRepository.findOne(itemId);
        findMovie.update(form);
    }
}
