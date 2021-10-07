package jpabook.jpashop.service;

import jpabook.jpashop.domain.item.Book;
import jpabook.jpashop.domain.item.Item;
import jpabook.jpashop.repository.ItemRepository;
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

    //변경 감지 기능(dirty checking) --> 최대한 'merge' 쓰지 말자!
    //                             --> 엔티티를 변경할 때는 항상 변경 감지를 사용하자!
    //** 주의 : 변경 감지 기능을 사용하면 원하는 속성만 선택해서 변경할 수 있지만, 병합을 사용하면 모든 속성이 변경됨
    //         병합시 값이 없으면 'null' 로 업데이트 할 위험도 있음(병합은 모든 필드를 교체함) **
    @Transactional
    public void updateItem(Long itemId, String name, int price, int stockQuantity) { //param : 파라미터로 넘어온 준영속 상태의 엔티티
        Item findItem = itemRepository.findOne(itemId); //같은 엔티티를 조회함
        findItem.setName(name); //데이터를 수정함
        findItem.setPrice(price);
        findItem.setStockQuantity(stockQuantity);
        //실무에서는 setter를 단발성으로 불러오지 말고 엔티티에서 의미있는 메서드를 생성하자!
        //ex) findItem.change(price, name, stockQuantity);
    }

    public List<Item> findItems() {
        return itemRepository.findAll();
    }

    public Item findOne(Long itemId) {
        return itemRepository.findOne(itemId);
    }
}
