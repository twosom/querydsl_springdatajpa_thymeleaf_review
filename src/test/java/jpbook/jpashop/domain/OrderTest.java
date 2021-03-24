package jpbook.jpashop.domain;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class OrderTest {
    
    @Autowired
    EntityManager em;
    
    
    @Test
    void entityListenerTest() throws Exception {
        //given
        Order order = new Order();
        
        //when
        em.persist(order);
        
        //then
        System.out.println("order.getOrderDate() = " + order.getOrderDate());
    
    }

}