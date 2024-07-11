package com.Broadcomapp.BLogic.repository;

import com.Broadcomapp.BLogic.beans.Address;
import com.Broadcomapp.BLogic.beans.BroadUser;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
class BroadUserRepositoryTest {

    @Autowired
    private  BroadUserRepository broadUserRepository;
    @Autowired
    private AddressRepository addressRepository;

    @Test
    public  void getAllBroadUsers(){
      List<BroadUser> broadUserList= broadUserRepository.findAll();
      for(BroadUser brd:broadUserList){
          System.out.println(brd);
          System.out.println(brd.getAddress());
      }
    }

    @Test
    public  void getById(){
        BroadUser broadUser= broadUserRepository.findById(10l).get();
        System.out.println(broadUser);
        System.out.println(broadUser.getAddress());
    }

    @Test
    public void testBroadSave(){
        Address address=Address.builder().city("Pune").state("MH").street("ABC").zipCode("1231").build();
        BroadUser user=BroadUser.builder().email("abc@gmail.com").name("userName").phoneNumber("12423").whatsAppId("123323@whatsapp.com").address(address).build();
        broadUserRepository.save(user);
    }
}