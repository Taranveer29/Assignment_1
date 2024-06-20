// package com.example.controllers;


package com.example.controllers;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.models.Rectangle;
import com.example.models.RectangleRepository;

import jakarta.servlet.http.HttpServletResponse;

@Controller
public class RectanglesController {

    @Autowired
    private RectangleRepository rectRepo;

    @GetMapping("/rectangles/view")
    public String getAllRectangles(Model model) {
        System.out.println("Getting all Rectangles");
        List<Rectangle> rectangles = rectRepo.findAll();
        model.addAttribute("re", rectangles);
        return "rectangles/showAll";
    }

    @GetMapping("/rectangles/{name}")
    public String getRectangle(@PathVariable String name, Model model) {
        System.out.println("Getting rectangle by name");
        List<Rectangle> rectangles = rectRepo.findByName(name);
        model.addAttribute("re", rectangles);
        return "rectangles/showRectangle";
    }

    
    @PostMapping("/rectangles/add")
public String addRectangle(@RequestParam Map<String, String> newRect, Model model, HttpServletResponse response) {
    System.out.println("Add rectangle");

    // Validate name
    String newName = newRect.get("name");
    if (newName == null || newName.trim().isEmpty()) {
        model.addAttribute("error", "Name cannot be blank");
        return "rectangles/addRectangle"; // Return to the form with an error message
    }

    // Validate width
    String widthStr = newRect.get("width");
    int newWidth;
    try {
        newWidth = Integer.parseInt(widthStr);
        if (newWidth <= 0) {
            model.addAttribute("error", "Width must be a positive integer");
            return "rectangles/addRectangle"; // Return to the form with an error message
        }
    } catch (NumberFormatException e) {
        model.addAttribute("error", "Width must be a valid integer");
        return "rectangles/addRectangle"; // Return to the form with an error message
    }

    // Validate height
    String heightStr = newRect.get("height");
    int newHeight;
    try {
        newHeight = Integer.parseInt(heightStr);
        if (newHeight <= 0) {
            model.addAttribute("error", "Height must be a positive integer");
            return "rectangles/addRectangle"; // Return to the form with an error message
        }
    } catch (NumberFormatException e) {
        model.addAttribute("error", "Height must be a valid integer");
        return "rectangles/addRectangle"; // Return to the form with an error message
    }

    // Validate color
    String newColor = newRect.get("color");
    if (newColor == null || newColor.trim().isEmpty()) {
        model.addAttribute("error", "Color cannot be blank");
        return "rectangles/addRectangle"; // Return to the form with an error message
    }

    // If all validations pass, save the new rectangle
    rectRepo.save(new Rectangle(newName, newWidth, newHeight, newColor));
    response.setStatus(201);
    return "redirect:/rectangles/view";
}
   

  
    // @PostMapping("/rectangles/updateRectangle")
    // public String updateRectangle(@RequestParam int rid, @RequestParam String name, @RequestParam int width, @RequestParam int height, @RequestParam String color, Model model, HttpServletResponse response) {
    //     System.out.println("Updating rectangle");

    //     List<Rectangle> rectangles = rectRepo.findByRid(rid);
    //     if (!rectangles.isEmpty()) {
    //         Rectangle rectangle = rectangles.get(0);
    //         rectangle.setName(name);
    //         rectangle.setWidth(width);
    //         rectangle.setHeight(height);
    //         rectangle.setColor(color);
    //         rectRepo.save(rectangle);
    //     }

    //     // Fetch the updated list of rectangles after update
    //     List<Rectangle> allRectangles = rectRepo.findAll();
    //     model.addAttribute("re", allRectangles);
    //     response.setStatus(201);
    //     return "rectangles/showAll";  
    // }

    @PostMapping("/rectangles/updateRectangle")
public String updateRectangle(
    @RequestParam int rid,
    @RequestParam String name,
    @RequestParam String width,
    @RequestParam String height,
    @RequestParam String color,
    Model model,
    HttpServletResponse response
) {
    System.out.println("Updating rectangle");

    // Validate name
    if (name == null || name.trim().isEmpty()) {
        model.addAttribute("error", "Name cannot be blank");
        model.addAttribute("rid", rid);
        model.addAttribute("name", name);
        model.addAttribute("width", width);
        model.addAttribute("height", height);
        model.addAttribute("color", color);
        return "rectangles/editRectangle"; // Return to the form with an error message
    }

    // Validate width
    int parsedWidth;
    try {
        parsedWidth = Integer.parseInt(width);
        if (parsedWidth <= 0) {
            model.addAttribute("error", "Width must be a positive integer");
            model.addAttribute("rid", rid);
            model.addAttribute("name", name);
            model.addAttribute("width", width);
            model.addAttribute("height", height);
            model.addAttribute("color", color);
            return "rectangles/editRectangle"; // Return to the form with an error message
        }
    } catch (NumberFormatException e) {
        model.addAttribute("error", "Width must be a valid integer");
        model.addAttribute("rid", rid);
        model.addAttribute("name", name);
        model.addAttribute("width", width);
        model.addAttribute("height", height);
        model.addAttribute("color", color);
        return "rectangles/editRectangle"; // Return to the form with an error message
    }

    // Validate height
    int parsedHeight;
    try {
        parsedHeight = Integer.parseInt(height);
        if (parsedHeight <= 0) {
            model.addAttribute("error", "Height must be a positive integer");
            model.addAttribute("rid", rid);
            model.addAttribute("name", name);
            model.addAttribute("width", width);
            model.addAttribute("height", height);
            model.addAttribute("color", color);
            return "rectangles/editRectangle"; // Return to the form with an error message
        }
    } catch (NumberFormatException e) {
        model.addAttribute("error", "Height must be a valid integer");
        model.addAttribute("rid", rid);
        model.addAttribute("name", name);
        model.addAttribute("width", width);
        model.addAttribute("height", height);
        model.addAttribute("color", color);
        return "rectangles/editRectangle"; // Return to the form with an error message
    }

    // Validate color
    if (color == null || color.trim().isEmpty()) {
        model.addAttribute("error", "Color cannot be blank");
        model.addAttribute("rid", rid);
        model.addAttribute("name", name);
        model.addAttribute("width", width);
        model.addAttribute("height", height);
        model.addAttribute("color", color);
        return "rectangles/editRectangle"; // Return to the form with an error message
    }

    // If all validations pass, update the rectangle
    List<Rectangle> rectangles = rectRepo.findByRid(rid);
    if (!rectangles.isEmpty()) {
        Rectangle rectangle = rectangles.get(0);
        rectangle.setName(name);
        rectangle.setWidth(parsedWidth);
        rectangle.setHeight(parsedHeight);
        rectangle.setColor(color);
        rectRepo.save(rectangle);
    }

    // Fetch the updated list of rectangles after update
    List<Rectangle> allRectangles = rectRepo.findAll();
    model.addAttribute("re", allRectangles);
    response.setStatus(201);
    return "rectangles/showAll";  
}

    @PostMapping("/rectangles/delete")
    public String deleteRectangle(@RequestParam int rid) {
        System.out.println("Deleting rectangle");
        rectRepo.deleteById(rid);
        return "redirect:/rectangles/view";
    }

}
