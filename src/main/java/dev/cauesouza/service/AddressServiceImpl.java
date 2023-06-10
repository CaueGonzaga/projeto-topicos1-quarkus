package dev.cauesouza.service;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import dev.cauesouza.dto.address.AddressDTO;
import dev.cauesouza.dto.address.AddressResponseDTO;
import dev.cauesouza.model.address.Address;
import dev.cauesouza.repository.AddressRepository;
import dev.cauesouza.repository.CityRepository;
import dev.cauesouza.repository.CustomerRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import jakarta.validation.Validator;
import jakarta.ws.rs.NotFoundException;

@ApplicationScoped
public class AddressServiceImpl implements AddressService {

    @Inject
    private CityRepository cityRepository;

    @Inject
    private AddressRepository addressRepository;

    @Inject
    private CustomerRepository customerRepository;

    @Inject
    private Validator validator;

    @Override
    public List<AddressResponseDTO> listAll() {
        List<Address> list = addressRepository.listAll();

        return list.stream().map(
                s -> new AddressResponseDTO(s)).collect(Collectors.toList());
    }

    @Override
    public AddressResponseDTO findById(Long id) {
        Address entity = addressRepository.findById(id);
        if (entity == null)
            throw new NotFoundException("State not found.");
        return new AddressResponseDTO(entity);
    }

    @Override
    public List<AddressResponseDTO> findByStreet(String name) {
        List<Address> list = addressRepository.findByName(name);

        return list.stream().map(
                s -> new AddressResponseDTO(s)).collect(Collectors.toList());
    }

    @Override
    @Transactional
    public AddressResponseDTO persist(AddressDTO receivedDTO) throws ConstraintViolationException {
        validate(receivedDTO);

        Address entity = new Address();
        entity.setAddress(receivedDTO.address());
        entity.setComplement(receivedDTO.complement());
        entity.setCity(cityRepository.findById(receivedDTO.idCity()));
        entity.setCustomer(customerRepository.findById(receivedDTO.idCustomer()));
        addressRepository.persist(entity);

        return new AddressResponseDTO(entity);
    }

    @Override
    @Transactional
    public AddressResponseDTO update(Long id, AddressDTO receivedDTO) throws ConstraintViolationException {
        validate(receivedDTO);

        Address entity = addressRepository.findById(id);
        entity.setAddress(receivedDTO.address());
        entity.setComplement(receivedDTO.complement());
        entity.setCity(cityRepository.findById(receivedDTO.idCity()));
        entity.setCustomer(customerRepository.findById(receivedDTO.idCustomer()));

        return new AddressResponseDTO(entity);
    }

    @Override
    @Transactional
    public void deleteById(Long id) {
        addressRepository.deleteById(id);
    }

    @Override
    public Long count() {
        return addressRepository.count();
    }

    private void validate(AddressDTO entity) throws ConstraintViolationException {
        Set<ConstraintViolation<AddressDTO>> violations = validator.validate(entity);
        if (!violations.isEmpty())
            throw new ConstraintViolationException(violations);
    }

}
