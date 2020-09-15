package com.udacity.jwdnd.course1.cloudstorage.mapper;

import com.udacity.jwdnd.course1.cloudstorage.model.File;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface FileMapper {
    @Select("SELECT * FROM FILES WHERE fileId = #{fileId}")
    File getFileById(int fileId);

    @Select("SELECT * FROM FILES WHERE userId = #{userId}")
    List<File> getFileByUser(int userId);

    @Insert("INSERT INTO FILES(fileName, contentType, fileSize, userId, fileData) VALUES(#{fileName}, #{contentType}, #{fileSize}, #{userId}, #{fileData})")
    @Options(useGeneratedKeys = true, keyProperty = "fileId")
    int uploadFile(File file);

//    @Update("UPDATE FILES SET fileName = #{fileName}, contentType = #{contentType}, fileSize = #{fileSize}, userId = #{userId}, fileData = #{fileData} WHERE fileId = #{fileId}")
//    boolean updateFile(File file);

    @Delete("DELETE FROM FILES WHERE fileId = #{fileId}")
    boolean deleteFileById(int fileId);

    @Delete("DELETE FROM FILES WHERE userId = #{userId}")
    boolean deleteFileByUser(int userId);
}
