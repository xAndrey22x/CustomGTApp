package com.customGTApp.data.implJpaRepo;

import com.customGTApp.data.OrderOptionsContract;
import com.customGTApp.model.OrderOptions;
import com.customGTApp.repository.OrderOptionsRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class OrderOptionsData implements OrderOptionsContract {

    private final OrderOptionsRepo orderOptionsRepo;

    @Autowired
    public OrderOptionsData(OrderOptionsRepo orderOptionsRepo) {
        this.orderOptionsRepo = orderOptionsRepo;
    }

    @Override
    public List<OrderOptions> findAll() {
        return this.orderOptionsRepo.findAll();
    }

    @Override
    public Optional<OrderOptions> findById(Long id) {
        return this.orderOptionsRepo.findById(id);
    }

    @Override
    public OrderOptions save(OrderOptions orderOptions) {
        return this.orderOptionsRepo.save(orderOptions);
    }

    @Override
    public void deleteById(Long id) {
        this.orderOptionsRepo.deleteById(id);
    }

    @Override
    public Optional<OrderOptions> findByOrderClientId(Long orderClientId) {
        return this.orderOptionsRepo.findByOrderClientId(orderClientId);
    }

}
