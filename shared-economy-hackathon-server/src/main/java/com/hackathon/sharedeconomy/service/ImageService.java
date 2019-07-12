package com.hackathon.sharedeconomy.service;

import com.hackathon.sharedeconomy.exception.UserDefineException;
import com.hackathon.sharedeconomy.model.entity.Image;
import com.hackathon.sharedeconomy.repository.ImageRepository;
import org.apache.commons.io.FileUtils;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.util.Base64;
import java.util.List;

/**
 * Created by YoungMan on 2019-02-14.
 */

@Service
public class ImageService {

    private final String WINDOW_PATH = "C:\\testimg\\";
    private final String LINUX_PATH = "/usr/local/tomcat-8.0.53/webapps/imgfile/";
    private ImageRepository imageRepository;

    public ImageService(ImageRepository imageRepository) {
        this.imageRepository = imageRepository;
    }

    /*
     * 안드로이드 -> 서버
     * return : 저장 경로
     */
    public String convertBase64ToImgFile(String strBase64, String writeFileName) {
        byte[] decodedBytes = Base64.getDecoder().decode(strBase64);
        String writeFilePath = LINUX_PATH + writeFileName + ".png";

        try {
            FileUtils.writeByteArrayToFile(new File(writeFilePath), decodedBytes);
        } catch (IOException e) {
            throw new UserDefineException("Base64에서 이미지 파일로 변환하는 과정에서 오류", e.toString());
        }

        return writeFilePath;
    }

    /*
     * 서버 -> 안드로이드
     * return : base64
     */
    public String convertImgFileToBase64(String readFilePath) {
//      String readFilePath = WINDOW_PATH + readFileName;
        byte[] fileContent = new byte[0];

        try {
            fileContent = FileUtils.readFileToByteArray(new File(readFilePath));
        } catch (IOException e) {
            throw new UserDefineException("이미지 파일에서 Base64로 변환하는 과정에서 오류", e.toString());
        }
        return Base64.getEncoder().encodeToString(fileContent);
    }

    public void saveAll(List<Image> images) {
        imageRepository.saveAll(images);
    }


}
