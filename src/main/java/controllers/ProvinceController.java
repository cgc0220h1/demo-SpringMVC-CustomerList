package controllers;

import model.Province;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import service.IService;

import java.util.List;

@Controller
@RequestMapping("/provinces")
public class ProvinceController {
    private final IService<Province> provinceService;

    @Autowired
    public ProvinceController(IService<Province> provinceService) {
        this.provinceService = provinceService;
    }

    @GetMapping
    public ModelAndView showProvinceList() {
        ModelAndView modelAndView = new ModelAndView("province/list");
        List<Province> provinceList = provinceService.findAll();
        modelAndView.addObject("provinceList", provinceList);
        return modelAndView;
    }

    @GetMapping("/create")
    public ModelAndView showCreateProvinceForm() {
        ModelAndView modelAndView = new ModelAndView("province/form");
        modelAndView.addObject("province", new Province());
        modelAndView.addObject("buttonLabel", "Tạo mới");
        return modelAndView;
    }

    @PostMapping("/create")
    public ModelAndView createProvince(@ModelAttribute("province") Province province) {
        provinceService.save(province);
        return showProvinceList();
    }

    @GetMapping("/edit/{id}")
    public ModelAndView showProvinceDetail(@PathVariable("id") Long id) {
        ModelAndView modelAndView = new ModelAndView("province/form");
        Province province = provinceService.findById(id);
        if (province != null) {
            modelAndView.addObject("province", province);
            modelAndView.addObject("buttonLabel", "Cập nhật");
        } else {
            modelAndView = new ModelAndView("error");
        }
        return modelAndView;
    }

    @PostMapping("/edit/{id}")
    public ModelAndView updateProvince(@ModelAttribute("province") Province province) {
        provinceService.save(province);
        return showProvinceList();
    }
}
