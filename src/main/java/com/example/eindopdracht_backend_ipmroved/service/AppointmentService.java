package com.example.eindopdracht_backend_ipmroved.service;

import com.example.eindopdracht_backend_ipmroved.exceptions.AppException;
import com.example.eindopdracht_backend_ipmroved.models.Appointment;
import com.example.eindopdracht_backend_ipmroved.models.User;
import com.example.eindopdracht_backend_ipmroved.models.requests.CreateAppointmentRequest;
import com.example.eindopdracht_backend_ipmroved.models.requests.UpdateAppointmentRequest;
import com.example.eindopdracht_backend_ipmroved.models.responses.AppointmentResponse;
import com.example.eindopdracht_backend_ipmroved.repository.AppointmentRepository;
import com.example.eindopdracht_backend_ipmroved.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.Base64;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class AppointmentService {

    @Autowired
    private AppointmentRepository appointmentRepository;

    @Autowired
    private UserRepository userRepository;

    public List<AppointmentResponse> getAppointmentsForUser(String username) {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new AppException("User not found", HttpStatus.NOT_FOUND));
        List<Appointment> appointments = appointmentRepository.findByUser(user);
        return appointments.stream()
                .map(appointment -> AppointmentResponse.from(appointment, user))
                .collect(Collectors.toList());
    }

    public AppointmentResponse createAppointment(String username, CreateAppointmentRequest request) {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new AppException("User not found", HttpStatus.NOT_FOUND));
        Appointment appointment = Appointment.builder()
                .bicycle_name(request.getBicycleName())
                .description(request.getDescription())
                .date_time(request.getDateTime())
                .user(user)
                .build();
        if (request.getAttachment() != null && !request.getAttachment().isEmpty()) {
            appointment.setAttachment(Base64.getDecoder().decode(request.getAttachment()));
        }
        Appointment savedAppointment = appointmentRepository.save(appointment);
        return AppointmentResponse.from(savedAppointment, user);
    }

    public AppointmentResponse updateAppointment(String username, int id, UpdateAppointmentRequest request) {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new AppException("User not found", HttpStatus.NOT_FOUND));
        Appointment appointment = appointmentRepository.findByIdAndUser(id, user)
                .orElseThrow(() -> new AppException("Appointment not found", HttpStatus.NOT_FOUND));
        appointment.setBicycle_name(request.getBicycleName());
        appointment.setDescription(request.getDescription());
        appointment.setDate_time(request.getDateTime());
        if (request.getAttachment() != null && !request.getAttachment().isEmpty()) {
            appointment.setAttachment(Base64.getDecoder().decode(request.getAttachment()));
        }
        Appointment updatedAppointment = appointmentRepository.save(appointment);
        return AppointmentResponse.from(updatedAppointment, user);
    }

    public void deleteAppointment(String username, int id) {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new AppException("User not found", HttpStatus.NOT_FOUND));
        Appointment appointment = appointmentRepository.findByIdAndUser(id, user)
                .orElseThrow(() -> new AppException("Appointment not found", HttpStatus.NOT_FOUND));
        appointmentRepository.delete(appointment);
    }
}
