package de.PogChampIsAlreadyTaken.Papaya.Webshop.Services;


import de.PogChampIsAlreadyTaken.Papaya.Webshop.Baseclasses.Address;

import javax.enterprise.context.ApplicationScoped;
import javax.transaction.Transactional;

@ApplicationScoped
public class AddressService {


    @Transactional
    public void createAddress(Address address) {
        address.persist();
    }

    public Address findAddress(int id) {
        return Address.findById(id);
    }
}
