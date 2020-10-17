package del.ac.id.demo.controller;

import del.ac.id.demo.model.Item;
import del.ac.id.demo.model.ItemDetail;
import del.ac.id.demo.model.User;
import del.ac.id.demo.repository.ItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Optional;

@RestController
public class ItemController {
    @Autowired
    ItemRepository itemRepository;
    @Autowired MongoTemplate mongoTemplate;
    @RequestMapping("/item")
    public ModelAndView item(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse){
        if(httpServletRequest.getSession().getAttribute("user")==null){
            return new ModelAndView("redirect:/");
        }
        List<Item> items = itemRepository.findAll();
        ModelAndView mv = new ModelAndView("item");
        mv.addObject("items",items);
        return mv;
    }
    @RequestMapping("/getOne")
    @ResponseBody
    public Optional<Item> getOne(String id){
//        Optional<Item> item = itemRepository.findById(id);
        Query query = new Query(Criteria.where("id").is(id));
        Item item = mongoTemplate.findOne(query,Item.class);
        if(item!=null){
            Update update = new Update().inc("seen",1);
            mongoTemplate.updateFirst(query,update,Item.class);
        }
        return itemRepository.findById(id);
    }
    @RequestMapping(value = "/update",method = {RequestMethod.PUT,RequestMethod.GET})
    public ModelAndView update(Item item, ItemDetail itemDetail,
                               RedirectAttributes redirectAttributes, HttpSession response){
        Query query = new Query(Criteria.where("id").is(item.getId()));
        Item item1 = mongoTemplate.findOne(query,Item.class);
        if(item1 != null){
            User user = (User)response.getAttribute("user");
            if( user.getRole() == 1){
//                System.out.println(itemDetail.getCategory());

                Update update = new Update();
                update.set("stock",item.getStock()+item1.getStock());
                update.set("item_detail.category",itemDetail.getCategory());
                update.set("item_detail.weight",itemDetail.getWeight());
                mongoTemplate.updateFirst(query,update,Item.class);
                return new ModelAndView("redirect:/item");
            }
            Update update = new Update();
            Double isStock = (item1.getStock()-item.getStock());
            Double rating = (item1.getRating()+item.getRating())/2;
            Double sold = item1.getSold()+item.getStock();
            if(isStock >= 0){
                update.set("stock",isStock);
                update.set("rating",rating);
                update.set("sold",sold);
                mongoTemplate.updateFirst(query,update,Item.class);
                return new ModelAndView("redirect:/item");
            }
            redirectAttributes.addFlashAttribute("Stock","Stock Kurang Harap menunggu barang update");
            return new ModelAndView("redirect:/item");
        }
        return new ModelAndView("redirect:/item");
    }
    @GetMapping("/add")
    public ModelAndView add(HttpServletResponse httpServletResponse,HttpServletRequest httpServletRequest){
        return new ModelAndView("add");
    }
    @PostMapping("/add")
    public String save(@ModelAttribute Item item){
//        item.setItemDetail(itemDetail);
//        ItemDetail itemDetail = new ItemDetail();
//        itemDetail.setCondition(condition);
//        itemDetail.setCategory(category);
//        itemDetail.setWeight(weight);
//        item.setItemDetail(itemDetail);
//        System.out.println(condition);
//        System.out.println(weight);
//        System.out.println(category);

        mongoTemplate.save(item);
//        mongoTemplate.insert(item);
        return "Hallo";
//        return new ModelAndView("redirect:/item");
    }
}
