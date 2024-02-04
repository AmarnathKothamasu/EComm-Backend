package com.springjwt.controllers;

import java.io.File;
import java.io.IOException;

import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.springjwt.dto.ProductDTO;

@RestController
@RequestMapping("/api/files")
public class FileUploadController {

	final String UPLOAD_DIR = "D:/Amarnath/Projects/New folder/";

	@RequestMapping(path = "/upload", method = RequestMethod.POST, consumes = {MediaType.MULTIPART_FORM_DATA_VALUE,"application/json"})
	public ResponseEntity<String> handleFileUpload(@RequestParam("file") MultipartFile file,
			@RequestParam("file") MultipartFile file2 ,@RequestBody ProductDTO productDto) {
		if (file.isEmpty()) {
			return ResponseEntity.badRequest().body("Please select a file to upload.");
		}

		try {
			// Ensure the upload directory exists
			File uploadDir = new File(UPLOAD_DIR);
			if (!uploadDir.exists()) {
				uploadDir.mkdirs();
			}
			
			// Save the file to the server
			String fileName = file.getOriginalFilename();
			File destFile = new File(uploadDir.getAbsolutePath() + File.separator + fileName);
			file.transferTo(destFile);

			// Provide a download link or other response as needed
			String fileDownloadUri = ServletUriComponentsBuilder.fromCurrentContextPath().path("/api/files/download/")
					.path(fileName).toUriString();

			return ResponseEntity.ok("File uploaded successfully. Download link: " + fileDownloadUri);
		} catch (IOException e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to upload the file.");
		}
	}

	@GetMapping("/download/{filename}")
	public ResponseEntity<?> downloadFile(@PathVariable String filename) throws IOException {
		// Implement file download logic
		// Here, you might load the file from storage and return it as a
		// ResponseEntity<Resource>

		// For demonstration purposes, creating a sample text file
		String content = "This is a sample file content.";
		ByteArrayResource resource = new ByteArrayResource(content.getBytes());

		return ResponseEntity.ok().header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=" + filename)
				.contentType(MediaType.TEXT_PLAIN).body(resource);
	}
	
	  @RequestMapping(value="/uploadfile", method=RequestMethod.POST)
	    public void uploadFile(@RequestParam Long id,
	                       @RequestPart MultipartFile file) {
	        //do some stuff
	    }
}
