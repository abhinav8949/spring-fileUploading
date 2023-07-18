package com.example;

import com.example.service.StorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@SpringBootApplication
@RestController
@RequestMapping(value = "/image")
public class FileUploadingApplication {

	@Autowired
	private StorageService storageService;

	@PostMapping("/filesystem")
	public ResponseEntity<?> uploadImageToFileSystem(@RequestParam("images")MultipartFile file) throws IOException{
		String uploadImage=storageService.uploadImageToFileSystem(file);

		return ResponseEntity.status(HttpStatus.OK).body(uploadImage);
	}

	@GetMapping("/filesystem/{filename}")
	public ResponseEntity<?> downloadImageFromFileSystem(@PathVariable("filename") String filename) throws IOException {
		byte[] imagedata=storageService.downloadImageFromFileSystem(filename);

		return ResponseEntity.status(HttpStatus.OK)
				.contentType(MediaType.IMAGE_JPEG)
				.body(imagedata);
//.contentType(MediaType.valueOf("image/png"))
	}


	public static void main(String[] args) {
		SpringApplication.run(FileUploadingApplication.class, args);
	}

}
