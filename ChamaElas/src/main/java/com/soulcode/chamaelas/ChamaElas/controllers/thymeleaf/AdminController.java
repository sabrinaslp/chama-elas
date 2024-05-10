package com.soulcode.chamaelas.ChamaElas.controllers.thymeleaf;
import com.soulcode.chamaelas.ChamaElas.models.dto.AdminDTO;
import com.soulcode.chamaelas.ChamaElas.models.dto.ChamadoDTO;
import com.soulcode.chamaelas.ChamaElas.services.AdminService;
import org.springframework.ui.Model;
import com.soulcode.chamaelas.ChamaElas.repositories.AdminRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class AdminController {

    @Autowired
    private AdminRepository adminRepository;

    @Autowired
    private AdminService adminService;

}