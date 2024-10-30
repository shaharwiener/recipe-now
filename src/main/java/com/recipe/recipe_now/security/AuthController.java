//package com.recipe.recipe_now.security;
//
//import com.recipe.recipe_now.user.User;
//import com.recipe.recipe_now.user.UserService;
//import org.springframework.stereotype.Controller;
//import org.springframework.ui.Model;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.ModelAttribute;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestParam;
//
//@Controller
//public class AuthController {
//
//    private final UserService userService;
//
//    public AuthController(UserService userService) {
//        this.userService = userService;
//    }
//
//    @GetMapping("/signup")
//    public String showSignupForm() {
//        return "signup";
//    }
//
//    @PostMapping("/signup")
//    public String registerUser(@ModelAttribute User user) {
//        userService.registerUser(user);
//        return "redirect:/signin";
//    }
//
//    @GetMapping("/signin")
//    public String showSigninForm() {
//        return "signin";
//    }
//
//    @PostMapping("/signin")
//    public String authenticateUser(@RequestParam String email, @RequestParam String password, Model model) {
//        User user = userService.findByEmail(email);
//        if (user != null && user.getPassword().equals(password)) {
//            // Successful login
//            return "redirect:/dashboard"; // or wherever you want to redirect after login
//        }
//        model.addAttribute("error", "Invalid email or password");
//        return "signin";
//    }
//}
//
