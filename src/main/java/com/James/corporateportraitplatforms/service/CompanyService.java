package com.James.corporateportraitplatforms.service;

import com.James.corporateportraitplatforms.mapper.CompanyExtendMapper;
import com.James.corporateportraitplatforms.mapper.CompanyMapper;
import com.James.corporateportraitplatforms.mapper.TagExtendMapper;
import com.James.corporateportraitplatforms.model.Company;
import com.James.corporateportraitplatforms.model.Tag;
import com.github.pagehelper.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CompanyService {

    @Autowired
    private CompanyMapper companyMapper;
    @Autowired
    private CompanyExtendMapper companyExtendMapper;
    @Autowired
    private TagExtendMapper tagExtendMapper;


    public List<Company> findAll(List<String> city, List<String> industry, List<String> companyType, List<String> controllerType, String companyFlagType) {
        return companyExtendMapper.selectAllAndCondition(city, industry, companyType, controllerType, companyFlagType);
    }

    public Company findById(String id) {
        return companyMapper.selectByPrimaryKey(id);
    }

    public List<Company> findByCity(String city, List<String> industry, List<String> companyType, List<String> controllerType, String companyFlagType) {
        return companyExtendMapper.selectByCity("%" + city + "%",industry, companyType, controllerType, companyFlagType);
    }

    public List<Company> findByIndustry(String industry, List<String> city, List<String> companyType, List<String> controllerType, String companyFlagType) {
        return companyExtendMapper.selectByIndustry("%" + industry + "%", city, companyType, controllerType, companyFlagType);
    }

    public List<Company> findByCompanyType(String companyType, List<String> city, List<String> industry, List<String> controllerType, String companyFlagType) {
        return companyExtendMapper.selectByCompanyType("%" + companyType + "%", city, industry, controllerType, companyFlagType);
    }

    public List<Company> findByControllerType(String controllerType, List<String> city, List<String> industry, List<String> companyType, String companyFlagType) {
        return companyExtendMapper.selectByControllerType("%" + controllerType + "%", city, industry, companyType, companyFlagType);
    }

    public List<Tag> findCompanyTagById(String id) {
        return tagExtendMapper.findByCid(id);
    }

    public List<String> findCompanyCity() {
        return companyExtendMapper.selectCompanyCity();
    }

    public List<String> findCompanyIndustry() {
        return companyExtendMapper.selectCompanyIndustry();
    }

    public List<String> findCompanyType() {
        return companyExtendMapper.selectCompanyType();
    }

    public List<String> findCompanyControllerType() {
        return companyExtendMapper.selectControllerType();
    }
}
