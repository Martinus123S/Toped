package del.ac.id.demo.controller;

import del.ac.id.demo.model.Item;
import del.ac.id.demo.model.Store;
import del.ac.id.demo.repository.StoreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;


@RestController
public class StoreController {
    @Autowired
    StoreRepository storeRepository;

    @Autowired
    MongoTemplate mongoTemplate;

    @RequestMapping("/produk")
    public ModelAndView getProduk(){
        List<Store> stores = storeRepository.findAll();
        ModelAndView mv = new ModelAndView("store");
        mv.addObject("stores",stores);
        return mv;
    }

}
