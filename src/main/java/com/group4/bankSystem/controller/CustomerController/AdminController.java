package com.group4.bankSystem.controller.CustomerController;

import com.group4.bankSystem.dto.AdminLoginRequest;
import com.group4.bankSystem.dto.AdminRegisterRequest;
import com.group4.bankSystem.entities.CustomerEntities.Admin;
import com.group4.bankSystem.entities.CustomerEntities.Customer;
import com.group4.bankSystem.services.CustomerServices.AdminService;
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

    public AdminController(AdminService adminService) {
        this.adminService = adminService;
    }

    // HTML Pages
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
        model.addAttribute("customers", List.of()); // JS ile yüklenecek
        return "admin-customers";
    }

    // API Endpoints
    @PostMapping("/api/admins/register")
    @ResponseBody
    public ResponseEntity<String> apiRegister(@RequestBody AdminRegisterRequest request) {
      try {
          Admin admin = new Admin(request.getUsername(), request.getPassword());
          boolean saved = adminService.saveAdmin(admin, request.getConfirmPassword());
          if (saved) return ResponseEntity.ok("Kayıt başarılı.");
          else return ResponseEntity.badRequest().body("Kullanıcı adı zaten var.");
      } catch (IllegalArgumentException e) {
          return ResponseEntity.badRequest().body(e.getMessage());
      }
  }

    @PostMapping("/api/admins/login")
    @ResponseBody
    public ResponseEntity<String> apiLogin(@RequestBody AdminLoginRequest request, HttpSession session) {
      Admin admin = adminService.login(request.getUsername(), request.getPassword());
      if (admin != null) {
          session.setAttribute("admin", admin);
          return ResponseEntity.ok("Giriş başarılı.");
      } else {
          return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Hatalı kullanıcı adı veya şifre.");
      }
  }


    @GetMapping("/api/admins/me")
    @ResponseBody
    public ResponseEntity<Admin> getLoggedInAdmin(HttpSession session) {
        Admin admin = (Admin) session.getAttribute("admin");
        if (admin == null) return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        return ResponseEntity.ok(admin);
    }

    @GetMapping("/api/customers")
    @ResponseBody
    public ResponseEntity<List<Customer>> getAllCustomers(HttpSession session) {
        Admin admin = (Admin) session.getAttribute("admin");
        if (admin == null) return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();

        return ResponseEntity.ok(adminService.getAllCustomers());
    }

    @DeleteMapping("/api/customers/{id}")
    @ResponseBody
    public ResponseEntity<String> deleteCustomer(@PathVariable Integer id, HttpSession session) {
        Admin admin = (Admin) session.getAttribute("admin");
        if (admin == null) return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();

        try {
            adminService.deleteCustomerAndRelatedData(id);
            return ResponseEntity.ok("Müşteri ve ilişkili veriler silindi.");
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Müşteri bulunamadı.");
        }
    }
}
