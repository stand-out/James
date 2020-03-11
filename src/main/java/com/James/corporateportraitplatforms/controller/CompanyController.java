package com.James.corporateportraitplatforms.controller;

import com.James.corporateportraitplatforms.model.AjaxResponseModel;
import com.James.corporateportraitplatforms.model.Company;
import com.James.corporateportraitplatforms.model.Tag;
import com.James.corporateportraitplatforms.service.CompanyService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;
import java.util.List;

@RestController
@RequestMapping("/company")
@Slf4j
public class CompanyController {

    @Autowired
    private CompanyService companyService;

    @GetMapping("/get-company-all")
    public AjaxResponseModel<PageInfo<Company>> getCompanyList(@RequestParam(defaultValue = "1") int pageNum,
                                                               @RequestParam(defaultValue = "10") int pageSize,
                                                               @RequestParam(name = "selectCity",defaultValue = "null") List<String> cityList,
                                                               @RequestParam(name = "selectIndustry",defaultValue = "null") List<String> industry,
                                                               @RequestParam(name = "selectCompanyType",defaultValue = "null")List<String> companyType,
                                                               @RequestParam(name = "selectControllerType",defaultValue = "null")List<String> controllerType) {
        PageHelper.startPage(pageNum, pageSize);
        return AjaxResponseModel.<PageInfo<Company>>builder()
                .code(0)
                .data(new PageInfo<>(companyService.findAll(cityList, industry, companyType, controllerType)))
                .build();
    }

    @GetMapping("/get-company-id")
    public AjaxResponseModel<PageInfo<Company>> getCompanyById(@RequestParam(defaultValue = "1") int pageNum,
                                                               @RequestParam(defaultValue = "10")int pageSize,
                                                               String id) {
        return AjaxResponseModel.<PageInfo<Company>>builder()
                .code(0)
                .data(new PageInfo<>(Collections.singletonList(companyService.findById(id))))
                .build();
    }

    @GetMapping("/get-company-city")
    public AjaxResponseModel<PageInfo<Company>> getCompanyByCity(@RequestParam(defaultValue = "1") int pageNum,
                                                                 @RequestParam(defaultValue = "10") int pageSize,
                                                                 String city,
                                                                 @RequestParam(name = "selectIndustry",defaultValue = "null") List<String> industry,
                                                                 @RequestParam(name = "selectCompanyType",defaultValue = "null")List<String> companyType,
                                                                 @RequestParam(name = "selectControllerType",defaultValue = "null")List<String> controllerType) {
        log.info("getCompanyByCity {}", city);
        PageHelper.startPage(pageNum, pageSize);
        return AjaxResponseModel.<PageInfo<Company>>builder()
                .code(0)
                .data(new PageInfo<>(companyService.findByCity(city, industry, companyType, controllerType)))
                .build();
    }

    @GetMapping("/get-company-industry")
    public AjaxResponseModel<PageInfo<Company>> getCompanyByIndustry(@RequestParam(defaultValue = "1") int pageNum,
                                                                     @RequestParam(defaultValue = "10") int pageSize,
                                                                     String industry,
                                                                     @RequestParam(name = "selectCity",defaultValue = "null") List<String> cityList,
                                                                     @RequestParam(name = "selectCompanyType",defaultValue = "null")List<String> companyType,
                                                                     @RequestParam(name = "selectControllerType",defaultValue = "null")List<String> controllerType) {
        PageHelper.startPage(pageNum, pageSize);
        return AjaxResponseModel.<PageInfo<Company>>builder()
                .code(0)
                .data(new PageInfo<>(companyService.findByIndustry(industry, cityList, companyType, controllerType)))
                .build();
    }

    @GetMapping("/get-company-companyType")
    public AjaxResponseModel<PageInfo<Company>> getCompanyByCompanyType(@RequestParam(defaultValue = "1") int pageNum,
                                                                        @RequestParam(defaultValue = "10") int pageSize,
                                                                        String companyType,
                                                                        @RequestParam(name = "selectCity",defaultValue = "null") List<String> cityList,
                                                                        @RequestParam(name = "selectIndustry",defaultValue = "null") List<String> industry,
                                                                        @RequestParam(name = "selectControllerType",defaultValue = "null")List<String> controllerType) {
        PageHelper.startPage(pageNum, pageSize);
        return AjaxResponseModel.<PageInfo<Company>>builder()
                .code(0)
                .data(new PageInfo<>(companyService.findByCompanyType(companyType, cityList, industry, controllerType)))
                .build();
    }

    @GetMapping("/get-company-controllerType")
    public AjaxResponseModel<PageInfo<Company>> getCompanyByControllerType(@RequestParam(defaultValue = "1") int pageNum,
                                                                           @RequestParam(defaultValue = "10") int pageSize,
                                                                           String controllerType,
                                                                           @RequestParam(name = "selectCity",defaultValue = "null") List<String> cityList,
                                                                           @RequestParam(name = "selectIndustry",defaultValue = "null") List<String> industry,
                                                                           @RequestParam(name = "selectCompanyType",defaultValue = "null")List<String> companyType) {
        PageHelper.startPage(pageNum, pageSize);
        return AjaxResponseModel.<PageInfo<Company>>builder()
                .code(0)
                .data(new PageInfo<>(companyService.findByControllerType(controllerType, cityList, industry, companyType)))
                .build();
    }

    @GetMapping("/get-tag")
    public AjaxResponseModel<List<Tag>> getCompanyTag(String id) {
        return AjaxResponseModel.<List<Tag>>builder()
                .code(0)
                .data(companyService.findCompanyTagById(id))
                .build();
    }

    @GetMapping("/get-city")
    public AjaxResponseModel<List<String>> getCity() {
        return AjaxResponseModel.<List<String>>builder()
                .code(0).data(companyService.findCompanyCity())
                .build();
    }

    @GetMapping("/get-industry")
    public AjaxResponseModel<List<String>> getIndustry() {
        return AjaxResponseModel.<List<String>>builder()
                .code(0)
                .data(companyService.findCompanyIndustry())
                .build();
    }

    @GetMapping("/get-CompanyType")
    public AjaxResponseModel<List<String>> getCompanyType() {
        return AjaxResponseModel.<List<String>>builder()
                .code(0)
                .data(companyService.findCompanyType())
                .build();
    }

    @GetMapping("/get-controllerType")
    public AjaxResponseModel<List<String>> getCompanyControllerType() {
        return AjaxResponseModel.<List<String>>builder()
                .code(0)
                .data(companyService.findCompanyControllerType())
                .build();
    }

}
