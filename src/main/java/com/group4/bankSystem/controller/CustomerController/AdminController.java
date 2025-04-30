package com.group4.bankSystem.controller.CustomerController;

import com.group4.bankSystem.entities.CustomerEntities.Admin;
import com.group4.bankSystem.entities.CustomerEntities.Customer;
import com.group4.bankSystem.services.CustomerServices.AdminService;
import com.group4.bankSystem.services.CustomerServices.CustomerService;
import jakarta.servlet.http.HttpSession;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class AdminController {

    private final AdminService adminService;
    private final CustomerService customerService;

    public AdminController(AdminService adminService, CustomerService customerService) {
        this.adminService = adminService;
        this.customerService = customerService;
    }

    // -------------------- HTML PAGES --------------------

    @GetMapping("/admin/login")
    public String loginPage() {
        return "admin-login";
    }

    @GetMapping("/admin/register")
    public String registerPage(Model model) {
        model.addAttribute("admin", new Admin());
        return "admin-register";
    }

    @GetMapping("/admin/dashboard")
    public String dashboard(HttpSession session, Model model) {
        Admin admin = (Admin) session.getAttribute("admin");
        if (admin == null) return "redirect:/admin/login";

        model.addAttribute("admin", admin);
        return "admin-dashboard";
    }

    @GetMapping("/admin/customers")
    public String allCustomers(HttpSession session, Model model) {
        Admin admin = (Admin) session.getAttribute("admin");
        if (admin == null) return "redirect:/admin/login";

        model.addAttribute("customers", List.of()); // Sayfa yüklenecek, veri JS ile gelecek
        return "admin-customers";
    }

    // -------------------- API ENDPOINTS --------------------

    @PostMapping("/api/admins/register")
    @ResponseBody
    public ResponseEntity<String> apiRegister(@RequestBody Admin admin) {
        boolean saved = adminService.saveAdmin(admin);
        if (saved) {
            return ResponseEntity.ok("Kayıt başarılı.");
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Kullanıcı adı zaten var.");
        }
    }

    @PostMapping("/api/admins/login")
    @ResponseBody
    public ResponseEntity<String> apiLogin(@RequestBody Admin admin, HttpSession session) {
        Admin existing = adminService.login(admin.getUsername(), admin.getPasswordHash());
        if (existing != null) {
            session.setAttribute("admin", existing);
            return ResponseEntity.ok("Giriş başarılı.");
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Hatalı giriş.");
        }
    }

    @GetMapping("/api/admins/me")
    @ResponseBody
    public ResponseEntity<Admin> getLoggedInAdmin(HttpSession session) {
        Admin admin = (Admin) session.getAttribute("admin");
        if (admin == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
        return ResponseEntity.ok(admin);
    }

    @GetMapping("/api/customers")
    @ResponseBody
    public ResponseEntity<List<Customer>> getAllCustomers(HttpSession session) {
        Admin admin = (Admin) session.getAttribute("admin");
        if (admin == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
        List<Customer> customers = customerService.getAllCustomers();
        return ResponseEntity.ok(customers);
    }
}
