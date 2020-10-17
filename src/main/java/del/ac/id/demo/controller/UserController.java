package del.ac.id.demo.controller;

import del.ac.id.demo.model.User;
import del.ac.id.demo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


@RestController
public class UserController {
    @Autowired
    UserRepository userRepository;

    @Autowired
    MongoTemplate mongoTemplate;
    @GetMapping("/")
    public ModelAndView index(){
        ModelAndView mv = new ModelAndView("index");
        mv.addObject("user",new User());
        return mv;
    }
    @PostMapping("/register")
    public ModelAndView register(User user, HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse,
                                 @ModelAttribute("passwordConfirmation") String passwordConfirmation,RedirectAttributes redirectAttributes){
        Query query= new Query(Criteria.where("username").is(user.getUsername()).and("password").is(user.getPassword()));
        User user1 = mongoTemplate.findOne(query,User.class);
        if(user.getPassword().equals(passwordConfirmation) ){
            System.out.println("benar");
            if(user1 == null){
                user.setRole(2);
                HttpSession session = httpServletRequest.getSession();
                session.setAttribute("user",user);
                mongoTemplate.insert(user);
                redirectAttributes.addFlashAttribute("isRegister","Berhasil Mendaftar");
                return new ModelAndView("redirect:/");
            }

            redirectAttributes.addFlashAttribute("notRegister","User sudah ada silahkan login");

            return new ModelAndView("redirect:/");
        }
        redirectAttributes.addFlashAttribute("notMatch","Password tidak sama");
        return new ModelAndView("redirect:/");
    }
    @PostMapping("/login")
    public ModelAndView login(User user, HttpServletRequest httpServletRequest,HttpServletResponse httpServletResponse
            ,RedirectAttributes redirectAttributes){
        Query query = new Query(Criteria.where("username").is(user.getUsername()).and("password").is(user.getPassword()));
        User user1 = mongoTemplate.findOne(query,User.class);
        if(user1!=null){
            HttpSession session = httpServletRequest.getSession();
            session.setAttribute("user",user1);
            return new ModelAndView("redirect:/item");
        }
        redirectAttributes.addFlashAttribute("notVerify","User tidak ditemukan");
        return new ModelAndView("redirect:/");
    }
    @GetMapping("/logout")
    public ModelAndView logout(HttpServletRequest httpServletRequest,HttpServletResponse httpServletResponse){
        httpServletRequest.getSession().removeAttribute("user");
        return new ModelAndView("redirect:/");
    }
}
