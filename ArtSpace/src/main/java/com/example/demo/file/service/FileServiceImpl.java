package com.example.demo.file.service;

import java.io.IOException;
import java.io.InputStream;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.ResourceUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.example.demo.company.dto.CompanyFileDTO;
import com.example.demo.file.dao.FileDAO;
import com.example.demo.file.dao.HallFileDAO;
import com.example.demo.file.dto.FileDTO;
import com.example.demo.file.dto.GCSRequest;
import com.example.demo.hall.dto.HallImageDTO;
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
	
	@Autowired
	private HallFileDAO hallFileDAO;

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
	private int saveToDB(MultipartFile file, String storedFileName) {
		FileDTO fileDTO = new FileDTO();
		fileDTO.setPath(getFileUrl(storedFileName)); // GCS의 파일 URL
		fileDTO.setOrg_file_name(file.getOriginalFilename());
		fileDTO.setStored_file_name(storedFileName);
		fileDAO.insertFile(fileDTO);
		// 저장한 파일ID 리턴
		return fileDTO.getFile_id();
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
	@Override
	public void uploadObject(MultipartFile[] files, Integer company_id) {
		try {
			for (MultipartFile file : files) {
				String storedFileName = generateFileName(file);
				uploadToGCS(file, storedFileName);
				Integer file_id = saveToDB(file, storedFileName);
				
				// company_file 테이블에 정보 넣기
				CompanyFileDTO cFile = new CompanyFileDTO();
				cFile.setFile_id(file_id);
				cFile.setCompany_id(company_id);
				cFile.setFile_name(file.getOriginalFilename());
				fileDAO.insertCFile(cFile);
				
			}
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	
	
	// Hall_공연장 이미지들 등록
	@Override
	public void uploadHallImage(MultipartFile[] files, Integer hall_id) {
		try {
			for (MultipartFile file : files) {
				if(!file.isEmpty()) {
					// 저장될 고유 이름 생성
	     			String storedFileName = generateFileName(file);
	     			uploadToGCS(file, storedFileName);
	    			Integer file_id = saveToDB(file, storedFileName);
	    			
	    			HallImageDTO hallImage = new HallImageDTO();
	    			hallImage.setFile_id(file_id);
	    			hallImage.setHall_id(hall_id);	    			
	    			hallFileDAO.insertHallImage(hallImage);
				}
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
}