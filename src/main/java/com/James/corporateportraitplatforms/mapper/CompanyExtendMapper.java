package com.James.corporateportraitplatforms.mapper;

import com.James.corporateportraitplatforms.model.Company;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.mapping.FetchType;
import org.apache.ibatis.type.JdbcType;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface CompanyExtendMapper {

    @Select({
            "select",
            "id, register_time, register_money, industry, city, company_type, controller_type, ",
            "controller_proportion, flag",
            "from company",
            "where id = #{id,jdbcType=VARCHAR}"
    })
    @Results({
            @Result(column = "id", property = "id", jdbcType = JdbcType.VARCHAR, id = true),
            @Result(column = "register_time", property = "registerTime", jdbcType = JdbcType.VARCHAR),
            @Result(column = "register_money", property = "registerMoney", jdbcType = JdbcType.VARCHAR),
            @Result(column = "industry", property = "industry", jdbcType = JdbcType.VARCHAR),
            @Result(column = "city", property = "city", jdbcType = JdbcType.VARCHAR),
            @Result(column = "company_type", property = "companyType", jdbcType = JdbcType.VARCHAR),
            @Result(column = "controller_type", property = "controllerType", jdbcType = JdbcType.VARCHAR),
            @Result(column = "controller_proportion", property = "controllerProportion", jdbcType = JdbcType.VARCHAR),
            @Result(column = "flag", property = "flag", jdbcType = JdbcType.VARCHAR),

            @Result(column = "id", property = "knowledgeReport",
                    one = @One(select = "com.James.corporateportraitplatforms.mapper.KnowledgeReportMapper.selectByCid", fetchType = FetchType.EAGER)),
            @Result(column = "id", property = "moneyReport",
                    one = @One(select = "com.James.corporateportraitplatforms.mapper.MoneyReportMapper.selectByCid", fetchType = FetchType.EAGER)),
            @Result(column = "id", property = "yearReport",
                    one = @One(select = "com.James.corporateportraitplatforms.mapper.YearReportMapper.selectByCid", fetchType = FetchType.EAGER)),
            @Result(column = "id", property = "tags",
                    many = @Many(select = "com.James.corporateportraitplatforms.mapper.TagExtendMapper.findByCid", fetchType = FetchType.LAZY))
    })
    Company selectByPrimaryKeyComplete(String id);

    List<Company> selectAllAndCondition(List<String> city, List<String> industry, List<String> companyType, List<String> controllerType, String companyFlagType);


    List<Company> selectByCity(String city, List<String> industry, List<String> companyType, List<String> controllerType, String companyFlagType);

    List<Company> selectByIndustry(String industry, List<String> city, List<String> companyType, List<String> controllerType, String companyFlagType);

    List<Company> selectByCompanyType(String companyType, List<String> city, List<String> industry, List<String> controllerType, String companyFlagType);

    List<Company> selectByControllerType(String controllerType, List<String> city, List<String> industry, List<String> companyType, String companyFlagType);


    //    @Select("SELECT industry as name, COUNT(id) as count FROM company GROUP BY industry")
    @Select("SELECT industry FROM company GROUP BY industry")
    List<String> selectCompanyIndustry();

    @Select("SELECT DISTINCT city FROM company")
    List<String> selectCompanyCity();

    @Select("SELECT DISTINCT company_type FROM company")
    List<String> selectCompanyType();

    @Select("SELECT DISTINCT controller_type FROM company")
    List<String> selectControllerType();

    /**
     * 获取指定省份的僵尸或非僵尸企业数量
     *
     * @return
     */
    @Select({
            "select count(id) from company where city = #{city,jdbcType=VARCHAR} and flag = #{flag,jdbcType=VARCHAR}"
    })
    int getCountByProvinceAndFlag(@Param("city") String city, @Param("flag") String flag);

    ///**
    // * 获取指定企业规模的僵尸或非僵尸企业
    // * @param flag 僵尸企业标志
    // * @param cidList 企业 id 列表
    // * @return
    // */
    //List<Company> selectByScaleAndCidList(String flag, List<String> cidList);
    //
    ///**
    // * 获取指定行业的僵尸或非僵尸企业
    // * @param flag 僵尸企业标志
    // * @param cidList 企业 id 列表
    // * @return
    // */
    //List<Company> selectByIndustryAndCidList(String flag, List<String> cidList);

    /**
     * 获取指定企业 id 列表里的僵尸或非僵尸企业
     * @param flag 僵尸企业标志
     * @param cidList 企业 id 列表
     * @return
     */
    List<Company> selectByFlagAndCidList(String flag, List<String> cidList);

    /**
     * 获取指定企业标志企业
     * @param flag 僵尸企业标志
     * @return
     */
    @Select({
            "select",
            "id, register_time, register_money, industry, city, company_type, controller_type, ",
            "controller_proportion, flag",
            "from company",
            "where flag = #{flag,jdbcType=VARCHAR}"
    })
    @Results({
            @Result(column="id", property="id", jdbcType=JdbcType.VARCHAR, id=true),
            @Result(column="register_time", property="registerTime", jdbcType=JdbcType.VARCHAR),
            @Result(column="register_money", property="registerMoney", jdbcType=JdbcType.VARCHAR),
            @Result(column="industry", property="industry", jdbcType=JdbcType.VARCHAR),
            @Result(column="city", property="city", jdbcType=JdbcType.VARCHAR),
            @Result(column="company_type", property="companyType", jdbcType=JdbcType.VARCHAR),
            @Result(column="controller_type", property="controllerType", jdbcType=JdbcType.VARCHAR),
            @Result(column="controller_proportion", property="controllerProportion", jdbcType=JdbcType.VARCHAR),
            @Result(column="flag", property="flag", jdbcType=JdbcType.VARCHAR)
    })
    List<Company> selectByFlag(@Param("flag") String flag);

    /**
     * 获取僵尸或非僵尸企业数量
     *
     * @return
     */
    @Select({
            "select count(id) from company where flag = #{flag,jdbcType=VARCHAR}"
    })
    int selectCountByFlag(@Param("flag") String flag);

    /**
     * 获取指定企业 id 列表里的僵尸或非僵尸企业数量
     * @param flag 僵尸企业标志
     * @param cidList 企业 id 列表
     * @return
     */
    int selectCountByFlagAndCidList(String flag, List<String> cidList);
}
