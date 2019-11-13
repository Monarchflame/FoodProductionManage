package cn.qxt.service;

import cn.qxt.dao.ClientDao;
import cn.qxt.pojo.Client;
import cn.qxt.service.AService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
public class AServiceImpl implements AService {

    @Autowired
    ClientDao clientDao;

    @Transactional(propagation= Propagation.REQUIRED,rollbackFor=Exception.class)
    public void addTwo() {
        Client c1 = new Client();
        c1.setId("3");
        c1.setName("短的名字");
        c1.setPassword("123456");
        c1.setCreditrating(1);
        c1.setType("零售商");
        c1.setPhone("15898955721");
        c1.setAddress("山东省");
        clientDao.insert(c1);

        Client c2 = new Client();
        c2.setId("4");
        c2.setName("名字长对应字段放不下,名字长对应字段放不下,名字长对应字段放不下,名字长对应字段放不下,名字长对应字段放不下,名字长对应字段放不下,名字长对应字段放不下,名字长对应字段放不下,");
        c2.setPassword("123456");
        c2.setCreditrating(2);
        c2.setType("零售商");
        c2.setPhone("15898955721");
        c2.setAddress("山东省");
        clientDao.insert(c2);
    };
}
