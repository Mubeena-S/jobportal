package com.example.jobportal.service;

import com.example.jobportal.dto.AdminDTO;
import com.example.jobportal.model.Admin;
import com.example.jobportal.repository.AdminRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AdminService {

    private final AdminRepository adminRepository;
    private final PasswordEncoder passwordEncoder;

    // Add new Admin
    public AdminDTO addAdmin(AdminDTO adminDTO, String rawPassword) {
        Admin admin = Admin.builder()
                .name(adminDTO.getName())
                .email(adminDTO.getEmail())
                .password(passwordEncoder.encode(rawPassword)) 
                .role(adminDTO.getRole())
                .active(adminDTO.isActive())
                .build();

        Admin saved = adminRepository.save(admin);
        return mapToDTO(saved);
    }

    //  Get all Admins
    public List<AdminDTO> getAllAdmins() {
        return adminRepository.findAll()
                .stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }

    // Get Admin by ID
    public Optional<AdminDTO> getAdminById(Long id) {
        return adminRepository.findById(id).map(this::mapToDTO);
    }

    // Update Admin
    public AdminDTO updateAdmin(Long id, AdminDTO updatedAdmin) {
        return adminRepository.findById(id)
                .map(admin -> {
                    admin.setName(updatedAdmin.getName());
                    admin.setEmail(updatedAdmin.getEmail());
                    admin.setRole(updatedAdmin.getRole());
                    admin.setActive(updatedAdmin.isActive());
                    Admin saved = adminRepository.save(admin);
                    return mapToDTO(saved);
                })
                .orElseThrow(() -> new RuntimeException("Admin not found with id: " + id));
    }

    // Delete Admin
    public void deleteAdmin(Long id) {
        adminRepository.deleteById(id);
    }

    // Map Entity - DTO
    private AdminDTO mapToDTO(Admin admin) {
        return AdminDTO.builder()
                .id(admin.getId())
                .name(admin.getName())
                .email(admin.getEmail())
                .role(admin.getRole())
                .active(admin.isActive())
                .build();
    }
}
