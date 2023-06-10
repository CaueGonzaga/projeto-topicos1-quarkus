package dev.cauesouza.service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import jakarta.validation.Validator;
import jakarta.ws.rs.NotFoundException;
import dev.cauesouza.dto.customer.CustomerDTO;
import dev.cauesouza.dto.customer.CustomerResponseDTO;
import dev.cauesouza.model.customer.Customer;
import dev.cauesouza.repository.CustomerRepository;
import dev.cauesouza.repository.UserRepository;

@ApplicationScoped
public class CustomerServiceImpl implements CustomerService {

    @Inject
    private CustomerRepository customerRepository;

    @Inject
    private UserRepository userRepository;

    @Inject
    private Validator validator;

    @Override
    public List<CustomerResponseDTO> listAll() {
        List<Customer> list = customerRepository.listAll();

        return list.stream().map(
                s -> new CustomerResponseDTO(s)).collect(Collectors.toList());
    }

    @Override
    public CustomerResponseDTO findById(Long id) {
        Customer entity = customerRepository.findById(id);
        if (entity == null)
            throw new NotFoundException("State not found.");
        return new CustomerResponseDTO(entity);
    }

    @Override
    public List<CustomerResponseDTO> findByName(String name) {
        List<Customer> list = customerRepository.findByName(name);

        return list.stream().map(
                s -> new CustomerResponseDTO(s)).collect(Collectors.toList());
    }

    @Override
    @Transactional
    public CustomerResponseDTO persist(CustomerDTO receivedDTO) throws ConstraintViolationException {
        validate(receivedDTO);

        Customer entity = new Customer();
        entity.setFirstName(receivedDTO.firstName());
        entity.setLastName(receivedDTO.lastName());
        entity.setAge(receivedDTO.age());
        entity.setGender(receivedDTO.gender());
        entity.setBirthday(convertStringToDate(receivedDTO.birthday()));
        entity.setUser(userRepository.findById(receivedDTO.idUser()));

        customerRepository.persist(entity);

        return new CustomerResponseDTO(entity);
    }

    @Override
    @Transactional
    public CustomerResponseDTO update(Long id, CustomerDTO receivedDTO) throws ConstraintViolationException {
        validate(receivedDTO);

        Customer entity = new Customer();
        entity.setFirstName(receivedDTO.firstName());
        entity.setLastName(receivedDTO.lastName());
        entity.setAge(receivedDTO.age());
        entity.setGender(receivedDTO.gender());
        entity.setBirthday(convertStringToDate(receivedDTO.birthday()));
        entity.setUser(userRepository.findById(receivedDTO.idUser()));

        return new CustomerResponseDTO(entity);
    }

    @Override
    @Transactional
    public void deleteById(Long id) {
        customerRepository.deleteById(id);
    }

    @Override
    public Long count() {
        return customerRepository.count();
    }

    private void validate(CustomerDTO entity) throws ConstraintViolationException {
        Set<ConstraintViolation<CustomerDTO>> violations = validator.validate(entity);
        if (!violations.isEmpty())
            throw new ConstraintViolationException(violations);
    }

    public LocalDateTime convertStringToDate(String date) {
        DateTimeFormatter parser = DateTimeFormatter.ofPattern("dd/MM/uuuu");
        LocalDateTime dateTime = LocalDate.parse(date, parser).atStartOfDay();
        return dateTime;
    }

}
