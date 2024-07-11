package com.Broadcomapp.user.repository;

import com.Broadcomapp.user.beans.Address;
import com.Broadcomapp.user.beans.BroadUser;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
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
    public void testBroadSave(){
        Address address=Address.builder().city("Pune").state("MH").street("ABC").zipCode("1231").build();
        BroadUser user=BroadUser.builder().fbId("fb@Id").email("abc@gmail.com").name("userName").instagraid("insta@insta.com").phoneNumber("12423").telegramid("234@telegram.com").whatsappid("123323@whatsapp.com").address(address).build();
        broadUserRepository.save(user);
    }
}