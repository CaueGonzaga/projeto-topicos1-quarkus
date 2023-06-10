package dev.cauesouza.service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import dev.cauesouza.dto.order.OrderDTO;
import dev.cauesouza.dto.order.OrderResponseDTO;
import dev.cauesouza.exception.NotFoundEntityException;
import dev.cauesouza.model.buy.OrderEntity;
import dev.cauesouza.model.buy.OrderedItem;
import dev.cauesouza.model.customer.Customer;
import dev.cauesouza.repository.CustomerRepository;
import dev.cauesouza.repository.OrderRepository;
import dev.cauesouza.repository.OrderedItemRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.validation.ConstraintViolationException;
import jakarta.ws.rs.NotFoundException;

@ApplicationScoped
public class OrderServiceImpl implements OrderService {

    @Inject
    private OrderRepository orderRepository;

    @Inject
    private OrderedItemRepository orderedItemRepository;

    @Inject
    private CustomerRepository customerRepository;

    @Override
    public List<OrderResponseDTO> listAll() {
        List<OrderResponseDTO> list = orderRepository.findAll().stream().map(p -> new OrderResponseDTO(p))
                .collect(Collectors.toList());
        return list;
    }

    @Override
    public OrderResponseDTO persist(OrderDTO receivedDTO) {
        try {
            OrderEntity entity = new OrderEntity();
            Customer clienteEntity = customerRepository.findByIdOptional(receivedDTO.idCustomer())
                    .orElseThrow(NotFoundException::new);

            entity.setCustomer(clienteEntity);
            entity.setOrderedItems(findItensByIds(receivedDTO.idsOrderedItens()));
            entity.setAmount(receivedDTO.amount());
            entity.setTotalPriceOrder(receivedDTO.totalPriceOrder());
            entity.setStatus(receivedDTO.status());
            orderRepository.persist(entity);

            return new OrderResponseDTO(entity);
        } catch (NotFoundException e) {
            throw new NotFoundEntityException("Customer não encontrado");
        }
    }

    @Override
    public OrderResponseDTO update(Long id, OrderDTO receivedDTO) {

        try {
            OrderEntity entity = orderRepository.findByIdOptional(id).orElseThrow(NotFoundException::new);
            Customer clienteEntity = customerRepository.findByIdOptional(receivedDTO.idCustomer())
                    .orElseThrow(NotFoundException::new);

            entity.setCustomer(clienteEntity);
            entity.setOrderedItems(findItensByIds(receivedDTO.idsOrderedItens()));
            entity.setAmount(receivedDTO.amount());
            entity.setTotalPriceOrder(receivedDTO.totalPriceOrder());
            entity.setStatus(receivedDTO.status());
            return new OrderResponseDTO(entity);

        } catch (NotFoundException e) {
            throw new NotFoundEntityException("Recurso nao encontrado");
        } catch (ConstraintViolationException e) {
            throw new ConstraintViolationException("Item pedido associado a outro cliente, ou item nao existente",
                    null);
        }

    }

    @Override
    public void deleteById(Long id) {
        try {
            orderRepository.findByIdOptional(id).orElseThrow(NotFoundException::new);
            orderRepository.deleteById(id);
        } catch (NotFoundException e) {
            throw new NotFoundEntityException("OrderEntity nao encontrado");
        }
    }

    @Override
    public OrderResponseDTO findById(Long id) {

        try {
            OrderEntity entity = orderRepository.findByIdOptional(id).orElseThrow(NotFoundException::new);
            return new OrderResponseDTO(entity);
        } catch (NotFoundException e) {
            throw new NotFoundEntityException("OrderEntity nao encontrado");
        }
    }

    private List<OrderedItem> findItensByIds(List<Long> ids) {
        try {
            List<OrderedItem> itensOrderEntity = new ArrayList<>();
            for (Long id : ids) {
                OrderedItem itemOrderEntity = orderedItemRepository.findByIdOptional(id)
                        .orElseThrow(NotFoundException::new);
                if (itemOrderEntity != null && !itensOrderEntity.contains(itemOrderEntity)) {
                    itensOrderEntity.add(itemOrderEntity);
                }
            }
            return itensOrderEntity;
        } catch (NotFoundException e) {
            throw new NotFoundEntityException("Item pedido não encontrado!");
        }
    }

    @Override
    public Long count() {
        return orderRepository.count();
    }
}