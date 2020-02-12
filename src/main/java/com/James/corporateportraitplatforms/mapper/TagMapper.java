package com.James.corporateportraitplatforms.mapper;

import com.James.corporateportraitplatforms.model.Tag;
import java.util.List;

import org.apache.ibatis.annotations.*;
import org.apache.ibatis.type.JdbcType;


@Mapper
public interface TagMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tag
     *
     * @mbg.generated Wed Feb 12 12:51:31 CST 2020
     */
    @Delete({
        "delete from tag",
        "where tid = #{tid,jdbcType=INTEGER}"
    })
    int deleteByPrimaryKey(Integer tid);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tag
     *
     * @mbg.generated Wed Feb 12 12:51:31 CST 2020
     */
    @Insert({
        "insert into tag (tag)",
        "values (#{tag,jdbcType=VARCHAR})"
    })
    @SelectKey(statement="CALL IDENTITY()", keyProperty="tid", before=false, resultType=Integer.class)
    int insert(Tag record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tag
     *
     * @mbg.generated Wed Feb 12 12:51:31 CST 2020
     */
    @Select({
        "select",
        "tid, tag",
        "from tag",
        "where tid = #{tid,jdbcType=INTEGER}"
    })
    @Results({
        @Result(column="tid", property="tid", jdbcType=JdbcType.INTEGER, id=true),
        @Result(column="tag", property="tag", jdbcType=JdbcType.VARCHAR)
    })
    Tag selectByPrimaryKey(Integer tid);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tag
     *
     * @mbg.generated Wed Feb 12 12:51:31 CST 2020
     */
    @Select({
        "select",
        "tid, tag",
        "from tag"
    })
    @Results({
        @Result(column="tid", property="tid", jdbcType=JdbcType.INTEGER, id=true),
        @Result(column="tag", property="tag", jdbcType=JdbcType.VARCHAR)
    })
    List<Tag> selectAll();

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tag
     *
     * @mbg.generated Wed Feb 12 12:51:31 CST 2020
     */
    @Update({
        "update tag",
        "set tag = #{tag,jdbcType=VARCHAR}",
        "where tid = #{tid,jdbcType=INTEGER}"
    })
    int updateByPrimaryKey(Tag record);
}