package Users.controller;

import Users.model.User;
import Users.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.Map;

@Controller
public class UserController {
    private UserService userService;

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @RequestMapping("/")
    public ModelAndView home() {
        List<User> users = userService.allUsers();
        ModelAndView mav = new ModelAndView("index");
        mav.addObject("userList", users);
        return mav;
    }
    @RequestMapping("/new")
    public String newUser(Map<String, Object> model) {
        User user = new User();
        model.put("user", user);
        return "newUser";
    }
    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public String saveUser(@ModelAttribute("user") User user) {
        userService.add(user);
        return "redirect:/";
    }
    @RequestMapping("/edit")
    public ModelAndView editUser(@RequestParam int id) {
        ModelAndView mav = new ModelAndView("editUser");
        User user = userService.getById(id);
        mav.addObject("user", user);

        return mav;
    }
        @RequestMapping(value = "/edit", method = RequestMethod.POST)
    public ModelAndView editUserId(@ModelAttribute("id") int id, User user) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("redirect:/");
        userService.edit(id,user);
        return modelAndView;
    }
    @RequestMapping("/delete")
    public String deleteUser(@RequestParam int id) {
        userService.delete(id);
        return "redirect:/";
    }
}
