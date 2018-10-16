/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package medisam.controller;

import medisam.model.UserRepository;
import medisam.model.User;
import java.security.Principal;
import java.util.List;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

/**
 *
 * @author jm
 */
@Controller
//@ComponentScan("entity")
public class MainController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
//    private UserValidator userValidator;

    @GetMapping("/admin")
    ModelAndView admin() {
        ModelAndView mv = new ModelAndView("admin");
        List<User> users = userRepository.findAll();
        mv.addObject("users", users);
        return mv;
    }

    @GetMapping(path = "/admin/user/new")
    String newUser(Model model) {
        model.addAttribute("user", new User());
        return "admin/user/new";
    }

    @PostMapping(path = "/admin/user/add")
    public ModelAndView submitUser(@ModelAttribute("user") @Valid User user, BindingResult bindingResult/*, Model model*/) {
//        userValidator.validate(user, bindingResult);
        ModelAndView mv;
        if (bindingResult.hasErrors()) {
            mv = new ModelAndView("admin/user/new", "user", user);
            return mv;
//            return "admin/user/new";
        }

//        userValidator.validate(userForm, bindingResult);
        //IN CASE WE INCORPORATE A REGISTER FORM
//        securityService.autologin(userForm.getUsername(), userForm.getPasswordConfirm());
//        userService.save(user);
        userRepository.save(user);

//        securityService.autologin(user.getUsername(), user.getPasswordConfirm());
//        return "redirect:/admin";
        mv = new ModelAndView("admin");
        return mv;
    }

//    String submitUser(@ModelAttribute User user) {
//        System.out.println(user.getId());
//        System.out.println(user.getName());
//        System.out.println(user.getLastname());
//        userRepository.save(user);
//        return "admin";
//    }
//    @PostMapping(value = "/users")
//    public String saveOrUpdateUser(@ModelAttribute("userForm") @Validated User user,
//            BindingResult result, Model model,
//            final RedirectAttributes redirectAttributes) {
//
//        if (result.hasErrors()) {
//            populateDefaultModel(model);
//            return "users/userform";
//        } else {
//
//            // Add message to flash scope
//            redirectAttributes.addFlashAttribute("css", "success");
//            if (user.isNew()) {
//                redirectAttributes.addFlashAttribute("msg", "User added successfully!");
//            } else {
//                redirectAttributes.addFlashAttribute("msg", "User updated successfully!");
//            }
//
//            userService.saveOrUpdate(user);
//
//            // POST/REDIRECT/GET
//            return "redirect:/users/" + user.getId();
//
//            // POST/FORWARD/GET
//            // return "user/list";
//        }
//
//    }
//    @PostMapping(path = "/admin/user/add")
////    public @ResponseBody
//    String addNewUser(@RequestParam String name,
//            @RequestParam String email) {
//        // @ResponseBody means the returned String is the response, not a view name
//        // @RequestParam means it is a parameter from the GET or POST request
//
//        User n = new User();
//        n.setName(name);
//        n.setEmail(email);
//        userRepository.save(n);
//        return "Saved";
//    }
//    @GetMapping(path = "/all")
//    public @ResponseBody
//    Iterable<User> getAllUsers() {
    // This returns a JSON or XML with the users
//        return userRepository.findAll();
//    }
//    @GetMapping("/**")
//    String request2(HttpServletRequest request, Model model) {
//        Authentication auth = SecurityContextHolder.getContext()
//                .getAuthentication();
//
//        ModelAndView mv = new ModelAndView("home");
//        mv.addObject("uri", request.getRequestURI())
//                .addObject("user", auth.getName())
//                .addObject("roles", auth.getAuthorities());
//        model.addAttribute("uri", request.getRequestURI())
//                .addAttribute("user", auth.getName())
//                .addAttribute("roles", auth.getAuthorities());
//
////        return "jsp/jsp";
//        return "html";
//    }
//    @GetMapping("/pharmacies")
//    public ModelAndView getPharmacies() {
//        System.out.println("phphphphphphphphphphphphphphphphphphphphphphphphphphphphphphphphphphphph");
//
//        RestTemplate restTemplate = new RestTemplate();
//        ResponseEntity<String> response = restTemplate.getForEntity(
//                "https://places.cit.api.here.com/places/v1/browse?app_id=vN0kw8ux8JRySeLEQ5Wk&app_code=LeV2hC1Gzg4vvpMsk5AJHg&in=52.521,13.3807;r=2000&cat=petrol-station&pretty&Accept=application/json",
//                String.class);
//        System.out.println(response.getStatusCode());
//        System.out.println(response.getBody());
//
//        ModelAndView mv = new ModelAndView("pharmacies");
//        mv.addObject("message", response.getStatusCode());
//        return mv;
//    }
//
//    @GetMapping("/layout")
//    public ModelAndView test() {
//        System.out.println("testtesttesttesttesttesttesttesttest");
//
//        ModelAndView mv = new ModelAndView("layout");
//        mv.addObject("message", "layoutd");
//        return mv;
//    }
//    @GetMapping("/pharmacies")
//    public ModelAndView getPharmacies() {
//        System.out.println("phphphphphphphphphphphphphphphphphphphphphphphphphphphphphphphphphphphph");
//
//        RestTemplate restTemplate = new RestTemplate();
////        ServiceResponse sr = restTemplate.getForObject("http://dev.virtualearth.net/REST/v1/Locations?query=pharmacy&includeNeighborhood=1&include=queryParse&maxResults=3&key=Aq7AyhBN8ISU1IYNJVHA3jdkoTaZo887AirwaHuOYd8EXBB0eHzfAs1iDxV1ge6U",
//        ServiceResponse sr = restTemplate.getForObject("https://places.cit.api.here.com/places/v1/autosuggest?q=farmacia&in=18.8576%2C-97.097%3Br%3D5000&app_id=vN0kw8ux8JRySeLEQ5Wk&app_code=LeV2hC1Gzg4vvpMsk5AJHg",
//                ServiceResponse.class);
//
//        System.out.println(sr.getStatusDescription());
//        System.out.println(sr.getAuthenticationResultCode());
//
//        ModelAndView mv = new ModelAndView("pharmacies");
//        mv.addObject("message", sr.getBrandLogoUri());
//        return mv;
//    }
}
