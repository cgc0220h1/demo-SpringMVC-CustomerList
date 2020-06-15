package controllers;

import model.Province;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;
import service.IService;
import service.exception.DuplicateException;

import java.util.List;

@Controller
@RequestMapping("/provinces")
public class ProvinceController {
    private final MessageSource messageSource;

    private final IService<Province> provinceService;

    @Autowired
    public ProvinceController(MessageSource messageSource, IService<Province> provinceService) {
        this.messageSource = messageSource;
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
        modelAndView.addObject("buttonLabel",
                messageSource.getMessage("province.form.button.new", null, LocaleContextHolder.getLocale()));
        return modelAndView;
    }

    @PostMapping("/create")
    public ModelAndView createProvince(@ModelAttribute("province") Province province) throws DuplicateException {
        provinceService.save(province);
        return showProvinceList();
    }

    @GetMapping("/edit/{id}")
    public ModelAndView showProvinceDetail(@PathVariable("id") Long id) throws Exception {
        ModelAndView modelAndView = new ModelAndView("province/form");
        Province province = provinceService.findById(id);
        modelAndView.addObject("province", province);
        modelAndView.addObject("buttonLabel",
                messageSource.getMessage("province.form.button.update", null, LocaleContextHolder.getLocale()));
        return modelAndView;
    }

    @PostMapping("/edit/{id}")
    public ModelAndView updateProvince(@ModelAttribute("province") Province province) throws DuplicateException {
        provinceService.save(province);
        return showProvinceList();
    }

    @ExceptionHandler(Exception.class)
    public RedirectView redirectNotFound() {
        return new RedirectView("error/not-found.html");
    }

    @ExceptionHandler(DuplicateException.class)
    public RedirectView redirectDuplicate() {
        return new RedirectView("error/duplicate.html");
    }
}
