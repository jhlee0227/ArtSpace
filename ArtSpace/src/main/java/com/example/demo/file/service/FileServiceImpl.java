package com.example.demo.file.service;

import java.io.IOException;
import java.io.InputStream;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.ResourceUtils;
import org.springframework.web.multipart.MultipartFile;

import com.example.demo.file.dao.FileDAO;
import com.example.demo.file.dto.FileDTO;
import com.example.demo.file.dto.GCSRequest;
import com.google.auth.oauth2.GoogleCredentials;
import com.google.cloud.storage.BlobId;
import com.google.cloud.storage.BlobInfo;
import com.google.cloud.storage.Storage;
import com.google.cloud.storage.StorageOptions;

@Service
public class FileServiceImpl implements FileService {

	@Value("${spring.cloud.gcp.storage.bucket}")
	private String bucketName; // GCS 버킷 이름

	@Autowired
	private FileDAO fileDAO;

//	public void uploadFile(MultipartFile file) {
//	    try {
//	        String storedFileName = generateFileName(file);
//	        uploadToGCS(file, storedFileName);
//	    } catch (IOException e) {
//	        e.printStackTrace();
//	    }
//	}

	// GCS에 파일 저장
	private void uploadToGCS(MultipartFile file, String storedFileName) throws IOException {
		String keyFileName = "quiet-chalice-419309-a18ccc6da276.json";
		InputStream keyFile = ResourceUtils.getURL("classpath:" + keyFileName).openStream();

		Storage storage = StorageOptions.newBuilder()
				.setCredentials(GoogleCredentials.fromStream(keyFile))
				.build()
				.getService();
		
		BlobId blobId = BlobId.of(bucketName, storedFileName);
		BlobInfo blobInfo = BlobInfo.newBuilder(blobId).setContentType(file.getContentType()).build();
		
		byte[] bytes = file.getBytes();
		storage.create(blobInfo, bytes);
	}

	// DB에 정보 저장
	private void saveToDB(MultipartFile file, String storedFileName) {
		FileDTO fileDTO = new FileDTO();
		fileDTO.setPath(getFileUrl(storedFileName)); // GCS의 파일 URL
		fileDTO.setOrg_file_name(file.getOriginalFilename());
		fileDTO.setStored_file_name(storedFileName);
		fileDAO.insertFile(fileDTO);
	}

	// 저장된 파일의 URL을 반환
	private String getFileUrl(String storedFileName) {
		return "https://storage.googleapis.com/" + bucketName + "/" + storedFileName;
	}

	// 저장될 파일 이름을 생성
	private String generateFileName(MultipartFile file) {
		return UUID.randomUUID().toString() + "_" + file.getOriginalFilename();
	}

	// GCS에 업로드, DB에 저장
	public void uploadObject(GCSRequest gcsRequest) {
		try {
			MultipartFile file = gcsRequest.getFile();
			String storedFileName = generateFileName(file);
			uploadToGCS(file, storedFileName);
			saveToDB(file, storedFileName);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}