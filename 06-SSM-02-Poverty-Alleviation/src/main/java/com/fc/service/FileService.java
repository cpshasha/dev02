package com.fc.service;

import com.fc.vo.ResultVO;
import org.springframework.web.multipart.MultipartFile;

public interface FileService {
    ResultVO filedUpload(MultipartFile file, String type);
}
