package backend.demo.Service;

import org.springframework.stereotype.Service;

import backend.demo.DTO.orderDTO;
import backend.demo.Model.orDers;

@Service
public class orderService {

    private final orderDTO orderRepository;

    public orderService(orderDTO orderRepository) {
        this.orderRepository = orderRepository;
    }

    // Phương thức tạo đơn hàng
    public int createOrder(orDers order) {
        return orderRepository.addOrder(order);
    }

    public int createOrder(String orderID, int userID, String nameUser, String addressU, String phone, String email, 
    double totalAmount, int status, int payment){
        return orderRepository.addOrder(orderID, userID, nameUser, addressU, phone, email, totalAmount, status, payment);
    }
    // Phương thức cập nhật đơn hàng
    public int updateOrder(orDers order) {
        return orderRepository.updateOrder(order);
    }
}
