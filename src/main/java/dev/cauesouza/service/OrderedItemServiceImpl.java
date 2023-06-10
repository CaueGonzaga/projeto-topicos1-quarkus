package dev.cauesouza.service;

import java.util.List;
import java.util.stream.Collectors;

import dev.cauesouza.dto.ordered_item.OrderedItemDTO;
import dev.cauesouza.dto.ordered_item.OrderedItemResponseDTO;
import dev.cauesouza.exception.NotFoundEntityException;
import dev.cauesouza.model.buy.OrderedItem;
import dev.cauesouza.model.product.Product;
import dev.cauesouza.repository.CustomerRepository;
import dev.cauesouza.repository.OrderedItemRepository;
import dev.cauesouza.repository.ProductRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.validation.ConstraintViolationException;
import jakarta.ws.rs.NotFoundException;

@ApplicationScoped
public class OrderedItemServiceImpl implements OrderedItemService {

    @Inject
    private ProductRepository productRepository;

    @Inject
    private OrderedItemRepository orderedItemRepository;

    @Inject
    private CustomerRepository customerRepository;

    @Override
    public List<OrderedItemResponseDTO> listAll() {
        List<OrderedItemResponseDTO> list = orderedItemRepository.findAll().stream()
                .map(o -> new OrderedItemResponseDTO(o)).collect(Collectors.toList());
        return list;
    }

    @Override
    public OrderedItemResponseDTO persist(OrderedItemDTO receivedDTO) throws ConstraintViolationException {
        try {
            OrderedItem entity = new OrderedItem();
            Product produtoEntity = productRepository.findByIdOptional(receivedDTO.idProduct())
                    .orElseThrow(NotFoundException::new);
            entity.setProduct(produtoEntity);
            entity.setAmount(receivedDTO.amount());
            entity.setPrice(produtoEntity.getPrice() * receivedDTO.amount());
            orderedItemRepository.persist(entity);
            return new OrderedItemResponseDTO(entity);
        } catch (ConstraintViolationException e) {
            throw new ConstraintViolationException("Product nao encontrado", null);
        }
    }

    @Override
    public OrderedItemResponseDTO update(Long id, OrderedItemDTO receivedDTO) {
        try {
            OrderedItem entity = orderedItemRepository.findByIdOptional(id).orElseThrow(NotFoundException::new);
            Product produtoEntity = productRepository.findByIdOptional(receivedDTO.idProduct())
                    .orElseThrow(NotFoundException::new);
            entity.setProduct(produtoEntity);
            entity.setAmount(receivedDTO.amount());
            entity.setPrice(produtoEntity.getPrice() * receivedDTO.amount());
            return new OrderedItemResponseDTO(entity);
        } catch (NotFoundException e) {
            throw new NotFoundEntityException("Recurso nao encontrado");
        }
    }

    @Override
    public void deleteById(Long id) throws ConstraintViolationException {
        try {
            orderedItemRepository.findByIdOptional(id).orElseThrow(NotFoundException::new);
            orderedItemRepository.deleteById(id);
        } catch (NotFoundException e) {
            throw new NotFoundEntityException("Item pedido nao encontrado");
        }
    }

    @Override
    public OrderedItemResponseDTO findById(Long id) {
        try {
            OrderedItem entity = orderedItemRepository.findByIdOptional(id).orElseThrow(NotFoundException::new);
            return new OrderedItemResponseDTO(entity);
        } catch (NotFoundException e) {
            throw new NotFoundEntityException("Item pedido não encontrado.");
        }
    }

    @Override
    public Long count() {
        return orderedItemRepository.count();
    }
}