package com.myntra.order.controller;

import com.myntra.order.model.BookTable;
import com.myntra.order.repository.ReservationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/bookTable")
public class ReservationController {

    public String reservationStatus = "";

    @Autowired
    private ReservationRepository reservationRepository;

    @GetMapping
    public String displayReservationpage(Model model) {
        model.addAttribute("reservationStatus", reservationStatus);
        return "/reservation.html";
    }

    @PostMapping
    public String bookTable(@ModelAttribute("bookTable") BookTable bookTable, BindingResult result, Model model) {
        if (!"".equals(bookTable.getBookDate()) && !"".equals(bookTable.getBookTime()) && !"".equals(bookTable.getNoOfPerson())
                && !"".equals(bookTable.getPersonName()) && !"".equals(bookTable.getpersonEmailId()) && !"".equals(bookTable.getPersonPhoneNumber())) {
            try {
                BookTable bookTable1 = reservationRepository.saveAndFlush(bookTable);
                reservationStatus = "Reservation Success"
                        +"\n"
                        +"\n"+ " Booking Id   :" + bookTable1.getBookingId()
                        +"\n"+" Name         :" + bookTable1.getPersonName()
                        +"\n"+" Phone Number :" + bookTable1.getPersonPhoneNumber();
                return "redirect:/bookTable?success";
            } catch (Exception e) {
                reservationStatus = e.getMessage();
            }
        } else {
            reservationStatus = "All Fields Are Mandatory";

        }
        return "redirect:/bookTable?error";

    }
}
